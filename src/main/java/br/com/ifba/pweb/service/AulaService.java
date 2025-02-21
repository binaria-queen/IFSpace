package br.com.ifba.pweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifba.pweb.dto.AulaDto;
import br.com.ifba.pweb.mapper.AulaMapper;
import br.com.ifba.pweb.repository.AulaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AulaService {

	@Autowired
	private AulaRepository repository;
	
	public List<AulaDto> listar(){
		return AulaMapper.toDTOList(repository.findAll());
	}
	
	public AulaDto alocar(AulaDto aula) {
		log.info("alocar() aula={} ", aula);
		//AndDayWeek para AndDiaSemana
		List<AulaDto> aulas = AulaMapper.toDTOList(repository.findBySalaIdAndDiaSemana(aula.sala().getId(), aula.diaSemana()));
		
		for (AulaDto a : aulas) {
			if(a.horarioInicio().equals(aula.horarioInicio())) {
				log.error("alocar() ERRO: Não foi possível alocar aula por sala encontrar-se ocupada.");
				throw new RuntimeException("Atenção! Conflito detectado, pois sala encontra-se ocupada.");
			}
		}
		log.info("alocar() FIM: ");
		return AulaMapper.toDTO(repository.save(AulaMapper.toEntity(aula)));
	}

	public AulaDto editar(AulaDto aula) {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(AulaDto dto) {
		// TODO Auto-generated method stub
		
	}
}	
