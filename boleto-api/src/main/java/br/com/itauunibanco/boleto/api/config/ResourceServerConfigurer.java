package br.com.itauunibanco.boleto.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


/**
 * Configura o acesso aos recursos.
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link ResourceServerConfigurer}
 * 
 */

@Configuration
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    /**Spring verifica se qualquer request  está atenticado*/
    
    @Override 
    public void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests().anyRequest().authenticated();
    }
}
