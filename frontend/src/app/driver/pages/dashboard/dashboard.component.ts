import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';

// Imports do Angular Material
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDividerModule } from '@angular/material/divider';

// Seus Serviços e Modelos
import { AgendamentoService } from '../../../admin/services/agendamento/agendamento.service';
import { Trip } from '../../../models/trip/trip.model'; // Supondo que você tenha este modelo

// Os dialogs que serão abertos pelas ações
import { StartTripDialogComponent } from '../../dialogs/start-trip-dialog/start-trip-dialog.component';
import { EndTripDialogComponent } from '../../dialogs/end-trip-dialog/end-trip-dialog.component';
import { TripDetailsDialogComponent } from '../../dialogs/trip-details-dialog/trip-details-dialog.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatDividerModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  // Injeção de dependências
  private agendamentoService = inject(AgendamentoService);
  private dialog = inject(MatDialog);

  // Propriedades do componente
  public agendamentos: Trip[] = [];
  public isLoading = true;

  ngOnInit(): void {
    this.loadAgendamentos();
  }

  loadAgendamentos(): void {
    const userIdString = localStorage.getItem('userId');
    const userId = userIdString ? parseInt(userIdString, 10) : null;
    if(userId!=null){
      this.isLoading = true;
      this.agendamentoService.getMySchedules(userId).subscribe({
        next: (response) => {
          this.agendamentos = response.data; 
          this.isLoading = false;
        },
        error: (err) => {
          console.error('Erro ao carregar agendamentos', err);
          this.isLoading = false;
        }
      });
    }
   
  }

  // Ação para RF005
  iniciarViagem(agendamentoId: number): void {
    const dialogRef = this.dialog.open(StartTripDialogComponent, {
      width: '400px',
      data: { id: agendamentoId } // Passa o ID para o dialog
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) { // Se o dialog retornou sucesso
        this.loadAgendamentos(); // Atualiza a lista
      }
    });
  }

  // Ação para RF006
  finalizarViagem(agendamentoId: number): void {
    const dialogRef = this.dialog.open(EndTripDialogComponent, {
      width: '400px',
      data: { id: agendamentoId }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadAgendamentos();
      }
    });
  }

  // Ação para RF007
  visualizarDetalhes(agendamento: Trip): void {
    this.dialog.open(TripDetailsDialogComponent, {
      width: '600px',
      data: agendamento // Passa o objeto inteiro para o dialog
    });
  }
}