import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class VeiculoService {
  private http = inject(HttpClient);
  private API_URL = 'http://localhost:8080/api/veiculos'; // Ajuste se necessário

  getVeiculos(): Observable<any[]> {
    return this.http.get<any[]>(this.API_URL);
  }

  salvar(veiculo: any): Observable<any> {
    // Se o veículo já tem ID, é uma atualização (PUT), senão é uma inserção (POST)
    if (veiculo.id) {
      return this.http.put<any>(`${this.API_URL}/${veiculo.id}`, veiculo);
    }
    return this.http.post<any>(this.API_URL, veiculo);
  }

  // Lembre-se que o requisito é INATIVAR, não deletar. O backend deve fazer a lógica.
  excluir(id: number): Observable<any> {
    return this.http.delete<any>(`${this.API_URL}/${id}`);
  }
}