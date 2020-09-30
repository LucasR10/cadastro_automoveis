package br.com.itauunibanco.boleto.api.dto;

import java.util.Calendar;

import br.com.itauunibanco.boleto.api.model.Boleto;

/**
 * DTO para response. 
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link Automovel}
 * 
 */
public class BoletoDTO {
  
    private Long codigo;
    private  Double valor;
    private Calendar dataVencimento ;
    
    public BoletoDTO(Long codigo, Double valor, Calendar dataVencimento) {
	this.codigo = codigo;
	this.valor = valor;
	this.dataVencimento = dataVencimento;
    }

    public BoletoDTO() {
    }
    
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Calendar getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Calendar dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    
    //criar boletoDTO from Boleto
    public static BoletoDTO criarBoletoDTO( Boleto boleto) {
	return new BoletoDTO(boleto.getCodigo(), boleto.getValor(), boleto.getDataVencimento());
    }
    
    
}
