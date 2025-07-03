package br.com.igrejabatistadocordeiro.oanse.factory;

import java.sql.Date;

import br.com.igrejabatistadocordeiro.oanse.domain.model.ManualDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Sessao;
import br.com.igrejabatistadocordeiro.oanse.domain.model.SessaoDoOansista;

public class SessaoDoOansistaFactory {	

	private SessaoDoOansista sessaoDoOansista;
	
	public SessaoDoOansistaFactory() {
		sessaoDoOansista = new SessaoDoOansista();		
	}
	
	public SessaoDoOansistaFactory comIdOansista(Long id) {
		this.sessaoDoOansista.setId(id);
		return this;
	}
	
	public SessaoDoOansistaFactory comOansista(Oansista oansista) {
		this.sessaoDoOansista.setOansista(oansista);
		return this;
	}
	
	public SessaoDoOansistaFactory comManualDoOansista(ManualDoOansista manualDoOansista) {
		this.sessaoDoOansista.setManualDoOansista(manualDoOansista);
		return this;
	}
	
	public SessaoDoOansistaFactory comSessao(Sessao sessao) {
		this.sessaoDoOansista.setSessao(sessao);
		return this;
	}
	
	public SessaoDoOansistaFactory comDataInicio(Date dataInicio) {
		this.sessaoDoOansista.setDataInicio(dataInicio);
		return this;
	}
	
	public SessaoDoOansistaFactory comDataConclusao(Date dataConclusao) {
		this.sessaoDoOansista.setDataConclusao(dataConclusao);
		return this;
	}
	
	public SessaoDoOansistaFactory comConcluido(Boolean concluido) {
		this.sessaoDoOansista.setConcluido(concluido);
		return this;
	}
	
	public SessaoDoOansista build() {
		return sessaoDoOansista;
	}
}
