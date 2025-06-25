import { AgendamentoService } from '../../services/agendamento/agendamento.service';
import { Component, OnInit, inject } from '@angular/core';

// Imports NECESSÁRIOS para o template
import { CommonModule } from '@angular/common'; // Para pipes como 'date' e diretivas como *ngIf
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button'; // Para os botões <button mat-icon-button>
import { MatIconModule } from '@angular/material/icon';     // Para os ícones <mat-icon>
import { ApiResponseDriver, ApiResponseTrip } from '../../../models/api/backend/api.response.model';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { Driver } from '../../../models/driver/driver.model';
import { AdminDialogComponent } from '../../components/admin-dialog/admin-dialog.component';
import { AdminService } from '../../services/admin/admin.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule
  ],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.scss'
})
export class AdminComponent {
  private adminService = inject(AdminService);
  public dataSource = new MatTableDataSource<Driver>(); 
  public dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  ngOnInit(): void {
    this.loadDrivers();
  }

  loadDrivers(): void {
    this.adminService.getAdministrators().subscribe({

      next: (response: ApiResponseDriver) => {
        this.dataSource.data = response.data;
      },
      error: (erro) => {
        console.error('Erro ao carregar administradores:', erro);
      }
    });
  }

  dialogoAgendamento(agendamento?: Driver): void {
    const dialogRef = this.dialog.open(AdminDialogComponent, {
      width: '600px',
      disableClose: true, 
      data: agendamento 
    });

    // Escuta o evento de fechamento do dialog
    dialogRef.afterClosed().subscribe(result => {
      
      if (result) {
        this.adminService.updateAdministrator(result).subscribe({
          next: () => {
            this.loadDrivers(); // Recarrega a lista para mostrar o item novo/atualizado
            this.mostrarMensagemDeSucesso("Administrador salvo com sucesso");
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
    if (confirm('Tem certeza que deseja inativar este administrador?')) {
      this.adminService.excludeAdministrator(id).subscribe({
        next: () => {
          this.loadDrivers(); // Recarrega a lista para remover o item
          this.mostrarMensagemDeSucesso("Administrador inativado com sucesso");
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


