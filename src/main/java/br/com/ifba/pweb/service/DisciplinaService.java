package br.com.ifba.pweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifba.pweb.dto.DisciplinaDto;
import br.com.ifba.pweb.entity.Disciplina;
import br.com.ifba.pweb.entity.Sala;
import br.com.ifba.pweb.mapper.DisciplinaMapper;
import br.com.ifba.pweb.mapper.SalaMapper;
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
		log.info("listar() FIM: ");
	    return mapper.toDTOList(repository.findAll());
	}
	
	public DisciplinaDto consultarPorId(Long id) {
		log.info("consultarPorId(): id={} ", id);
		
		Optional<Disciplina> disciplinaOptional = repository.findById(id);
		
		if (disciplinaOptional.isPresent()) {
			Disciplina disciplina = disciplinaOptional.get();    
			log.info("consultarPorId() FIM: Id localizado com sucesso.");
			return DisciplinaMapper.toDTO(disciplinaOptional.get());
        } else {
            log.error("consultarPorId() ERRO: Disciplina não encontrada com o ID {}", id);
            throw new RuntimeException("Disciplina não encontrada com o ID " + id);
        }
		
	}
	
	public DisciplinaDto cadastrar(DisciplinaDto disciplinaDto) {
		log.info("cadastrar(): disciplinaDto={} ", disciplinaDto);
      
        Disciplina disciplina = mapper.toEntity(disciplinaDto);
       
        Disciplina disciplinaSalva = repository.save(disciplina);
        log.info("cadastrar() FIM: disciplina cadastrada com sucesso.");
        return mapper.toDTO(disciplinaSalva); //como os métodos de mapper são estáticos não precisamos de uma instância(DisciplinaMapper.toDto)
	}

	public DisciplinaDto editar(DisciplinaDto disciplinaDto) {
		log.info("editar(): disciplinaDto={} ", disciplinaDto);
       
        Optional<Disciplina> disciplinaOptional = repository.findById(disciplinaDto.id());
        if (disciplinaOptional.isPresent()) {            
            Disciplina disciplina = mapper.toEntity(disciplinaDto);                            
            Disciplina disciplinaAtualizada = repository.save(disciplina);
            log.info("editar() FIM: disciplina atualizada com sucesso.");
            return mapper.toDTO(disciplinaAtualizada);
        } else {
            log.error("editar() ERRO: Disciplina não encontrada com o ID {}", disciplinaDto.id());
            throw new RuntimeException("Disciplina não encontrada com o ID " + disciplinaDto.id());
        }
    }

	public void excluir(Long id) {
		log.info("excluir(): id={} ", id);
              
        if (repository.existsById(id)) {           
            repository.deleteById(id);
            log.info("excluir() FIM: sala removida com sucesso.");
        } else {
            log.error("excluir() ERRO: Sala não encontrada com o ID {}", id);
            throw new RuntimeException("Sala não encontrada com o ID " + id);
        }
	}

}
