import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // Injeta o HttpClient para fazer as requisições
  private http = inject(HttpClient);

  // A URL do seu endpoint de login no Spring Boot
  // Dica: No futuro, é uma boa prática colocar isso nos arquivos de environment.
  private API_URL = 'http://localhost:8080/login/user';

  constructor() { }

  /**
   * Envia as credenciais para a API e retorna um Observable com a resposta.
   * @param credentials - O objeto com email e senha do formulário.
   */
  login(credentials: any): Observable<any> {
    // Usamos o método post, enviando a URL e o corpo da requisição (as credenciais)
    return this.http.post<any>(this.API_URL, credentials);
  }
}