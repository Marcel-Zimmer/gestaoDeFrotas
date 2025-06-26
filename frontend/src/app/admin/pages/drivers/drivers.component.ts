import { AgendamentoService } from '../../services/agendamento/agendamento.service';
import { Component, OnInit, inject } from '@angular/core';

// Imports NECESSÁRIOS para o template
import { CommonModule } from '@angular/common'; 
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button'; 
import { MatIconModule } from '@angular/material/icon';    
import { ApiResponseDriver, ApiResponseTrip } from '../../../models/api/backend/api.response.model';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { Driver } from '../../../models/driver/driver.model';
import { DriverComponentComponent } from '../../components/driver-component/driver-component.component';
import { DriversService } from '../../services/drivers/drivers.service';

@Component({
  selector: 'app-drivers',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule
  ],
  templateUrl: './drivers.component.html',
  styleUrl: './drivers.component.scss'
})
export class DriversComponent {
  private driverService = inject(DriversService);
  public dataSource = new MatTableDataSource<Driver>(); 
  public dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  ngOnInit(): void {
    this.loadDrivers();
  }

  loadDrivers(): void {
    this.driverService.getDrivers().subscribe({

      next: (response: ApiResponseDriver) => {
        this.dataSource.data = response.data;
      },
      error: (erro) => {
        console.error('Erro ao carregar motoristas:', erro);
      }
    });
    console.log(this.dataSource)
  }

  dialogDriver(agendamento?: Driver): void {
    const dialogRef = this.dialog.open(DriverComponentComponent, {
      width: '600px',
      disableClose: true, 
      data: agendamento 
    });

    // Escuta o evento de fechamento do dialog
    dialogRef.afterClosed().subscribe(result => {
      
      if (result) {
        this.driverService.updateDriver(result).subscribe({
          next: () => {
            this.loadDrivers(); // Recarrega a lista para mostrar o item novo/atualizado
            this.mostrarMensagemDeSucesso("Motorista salvo com sucesso");
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
  excluirDriver(id: number): void {
    console.log(id)
    if (confirm('Tem certeza que deseja inativar este motorista?')) {
      this.driverService.excludeDriver(id).subscribe({
        next: () => {
          this.loadDrivers(); // Recarrega a lista para remover o item
          this.mostrarMensagemDeSucesso("Motorista excluido com sucesso");
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
      horizontalPosition: 'center', 
      verticalPosition: 'bottom', 
    });
  }

  mostrarMensagemDeErro(mensagem: string): void {
    this.snackBar.open(mensagem, 'OK', {
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      panelClass: ['snackbar-error'] 
    });
  }  
}
