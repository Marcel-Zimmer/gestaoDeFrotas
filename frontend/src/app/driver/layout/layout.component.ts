import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

// Imports do Angular Material para o Layout
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { ReportIssueDialogComponent } from '../dialogs/report-issue-dialog/report-issue-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';
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
  styleUrl: './layout.component.scss'
})
export class LayoutComponent {
  // Injeta o Router para usarmos na função de logout
  private router = inject(Router);
  private dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  logout(): void {
    // 1. Limpa os dados do usuário do armazenamento local
    localStorage.removeItem('userId');

    // 2. Redireciona o usuário para a tela de login
    this.router.navigate(['/login']);
  }

abrirDialogOcorrencia(): void {
  const dialogRef = this.dialog.open(ReportIssueDialogComponent, {
    width: '600px',
  });

  dialogRef.afterClosed().subscribe(result => {
    
    // Apenas executa a lógica se o 'result' for verdadeiro (ou seja, o botão 'Enviar' foi clicado)
    if (result) {
      this.mostrarMensagemDeSucesso("Ocorrência registrada com sucesso!");
      
      // Se você tiver uma tabela de ocorrências nesta tela, aqui seria o lugar
      // para recarregá-la, chamando um método como this.loadOcorrencias();
    }
    // Se o resultado não for 'true' (ex: o usuário cancelou), nada acontece.
  });
}
  mostrarMensagemDeSucesso(mensagem: string): void {
    this.snackBar.open(mensagem, 'Fechar', {
      duration: 3000, // A mensagem some após 3 segundos
      horizontalPosition: 'center', // Posição horizontal (pode ser 'start', 'center', 'end', 'left', 'right')
      verticalPosition: 'bottom', // Posição vertical (pode ser 'top' ou 'bottom')
    });
  }    
}
