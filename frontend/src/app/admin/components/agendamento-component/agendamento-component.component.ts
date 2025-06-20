import { Component, Inject, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';

// Imports dos módulos do Angular Material para o formulário
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
;

@Component({
  selector: 'app-agendamento-component',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule, // Essencial para FormGroups
    MatDialogModule,     // Para as diretivas mat-dialog-title, content, etc.
    MatFormFieldModule,  // Para <mat-form-field>
    MatInputModule,      // Para a diretiva matInput nos campos
    MatButtonModule,      // Para os botões mat-button
    MatSelectModule,
  ],
  templateUrl: './agendamento-component.component.html',
  styleUrl: './agendamento-component.component.scss'
})
export class AgendamentoComponentComponent {
  
  form: FormGroup;
  isEditMode: boolean;

  private fb = inject(FormBuilder);
  public dialogRef = inject(MatDialogRef<AgendamentoComponentComponent>);

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    // Se 'data' existe, estamos em modo de edição
    this.isEditMode = !!data;

    this.form = this.fb.group({
      id: [data?.tripId], 
      justify: [data?.justify, Validators.required],
      date: [data?.date, [Validators.required, Validators.min(1950), Validators.max(new Date().getFullYear() + 1)]],
      statusTrip: [data?.statusTrip, Validators.required],
      nameDriver: [data?.nameDriver, Validators.required],
      licencePlate: [data?.licencePlate, Validators.required],
      modelVehicle: [data?.modelVehicle, Validators.required],
      typeVehicle: [data?.typeVehicle, Validators.required],
    });
  }

  save(): void {
    if (this.form.valid) {
      this.dialogRef.close(this.form.value);
    }
  }

  close(): void {
    this.dialogRef.close();
  }
}
