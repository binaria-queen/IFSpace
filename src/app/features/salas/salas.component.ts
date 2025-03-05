import { Component, OnInit } from '@angular/core';
import { SalasService } from '../../services/salas.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-salas',
  templateUrl: './salas.component.html',
  imports: [CommonModule]
})
export class SalasComponent implements OnInit {
  salas: any[] = [];

  constructor(private salasService: SalasService) {}

  ngOnInit(): void {
    this.salasService.getSalas().subscribe(data => {
      this.salas = data;
    });
  }
}
