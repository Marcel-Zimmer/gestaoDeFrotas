import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private http = inject(HttpClient);

  private API_URL = 'http://localhost:8080/login/user';

  constructor() { }

  /**
   * Envia as credenciais para a API e retorna um Observable com a resposta.
   * @param credentials - O objeto com email e senha do formulário.
   */
  login(credentials: any): Observable<any> {
    return this.http.post<any>(this.API_URL, credentials);
  }
}