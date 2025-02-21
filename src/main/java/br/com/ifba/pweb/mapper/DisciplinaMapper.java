package br.com.ifba.pweb.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.ifba.pweb.dto.DisciplinaDto;
import br.com.ifba.pweb.entity.Disciplina;

public class DisciplinaMapper {

	public static List<DisciplinaDto> add(DisciplinaDto disciplina) {
		
		List<DisciplinaDto> dto = new ArrayList<DisciplinaDto>();
		dto.add(disciplina);
		
		return dto;
	}

	public static List<DisciplinaDto> toDTOList(List<Disciplina> all) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<DisciplinaDto> toDto(Optional<Disciplina> byId) {
		// TODO Auto-generated method stub
		return null;
	}

}
