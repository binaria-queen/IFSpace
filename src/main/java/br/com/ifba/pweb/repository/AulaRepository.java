package br.com.ifba.pweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifba.pweb.dto.AulaDto;
import br.com.ifba.pweb.entity.Aula;

//estava recebendo AulaDto
public interface AulaRepository extends JpaRepository<Aula, Long> {
	//mudei o método de DayWeek para DiaSemana(como está na entidade)
	List<Aula> findBySalaIdAndDiaSemana(Long salaId, String diaSemana);
}
