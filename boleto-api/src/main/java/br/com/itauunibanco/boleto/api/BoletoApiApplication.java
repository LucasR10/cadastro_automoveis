package br.com.itauunibanco.boleto.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;



/**
 * Recurso que retorna os dados para navegador . 
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link Boleto}
 * 
 */

@SpringBootApplication @EnableResourceServer
public class BoletoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoletoApiApplication.class, args);
	}

}
