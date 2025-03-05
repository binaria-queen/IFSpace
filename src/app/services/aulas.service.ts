import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AulasService {
  private apiUrl = 'http://localhost:3000/aulas'; 

  constructor(private http: HttpClient) {}

  // Buscar todas as aulas
  getAulas(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  // Buscar uma aula pelo ID
  getAulaById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  // Criar uma nova aula
  criarAula(aula: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, aula);
  }

  // Atualizar uma aula existente
  atualizarAula(id: number, aula: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, aula);
  }

  // Excluir uma aula
  excluirAula(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
