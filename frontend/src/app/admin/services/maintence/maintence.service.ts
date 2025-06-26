import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponseMaintence } from '../../../models/api/backend/api.response.model'; // Supondo um ApiResponse genérico
import { Maintence } from '../../../models/maintence/maintence.model'; // Crie este modelo

@Injectable({
  providedIn: 'root'
})
export class MaintenanceService {
  private http = inject(HttpClient);
  private API_URL = 'http://localhost:8080/maintenance'; // Exemplo de URL base da API

  // Busca todas as manutenções
  getMaintenances(): Observable<ApiResponseMaintence> {
    return this.http.get<ApiResponseMaintence>(this.API_URL +"/maintenances");
  }

  // Cria um novo registro de manutenção
  createMaintenance(maintenanceData: Maintence): Observable<Maintence> {
    return this.http.post<Maintence>(`${this.API_URL}/register`, maintenanceData);
  }

  // Deleta um registro de manutenção
  deleteMaintenance(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }
  
  // Opcional: método de update
  updateMaintenance(id: number, maintenanceData: Maintence): Observable<Maintence> {
    return this.http.put<Maintence>(`${this.API_URL}/${id}`, maintenanceData);
  }
}