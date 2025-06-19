import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponseVehicle } from '../../../models/api/api.response.model'
import { VehicleRequest } from '../../../models/api/api.request.model'

@Injectable({ providedIn: 'root' })
export class VeiculoService {

  private http = inject(HttpClient);
  private API_URL = 'http://localhost:8080/vehicle'; 

  getVeiculos(): Observable<ApiResponseVehicle> {
    return this.http.get<ApiResponseVehicle>(this.API_URL + "/vehicles");
  }


  updateVehicle(vehicle: VehicleRequest): Observable<any> {
    // Se o veículo já tem ID, é uma atualização (PUT), senão é uma inserção (POST)
    if (vehicle.id) {
      console.log(vehicle)
      return this.http.put<any>(`${this.API_URL}/update/${vehicle.id}`, vehicle);
    }
    return this.http.post<ApiResponseVehicle>(this.API_URL+"/register", vehicle);
  }

  // Lembre-se que o requisito é INATIVAR, não deletar. O backend deve fazer a lógica.
  excluir(id: number): Observable<any> {
    return this.http.delete<any>(`${this.API_URL}/${id}`);
  }
}