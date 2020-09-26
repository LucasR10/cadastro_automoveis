package br.com.itauunibanco.automoveis.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itauunibanco.automoveis.api.model.Automovel;


/**
 * Class responsavél acessar banco de dados e o crud do automovel. 
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link Automovel}
 * 
 */

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {

}
