package br.com.igrejabatistadocordeiro.oanse.factory;

import java.sql.Date;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;
import br.com.igrejabatistadocordeiro.oanse.domain.model.ManualDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;

public class ManualDoOansistaFactory {

	private ManualDoOansista manualDoOansista;
	
	public ManualDoOansistaFactory() {
		manualDoOansista = new ManualDoOansista();
	}
	
	public ManualDoOansistaFactory comId(Long id) {
		this.manualDoOansista.setId(id);
		return this;
	}
	
	public ManualDoOansistaFactory comOansista(Oansista oansista) {
		this.manualDoOansista.setOansista(oansista);
		return this;
	}
	
	public ManualDoOansistaFactory comManual(Manual manual) {
		this.manualDoOansista.setManual(manual);
		return this;
	}
	
	public ManualDoOansistaFactory comDataInicio(Date dataInicio) {
		this.manualDoOansista.setDataInicio(dataInicio);
		return this;
	}
	
	public ManualDoOansistaFactory comDataConclusao(Date dataConclusao) {
		this.manualDoOansista.setDataConclusao(dataConclusao);
		return this;
	}
	
	public ManualDoOansistaFactory comConcluido(boolean concluido) {
		this.manualDoOansista.setConcluido(concluido);
		return this;
	}
	
	public ManualDoOansista build() {
		return this.manualDoOansista;
	}
}
