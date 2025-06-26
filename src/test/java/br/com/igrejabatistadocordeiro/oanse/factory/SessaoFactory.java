package br.com.igrejabatistadocordeiro.oanse.factory;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Sessao;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Trilha;

public class SessaoFactory {

	private Sessao sessao;
	
	public SessaoFactory() {
		sessao = new Sessao();
	}
	
	public SessaoFactory comId(Long id) {
		this.sessao.setId(id);
		return this;
	}
	
	public SessaoFactory comNumero(Integer numero) {
		this.sessao.setNumero(numero);
		return this;
	}
	
	public SessaoFactory comTrilhia(Trilha trilha) {
		this.sessao.setTrilha(trilha);
		return this;
	}
	
	public Sessao build() {
		return sessao;
	}
 
}
