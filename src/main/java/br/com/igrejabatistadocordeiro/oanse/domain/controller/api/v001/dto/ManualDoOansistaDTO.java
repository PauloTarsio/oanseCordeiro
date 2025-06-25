package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import java.sql.Date;

import br.com.igrejabatistadocordeiro.oanse.domain.model.ManualDoOansista;

public class ManualDoOansistaDTO {

	private Long idOansista;
	private String nomeOansista;
	private String clube;
	private Long idManual;
	private String nomeManual;
	private Date dataInicio;
	private Date dataConclusao;
	private Boolean concluido;
	
	public ManualDoOansistaDTO() {}
	
	public ManualDoOansistaDTO(ManualDoOansista manualDoOansista) {
		this.idOansista = manualDoOansista.getOansista().getId();
		this.nomeOansista = manualDoOansista.getOansista().getNome();
		this.clube = manualDoOansista.getManual().getClube().toString();
		this.idManual = manualDoOansista.getManual().getId();
		this.nomeManual = manualDoOansista.getManual().getDescricao();
		this.dataInicio = manualDoOansista.getDataInicio();
		this.dataConclusao = manualDoOansista.getDataConclusao();
		this.concluido = manualDoOansista.getConcluido();
	}
	
	public Long getIdOansista() {
		return idOansista;
	}

	public void setIdOansista(Long idOansista) {
		this.idOansista = idOansista;
	}

	public String getNomeOansista() {
		return nomeOansista;
	}

	public void setNomeOansista(String nomeOansista) {
		this.nomeOansista = nomeOansista;
	}

	public String getClube() {
		return clube;
	}

	public void setClube(String clube) {
		this.clube = clube;
	}

	public Long getIdManual() {
		return idManual;
	}

	public void setIdManual(Long idManual) {
		this.idManual = idManual;
	}

	public String getNomeManual() {
		return nomeManual;
	}

	public void setNomeManual(String nomeManual) {
		this.nomeManual = nomeManual;
	}

	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataConclusao() {
		return dataConclusao;
	}
	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	public Boolean getConcluido() {
		return concluido;
	}
	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}
	
}
