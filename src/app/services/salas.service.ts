import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SalasService {
  private apiUrl = 'http://localhost:3000/salas'; 

  constructor(private http: HttpClient) {}

  getSalas(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
