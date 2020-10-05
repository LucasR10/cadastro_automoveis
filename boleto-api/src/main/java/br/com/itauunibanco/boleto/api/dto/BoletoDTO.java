package br.com.itauunibanco.boleto.api.dto;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.itauunibanco.boleto.api.model.Boleto;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para response.
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link Automovel}
 * 
 */
@Getter @Setter
public class BoletoDTO {

    private Long codigo;
    private BigDecimal valor;
    private Calendar dataVencimento;

    public BoletoDTO(Long codigo, BigDecimal valor, Calendar dataVencimento) {
	this.codigo = codigo;
	this.valor = valor;
	this.dataVencimento = dataVencimento;
    }

    public BoletoDTO() {}


    public void setDataVencimento(Calendar dataVencimento) {
	this.dataVencimento = dataVencimento;
    }

    // criar boletoDTO from Boleto
    public static BoletoDTO criarBoletoDTO(Boleto boleto) {
	return new BoletoDTO(boleto.getCodigo(), boleto.getValor(), boleto.getDataVencimento());
    }

}
