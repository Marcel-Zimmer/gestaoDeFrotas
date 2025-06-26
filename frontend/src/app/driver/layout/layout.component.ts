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
    CommonModule,
    RouterOutlet, 
    RouterLink,   
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
    
    if (result) {
      this.mostrarMensagemDeSucesso("Ocorrência registrada com sucesso!");
      
    }
  });
}
  mostrarMensagemDeSucesso(mensagem: string): void {
    this.snackBar.open(mensagem, 'Fechar', {
      duration: 3000, // A mensagem some após 3 segundos
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  }    
}
