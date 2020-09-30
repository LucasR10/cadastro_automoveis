package br.com.itauunibanco.boleto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itauunibanco.boleto.api.model.Boleto;


/**
 * Class responsavél acessar banco de dados e o crud do Boleto. 
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link Boleto}
 * 
 */

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {

}
