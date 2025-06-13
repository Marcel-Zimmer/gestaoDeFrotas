import { Component, Inject, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';

// Imports dos módulos do Angular Material para o formulário
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

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
    MatButtonModule      // Para os botões mat-button
  ],
  templateUrl: './veiculo-dialog.component.html',
  styleUrls: ['./veiculo-dialog.component.scss']
})
export class VeiculoDialogComponent {
  // O restante do seu código, que já estava correto:
  form: FormGroup;
  isEditMode: boolean;

  private fb = inject(FormBuilder);
  public dialogRef = inject(MatDialogRef<VeiculoDialogComponent>);

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    // Se 'data' existe, estamos em modo de edição
    this.isEditMode = !!data;

    this.form = this.fb.group({
      id: [data?.id], // Inclui o ID para a lógica de update
      placa: [data?.placa, Validators.required],
      modelo: [data?.modelo, Validators.required],
      tipo: [data?.tipo, Validators.required],
      ano: [data?.ano, [Validators.required, Validators.min(1950), Validators.max(new Date().getFullYear() + 1)]],
      quilometragem: [data?.quilometragem, [Validators.required, Validators.min(0)]],
    });
  }

  salvar(): void {
    if (this.form.valid) {
      // Fecha o dialog e retorna os dados do formulário para o componente que o chamou
      this.dialogRef.close(this.form.value);
    }
  }

  cancelar(): void {
    // Apenas fecha o dialog, sem retornar dados
    this.dialogRef.close();
  }
}