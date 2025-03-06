package br.com.ifba.pweb.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
		    
	    if (existeConflitoNoMesmoDiaSemana(aulaDto)) {
	        log.error("alocar() ERRO: Conflito de horário no mesmo dia da semana.");
	        throw new ConflitoHorarioException("Já existe uma aula no mesmo dia da semana e horário.");
	    }

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
            
            if (existeConflitoNoMesmoDiaSemana(aulaDto)) {
                log.error("editar() ERRO: Conflito de horário no mesmo dia da semana.");
                throw new ConflitoHorarioException("Já existe uma aula no mesmo dia da semana e horário.");
            }
            
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
    
    private boolean existeConflitoNoMesmoDiaSemana(AulaDto aulaDto) {
        List<Aula> aulasNoMesmoDiaSemana = repository.findByDiaSemanaAndSalaId(aulaDto.diaSemana(), aulaDto.sala_id());
        int inicioNova = toMinutes(aulaDto.horarioInicio().toLocalTime());
        int fimNova = inicioNova + aulaDto.duracao();

        for (Aula aulaExistente : aulasNoMesmoDiaSemana) {
            int inicioExistente = toMinutes(aulaExistente.getHorarioInicio().toLocalTime());
            int fimExistente = inicioExistente + aulaExistente.getDuracao();
            if (intervalosSeSobrepoem(inicioNova, fimNova, inicioExistente, fimExistente)) {
                return true;
            }
        }
        return false;
    }

    private int toMinutes(LocalTime time) {
        return time.getHour() * 60 + time.getMinute();
    }

    private boolean intervalosSeSobrepoem(int inicio1, int fim1, int inicio2, int fim2) {
        List<int[]> segmentos1 = splitInterval(inicio1, fim1);
        List<int[]> segmentos2 = splitInterval(inicio2, fim2);

        for (int[] seg1 : segmentos1) {
            for (int[] seg2 : segmentos2) {
                if (seg1[0] < seg2[1] && seg1[1] > seg2[0]) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<int[]> splitInterval(int inicio, int fim) {
        List<int[]> segmentos = new ArrayList<>();
        if (fim <= 1440) {
            segmentos.add(new int[]{inicio, fim});
        } else {
        	segmentos.add(new int[]{inicio, 1440});
            segmentos.add(new int[]{0, fim - 1440});
        }
        return segmentos;
    }
	
}	
