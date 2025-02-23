package br.com.ifba.pweb.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifba.pweb.dto.AulaDto;
import br.com.ifba.pweb.entity.Aula;
import br.com.ifba.pweb.entity.Disciplina;
import br.com.ifba.pweb.entity.Sala;
import br.com.ifba.pweb.exception.ConflitoHorarioException;
import br.com.ifba.pweb.exception.DuracaoInvalidaException;
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
	private SalaRepository salaRepository;

	
	public List<AulaDto> listar(){
		return AulaMapper.toDTOList(repository.findAll());
	}
	
	public AulaDto alocar(AulaDto aulaDto) {
		log.info("alocar() aula={} ", aulaDto);

	    if (aulaDto.duracao() % 50 != 0) {
	        log.error("alocar() ERRO: A duração da aula deve ser um múltiplo de 50 minutos.");
	        throw new DuracaoInvalidaException("A duração da aula deve ser um múltiplo de 50 minutos.");
	    }
	    
	    Disciplina disciplina = disciplinaRepository.findById(aulaDto.disciplina_id())
		        .orElseThrow(() -> new RuntimeException("Disciplina não encontrada!"));
		    Sala sala = salaRepository.findById(aulaDto.sala_id())
		        .orElseThrow(() -> new RuntimeException("Sala não encontrada!"));

	    List<Aula> aulasNaSala = repository.findBySalaIdAndDiaSemana(
	        aulaDto.sala_id(), 
	        aulaDto.diaSemana()
	    );
	    
	    LocalDateTime inicioNovaAula = aulaDto.horarioInicio();
	    LocalDateTime fimNovaAula = inicioNovaAula.plusMinutes(aulaDto.duracao());
	    

	    for (Aula aulaExistente : aulasNaSala) {
	        LocalDateTime inicioExistente = aulaExistente.getHorarioInicio();
	        LocalDateTime fimExistente = inicioExistente.plusMinutes(aulaExistente.getDuracao());

	        if (inicioNovaAula.isBefore(fimExistente) && fimNovaAula.isAfter(inicioExistente)) {
	            log.error("alocar() ERRO: Conflito de horário na sala {}", aulaDto.sala_id());
	            throw new ConflitoHorarioException("A sala já está ocupada neste horário");
	        }
	    }
		
		log.info("alocar() FIM: ");
		AulaMapper mapper = new AulaMapper(disciplinaRepository, salaRepository); //tendo que instanciar agora que toEntity não é estático
		return AulaMapper.toDTO(repository.save(mapper.toEntity(aulaDto)));
	}
	
	public AulaDto editar(AulaDto aulaDto) {
        log.info("editar() aulaDto={} ", aulaDto);
        
        if (aulaDto.duracao() % 50 != 0) {
            log.error("editar() ERRO: A duração da aula deve ser um múltiplo de 50 minutos.");
            throw new DuracaoInvalidaException("A duração da aula deve ser um múltiplo de 50 minutos.");
        }

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
            
            List<Aula> aulasNaSala = repository.findBySalaIdAndDiaSemana(
                    aulaDto.sala_id(), 
                    aulaDto.diaSemana()
            );
            
            //não verificar a própria aula	
            aulasNaSala.removeIf(a -> a.getId().equals(aulaDto.id()));
            
            LocalDateTime inicioNovaAula = aulaDto.horarioInicio();
            LocalDateTime fimNovaAula = inicioNovaAula.plusMinutes(aulaDto.duracao());

            for (Aula aulaExistente : aulasNaSala) {
                LocalDateTime inicioExistente = aulaExistente.getHorarioInicio();
                LocalDateTime fimExistente = inicioExistente.plusMinutes(aulaExistente.getDuracao());

                if (inicioNovaAula.isBefore(fimExistente) && fimNovaAula.isAfter(inicioExistente)) {
                    log.error("editar() ERRO: Conflito de horário na sala {}", aulaDto.sala_id());
                    throw new ConflitoHorarioException("A sala já está ocupada neste horário");
                }
            }
            
            Aula aulaAtualizada = repository.save(aula);
            log.info("editar() FIM: aula atualizada com sucesso.");
            return AulaMapper.toDTO(aulaAtualizada);
        } else {
            log.error("editar() ERRO: Aula não encontrada com o ID {}", aulaDto.id());
            throw new RuntimeException("Aula não encontrada com o ID " + aulaDto.id());
        }
        
        
    }
	
	
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
