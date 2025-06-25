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
  selector: 'app-end-trip-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './end-trip-dialog.component.html',
  styleUrls: ['./end-trip-dialog.component.scss']
})
export class EndTripDialogComponent {
  
  form: FormGroup;

  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<EndTripDialogComponent>);
  private agendamentoService = inject(AgendamentoService);

  constructor(@Inject(MAT_DIALOG_DATA) public data: { id: number }) {
    this.form = this.fb.group({
      quilometragemFinal: [null, [Validators.required, Validators.min(0)]],
      observacoesRetorno: ['']
    });
  }

  save(): void {
    if (this.form.invalid) {
      return;
    }

    this.agendamentoService.finalizarViagem(this.data.id, this.form.value).subscribe({
      next: () => {
        this.dialogRef.close(true); // Retorna sucesso
      },
      error: (err) => console.error('Erro ao finalizar viagem', err)
    });
  }

  close(): void {
    this.dialogRef.close();
  }
}