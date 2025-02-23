package br.com.ifba.pweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true) 
public class IfspaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfspaceApplication.class, args);
	}

}
