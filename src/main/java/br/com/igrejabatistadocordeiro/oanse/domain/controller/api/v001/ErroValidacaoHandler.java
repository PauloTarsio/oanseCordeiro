package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroValidacaoHandler extends GeneralController {
	
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidFormat(HttpMessageNotReadableException ex) {
        String mensagem = "Erro ao processar a requisição: formato de dado inválido.";
        
        // Tentar detectar erro específico de data
        if (ex.getCause() instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException) {
            var cause = (com.fasterxml.jackson.databind.exc.InvalidFormatException) ex.getCause();
            if (cause.getTargetType() == java.time.LocalDate.class) {
                mensagem = "Data inválida. Use o formato yyyy-MM-dd (ex: 2025-07-04).";
            }
        }

        List<String> erros = List.of(mensagem);
        return mensagemDeErro(erros);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();
        return mensagemDeErro(erros);
    }
}
