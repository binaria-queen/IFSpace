import { Component, OnInit } from '@angular/core';
import { AulasService } from '../../services/aulas.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-aulas',
  templateUrl: './aulas.component.html',
  imports: [FormsModule, CommonModule],
  styleUrls: ['./aulas.component.css']
})
export class AulasComponent implements OnInit {
  aulas: any[] = [];
  aulaForm = { id: null, disciplina_id: '', sala_id: '', diaSemana: '', horarioInicio: '', duracao: '' };

  constructor(private aulaService: AulasService) {}

  ngOnInit(): void {
    this.carregarAulas();
  }

  carregarAulas(): void {
    this.aulaService.getAulas().subscribe(data => {
      this.aulas = data;
    });
  }

  salvarAula(): void {
    if (this.aulaForm.id) {
      // Atualizar aula
      this.aulaService.atualizarAula(this.aulaForm.id, this.aulaForm).subscribe(() => {
        this.resetarFormulario();
        this.carregarAulas();
      });
    } else {
      // Criar nova aula
      this.aulaService.criarAula(this.aulaForm).subscribe(() => {
        this.resetarFormulario();
        this.carregarAulas();
      });
    }
  }

  editarAula(aula: any): void {
    this.aulaForm = { ...aula };
  }

  excluirAula(id: number): void {
    if (confirm('Tem certeza que deseja excluir esta aula?')) {
      this.aulaService.excluirAula(id).subscribe(() => {
        this.carregarAulas();
      });
    }
  }

  resetarFormulario(): void {
    this.aulaForm = { id: null, disciplina_id: '', sala_id: '', diaSemana: '', horarioInicio: '', duracao: '' };
  }
}
