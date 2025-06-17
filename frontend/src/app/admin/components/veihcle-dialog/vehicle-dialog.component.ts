import { Component, Inject, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';

// Imports dos módulos do Angular Material para o formulário
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';


@Component({
  selector: 'app-veiculo-dialog', // O seletor CSS para o componente
  standalone: true,
  // Lista de todas as dependências que o TEMPLATE deste componente usa
  imports: [
    CommonModule,
    ReactiveFormsModule, // Essencial para FormGroups
    MatDialogModule,     // Para as diretivas mat-dialog-title, content, etc.
    MatFormFieldModule,  // Para <mat-form-field>
    MatInputModule,      // Para a diretiva matInput nos campos
    MatButtonModule,      // Para os botões mat-button
    MatSelectModule,
  ],
  templateUrl: './vehicle-dialog.component.html',
  styleUrls: ['./vehicle-dialog.component.scss']
})
export class VeiculoDialogComponent {
  
  form: FormGroup;
  isEditMode: boolean;

  status = [
    { value: 'DISPONIVEL', viewValue: 'Disponível' },
    { value: 'EM_MANUTENCAO', viewValue: 'Em Manutenção' },
    { value: 'EM_USO', viewValue: 'Em uso' },
    { value: 'INATIVO', viewValue: 'Inativo' }
  ];

  typoVeihcle = [

    { value: 'CARRO', viewValue: 'Carro' },
    { value: 'MOTO', viewValue: 'Motocicleta' },
    { value: 'PICAPE', viewValue: 'Picape' },
    { value: 'SUV', viewValue: 'SUV' },
    { value: 'VAN', viewValue: 'Van' },
    { value: 'FURGAO', viewValue: 'Furgão' },
    { value: 'UTILITARIO', viewValue: 'Utilitário' },
    { value: 'CAMINHAO', viewValue: 'Caminhão' },
    { value: 'ONIBUS', viewValue: 'Ônibus' },
    { value: 'TRATOR', viewValue: 'Trator' },
    { value: 'REBOQUE', viewValue: 'Reboque' },
    { value: 'OUTRO', viewValue: 'Outro' }
  ];
  private fb = inject(FormBuilder);
  public dialogRef = inject(MatDialogRef<VeiculoDialogComponent>);

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    // Se 'data' existe, estamos em modo de edição
    this.isEditMode = !!data;

    this.form = this.fb.group({
      id: [data?.vehicleId], 
      licence: [data?.licencePlate, Validators.required,],
      model: [data?.modelVehicle, Validators.required],
      type: [data?.typeVehicle, Validators.required],
      year: [data?.yearVehicle, [Validators.required, Validators.min(1950), Validators.max(new Date().getFullYear() + 1)]],
      mileage: [data?.currentMileage, [Validators.required, Validators.min(0)]],
      status: [data?.statusVehicle, Validators.required],
    });
  }

  save(): void {
    if (this.form.valid) {
      this.dialogRef.close(this.form.value);
    }
  }

  close(): void {
    // Apenas fecha o dialog, sem retornar dados
    this.dialogRef.close();
  }
}