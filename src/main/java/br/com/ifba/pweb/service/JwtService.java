package br.com.ifba.pweb.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final SecretKey keySecret = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long expiracao = 86400000;

	public String gerarToken(String email, String role) {
		return Jwts.builder()
			.setSubject(email)
			.claim("role", role)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + expiracao))
			.signWith(keySecret)
			.compact();
	}
	
	public String obterEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(keySecret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
	
	public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(keySecret).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
	
}
