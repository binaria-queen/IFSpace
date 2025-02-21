package br.com.ifba.pweb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.pweb.dto.DisciplinaDto;
import br.com.ifba.pweb.service.DisciplinaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/disciplina")
@RestController
public class DisciplinaController {

	@Autowired
	private DisciplinaService service;
	
	@GetMapping("/listar")
	public List<DisciplinaDto> listar(){
		log.info("listar() INICIO: ");
		return service.listar();
	}
	
	@GetMapping("/consultar/{id}")
	public Optional<DisciplinaDto> consultarPorId(@RequestParam Long id){
		log.info("consultarPorId() INICIO: ");
		return service.consultarPorId(id);
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<DisciplinaDto> cadastrar(@RequestBody DisciplinaDto dto){
		log.info("cadastrar() INICIO: ");
		DisciplinaDto response = service.cadastrar(dto);
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/editar")
	public DisciplinaDto editar(DisciplinaDto disciplina) {
		log.info("editar() INICIO: ");
		return service.editar(disciplina);
	}
	
	@DeleteMapping("/excluir")
	public void excluir(DisciplinaDto dto) {
		log.info("excluir() INICIO: ");
		service.excluir(dto);
	}
}
