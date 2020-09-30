package br.com.itauunibanco.boleto.api.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * Class de dominio responsavél persistencia do boleto. 
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link Automovel}
 * 
 */

/**
* https://stackoverflow.com/questions/24994440/no-serializer-found-for-class-org-hibernate-proxy-pojo-javassist-javassist/52148725
*  solução para error: no serializer found for class org.hibernate.proxy.pojo.javassist.Javassist adiconar  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
* **/

@Entity 
@Table(name = "tb_boleto")
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})	
public class Boleto {

    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "codigo",length = 20, updatable = false)
    @Id private Long codigo;
    
    @JsonIgnoreProperties
    @Column(name = "marca", length = 30 , nullable = false)
    @NotNull @Size(min = 2 ,  max = 30)
    private String marca;
    
    @JsonIgnoreProperties
    @Column(name = "modelo", length = 30, nullable = false )
    @NotNull @Size(min = 2 ,  max = 30)
    private String modelo;
    
    @Column(name = "valor", length = 50, nullable = false)
    @NotEmpty @PositiveOrZero
    private  Double valor;
    
    @Column(name = "data_vencimento", length = 15)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataVencimento ;
 
    public Boleto() {}

    
    
}
