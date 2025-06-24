package br.com.igrejabatistadocordeiro.oanse.domain.exceptions;

import java.util.ArrayList;
import java.util.List;

public class OanseValidationException extends RuntimeException {
	private static final long serialVersionUID = 214993015039924947L;

	private List<String> erros;
	
	public OanseValidationException(String erro) {
		if (erros == null)
			erros = new ArrayList<>();
		this.erros.add(erro);
	}
	
	public OanseValidationException(List<String> erros) {
		if (erros == null)
			erros = new ArrayList<>();
		this.erros = erros;
	}

	public List<String> getErros() {
		return erros;
	}
	
	@Override
	public String getMessage() {
		return erros != null && !erros.isEmpty() ? String.join(", ", erros) : "Erro de validação genérico.";
	}
}
