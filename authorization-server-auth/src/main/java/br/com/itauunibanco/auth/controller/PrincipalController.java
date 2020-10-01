package br.com.itauunibanco.auth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller para devolver as configurações dos usuários logado.
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link PrincipalController}
 * 
 */

@RestController
@RequestMapping("/oauth")
public class PrincipalController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
	return user;
    }

}
