package br.com.itauunibanco.boleto.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.itauunibanco.boleto.api.dto.BoletoDTO;
import br.com.itauunibanco.boleto.api.event.LocationEvent;
import br.com.itauunibanco.boleto.api.model.Boleto;
import br.com.itauunibanco.boleto.api.repository.BoletoRepository;


/**
 * Recurso que retorna os dados para navegador . 
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link Boleto}
 * 
 */

@RestController
@RequestMapping("/boletos")
public class BoletoResource {

    @Autowired
    private BoletoRepository boletoRepository;
    
    @Autowired
    private ApplicationEventPublisher publisher ;
    
    /**
     * Lista todos os boletos disponível.
     * @param empty
     * @return JSON de Automoveis
     */
    
    @GetMapping
    public ResponseEntity<List<Boleto>>  listar(){
	return ResponseEntity.ok( boletoRepository.findAll() );
    }
    
    
    /**
     * Cadastra um boleto via POST .
     * @param Boleto
     * @return Json do boleto cadastrado e retorna o Location do boleto buscando por codigo.
     */

    @PostMapping
    public ResponseEntity<BoletoDTO> cadastrar(@RequestBody @Valid Boleto boleto, HttpServletResponse response ) {
	boleto.setCodigo(null);
	Boleto novoBoleto = boletoRepository.save(boleto);
	publisher.publishEvent( new LocationEvent(this, response , novoBoleto.getCodigo(), "/{codigo}" ) );
	return ResponseEntity.status(HttpStatus.CREATED).body( BoletoDTO.criarBoletoDTO(novoBoleto) );
    }
    
    
    /**
     * Atualizar um boleto já cadastrado.
     * @param codigo
     * @return Json do boleto cadastrado.
     */
    
    @PutMapping("/{codigo}")
    public ResponseEntity<BoletoDTO> atualizar(@PathVariable Long codigo,@Valid @RequestBody Boleto boleto) {
	Boleto novoBoleto = boletoRepository.getOne(codigo);
	BeanUtils.copyProperties(boleto, novoBoleto,"codigo");
	boletoRepository.save(novoBoleto);
	return ResponseEntity.ok( BoletoDTO.criarBoletoDTO(novoBoleto) );
    }
    
    
    /**
     * Excluir um boleto por codigo.
     * @param codigo
     * @return  204 No Content.
     */
    
    @DeleteMapping("/{codigo}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void  excluir(@PathVariable Long codigo) {
	boletoRepository.deleteById(codigo);
    }
    
    /**
     * Busca por codigo do boleto disponível.
     * @param codigo
     * @return Json do boleto cadastrado.
     */
    @GetMapping("/{codigo}")
    public ResponseEntity<BoletoDTO> buscarPeloCodigo(@PathVariable Long codigo) {
	Boleto novoBoleto = boletoRepository.getOne(codigo);
	return ResponseEntity.ok( BoletoDTO.criarBoletoDTO(novoBoleto) ) ;
    }
}
