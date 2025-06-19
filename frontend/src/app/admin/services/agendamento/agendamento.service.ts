import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponseTrip} from '../../../models/api/api.response.model'

@Injectable({
  providedIn: 'root'
})
export class AgendamentoService {
  private http = inject(HttpClient);
  // Endpoint para buscar todos os agendamentos (você precisa criar no seu backend)
  private API_URL = 'http://localhost:8080/trip/schedules';

  getAgendamentos(): Observable<ApiResponseTrip> {
    return this.http.get<ApiResponseTrip>(this.API_URL);
  }
}