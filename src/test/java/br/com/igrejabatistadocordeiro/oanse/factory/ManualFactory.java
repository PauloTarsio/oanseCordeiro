package br.com.igrejabatistadocordeiro.oanse.factory;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Clubes;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;

public class ManualFactory {

	private Manual manual;
	
	public ManualFactory() {
		manual = new Manual();
	}
	
	public ManualFactory comClube(Clubes clube) {
		this.manual.setClube(clube);
		return this;
	}
	
	public ManualFactory comDescricao(String descricao) {
		this.manual.setDescricao(descricao);
		return this;
	}
	
	public Manual build() {
		return this.manual;
	}
}
