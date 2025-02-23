package br.com.ifba.pweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifba.pweb.dto.SalaDto;
import br.com.ifba.pweb.entity.Sala;
import br.com.ifba.pweb.mapper.SalaMapper;
import br.com.ifba.pweb.repository.SalaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SalaService {

	@Autowired
	private SalaRepository repository;
	
	public List<Sala> listar(){
		log.info("listar() FIM: ");
		return repository.findAll();
	}
	
	public SalaDto alocar(SalaDto sala) {
		log.info("alocar(): sala={} ", sala);
		//existy para exists
		if(repository.existsByCodigo(sala.codigo())) {
			log.error("alocar() ERRO: cadastro não realizado por já existir sala com o código definido.");
			throw new RuntimeException("Código da sala já existe");
		}
		
		log.info("alocar() FIM: ");
		return SalaMapper.toDTO(repository.save(SalaMapper.toEntity(sala)));
	}

	public SalaDto editar(SalaDto salaDto) {
		log.info("editar(): salaDto={} ", salaDto);
        
        Optional<Sala> salaOptional = repository.findById(salaDto.id());
        if (salaOptional.isPresent()) {
        	Sala sala = salaOptional.get();
            
            sala.setCodigo(salaDto.codigo());
            sala.setNome(salaDto.nome());
            
            Sala salaAtualizada = repository.save(sala);
            log.info("editar() FIM: sala atualizada com sucesso.");
            return SalaMapper.toDTO(salaAtualizada);
        } else {
            log.error("editar() ERRO: Sala não encontrada com o ID {}", salaDto.id());
            throw new RuntimeException("Sala não encontrada com o ID " + salaDto.id());
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
