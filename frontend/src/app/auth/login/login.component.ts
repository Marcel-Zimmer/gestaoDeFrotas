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
      this.authService.login(this.loginForm.value).subscribe({
        next: (response) => {
          // A LÓGICA DE PÓS-LOGIN FICA AQUI, NO COMPONENTE!
          

          // 1. Salva os dados no localStorage
          //localStorage.setItem('usuario_logado', JSON.stringify(response));

          // 2. Avisa o usuário
          if(response.success){
            alert('Login realizado com sucesso!');
            console.log('Login bem-sucedido!', response);
            if (response.superUser) {
              this.router.navigate(['/admin']);
            } else{
              this.router.navigate(['/driver']);
            }
            localStorage.setItem('userId', response.userId.toString());
          }else{
            alert('Usuario ou senha incorretos!');
          }
          
          
          
          
          // 3. Redireciona o usuário com base no perfil

        },
        error: (err) => {
          // O tratamento de erro também é responsabilidade do componente
          console.error('Erro no login:', err);
          alert('E-mail ou senha inválidos. Tente novamente.');
        }
      });
    }
  }
}
