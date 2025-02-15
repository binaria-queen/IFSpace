package br.com.ifba.pweb.dto;

import java.time.LocalTime;

import br.com.ifba.pweb.entity.Disciplina;
import br.com.ifba.pweb.entity.Sala;
import jakarta.persistence.Entity;

public record AulaDto(Disciplina disciplina, Sala sala, String diaSemana, LocalTime horarioInicio, int duracao) {

}
