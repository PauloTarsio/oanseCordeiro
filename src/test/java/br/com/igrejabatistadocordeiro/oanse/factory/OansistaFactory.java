package br.com.igrejabatistadocordeiro.oanse.factory;

import java.time.LocalDate;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;

public class OansistaFactory {

	private Oansista oansista;
	
	public OansistaFactory() {
		oansista = new Oansista();
	}
	
	public OansistaFactory comNome(String nome) {
		this.oansista.setNome(nome);
		return this;
	}
	
	public OansistaFactory comDataNascimento(LocalDate dataNascimento) {
		this.oansista.setDataNascimento(dataNascimento);
		return this;
	}
	
	public Oansista build() {
		return this.oansista;
	}
}
