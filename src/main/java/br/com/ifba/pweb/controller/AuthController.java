package br.com.ifba.pweb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.pweb.entity.Usuario;
import br.com.ifba.pweb.repository.UsuarioRepository;
import br.com.ifba.pweb.service.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/login")
	public Map<String, String> login(@RequestBody Usuario usuario) {
		Usuario user = usuarioRepository.findByEmail(usuario.getEmail())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
		
		if (!user.getSenha().equals(usuario.getSenha())) {
			throw new RuntimeException("Senha inválida!");
		}
		
		String token = jwtService.gerarToken(user.getEmail(), user.getRole());
		Map<String, String> resposta = new HashMap<>();
		
		resposta.put("token", token);
		return resposta;
	}
}
