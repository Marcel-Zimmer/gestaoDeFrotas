import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponseDriver, ApiResponseTrip, ApiResponseVehicle} from '../../../models/api/backend/api.response.model'
import { ApiCepResponse } from '../../../models/api/viacep/viacep.request.model';
import { Driver } from '../../../models/driver/driver.model';

@Injectable({
  providedIn: 'root'
})
export class DriversService {

  private http = inject(HttpClient);
  private API_URL_BACKEND = 'http://localhost:8080/driver';
  private API_URL_VIACEP = 'https://viacep.com.br/ws/'

  getDrivers(): Observable<ApiResponseDriver> {
    return this.http.get<ApiResponseDriver>(this.API_URL_BACKEND + "/drivers");
  }

  getAddress(cep:string): Observable<ApiCepResponse> {
    return this.http.get<ApiCepResponse>(this.API_URL_VIACEP + cep + "/json")
  }

  updateDriver(driver:Driver): Observable<ApiResponseDriver>{
    if (driver.driverId) {
      return this.http.put<ApiResponseDriver>(`${this.API_URL_BACKEND}/update/${driver.driverId}`, driver);
    }
    return this.http.post<ApiResponseDriver>(this.API_URL_BACKEND+"/register", driver);
  }
  
  excludeDriver(teste:any): Observable<ApiResponseDriver>{
    return this.http.get<ApiResponseDriver>(this.API_URL_BACKEND + "/drivers");
  }
}
