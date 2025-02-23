package br.com.ifba.pweb.security.filters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.ifba.pweb.repository.UsuarioRepository;
import br.com.ifba.pweb.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	var token = recuperarToken(request);
		 System.out.println("Token: " + token);
		 if(token!=null) {
			 try {
	                var email = jwtService.obterEmail(token);
	                var usuario = usuarioRepository.findByEmail(email).get(); //.get() para pegar a entidade e não o optional
	                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
	                SecurityContextHolder.getContext().setAuthentication(authentication);            
	            } catch (Exception e) {
	                logger.error("Token inválido", e);
	            }
		 }
		 filterChain.doFilter(request, response);
    }
    
    public String recuperarToken(HttpServletRequest request) {
		 var token = request.getHeader("Authorization");
		 System.out.println("Token em authorization é: " + token);
		        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
		            return null;
		        }
		        return token.replace("Bearer ", "");
	}
    
}
