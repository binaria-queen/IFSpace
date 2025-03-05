import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  senha: string = '';
  erro: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  fazerLogin(): void {
    this.authService.login(this.email, this.senha).subscribe({
      next: () => {
        const role = this.authService.getUserRole();
        if (role == 'admin' || role == 'professor') {
          this.router.navigate(['/salas']);  // Redireciona para salas
        } else {
          this.erro = 'Acesso negado!';
        }
      },
      error: () => {
        this.erro = 'Email ou senha inválidos!';
      }
    });
  }
}