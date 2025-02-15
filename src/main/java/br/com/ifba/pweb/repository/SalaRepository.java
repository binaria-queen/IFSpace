package br.com.ifba.pweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifba.pweb.entity.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long>{
	boolean existyByCodigo(String codigo);
}
