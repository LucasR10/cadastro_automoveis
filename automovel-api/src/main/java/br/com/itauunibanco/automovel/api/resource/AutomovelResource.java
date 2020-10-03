package br.com.itauunibanco.automovel.api.resource;

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

import br.com.itauunibanco.automovel.api.event.LocationEvent;
import br.com.itauunibanco.automovel.api.model.Automovel;
import br.com.itauunibanco.automovel.api.repository.AutomovelRepository;


/**
 * Recurso que retorna os dados para navegador . 
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link Automovel}
 * 
 */

@RestController
@RequestMapping("/automoveis")
public class AutomovelResource {

    @Autowired
    private AutomovelRepository automovelRepository;
    
    @Autowired
    private ApplicationEventPublisher publisher ;
    
    /**
     * Lista os automóveis disponível para compra.
     * @param empty
     * @return JSON de Automoveis
     */
    
    @GetMapping
    public ResponseEntity<List<Automovel>>  listar(){
	return ResponseEntity.ok( automovelRepository.findAll() );
    }
    
    
    /**
     * Cadastra o automóvel disponível para compra.
     * @param Automovel
     * @return Json do automovál cadastrado e retorna o Location do automovel buscando por codigo.
     */

    @PostMapping
    public ResponseEntity<Automovel> cadastrar(@RequestBody @Valid Automovel automovel, HttpServletResponse response ) {
	automovel.setCodigo(null);//evita atualizar 
	Automovel novoAutomovel = automovelRepository.save(automovel);
	publisher.publishEvent( new LocationEvent(this, response , novoAutomovel.getCodigo(), "/{codigo}" ) );
	return ResponseEntity.status(HttpStatus.CREATED).body(novoAutomovel);
    }
    
    
    /**
     * Atualizar um automóvel já cadastrado.
     * @param codigo
     * @return Json do automovál cadastrado.
     */
    
    @PutMapping("/{codigo}")
    public ResponseEntity<Automovel> atualizar(@PathVariable Long codigo, @RequestBody  @Valid  Automovel automovel) {
	Automovel novoAutomovel = automovelRepository.getOne(codigo);
	BeanUtils.copyProperties(automovel, novoAutomovel,"codigo");
	automovelRepository.save(novoAutomovel);
	return ResponseEntity.ok(novoAutomovel);
    }
    
    
    /**
     * Excluir um automóvel por codigo.
     * @param codigo
     * @return  204 No Content.
     */
    
    @DeleteMapping("/{codigo}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void  excluir(@PathVariable Long codigo) {
	automovelRepository.deleteById(codigo);
    }
    
    /**
     * Buscar por codigo do automóvel disponível para compra.
     * @param codigo
     * @return Json do automovál cadastrado.
     */
    @GetMapping("/{codigo}")
    public ResponseEntity<Automovel> buscarPeloCodigo(@PathVariable Long codigo) {
	Automovel novoAutomovel = automovelRepository.getOne(codigo);
	return ResponseEntity.ok(novoAutomovel) ;
    }
}
