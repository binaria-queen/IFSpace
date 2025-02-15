package br.com.ifba.pweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifba.pweb.dto.AulaDto;
import br.com.ifba.pweb.repository.AulaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AulaService {

	@Autowired
	private AulaRepository repository;
	
	public List<AulaDto> listar(){
		return repository.findAll();
	}
	
	public AulaDto alocar(AulaDto aula) {
		log.info("alocar() aula={} ", aula);
		List<AulaDto> aulas = repository.findBySalaIdAndDayWeek(aula.sala().getId(), aula.diaSemana());
		
		for (AulaDto a : aulas) {
			if(a.horarioInicio().equals(aula.horarioInicio())) {
				log.error("alocar() ERRO: Não foi possível alocar aula por sala encontrar-se ocupada.");
				throw new RuntimeException("Atenção! Conflito detectado, pois sala encontra-se ocupada.");
			}
		}
		log.info("alocar() FIM: ");
		return repository.save(aula);
	}
}	
