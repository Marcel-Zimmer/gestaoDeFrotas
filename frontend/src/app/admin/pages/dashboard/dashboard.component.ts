import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { forkJoin } from 'rxjs';

// Models e Services
import { AgendamentoService } from '../../services/agendamento/agendamento.service';
import { Trip } from '../../../models/trip/trip.model';
import { ApiResponseDriver, ApiResponseTrip } from '../../../models/api/backend/api.response.model';
import { Driver } from '../../../models/driver/driver.model';

// Angular Material
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

// Componente do Dialog
import { AgendamentoComponentComponent } from '../../components/agendamento-component/agendamento-component.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
  private agendamentoService = inject(AgendamentoService);
  private fb = inject(FormBuilder);
  public dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  public dataSource = new MatTableDataSource<Trip>();
  // Array para guardar a lista original de agendamentos, sem filtros
  private originalAgendamentos: Trip[] = [];
  
  public displayedColumns: string[] = ['data', 'motorista', 'veiculo', 'status', 'acoes'];
  
  // Variáveis para popular os dropdowns de filtro
  public motoristas: Driver[] = [];
  
  public statusOpcoes = [
    { value: 'AGENDADO', viewValue: 'Agendado' },
    { value: 'EM_VIAGEM', viewValue: 'Em viagem' },
    { value: 'FINALIZADO', viewValue: 'Finalizado' },
    { value: 'CANCELADO', viewValue: 'Cancelado' }
  ];

  // Formulário para controlar os filtros
  public filterForm: FormGroup;

  constructor() {
    this.filterForm = this.fb.group({
      startDate: [null],
      endDate: [null],
      driverId: [null],
      status: [null]
    });
  }

  ngOnInit(): void {
    this.loadInitialData();
  }

  loadInitialData(): void {
    const sources = {
      agendamentos: this.agendamentoService.getAgendamentos(),
      drivers: this.agendamentoService.getDrivers()
    };

    forkJoin(sources).subscribe({
      next: (responses) => {
        // Guarda a lista original e também popula a tabela pela primeira vez
        this.originalAgendamentos = responses.agendamentos.data;
        this.dataSource.data = this.originalAgendamentos;
        this.motoristas = responses.drivers.data;
      },
      error: (err) => this.mostrarMensagemDeErro('Erro ao carregar dados iniciais.')
    });
  }

  // Método para aplicar os filtros do formulário no array já carregado
  aplicarFiltros(): void {
    const filtros = this.filterForm.value;
    let dadosFiltrados = [...this.originalAgendamentos]; // Começa com uma cópia da lista original

    if (filtros.status) {
      dadosFiltrados = dadosFiltrados.filter(trip => trip.statusTrip === filtros.status);
    }

    // Filtra por Motorista, se um motorista for selecionado
    if (filtros.driverId) {
      dadosFiltrados = dadosFiltrados.filter(trip => trip?.idDriver === filtros.driverId);
    }

    // Filtra por Período de Datas
    const startDate = filtros.startDate ? new Date(filtros.startDate) : null;
    const endDate = filtros.endDate ? new Date(filtros.endDate) : null;

    if (startDate) {
        startDate.setHours(0, 0, 0, 0); // Zera a hora para comparar apenas o dia
        // CORREÇÃO: Converte explicitamente para string para garantir o tipo correto
        dadosFiltrados = dadosFiltrados.filter(trip => new Date(String(trip.date)) >= startDate);
    }
    if (endDate) {
        endDate.setHours(23, 59, 59, 999); // Define o fim do dia para incluir o dia todo
        // CORREÇÃO: Converte explicitamente para string para garantir o tipo correto
        dadosFiltrados = dadosFiltrados.filter(trip => new Date(String(trip.date)) <= endDate);
    }

    // Atualiza a tabela com os dados já filtrados
    this.dataSource.data = dadosFiltrados;
  }

  // Método para limpar os filtros e recarregar a lista original
  limparFiltros(): void {
    this.filterForm.reset();
    this.dataSource.data = this.originalAgendamentos;
  }

  dialogoAgendamento(agendamento?: Trip): void {
    const dialogRef = this.dialog.open(AgendamentoComponentComponent, {
      width: '600px',
      disableClose: true, 
      data: agendamento 
    });

    // Escuta o evento de fechamento do dialog
    dialogRef.afterClosed().subscribe(result => {
      
      if (result) {
        this.agendamentoService.updateAgendamento(result).subscribe({
          next: () => {
            this.loadInitialData(); 
            this.mostrarMensagemDeSucesso("Agendamento salvo com sucesso");
          },
          error: (erro:any) => {
            this.mostrarMensagemDeErro("Erro ao salvar : " + erro.error.message);
          }
        });
      }
    });
  }

  excluirAgendamento(id: number): void {
    if (confirm('Tem certeza que deseja inativar este veículo?')) {
      this.agendamentoService.excluir(id).subscribe({
        next: () => {
          this.loadInitialData();
          this.mostrarMensagemDeSucesso("Agendamento excluido com sucesso");
        },
        error: (erro :any) => {
          this.mostrarMensagemDeErro(erro.message);
        }
      });
    }
  }
  mostrarMensagemDeSucesso(mensagem: string): void {
    this.snackBar.open(mensagem, 'Fechar', {
      duration: 3000, // A mensagem some após 3 segundos
      horizontalPosition: 'center', // Posição horizontal (pode ser 'start', 'center', 'end', 'left', 'right')
      verticalPosition: 'bottom', // Posição vertical (pode ser 'top' ou 'bottom')
    });
  }

  mostrarMensagemDeErro(mensagem: string): void {
    this.snackBar.open(mensagem, 'OK', {
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      panelClass: ['snackbar-error'] // (Opcional) Classe CSS para estilizar o erro
    });
  }  
}