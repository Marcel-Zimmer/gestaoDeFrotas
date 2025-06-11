// Imports que você já tinha
import { AgendamentoService } from '../../services/agendamento.service';
import { Component, OnInit, inject } from '@angular/core';

// Imports NECESSÁRIOS para o template
import { CommonModule } from '@angular/common'; // Para pipes como 'date' e diretivas como *ngIf
import { MatTableModule } from '@angular/material/table';   // Para a tabela <mat-table>
import { MatButtonModule } from '@angular/material/button'; // Para os botões <button mat-icon-button>
import { MatIconModule } from '@angular/material/icon';     // Para os ícones <mat-icon>


@Component({
  selector: 'app-dashboard',
  standalone: true,
  // O array de imports precisa ser preenchido com todos os módulos usados no HTML
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})

export class DashboardComponent implements OnInit {
  private agendamentoService = inject(AgendamentoService);
  public agendamentos: any[] = []; // Array para guardar os dados

  ngOnInit(): void {
    this.carregarAgendamentos();
  }

  carregarAgendamentos(): void {
    this.agendamentoService.getAgendamentos().subscribe(dados => {
      this.agendamentos = dados;
      console.log('Agendamentos carregados:', this.agendamentos);
    });
  }
}