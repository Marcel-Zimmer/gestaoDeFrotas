import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AgendamentoService {
  private http = inject(HttpClient);
  // Endpoint para buscar todos os agendamentos (você precisa criar no seu backend)
  private API_URL = 'http://localhost:8080/api/agendamentos';

  getAgendamentos(): Observable<any[]> {
    return this.http.get<any[]>(this.API_URL);
    // Adicione aqui a lógica para enviar o token de autenticação no header!
  }
}