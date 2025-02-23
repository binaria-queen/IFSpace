package br.com.ifba.pweb.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifba.pweb.entity.Aula;

//estava recebendo AulaDto

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
	//mudei o método de DayWeek para DiaSemana(como está na entidade)
	List<Aula> findBySalaIdAndDiaSemanaAndHorarioInicio(Long salaId, String diaSemana, LocalDateTime horarioInicio);
	List<Aula> findBySalaIdAndDiaSemana(Long salaId, String diaSemana);
}
