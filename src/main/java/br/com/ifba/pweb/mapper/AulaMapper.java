package br.com.ifba.pweb.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ifba.pweb.dto.AulaDto;
import br.com.ifba.pweb.entity.Aula;


public class AulaMapper {
	public static Aula toEntity(AulaDto dto) {
        Aula aula = new Aula();
        aula.setDiaSemana(dto.diaSemana());
        aula.setDisciplina(dto.disciplina());
        aula.setDuracao(dto.duracao());
        aula.setHorarioInicio(dto.horarioInicio());
        aula.setSala(dto.sala());
        return aula;
    }
    
    public static AulaDto toDTO(Aula aula) {
        return new AulaDto(
        		aula.getId(),
        		aula.getDisciplina(),
        		aula.getSala(),
        		aula.getDiaSemana(),
        		aula.getHorarioInicio(),                                                                                                                    
                aula.getDuracao()
            );
    }
    
    
    public static List<AulaDto> toDTOList(List<Aula> aulas) {
        return aulas.stream().map(AulaMapper::toDTO).collect(Collectors.toList());
    }	
}
