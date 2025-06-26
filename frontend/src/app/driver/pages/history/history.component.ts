import { Component, OnInit, ViewChild, AfterViewInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PLATFORM_ID, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
// Imports do Angular Material para a tabela
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';

// Seus Serviços e Modelos
import { AgendamentoService } from '../../../admin/services/agendamento/agendamento.service';
import { Trip } from '../../../models/trip/trip.model'; // Seu modelo de Trip/Agendamento

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatProgressSpinnerModule,
    MatIconModule
  ],
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss']
})
export class HistoryComponent implements OnInit, AfterViewInit {

  // Colunas que serão exibidas na tabela. O nome deve corresponder ao matColumnDef no HTML.
  displayedColumns: string[] = ['date', 'destination', 'vehicle', 'startMileage', 'endMileage', 'totalMileage'];
  
  // Fonte de dados da tabela
  dataSource = new MatTableDataSource<Trip>();
  isLoading = true;
  private platformId = inject(PLATFORM_ID);

  // Acessa os componentes de paginação e ordenação do template
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  // Injeção de dependências
  private agendamentoService = inject(AgendamentoService);

  ngOnInit(): void {
    this.loadHistory();
  }

  loadHistory(): void {
    this.isLoading = true;
    if (isPlatformBrowser(this.platformId)) {
      const userIdString = localStorage.getItem('userId');
      const userId = userIdString ? parseInt(userIdString, 10) : null;
      if(userId != null){
        this.agendamentoService.getMyHistory(userId).subscribe({ 
          next: (response) => {
            this.dataSource.data = response.data;
            this.isLoading = false;
          },
          error: (err) => {
            console.error('Erro ao carregar histórico', err);
            this.isLoading = false;
          }
        });
      }
    }
  }

  // Este método é chamado depois que a view do componente é inicializada.
  // É o lugar certo para conectar o dataSource com o paginator e o sort.
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  // Método para o campo de filtro da tabela
  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  
}