import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponseDriver, ApiResponseTrip, ApiResponseVehicle} from '../../../models/api/backend/api.response.model'
import { AgendamentoRequest } from '../../../models/api/backend/api.request.model'
import { ApiCepResponse } from '../../../models/api/viacep/viacep.request.model';

@Injectable({
  providedIn: 'root'
})
export class AgendamentoService {
  private http = inject(HttpClient);
  private API_URL_BACKEND = 'http://localhost:8080/trip';
  private API_URL_VIACEP = 'https://viacep.com.br/ws/'

  getAgendamentos(): Observable<ApiResponseTrip> {
    return this.http.get<ApiResponseTrip>(this.API_URL_BACKEND + "/schedules");
  }

  updateAgendamento(agendamento : AgendamentoRequest): Observable<ApiResponseTrip> {
    if (agendamento.id) {
      return this.http.put<any>(`${this.API_URL_BACKEND}/update/${agendamento.id}`, agendamento);
    }
    return this.http.post<ApiResponseTrip>(this.API_URL_BACKEND+"/schedule", agendamento);
    
  }

  excluir(id:number): Observable<any> {
    return this.http.delete<any>(`${this.API_URL_BACKEND}/${id}`);
  }

  getAddress(cep:string): Observable<ApiCepResponse> {
    return this.http.get<ApiCepResponse>(this.API_URL_VIACEP + cep + "/json")
  }

  getDrivers(): Observable<ApiResponseDriver>{
     return this.http.get<ApiResponseDriver>("http://localhost:8080/driver/disponibleDrivers")
  }
  
  getVehicles(): Observable<ApiResponseVehicle>{
    return this.http.get<ApiResponseVehicle>("http://localhost:8080/vehicle/vehicles")
  }

}