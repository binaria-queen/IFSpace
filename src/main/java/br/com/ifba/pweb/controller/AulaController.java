package br.com.ifba.pweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.pweb.dto.AulaDto;
import br.com.ifba.pweb.service.AulaService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/aulas")
@RestController
public class AulaController {

	@Autowired
	private AulaService service;
	
	@GetMapping("/listar")
	public List<AulaDto> listar(){
		log.info("listar() INICIO: ");
		return service.listar();
	}
	
	@PostMapping("/alocar")
	public AulaDto alocar(@RequestBody AulaDto aula) {
		log.info("alocar() INICIO: ");
		return service.alocar(aula);
	}
	
	@PutMapping("/editar")
	public AulaDto editar(AulaDto aula) {
		log.info("editar() INICIO: ");
		return service.editar(aula);
	}
	
	@DeleteMapping("/excluir")
	public void excluir(AulaDto dto) {
		log.info("excluir() INICIO: ");
		service.excluir(dto);
	}
	
	
}
