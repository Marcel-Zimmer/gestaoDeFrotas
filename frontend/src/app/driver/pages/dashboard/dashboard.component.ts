import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { PLATFORM_ID, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

// Imports do Angular Material
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDividerModule } from '@angular/material/divider';
import { MatTooltipModule } from '@angular/material/tooltip'; 

// Seus Serviços e Modelos
import { AgendamentoService } from '../../../admin/services/agendamento/agendamento.service';
import { Trip } from '../../../models/trip/trip.model'; 

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
    MatDividerModule,
    MatTooltipModule 
  ],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  // Injeção de dependências
  private agendamentoService = inject(AgendamentoService);
  private dialog = inject(MatDialog);
  private platformId = inject(PLATFORM_ID);

  // Propriedades do componente
  public agendamentos: Trip[] = [];
  public isLoading = true;
  public busyDriverIds = new Set<number>();

  ngOnInit(): void {
    this.loadAgendamentos();
  }

  loadAgendamentos(): void {
      if (isPlatformBrowser(this.platformId)) {
        const userIdString = localStorage.getItem('userId');
        const userId = userIdString ? parseInt(userIdString, 10) : null;  
        if(userId!=null){
          this.isLoading = true;
          this.agendamentoService.getMySchedules(userId).subscribe({
            next: (response) => {
              this.agendamentos = response.data;
              this.busyDriverIds.clear();
              this.agendamentos.forEach(agendamento => {
                // Supondo que 'agendamento.driverId' exista no seu modelo Trip
                if (agendamento.statusTrip === 'EM_VIAGEM' && agendamento.idDriver) {
                  this.busyDriverIds.add(agendamento.idDriver); 
                }
              });
              this.isLoading = false;
            },
            error: (err) => {
              console.error('Erro ao carregar agendamentos', err);
              this.isLoading = false;
            }
          });
        }
      }
  }

  iniciarViagem(agendamentoId: number): void {
    const dialogRef = this.dialog.open(StartTripDialogComponent, {
      width: '400px',
      data: { id: agendamentoId } 
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) { 
        this.loadAgendamentos(); 
      }
    });
  }

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

  visualizarDetalhes(agendamento: Trip): void {
    this.dialog.open(TripDetailsDialogComponent, {
      width: '600px',
      data: agendamento 
    });
  }

  // Função para "traduzir" o status para um texto mais amigável
  public getStatusViewValue(status: string): string {
    switch (status) {
      case 'AGENDADO':
        return 'Agendado';
      case 'EM_VIAGEM':
        return 'Em Viagem';
      case 'FINALIZADO':
        return 'Finalizado';
      case 'CANCELADO':
        return 'Cancelado';
      default:
        // Retorna o próprio status em TitleCase como um fallback
        return status.charAt(0).toUpperCase() + status.slice(1).toLowerCase().replace(/_/g, ' ');
    }
  }
}