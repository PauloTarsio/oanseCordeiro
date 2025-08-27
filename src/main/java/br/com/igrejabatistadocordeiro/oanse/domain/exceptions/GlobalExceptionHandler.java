package br.com.igrejabatistadocordeiro.oanse.domain.exceptions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.erro.ErroCampo;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.erro.ErroResposta;

@RestControllerAdvice //capturar exceções de todo o sistema inclusive as validações de Bean Validation
public class GlobalExceptionHandler {
	
	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) //código 422
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		logger.warn("Erro de validação nos parâmetros: {}", e.getMessage());
		
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
	
	@ResponseStatus(HttpStatus.FORBIDDEN) //código 403
	@ExceptionHandler(AuthorizationDeniedException.class)
	public ErroResposta handleAccesDeniedException(AuthorizationDeniedException e) {
		logger.warn("Acesso negado: {}", e.getMessage());
		
		return new ErroResposta(
				HttpStatus.FORBIDDEN.value(),
				"Acesso negado.",
				List.of());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErroResposta handleInvalidFormat(HttpMessageNotReadableException ex) {
	    logger.warn("Erro ao ler mensagem HTTP: {}", ex.getMessage());

	    List<ErroCampo> erros = new ArrayList<>();

	    Throwable cause = ex.getCause();

	    if (cause instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException invalidFormat) {
	        String campo = null;

	        if (!invalidFormat.getPath().isEmpty()) {
	            campo = invalidFormat.getPath().stream()
	                    .map(ref -> ref.getFieldName())
	                    .filter(Objects::nonNull)
	                    .collect(Collectors.joining("."));
	        }

	        Class<?> targetType = invalidFormat.getTargetType();

	        if (targetType.equals(LocalDate.class)) {
	            erros.add(new ErroCampo(campo, "Formato de data inválido. Use o padrão yyyy-MM-dd"));
	        } else if (targetType.isEnum()) {
	            String valoresAceitos = Arrays.stream(targetType.getEnumConstants())
	                                          .map(Object::toString)
	                                          .collect(Collectors.joining(", "));
	            erros.add(new ErroCampo(campo, "Valor inválido. Use um dos seguintes: " + valoresAceitos));
	        } else {
	            erros.add(new ErroCampo(campo, "Valor inválido para o tipo esperado."));
	        }
	    } else {
	        erros.add(new ErroCampo(null, "Erro ao ler requisição. Verifique o formato dos dados."));
	    }

	    return new ErroResposta(
	            HttpStatus.BAD_REQUEST.value(),
	            "Erro de validação",
	            erros
	    );
	}	
	
	@ResponseStatus(HttpStatus.CONFLICT) //código 409
	@ExceptionHandler(RegistroDuplicadoException.class)
	public ErroResposta handleRegistroDuplicadoException(RegistroDuplicadoException e) {
		logger.error("Registro duplicado detectado: {}", e.getMessage());
		return ErroResposta.conflito(e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST) //código 400
	@ExceptionHandler(IllegalArgumentException.class)
	public ErroResposta handleIllegalArgumentException(IllegalArgumentException e) {
		logger.error("IllegalArgumentException: {}", e.getMessage());
		return ErroResposta.respostaPadrao(e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //código 500
	@ExceptionHandler(Exception.class)
	public ErroResposta handleException(Exception e) {
		logger.error("Erro inesperado no sistema", e);
		return new ErroResposta(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.",
				List.of());
	}
	
}
