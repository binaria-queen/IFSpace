import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SalasComponent } from './features/salas/salas.component';
import { DisciplinasComponent } from './features/disciplinas/disciplinas.component';
import { AulasComponent } from './features/aulas/aulas.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'salas', component: SalasComponent, canActivate: [AuthGuard] },
  { path: 'disciplinas', component: DisciplinasComponent, canActivate: [AuthGuard] },
  { path: 'aulas', component: AulasComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '/login' }  // Qualquer rota inválida redireciona para salas
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }