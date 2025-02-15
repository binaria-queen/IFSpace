package br.com.ifba.pweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifba.pweb.dto.AulaDto;

public interface AulaRepository extends JpaRepository<AulaDto, Long> {
	List<AulaDto> findBySalaIdAndDayWeek(Long salaId, String diaSemana);
}
