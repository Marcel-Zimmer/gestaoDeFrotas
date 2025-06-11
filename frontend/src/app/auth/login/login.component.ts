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
      password: ['', [Validators.required]]
    });
  }

login() {
  if (this.loginForm.valid) {
    console.log('Enviando para a API:', this.loginForm.value);

    // Chama o método login do nosso serviço, passando os dados do formulário
    this.authService.login(this.loginForm.value).subscribe({
      // O 'next' é executado quando a API retorna uma resposta de sucesso (status 2xx)
      next: (response) => {
        console.log('Login bem-sucedido!', response);
        alert('Login realizado com sucesso!');
        // Futuramente, aqui você vai salvar o token e redirecionar o usuário
        // Ex: this.router.navigate(['/motorista']);
      },
      // O 'error' é executado quando a API retorna um erro (status 4xx, 5xx)
      error: (err) => {
        console.error('Erro no login:', err);
        alert('E-mail ou senha inválidos. Tente novamente.');
      }
    });

  } else {
    console.error('Formulário inválido!');
  }
}
}