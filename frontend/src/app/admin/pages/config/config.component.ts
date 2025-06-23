import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// Assumindo que você tem um serviço para buscar e salvar os dados do usuário/admin


// Importe os módulos do Material
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatTabsModule } from '@angular/material/tabs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

@Component({
  selector: 'app-config',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatTabsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  templateUrl: './config.component.html',
  styleUrl: './config.component.scss'
})
export class ConfigComponent {
  private fb = inject(FormBuilder);
  
  // O FormGroup que vai controlar todos os campos de todas as abas
  userForm: FormGroup;

  constructor() {
    // Inicializa o formulário com todos os campos
    this.userForm = this.fb.group({
      // Aba 1: Informações Pessoais
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      cpf: ['', Validators.required],
      ddd: [''],
      phoneNumber: [''],
      cnh: ['', Validators.required],
      expirationDate: ['', Validators.required],

      // Aba 2: Endereço
      cep: ['', Validators.required],
      logradouro: [{ value: '', disabled: true }],
      numberAdress: ['', Validators.required],
      complemento: [''],
      bairro: [{ value: '', disabled: true }],
      localidade: [{ value: '', disabled: true }], // 'localidade' é a cidade
      uf: [{ value: '', disabled: true }],

      // Aba 3: Senha (não são preenchidos inicialmente)
      currentPassword: [''],
      newPassword: [''],
      confirmPassword: ['']
    });
  }

  ngOnInit(): void {
    this.loadUserData();
  }

  // Método para carregar os dados do usuário e preencher o formulário
  loadUserData(): void {

  }

  // Método para salvar as alterações
  saveChanges(): void {
    if (this.userForm.invalid) {
      console.error('Formulário inválido!');
      return;
    }
    console.log('Salvando dados:', this.userForm.value);
    // Aqui você chamaria seu serviço para enviar os dados atualizados
    // this.userService.updateUserData(this.userForm.value).subscribe(...);
  }
}

