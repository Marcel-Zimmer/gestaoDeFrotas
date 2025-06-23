import { Component, Inject, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, provideNativeDateAdapter } from '@angular/material/core';

// Imports dos módulos do Angular Material para o formulário
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { AgendamentoService } from '../../services/agendamento/agendamento.service';
import { ApiResponseDriver, ApiResponseVehicle } from '../../../models/api/backend/api.response.model';
import { Driver } from '../../../models/driver/driver.model';
import { Veiculo } from '../../../models/veiculo/veiculo.model';


@Component({
  selector: 'app-driver-component',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule, // Essencial para FormGroups
    MatDialogModule,     // Para as diretivas mat-dialog-title, content, etc.
    MatFormFieldModule,  // Para <mat-form-field>
    MatInputModule,      // Para a diretiva matInput nos campos
    MatButtonModule,      // Para os botões mat-button
    MatSelectModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule    
  ],
    providers: [
    provideNativeDateAdapter() // A LÓGICA da data agora é um provider
  ],
  templateUrl: './driver-component.component.html',
  styleUrl: './driver-component.component.scss'
})
export class DriverComponentComponent {
 
  form: FormGroup;
  isEditMode: boolean;
  private agendamentoService = inject(AgendamentoService);
  private fb = inject(FormBuilder);
  public dialogRef = inject(MatDialogRef<DriverComponentComponent>);
  
  ngOnInit(): void {
  }

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    // Se 'data' existe, estamos em modo de edição
    this.isEditMode = !!data;

    this.form = this.fb.group({
      id: [data?.tripId], 
      name: [data?.idDriver, Validators.required],
      email: [data?.idDriver, Validators.required],
      cpf:[data?.idVehicle, Validators.required],
      cnh: [data?.statusTrip, Validators.required],
      expirationDate : [data?.date, [Validators.required, Validators.min(1950), Validators.max(new Date().getFullYear() + 1)]],
      dddNumber:[data?.idDriver, Validators.required],
      phoneNumber:[data?.idDriver, Validators.required],
      isAtive:[true],
      isSuperUser:[false],
      statusDriver:["disponivel"],
      password: ["Senha123@"],

      cep: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]], 
      logradouro: ['',Validators.required],
      unidade:[''],
      bairro: ['',Validators.required],
      localidade: ['',Validators.required],
      uf: ['',Validators.required],
      estado:[''],
      regiao:[''],
      ibge:[''],
      gia:[''],
      ddd:[''],
      siafi:[''],
      numberAdress: ['', Validators.required],
      complemento: ['']       
    });
  }

  buscarCep(): void {
    const cep = this.form.get('cep')?.value;
    if (cep && cep.length === 8) {
      this.agendamentoService.getAddress(cep).subscribe(dadosDoEndereco => {
        this.form.patchValue({
          logradouro: dadosDoEndereco.logradouro,
          unidade:dadosDoEndereco.unidade,
          bairro: dadosDoEndereco.bairro,
          localidade: dadosDoEndereco.localidade, 
          uf: dadosDoEndereco.uf,
          estado:dadosDoEndereco.estado,
          regiao : dadosDoEndereco.regiao,
          ibge : dadosDoEndereco.ibge,
          gia:dadosDoEndereco.gia,
          ddd:dadosDoEndereco.ddd,
          siafi : dadosDoEndereco.siafi,
        });

      });
    }
  }
  save(): void {
    if (this.form.valid) {
      
    }
    this.dialogRef.close(this.form.value);
  }

  close(): void {
    this.dialogRef.close();
  }
}
