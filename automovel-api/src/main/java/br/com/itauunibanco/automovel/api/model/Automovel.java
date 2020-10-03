package br.com.itauunibanco.automovel.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


/**
 * Class de dominio responsavél persistencia do automovél. 
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link Automovel}
 * 
 */

/**
 * https://stackoverflow.com/questions/24994440/no-serializer-found-for-class-org-hibernate-proxy-pojo-javassist-javassist/52148725
 *  solução para error: no serializer found for class org.hibernate.proxy.pojo.javassist.Javassist adiconar @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
 * **/

@Entity 
@Table(name = "tb_automovel")
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})	
public class Automovel implements Serializable {

    
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "codigo",length = 20, updatable = false)
    @Id private Long codigo;
    
    @Column(name = "marca", length = 30 , nullable = false)
    @NotNull @Size(min = 2 ,  max = 30)
    private String marca;
    
    @Column(name = "modelo", length = 30, nullable = false )
    @NotNull @Size(min = 2 ,  max = 30)
    private String modelo;
    
    @Column(name = "valor", length = 50, nullable = false)
    @NotNull @PositiveOrZero
    private  BigDecimal valor;
    
    @Column(name = "data_cadastro", length = 15 , updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataCadastro; 

    public Automovel(Long codigo,  String marca,String modelo,  BigDecimal valor, Calendar dataCadastro) {
	this.codigo = codigo;
	this.marca = marca; 
	this.modelo = modelo;
	this.valor = valor;
	this.dataCadastro = dataCadastro;
    }
    
    public Automovel() {
    }
    
}
