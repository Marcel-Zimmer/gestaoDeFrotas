// Imports que você já tinha
import { AgendamentoService } from '../../services/agendamento/agendamento.service';
import { Component, OnInit, inject } from '@angular/core';

// Imports NECESSÁRIOS para o template
import { CommonModule } from '@angular/common'; // Para pipes como 'date' e diretivas como *ngIf
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button'; // Para os botões <button mat-icon-button>
import { MatIconModule } from '@angular/material/icon';     // Para os ícones <mat-icon>
import {Trip} from '../../../models/trip/trip.model';
import { ApiResponseTrip } from '../../../models/api/api.response.model';


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
  public dataSource = new MatTableDataSource<Trip>(); 

  ngOnInit(): void {
    this.carregarAgendamentos();
  }

  carregarAgendamentos(): void {
    this.agendamentoService.getAgendamentos().subscribe({

      next: (response: ApiResponseTrip) => {
        this.dataSource.data = response.data;
      },
      error: (erro) => {
        console.error('Erro ao carregar veículos:', erro);
      }
    });
  }
}