package br.com.itauunibanco.automoveis.api.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.itauunibanco.automoveis.api.event.LocationEvent;

/**
 * Class Listener do LocationEvent. 
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link Automovel}
 * 
 */

@Component
public class LocationListener implements ApplicationListener<LocationEvent> {

    private static final String LOCATION = "Location";

    @Override
    public void onApplicationEvent(LocationEvent event) {
        addLocationHeader( event );
    }

    /**
     * Metodo responsavel por adiconar o Location no Header.
     *
     * @param LocationEven com nome pathVariable e valor da PathVariable
     * @return empty.
     */
    private void addLocationHeader(LocationEvent event) {
	event.getResponse().setHeader( LOCATION,  getURI( event )  );
    }

    /**
     * Pegar o URI atual e devolver a string.
     *
     * @param LocationEven com nome pathVariable e valor da PathVariable
     * @return retorna a string da URI atual.
     */
    private String getURI(LocationEvent event) {
	return ServletUriComponentsBuilder.fromCurrentRequestUri().path(event.getPathVariable())
		.buildAndExpand(event.getValue()).toUri().toASCIIString();
    }

}
