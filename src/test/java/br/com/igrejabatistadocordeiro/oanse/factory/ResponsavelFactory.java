package br.com.igrejabatistadocordeiro.oanse.factory;

import br.com.igrejabatistadocordeiro.oanse.model.Responsavel;

public class ResponsavelFactory {
	
	private Responsavel responsavel;
	
	public ResponsavelFactory() {
		responsavel = new Responsavel();
	}
	
	public ResponsavelFactory comNome(String nome) {
		this.responsavel.setNome(nome);
		return this;
	}
	
	public Responsavel build() {
		return this.responsavel;
	}
}
