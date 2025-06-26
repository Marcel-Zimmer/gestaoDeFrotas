import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

// Imports dos Módulos e Serviços do Angular Material
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTooltipModule } from '@angular/material/tooltip';

// Seus Serviços e Modelos
import { MaintenanceService } from '../../services/maintence/maintence.service';
import { Maintence } from '../../../models/maintence/maintence.model';
import { ApiResponseMaintence } from '../../../models/api/backend/api.response.model';

// Componente do Dialog
import { MaintenceDialogComponent } from '../../components/maintence-dialog/maintence-dialog.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-maintence', // O seletor que você usará no HTML do layout
  standalone: true,
  imports: [
    CommonModule,             // Para diretivas como *ngIf, *ngFor e pipes (date, currency)
    ReactiveFormsModule,      // Essencial para o campo de filtro se ele estiver em um FormGroup

    // Módulos do Angular Material
    MatTableModule,           // Para a tabela (<mat-table>, mat-cell, etc.)
    MatPaginatorModule,       // Para o <mat-paginator>
    MatSortModule,            // Para a ordenação de colunas (matSort)
    MatFormFieldModule,       // Para <mat-form-field> do filtro
    MatInputModule,           // Para a diretiva matInput no campo de filtro
    MatButtonModule,          // Para mat-button e mat-icon-button
    MatIconModule,            // Para <mat-icon>
    MatTooltipModule,         // Para as dicas (matTooltip) nos botões
    MatDialogModule,          // Para poder abrir o dialog
    MatSnackBarModule,        // Para as notificações de sucesso/erro
    MatProgressSpinnerModule  // Para o <mat-spinner>
  ],
  templateUrl: './maintence.component.html',
  styleUrls: ['./maintence.component.scss']
})
export class MaintenanceComponent implements OnInit {
  
  // Colunas a serem exibidas na tabela
 displayedColumns: string[] = ['vehicle', 'date', 'type', 'description', 'price', 'actions'];
  
  // Fonte de dados para a MatTable
  dataSource = new MatTableDataSource<Maintence>();
  isLoading = true;
  
  // Injeção de dependências usando inject()
  private maintenanceService = inject(MaintenanceService);
  private dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  ngOnInit(): void {
    this.loadMaintenances();
  }

  /**
   * Carrega ou recarrega a lista de manutenções da API.
   */
  loadMaintenances(): void {
    this.isLoading = true;
    this.maintenanceService.getMaintenances().subscribe({
      next: (response: ApiResponseMaintence) => {
        this.dataSource.data = response.data;
        this.isLoading = false;
      },
      error: (err) => {
        this.isLoading = false;
        this.snackBar.open('Erro ao carregar registros de manutenção.', 'Fechar', { duration: 5000 });
        console.error(err);
      }
    });
  }

  /**
   * Abre o dialog para criar uma nova manutenção ou editar uma existente.
   * @param maintenance O registro de manutenção a ser editado. Se for nulo, abre em modo de criação.
   */
  openMaintenanceDialog(maintenance?: Maintence): void {
    const dialogRef = this.dialog.open(MaintenceDialogComponent, {
      width: '600px',
      disableClose: true, // Impede que o dialog seja fechado clicando fora
      data: maintenance // Passa os dados para o dialog. Será 'null' no modo de criação.
    });

    dialogRef.afterClosed().subscribe(result => {
      // 'result' conterá os dados do formulário se o usuário salvou.
      if (result) {
        // Decide se a ação é de criar ou atualizar
        const isEditing = !!maintenance;
        const action = isEditing
          ? this.maintenanceService.updateMaintenance(result.id, result): this.maintenanceService.createMaintenance(result);
        
        const message = isEditing
          ? "Manutenção atualizada com sucesso!"
          : "Manutenção registrada com sucesso!";
        
        action.subscribe({
          next: () => {
            this.loadMaintenances(); // Recarrega a tabela com os novos dados
            this.snackBar.open(message, 'Fechar', { duration: 3000 });
          },
          error: (err) => {
            this.snackBar.open('Erro ao salvar manutenção.', 'Fechar');
            console.error(err);
          }
        });
      }
    });
  }

  /**
   * Deleta um registro de manutenção após confirmação do usuário.
   * @param id O ID da manutenção a ser deletada.
   */
  deleteMaintenance(id: number): void {
    // Usar um dialog de confirmação do Material seria mais elegante, mas confirm() é mais rápido.
    if (confirm('Tem certeza que deseja excluir este registro de manutenção? Esta ação não pode ser desfeita.')) {
      this.maintenanceService.deleteMaintenance(id).subscribe({
        next: () => {
          this.loadMaintenances();
          this.snackBar.open("Registro excluído com sucesso!", 'Fechar', { duration: 3000 });
        },
        error: (err) => {
          this.snackBar.open('Erro ao excluir registro.', 'Fechar');
          console.error(err);
        }
      });
    }
  }
}