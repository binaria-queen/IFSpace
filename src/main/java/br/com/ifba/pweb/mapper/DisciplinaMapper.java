package br.com.ifba.pweb.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.ifba.pweb.dto.DisciplinaDto;
import br.com.ifba.pweb.entity.Disciplina;

@Component
public class DisciplinaMapper {

	public static List<DisciplinaDto> add(DisciplinaDto disciplina) {
		
		List<DisciplinaDto> dto = new ArrayList<DisciplinaDto>();
		dto.add(disciplina);
		
		return dto;
	}

	public static List<DisciplinaDto> toDTOList(List<Disciplina> all) {
		List<DisciplinaDto> dtoList = new ArrayList<>();
        for (Disciplina disciplina : all) {
            dtoList.add(toDTO(disciplina));
        }
        return dtoList;
	}

	public static Optional<DisciplinaDto> toDto(Optional<Disciplina> byId) {
		return null;
	}

	public static Disciplina toEntity(DisciplinaDto disciplinaDto) {
		return new Disciplina(
            disciplinaDto.id(),
            disciplinaDto.nome(),
            disciplinaDto.codigoTurma(),
            disciplinaDto.nomeProfessor()
        );
	}
	
	public static DisciplinaDto toDTO(Disciplina disciplina) {
        return new DisciplinaDto(
            disciplina.getId(),
            disciplina.getNome(),
            disciplina.getCodigoTurma(),
            disciplina.getNomeProfessor()
        );
    }

}
