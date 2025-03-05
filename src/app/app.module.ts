import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { AulasComponent } from './features/aulas/aulas.component';
import { DisciplinasComponent } from './features/disciplinas/disciplinas.component';
import { SalasComponent } from './features/salas/salas.component';
import { BrowserModule } from '@angular/platform-browser';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

import { AppRoutingModule } from './app-routing.module'; 
import { CommonModule } from '@angular/common';
import { provideHttpClient } from '@angular/common/http';



@NgModule({
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    AppRoutingModule
  ],
  declarations: [
    AulasComponent,
    DisciplinasComponent,
    SalasComponent,
    AppComponent, 
    LoginComponent
  ],
  exports: [
    AulasComponent,
    DisciplinasComponent,
    SalasComponent
  ],
  providers: [provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }
