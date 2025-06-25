
import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponseDriver, ApiResponseRefueling, ApiResponseVehicle} from '../../../models/api/backend/api.response.model'
import { Refueling } from '../../../models/refueling/refueling.mdel';

@Injectable({
  providedIn: 'root'
})
export class RefuelingService {
  private http = inject(HttpClient);
  private API_URL_BACKEND = 'http://localhost:8080/refueling';

  getRefuelings(): Observable<ApiResponseRefueling> {
    return this.http.get<ApiResponseRefueling>(this.API_URL_BACKEND + "/refuelings");
  }


  getDrivers(): Observable<ApiResponseDriver>{
     return this.http.get<ApiResponseDriver>("http://localhost:8080/driver/drivers")
  }
  
  getVehicles(): Observable<ApiResponseVehicle>{
    return this.http.get<ApiResponseVehicle>("http://localhost:8080/vehicle/vehicles")
  }

  createRefueling(refueling:Refueling):Observable<any>{
    return this.http.post<ApiResponseVehicle>(this.API_URL_BACKEND+"/register", refueling);
  }

  excluir(id: number): Observable<any> {
    return this.http.delete<any>(`${this.API_URL_BACKEND}/${id}`);
  }

}