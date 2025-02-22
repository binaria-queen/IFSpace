package br.com.ifba.pweb.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ifba.pweb.dto.AulaDto;
import br.com.ifba.pweb.entity.Aula;
import br.com.ifba.pweb.repository.DisciplinaRepository;
import br.com.ifba.pweb.repository.SalaRepository;

@Component
public class AulaMapper {
	
	private final DisciplinaRepository disciplinaRepository;
    private final SalaRepository salaRepository;

    // Construtor para injeção de dependências
    public AulaMapper(DisciplinaRepository disciplinaRepository, SalaRepository salaRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.salaRepository = salaRepository;
    }

	
	public Aula toEntity(AulaDto dto) {
        Aula aula = new Aula();
        aula.setDiaSemana(dto.diaSemana());
        aula.setDisciplina(disciplinaRepository.findById(dto.disciplina_id()). //usando repository para pegar o id e transformar em objeto
        		orElseThrow(() -> new RuntimeException("Disciplina não encontrada com o ID: " + dto.disciplina_id()))); //orElseThrow para não enviar um <Optional> para a entidade
        aula.setDuracao(dto.duracao());
        aula.setHorarioInicio(dto.horarioInicio()); //usando repository para pegar o id e transformar em objeto
        aula.setSala(salaRepository.findById(dto.sala_id()).
        		orElseThrow(() -> new RuntimeException("Sala não encontrada com o ID: " + dto.sala_id())));
        return aula;
    }
    
    public static AulaDto toDTO(Aula aula) {
        return new AulaDto(
        		aula.getId(),
        		aula.getDisciplina().getId(), //mudança para pegar o id e não o objeto
        		aula.getSala().getId(), //mudança para pegar o id  e não o objeto
        		aula.getDiaSemana(),
        		aula.getHorarioInicio(),                                                                                                                    
                aula.getDuracao()
            );
    }
    
    
    public static List<AulaDto> toDTOList(List<Aula> aulas) {
        return aulas.stream().map(AulaMapper::toDTO).collect(Collectors.toList());
    }	
}
