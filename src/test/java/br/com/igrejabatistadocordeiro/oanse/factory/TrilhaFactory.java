package br.com.igrejabatistadocordeiro.oanse.factory;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Sessao;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Trilha;

public class TrilhaFactory {

	private Trilha trilha;
	
	public TrilhaFactory() {
		trilha = new Trilha();
	}
	
	public TrilhaFactory comId(Long id) {
		this.trilha.setId(id);
		return this;
	}
	
	public TrilhaFactory comNome(String nome) {
		this.trilha.setNome(nome);
		return this;
	}
	
	public TrilhaFactory comManual(Manual manual) {
		this.trilha.setManual(manual);
		return this;
	}	
	
	public TrilhaFactory comSessoes(List<Sessao> sessoes) {
		this.trilha.setSessoes(sessoes);
		return this;
	}
	
	public Trilha build() {		
		return trilha;
	}
	
}
