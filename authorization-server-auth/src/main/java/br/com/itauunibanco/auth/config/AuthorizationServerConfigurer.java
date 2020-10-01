package br.com.itauunibanco.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;


/**
* Class para configurar do aturizador service. 
* 
* @author Lucas Carvalho
* @author https://www.linkedin.com/in/lucas-carvalho-793609134/
* @version 1.0
* @since 1.0 {@link AuthorizationServerConfigurer}
* 
*/

@Configuration
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	clients.inMemory()
	.withClient("automoveis-api")
		.secret(passwordEncoder.encode("12345"))
		.scopes("web","mobile")
		.authorizedGrantTypes("password")
	.and()
	.withClient("boletos-api")
		.secret(passwordEncoder.encode("12345"))
		.scopes("web","mobile")
		.authorizedGrantTypes("password");
	
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
    }
}

