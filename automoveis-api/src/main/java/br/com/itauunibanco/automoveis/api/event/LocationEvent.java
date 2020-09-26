package br.com.itauunibanco.automoveis.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

/**
 * Class Event do projeto para aplicar Location no Header sempre que um recurso for criado. 
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link Automovel}
 * 
 */

@Getter
public class LocationEvent extends ApplicationEvent {

    
    private HttpServletResponse response;
    private Long value;
    private String pathVariable;
    
    
    /**
     *
     * @param HttpServletResponse
     * @param Long
     * @param pathVariable
     */
    public LocationEvent(Object source, HttpServletResponse response, Long value, String pathVariable) {
	super(source);
	this.response = response;
	this.value = value;
	this.pathVariable = pathVariable;
    }
    
      
}
