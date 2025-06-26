import { Component, Inject, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { AgendamentoService } from '../../../admin/services/agendamento/agendamento.service';

// Material Imports
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-start-trip-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './start-trip-dialog.component.html',
  styleUrls: ['./start-trip-dialog.component.scss']
})
export class StartTripDialogComponent {
  
  form: FormGroup;

  // Injeção de dependências
  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<StartTripDialogComponent>);
  private agendamentoService = inject(AgendamentoService);

  // Recebe o ID do agendamento do componente que abriu o dialog
  constructor(@Inject(MAT_DIALOG_DATA) public data:any) {
    this.form = this.fb.group({
      startMileage: [null, [Validators.required, Validators.min(0)]],
      startObservations: [''] // Campo opcional
    });
  }

  save(): void {
    if (this.form.invalid) {
      return;
    }

    this.agendamentoService.startTrip(this.data.id, this.form.value).subscribe({
      next: () => {
        this.dialogRef.close(true);
      },
      error: (err) => console.error('Erro ao iniciar viagem', err)
    });
  }

  close(): void {
    this.dialogRef.close();
  }
}