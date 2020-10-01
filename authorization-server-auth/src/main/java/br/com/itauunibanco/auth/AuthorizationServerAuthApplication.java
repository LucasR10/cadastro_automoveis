package br.com.itauunibanco.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


/**
 * Class Startap. 
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link AuthorizationServerAuthApplication}
 * 
 */


@SpringBootApplication 
@EnableAuthorizationServer 
@EnableResourceServer
public class AuthorizationServerAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerAuthApplication.class, args);
	}

}
