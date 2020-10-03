package br.com.itauunibanco.automovel.api.exceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class responsavel por centralizar e pegar os erros da aplicação, de forma mais profisional. 
 * 
 * @author Lucas Carvalho
 * @author https://www.linkedin.com/in/lucas-carvalho-793609134/
 * @version 1.0
 * @since 1.0 {@link AutomovelExceptionHandler}
 *  @since 1.0 {@link ResponseEntityExceptionHandler}
 * 
 */

@ControllerAdvice
public class AutomovelExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Acessar as mensagem do resource da aplicação.
     */
    
    @Autowired
    private MessageSource msg;

    
    
    /**
     * Metodo customizado para tratar a HttpMessageNotWritableException au tentar buscar recurso não encontrado".
     * @exception EmptyResultDataAccessException
     * */
    
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	String msgDesenvolvedor = ex.toString();
	String msgUsuario = msg.getMessage("recurso.nao-encontrado", null, Locale.getDefault());
	return handleExceptionInternal(ex,  new MensagemErro(msgUsuario, msgDesenvolvedor), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    } 
    
    
    
    /**
     * Metodo customizado para tratar a EmptyResultDataAccessException buscar e excluir recurso não encontrado".
     * @exception EmptyResultDataAccessException
     * */
    
    @ExceptionHandler({EmptyResultDataAccessException.class }) 
    public ResponseEntity<Object>  handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
	String msgDesenvolvedor = ex.toString();
	String msgUsuario = msg.getMessage("recurso.nao-encontrado", null, Locale.getDefault());
	return handleExceptionInternal(ex,  new MensagemErro(msgUsuario, msgDesenvolvedor), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    /**
     * Metodo customizado para tratar a EntityNotFoundException para um recurso não encontrado".
     * @exception EntityNotFoundException
     * */
    @ExceptionHandler({EntityNotFoundException.class }) 
    public ResponseEntity<Object>  handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request, HttpHeaders headers ) {
	String msgDesenvolvedor = ex.toString();
	String msgUsuario = msg.getMessage("recurso.nao-encontrado", null, Locale.getDefault());
	return handleExceptionInternal(ex,  new MensagemErro(msgUsuario, msgDesenvolvedor), headers, HttpStatus.NOT_FOUND, request);
    }
    
    /**
     * Metodo responsavel por tratar argumentos não legivel.
     * @exception HttpMessageNotReadableException
     */
    
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	String msgDesenvolvedor = ex.getCause().toString();
	String msgUsuario = msg.getMessage("mensagem.invalida", null, Locale.getDefault());
	return handleExceptionInternal(ex, new MensagemErro(msgUsuario, msgDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request);
    }
    
    /**
     * Metodo responsavel por tratar argumentos não validos e campos validados no BeanValidation.
     * @exception MethodArgumentNotValidException
     */
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, criarListaeDerros( ex.getBindingResult() ), headers, HttpStatus.BAD_REQUEST, request);
    }
    
    
    /**
     * Metodo responsavel por tratar formatos não validos.
     * @exception TypeMismatchException
     */
    
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,  HttpStatus status, WebRequest request) {
	String msgDesenvolvedor = ex.getCause().toString();
	String msgUsuario = msg.getMessage("mensagem.valor.invalido", null, Locale.getDefault());
	return handleExceptionInternal(ex,  new MensagemErro(msgUsuario, msgDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Metodo responsavel por criar uma lista de erro de valisação.
     * @param BindingResult.
     */
    
    private List<MensagemErro> criarListaeDerros(BindingResult result) {
	List<MensagemErro> erros = new ArrayList<>();
	 result.getFieldErrors().forEach( field ->{
	     erros.add( new MensagemErro( msg.getMessage(field, LocaleContextHolder.getLocale() ) , field.toString()));
	 });
	return erros;
    }


    /**
     * Class de uso interno para imprimir uma mensagem mais detalhada para o usuáro e o desenvolvedor.
     * 
     * @param mensagemUsuario       mensagem com menos detalhes para o usuário .
     * @param mensagemDesenvolvedor mensagem com mais detalhes para o desenvolvedor do lados do cliente.
     */
    @Getter @AllArgsConstructor
    public static class MensagemErro {
	
	
	private String mensagemUsuario;
	private String mensagemDesenvolvedor;
        

    }
}
