package br.com.ifba.pweb.dto;

import jakarta.persistence.Entity;

public record DisciplinaDto(Long id, String nome, String codigoTurma, String nomeProfessor) {
	
	
}
