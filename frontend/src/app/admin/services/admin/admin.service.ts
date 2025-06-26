import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponseDriver, ApiResponseTrip, ApiResponseVehicle} from '../../../models/api/backend/api.response.model'
import { ApiCepResponse } from '../../../models/api/viacep/viacep.request.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private http = inject(HttpClient);
  private API_URL_BACKEND = 'http://localhost:8080/administrador';
  private API_URL_VIACEP = 'https://viacep.com.br/ws/'

  getAdministrators(): Observable<ApiResponseDriver> {
    return this.http.get<ApiResponseDriver>(this.API_URL_BACKEND + "/administradores");
  }

  getAddress(cep:string): Observable<ApiCepResponse> {
    return this.http.get<ApiCepResponse>(this.API_URL_VIACEP + cep + "/json")
  }

  updateAdministrator(driver:any): Observable<ApiResponseDriver>{
    console.log(driver)
    if (driver.administradorId) {
      return this.http.put<ApiResponseDriver>(`${this.API_URL_BACKEND}/update/${driver.administradorId}`, driver);
    }
    return this.http.post<ApiResponseDriver>(this.API_URL_BACKEND+"/register", driver);
  }
  
  excludeAdministrator(id:number): Observable<ApiResponseDriver>{
    return this.http.delete<ApiResponseDriver>(this.API_URL_BACKEND + "/" + id);
  }
}
