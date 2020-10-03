package br.com.itauunibanco.automovel.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


/**
 * Class resposnsavel pela executa da aplicação
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link AutomoveisApplication}
 * 
 */
   
@EnableResourceServer
@SpringBootApplication
public class Aplication {

	public static void main(String[] args) {
		SpringApplication.run(Aplication.class, args);
	}

}
