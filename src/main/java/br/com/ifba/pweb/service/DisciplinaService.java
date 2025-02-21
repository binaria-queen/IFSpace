package br.com.ifba.pweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifba.pweb.dto.DisciplinaDto;
import br.com.ifba.pweb.mapper.DisciplinaMapper;
import br.com.ifba.pweb.repository.DisciplinaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository repository;
	
	@Autowired
	private DisciplinaMapper mapper;
	
	public List<DisciplinaDto> listar(){
		return mapper.toDTOList(repository.findAll());
	}
	
	public Optional<DisciplinaDto> consultarPorId(Long id) {
		return mapper.toDto(repository.findById(id));
		
	}
	
	public DisciplinaDto cadastrar(DisciplinaDto disciplina) {
		return null;
	}

	public DisciplinaDto editar(DisciplinaDto disciplina) {
		return null;
	}

	public void excluir(DisciplinaDto dto) {
		
	}

}
