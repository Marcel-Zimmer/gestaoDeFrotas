import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

// Imports do Angular Material para esta página
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';


import { VeiculoService } from '../../services/vehicles/veiculo.service';
import { VeiculoDialogComponent } from '../../components/veihcle-dialog/vehicle-dialog.component';
import { Veiculo } from '../../../models/veiculo/veiculo.model';
import { ApiResponseVehicle } from '../../../models/api/backend/api.response.model';

@Component({
  selector: 'app-veiculos',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule
  ],
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.scss']
})


export class VeiculosComponent implements OnInit {
  private snackBar = inject(MatSnackBar);
  // Array que guardará os dados para a tabela
  public dataSource = new MatTableDataSource<Veiculo>(); 
  
  // Define as colunas que a tabela irá exibir e a ordem delas.
  // Deve corresponder exatamente aos 'matColumnDef' no seu HTML.
  public displayedColumns: string[] = ['placa', 'modelo', 'tipo', 'ano', 'quilometragem', 'status', 'acoes'];

  // Injeção de dependências da forma moderna
  private veiculoService = inject(VeiculoService);
  public dialog = inject(MatDialog);


  
  // ngOnInit é chamado uma vez quando o componente é inicializado.
  ngOnInit(): void {
    this.loadVehicles();
  }


 loadVehicles(): void {
    this.veiculoService.getVeiculos().subscribe({

      next: (response: ApiResponseVehicle) => {
        this.dataSource.data = response.data;
      },
      error: (erro) => {
        console.error('Erro ao carregar veículos:', erro);
      }
    });
  }

  dialogVehicle(veiculo?: Veiculo): void {
    const dialogRef = this.dialog.open(VeiculoDialogComponent, {
      width: '600px',
      disableClose: true, 
      data: veiculo 
    });

    // Escuta o evento de fechamento do dialog
    dialogRef.afterClosed().subscribe(result => {
      
      if (result) {
        this.veiculoService.updateVehicle(result).subscribe({
          next: () => {
            this.loadVehicles(); // Recarrega a lista para mostrar o item novo/atualizado
            this.mostrarMensagemDeSucesso("Veiculo salvo com sucesso");
          },
          error: (erro) => {
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
  excluirVeiculo(id: number): void {
    console.log(id)
    // Usamos o 'confirm' do navegador para uma confirmação simples
    if (confirm('Tem certeza que deseja inativar este veículo?')) {
      this.veiculoService.excluir(id).subscribe({
        next: () => {
          this.loadVehicles(); // Recarrega a lista para remover o item
          this.mostrarMensagemDeSucesso("Veiculo excluido com sucesso");
        },
        error: (erro) => {
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

