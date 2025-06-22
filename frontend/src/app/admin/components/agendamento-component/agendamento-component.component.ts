import { Component, Inject, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';

// Imports dos módulos do Angular Material para o formulário
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { AgendamentoService } from '../../services/agendamento/agendamento.service';
import { ApiResponseDriver } from '../../../models/api/backend/api.response.model';
import { Driver } from '../../../models/driver/driver.model';



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
  private agendamentoService = inject(AgendamentoService);
  private fb = inject(FormBuilder);
  public dialogRef = inject(MatDialogRef<AgendamentoComponentComponent>);
  public motoristas: Driver[] = []; 
  
  ngOnInit(): void {
    this.loadDrivers();
  }

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
      cep: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]], // Validador para 8 dígitos numéricos
      
      // Campos que serão preenchidos pela API. Começam desabilitados.
      logradouro: [{ value: '', disabled: true }, Validators.required],
      bairro: [{ value: '', disabled: true }, Validators.required],
      cidade: [{ value: '', disabled: true }, Validators.required],
      uf: [{ value: '', disabled: true }, Validators.required],
      
      // Campos que o usuário deve preencher manualmente
      numero: ['', Validators.required],
      complemento: [''] // Opcional
    });
  }

  loadDrivers(){
    this.agendamentoService.getDrivers().subscribe({

      next: (response: ApiResponseDriver) => {
        this.motoristas = response.data;
        console.log(this.motoristas)
      },
      error: (erro) => {
        console.error('Erro ao carregar veículos:', erro);
      }
    });
  }


  buscarCep(): void {
    const cep = this.form.get('cep')?.value;

    // Verifica se o CEP não é nulo e tem 8 dígitos
    if (cep && cep.length === 8) {
      this.agendamentoService.getAddress(cep).subscribe(dadosDoEndereco => {
        this.form.patchValue({
          logradouro: dadosDoEndereco.logradouro,
          bairro: dadosDoEndereco.bairro,
          cidade: dadosDoEndereco.localidade, 
          uf: dadosDoEndereco.uf
        });

      });
    }
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
