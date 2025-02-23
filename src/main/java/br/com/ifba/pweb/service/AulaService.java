package br.com.ifba.pweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifba.pweb.dto.AulaDto;
import br.com.ifba.pweb.entity.Aula;
import br.com.ifba.pweb.entity.Disciplina;
import br.com.ifba.pweb.entity.Sala;
import br.com.ifba.pweb.mapper.AulaMapper;
import br.com.ifba.pweb.repository.AulaRepository;
import br.com.ifba.pweb.repository.DisciplinaRepository;
import br.com.ifba.pweb.repository.SalaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AulaService {

	@Autowired
	private AulaRepository repository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private AulaRepository aulaRepository;
	@Autowired
	private SalaRepository salaRepository;

	
	public List<AulaDto> listar(){
		return AulaMapper.toDTOList(repository.findAll());
	}
	
	public AulaDto alocar(AulaDto aula) {
		log.info("alocar() aula={} ", aula);	
		//AndDayWeek para AndDiaSemana
		List<AulaDto> aulas;		
		aulas = AulaMapper.toDTOList(repository.findBySalaIdAndDiaSemana(aula.sala_id(),aula.diaSemana()));		 		
			
		for (AulaDto a : aulas) {
			if(a.horarioInicio().equals(aula.horarioInicio())) {
				log.error("alocar() ERRO: Não foi possível alocar aula por sala encontrar-se ocupada.");
				throw new RuntimeException("Atenção! Conflito detectado, pois sala encontra-se ocupada.");
			}
		}
		
		Disciplina disciplina = disciplinaRepository.findById(aula.disciplina_id())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada!"));

        // Busca a Sala pelo ID
        Sala sala = salaRepository.findById(aula.sala_id())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada!"));
        
		
		log.info("alocar() FIM: ");
		AulaMapper mapper = new AulaMapper(disciplinaRepository, salaRepository); //tendo que instanciar agora que toEntity não é estático
		return AulaMapper.toDTO(repository.save(mapper.toEntity(aula)));
	}
	
	public AulaDto editar(AulaDto aulaDto) {
        log.info("editar() aulaDto={} ", aulaDto);      
        Optional<Aula> aulaOptional = repository.findById(aulaDto.id());
        if (aulaOptional.isPresent()) {            
            Aula aula = aulaOptional.get();
            
        	aula.setDisciplina(disciplinaRepository.findById(aulaDto.disciplina_id())
                    .orElseThrow(() -> new RuntimeException("Disciplina não encontrada!")));
            aula.setSala(salaRepository.findById(aulaDto.sala_id())
                    .orElseThrow(() -> new RuntimeException("Sala não encontrada!")));
            aula.setDiaSemana(aulaDto.diaSemana());
            aula.setHorarioInicio(aulaDto.horarioInicio());
            aula.setDuracao(aulaDto.duracao());
            
            Aula aulaAtualizada = repository.save(aula);
            log.info("editar() FIM: aula atualizada com sucesso.");
            return AulaMapper.toDTO(aulaAtualizada);
        } else {
            log.error("editar() ERRO: Aula não encontrada com o ID {}", aulaDto.id());
            throw new RuntimeException("Aula não encontrada com o ID " + aulaDto.id());
        }
    }
	
	/*
    public void excluir(AulaDto aulaDto) {
        log.info("excluir() aulaDto={} ", aulaDto);
        Optional<Aula> aulaOptional = repository.findById(aulaDto.id());
        if (aulaOptional.isPresent()) {
            repository.delete(aulaOptional.get());
        } else {
            log.error("excluir() ERRO: Aula não encontrada com o ID {}", aulaDto.id());
            throw new RuntimeException("Aula não encontrada com o ID " + aulaDto.id());
        }
    }
    */
	
    public void excluir(Long id) {
        log.info("excluir() id={} ", id);
     
        Optional<Aula> aulaOptional = repository.findById(id);
        if (aulaOptional.isPresent()) {            
            repository.delete(aulaOptional.get());
            log.info("excluir() FIM: aula removida com sucesso.");
        } else {
            log.error("excluir() ERRO: Aula não encontrada com o ID {}", id);
            throw new RuntimeException("Aula não encontrada com o ID " + id);
        }
    }
	

}	
