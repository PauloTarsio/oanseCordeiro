package br.com.igrejabatistadocordeiro.oanse.domain.controller.common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.erro.ErroCampo;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.erro.ErroResposta;

@RestControllerAdvice //capturar exceções de todo o sistema inclusive as validações de Bean Validation
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) //código 422
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<ErroCampo> erros = e.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(erro -> new ErroCampo(erro.getField(), erro.getDefaultMessage()))
				.collect(Collectors.toList());
		
		return new ErroResposta(
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro de validação",
				erros);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST) //código 400 
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErroResposta handleInvalidFormat(HttpMessageNotReadableException ex) {
    	List<ErroCampo> erros = new ArrayList<>();
        Throwable cause = ex.getCause();
        
        if (cause instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException)
            erros.add(new ErroCampo("dataNascimento", "Formato de data inválido. Use o padrão yyyy-MM-dd"));            
        
        return new ErroResposta(
				HttpStatus.BAD_REQUEST.value(),
				"Erro de validação",
				erros);
    }		
}
