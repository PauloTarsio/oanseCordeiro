package br.com.igrejabatistadocordeiro.oanse.domain.exceptions;

public class RegistroDuplicadoException extends RuntimeException {
	
	private static final long serialVersionUID = 8124365307714710464L;

	public RegistroDuplicadoException(String mensagem) {
		super(mensagem);
	}
}
