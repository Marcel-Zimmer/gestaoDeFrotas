import { Component, Inject, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
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
    // Esta parte está correta
    console.log(data)
    this.isEditMode = !!data;

    // Lógica do telefone pode ser um pouco mais segura e concisa
    const phoneNumber = data?.phoneNumber || ''; // Garante que phoneNumber seja uma string
    const ddd = phoneNumber.slice(0, 2);
    const numero = phoneNumber.slice(2);

    this.form = this.fb.group({
        // Use '??' para fornecer um valor padrão caso o da esquerda seja nulo/indefinido
        driverId: [data?.driverId ?? null], 
        name: [data?.nameDriver ?? '', Validators.required],
        email: [data?.email ?? '', [Validators.required, Validators.email]], // Adicionei o validador de email
        cpf:[data?.cpf ?? '', Validators.required],
        cnh: [data?.cnh ?? '', Validators.required],
        password: ['', [this.passwordStrengthValidator()]],
        expirationDate : [data?.cnhExpiration ?? null, [Validators.required]], // Ajuste conforme necessário
        
        dddNumber: [ddd, Validators.required],
        phoneNumber: [numero, Validators.required],
        
        isAtive: [{ value: data?.isAtive ?? true, disabled: true }],
        isSuperUser: [data?.isSuperUser ?? false],

        // --- CORREÇÃO PRINCIPAL PARA O ENDEREÇO ---
        // Use o encadeamento opcional em toda a cadeia: data?.address?.<propriedade>
        
        zipCode: [data?.address?.zipCode ?? '', [Validators.required, Validators.pattern(/^\d{8}$/)]],
        street: [data?.address?.street ?? '', Validators.required],           // era 'logradouro'
        complement: [data?.address?.complement ?? ''],                         // era 'complemento'
        unit: [data?.address?.unit ?? ''],                                     // era 'unidade'
        neighborhood: [data?.address?.neighborhood ?? '', Validators.required], // era 'bairro'
        city: [data?.address?.city ?? '', Validators.required],                 // era 'localidade'
        stateAbbreviation: [data?.address?.uf ?? '', Validators.required],      // era 'uf'
        state: [data?.address?.state ?? ''],                                   // era 'estado'
        region: [data?.address?.region ?? ''],                                 // era 'regiao'
        ibgeCode: [data?.address?.ibgeCode ?? ''],                             // era 'ibge'
        giaCode: [data?.address?.giaCode ?? ''],                               // era 'gia'
        ddd: [data?.address?.ddd ?? ''],                                       // DDD do endereço, vindo do CEP
        siafiCode: [data?.address?.siafiCode ?? ''],                           // era 'siafi'
        numberAddress: [data?.address?.numberAddress ?? '', Validators.required], // era 'numberAdress'
    });
}

  buscarCep(): void {
    const cep = this.form.get('zipCode')?.value;
    if (cep && cep.length === 8) {
      this.agendamentoService.getAddress(cep).subscribe(dadosDoEndereco => {
        this.form.patchValue({
          zipCode:cep,
          street: dadosDoEndereco.logradouro,           // <-- Mapeamento
          complement: "",       // <-- Mapeamento
          unit: dadosDoEndereco.unidade,                 // <-- Mapeamento
          neighborhood: dadosDoEndereco.bairro,         // <-- Mapeamento
          city: dadosDoEndereco.localidade,              // <-- Mapeamento
          stateAbbreviation: dadosDoEndereco.uf,        // <-- Mapeamento
          state: dadosDoEndereco.estado,                 // <-- Mapeamento
          ibgeCode: dadosDoEndereco.ibge,                // <-- Mapeamento
          giaCode: dadosDoEndereco.gia,                  // <-- Mapeamento
          ddd: dadosDoEndereco.ddd,                      // <-- Mapeamento
          siafiCode: dadosDoEndereco.siafi               // <-- Mapeamento
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
  passwordStrengthValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    
    const value = control.value;

    // 1. Se não houver valor, o campo é opcional e, portanto, válido.
    if (!value) {
      return null;
    }

    // 2. Traduzindo suas regras de Java para Regex de JavaScript
    const hasLetter = /[a-zA-Z]/.test(value);
    const hasDigit = /\d/.test(value);
    const hasSpecialChar = /[^a-zA-Z0-9]/.test(value);

    // 3. Verifica se todas as condições são verdadeiras
    const passwordIsValid = hasLetter && hasDigit && hasSpecialChar;

    // 4. Retorna null se for válido, ou um objeto de erro se for inválido.
    // A chave 'passwordStrength' poderá ser usada no HTML para mostrar a mensagem de erro.
    return !passwordIsValid ? { passwordStrength: true } : null;
  };
}  
}
