import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { forkJoin } from 'rxjs';

// Models e Services
import { RefuelingService } from '../../services/refueling/refueling.service';

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
import { Refueling } from '../../../models/refueling/refueling.mdel';
import { Veiculo } from '../../../models/veiculo/veiculo.model';
import { RefuelingDialogComponent } from '../../components/refueling-dialog/refueling-dialog.component';

@Component({
  selector: 'app-refueling',
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
  templateUrl: './refueling.component.html',
  styleUrl: './refueling.component.scss'
})
export class RefuelingComponent {
  private refuelingService = inject(RefuelingService);
  private fb = inject(FormBuilder);
  public dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  public dataSource = new MatTableDataSource<Refueling>();
  public refuelings: Refueling[] = [];

  ngOnInit(): void {
    this.loadInitialData();
  }

  loadInitialData(): void {
    const sources = {
      refuelings: this.refuelingService.getRefuelings(),
    };

    forkJoin(sources).subscribe({
      next: (responses) => {
        this.refuelings = responses.refuelings.data;
        this.dataSource.data = this.refuelings;
      },
      error: (err) => {
        console.error('Erro ao carregar dados iniciais:', err);
      }
    });
  }
  public type = [
    { value: 'GASOLINA_COMUM', viewValue: 'Gasolina' },
    { value: 'GASOLINA_ADITIVADA', viewValue: 'Gasolina Adtivada' },
    { value: 'ETANOL', viewValue: 'Etanol' },
    { value: 'DIESEL_COMUM', viewValue: 'Diesel' },
    { value: 'DIESEL_S10', viewValue: 'Diesel S10' },
    { value: 'GNV', viewValue: 'Gnv' },
    { value: 'ELETRICIDADE', viewValue: 'Eletricidade' },
    { value: 'BIODIESEL', viewValue: 'Biodisel' }
  ];

public getFuelTypeViewValue(backendValue: string): string {
  // Procura na lista 'type' pelo objeto cujo 'value' corresponde ao valor do backend
  const foundType = this.type.find(t => t.value === backendValue);
  
  // Se encontrou, retorna o viewValue. Senão, retorna o próprio valor bruto ou um texto padrão.
  return foundType ? foundType.viewValue : backendValue;
}
  dialogoRefueling(refueling?: Refueling): void {
    const dialogRef = this.dialog.open(RefuelingDialogComponent, {
      width: '600px',
      disableClose: true, 
      data: refueling 
    });

    // Escuta o evento de fechamento do dialog
    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.mostrarMensagemDeSucesso("Abastecimento salvo com sucesso!");
        this.loadInitialData(); 
      }
    });
  }

  excluirAgendamento(id: number): void {
    if (confirm('Tem certeza que deseja excluir este abastecimento?')) {
      this.refuelingService.excluir(id).subscribe({
        next: () => {
          this.loadInitialData();
          this.mostrarMensagemDeSucesso("Abastecimento excluido com sucesso");
        },
        error: (erro :any) => {
          this.mostrarMensagemDeErro(erro.message);
        }
      });
    }
  }
  mostrarMensagemDeSucesso(mensagem: string): void {
    this.snackBar.open(mensagem, 'Fechar', {
      duration: 3000, 
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
