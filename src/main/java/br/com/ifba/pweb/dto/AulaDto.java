package br.com.ifba.pweb.dto;

import java.time.LocalDateTime;

import br.com.ifba.pweb.entity.Disciplina;
import br.com.ifba.pweb.entity.Sala;

//recebendo disciplina e sala id
public record AulaDto(Long id, Long disciplina_id, Long sala_id, String diaSemana, LocalDateTime horarioInicio, int duracao) {

}
