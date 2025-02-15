package br.com.ifba.pweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.pweb.dto.SalaDto;
import br.com.ifba.pweb.entity.Sala;
import br.com.ifba.pweb.mapper.SalaMapper;
import br.com.ifba.pweb.service.SalaService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/salas")
@RestController
public class SalaController {

	@Autowired
	private SalaService service;
	
	@GetMapping("/listar")
	public List<SalaDto> listar() {
		log.info("listar() INICIO: ");
		List<SalaDto> salas = SalaMapper.toDTOList(service.listar());
		return salas;
	}
	
	@PostMapping("/alocar")
	public SalaDto alocar(@RequestBody SalaDto salaDto) {
		log.info("alocar() INICIO: ");
		Sala sala = SalaMapper.toEntity(salaDto);
		return SalaMapper.toDTO(service.alocar(sala));
	}
	
	
}
