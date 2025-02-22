package br.com.ifba.pweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifba.pweb.entity.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long>{
	//mudei de existy para exists
	boolean existsByCodigo(String codigo);

}
