import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

// Imports do Angular Material para o Layout
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [
    // Módulos Essenciais do Angular
    CommonModule,
    RouterOutlet, // Para renderizar as rotas filhas (dashboard, etc.)
    RouterLink,   // Para os links do menu

    // Módulos do Angular Material
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatIconModule,
    MatButtonModule
  ],
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent {
  // Injeta o Router para usarmos na função de logout
  private router = inject(Router);

  logout(): void {
    // 1. Limpa os dados do usuário do armazenamento local
    localStorage.removeItem('usuario_logado');

    // 2. Redireciona o usuário para a tela de login
    this.router.navigate(['/login']);
  }
}