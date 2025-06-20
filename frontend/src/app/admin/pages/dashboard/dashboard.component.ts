// Imports que você já tinha
import { AgendamentoService } from '../../services/agendamento/agendamento.service';
import { Component, OnInit, inject } from '@angular/core';

// Imports NECESSÁRIOS para o template
import { CommonModule } from '@angular/common'; // Para pipes como 'date' e diretivas como *ngIf
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button'; // Para os botões <button mat-icon-button>
import { MatIconModule } from '@angular/material/icon';     // Para os ícones <mat-icon>
import {Trip} from '../../../models/trip/trip.model';
import { ApiResponseTrip } from '../../../models/api/api.response.model';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { AgendamentoComponentComponent } from '../../components/agendamento-component/agendamento-component.component';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  // O array de imports precisa ser preenchido com todos os módulos usados no HTML
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})

export class DashboardComponent implements OnInit {
  private agendamentoService = inject(AgendamentoService);
  public dataSource = new MatTableDataSource<Trip>(); 
  public dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  ngOnInit(): void {
    this.carregarAgendamentos();
  }

  carregarAgendamentos(): void {
    this.agendamentoService.getAgendamentos().subscribe({

      next: (response: ApiResponseTrip) => {
        this.dataSource.data = response.data;
      },
      error: (erro) => {
        console.error('Erro ao carregar veículos:', erro);
      }
    });
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
            this.carregarAgendamentos(); // Recarrega a lista para mostrar o item novo/atualizado
            this.mostrarMensagemDeSucesso("Agendamento salvo com sucesso");
          },
          error: (erro:any) => {
            this.mostrarMensagemDeErro("Erro ao salvar : " + erro.error.message);
          }
        });
      }
    });
  }

  /**
   * Método para excluir (inativar) um veículo.
   * @param id O ID do veículo a ser excluído.
   */
  excluirAgendamento(id: number): void {
    console.log(id)
    // Usamos o 'confirm' do navegador para uma confirmação simples
    if (confirm('Tem certeza que deseja inativar este veículo?')) {
      this.agendamentoService.excluir(id).subscribe({
        next: () => {
          this.carregarAgendamentos(); // Recarrega a lista para remover o item
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