import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

// Imports do Angular Material
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

// Importe seu serviço (vamos usá-lo depois)
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  // No modo standalone, importamos tudo que o componente usa aqui
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup;

  // Injeção de dependência moderna
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  constructor() {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required]]
    });
  }

  login() {
    if (this.loginForm.valid) {
      console.log('Formulário válido. Dados:', this.loginForm.value);
      // Futuramente, aqui chamaremos o this.authService.login(...)
    } else {
      console.error('Formulário inválido!');
    }
  }
}