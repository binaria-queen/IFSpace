package br.com.ifba.pweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifba.pweb.dto.SalaDto;
import br.com.ifba.pweb.entity.Sala;
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
	
	public Sala alocar(Sala sala) {
		log.info("alocar(): sala={} ", sala);
		//existy para exists
		if(repository.existsByCodigo(sala.getCodigo())) {
			log.error("alocar() ERRO: cadastro não realizado por já existir sala com o código definido.");
			throw new RuntimeException("Código da sala já existe");
		}
		
		log.info("alocar() FIM: ");
		return repository.save(sala);
	}

	public SalaDto editar(SalaDto disciplina) {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(SalaDto dto) {
		// TODO Auto-generated method stub
		
	}
}
