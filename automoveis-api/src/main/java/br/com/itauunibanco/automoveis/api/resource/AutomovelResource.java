package br.com.itauunibanco.automoveis.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.itauunibanco.automoveis.api.model.Automovel;
import br.com.itauunibanco.automoveis.api.repository.AutomovelRepository;


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
    
    /**
     * Lista os automóveis disponível para compra.
     *
     * @param empty
     * @return JSON de Automoveis
     */
    
    @GetMapping
    public ResponseEntity<List<Automovel>>  listar(){
	return ResponseEntity.ok( automovelRepository.findAll() );
    }
    
    
    
    /**
     * Cadastra o automóvel disponível para compra.
     *
     * @param Automovel
     * @return Json do automovál cadastrado e retorna o Location do automovel buscando por codigo.
     */

    @PostMapping
    public ResponseEntity<Automovel> cadastrar(@RequestBody @Valid Automovel automovel ) {
	Automovel novoAutomovel = automovelRepository.save(automovel);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(novoAutomovel.getCodigo()).toUri();
	return ResponseEntity.created(uri).body(novoAutomovel);
    }
    
    /**
     * Buscar um por codigo do automóvel disponível para compra.
     *
     * @param codigo
     * @return Json do automovál cadastrado.
     */
    @GetMapping("/{codigo}")
    public ResponseEntity<Automovel> buscarPeloCodigo(@PathVariable Long codigo) {
	Optional<Automovel> op = Optional.of(automovelRepository.getOne(codigo));
	return op.isPresent() ? ResponseEntity.ok(op.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(op.get());
    }
}
