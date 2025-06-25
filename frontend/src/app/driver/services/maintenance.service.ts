import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponseDriver } from '../../models/api/backend/api.response.model';

@Injectable({
  providedIn: 'root'
})
export class MaintenanceService {

  private http = inject(HttpClient);
  private API_URL_BACKEND = 'http://localhost:8080/administrador';
  private API_URL_VIACEP = 'https://viacep.com.br/ws/'

  createOcorrencia(teste:any): Observable<ApiResponseDriver> {
    return this.http.get<ApiResponseDriver>(this.API_URL_BACKEND + "/administradores");
  }  
}
