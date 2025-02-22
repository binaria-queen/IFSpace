package br.com.ifba.pweb.dto;

import java.time.LocalDateTime;

import br.com.ifba.pweb.entity.Disciplina;
import br.com.ifba.pweb.entity.Sala;

public record AulaDto(Long id, Disciplina disciplina, Sala sala, String diaSemana, LocalDateTime horarioInicio, int duracao) {

}
