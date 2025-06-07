package br.com.igrejabatistadocordeiro.oanse.domain.exceptions;

import java.util.ArrayList;
import java.util.List;

public class OanseValildationException extends RuntimeException {
	private static final long serialVersionUID = 214993015039924947L;

	private List<String> erros;
	
	public OanseValildationException(String erro) {
		if (erros == null)
			erros = new ArrayList<>();
		this.erros.add(erro);
	}
	
	public OanseValildationException(List<String> erros) {
		if (erros == null)
			erros = new ArrayList<>();
		this.erros = erros;
	}

	public List<String> getErros() {
		return erros;
	}
}
