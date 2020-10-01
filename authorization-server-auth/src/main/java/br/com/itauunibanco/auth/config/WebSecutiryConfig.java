package br.com.itauunibanco.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.itauunibanco.auth.AuthorizationServerAuthApplication;

/**
* Class para configurar seguranca. 
* 
* @author Lucas Carvalho
* @author https://www.linkedin.com/in/lucas-carvalho-793609134/
* @version 1.0
* @since 1.0 {@link AuthorizationServerAuthApplication}
* 
*/

@Configuration
public class WebSecutiryConfig  extends WebSecurityConfigurerAdapter{

        /**  Configuracao em memoris do usuários para facilitar a configuração mais pode ser via banco de dados*/
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	   auth.inMemoryAuthentication()
    	      .passwordEncoder(passwordEncoder())
              .withUser("itau")
              .password( passwordEncoder().encode("12345") )
              .roles("USER").
    	   and()
    	      .passwordEncoder(passwordEncoder())
              .withUser("admin")
              .password( passwordEncoder().encode("12345"))
              .roles("ADMIN");
        }
    
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
	return super.authenticationManager();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
	return super.userDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }
    
}
