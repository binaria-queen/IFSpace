package br.com.ifba.pweb.dto;

import jakarta.persistence.Entity;

public record UsuarioDto(Long id, String email, String senha, String role){
	
	
}