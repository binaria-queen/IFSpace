import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:3000/auth/login'; 

  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, senha: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { email, senha }).pipe(
      tap(response => {
        if (response && response.token) {
          localStorage.setItem('token', response.token);
          localStorage.setItem('role', response.role); // Armazena a função do usuário (admin, professor, etc.)
        }
      })
    );
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token'); // Retorna true se o token existir
  }

  getUserRole(): string | null {
    return localStorage.getItem('role');
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    this.router.navigate(['/login']);
  }
}
