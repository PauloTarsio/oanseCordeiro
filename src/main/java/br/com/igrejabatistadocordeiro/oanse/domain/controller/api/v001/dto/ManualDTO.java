package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;

public class ManualDTO {

	private Long idManual;
	private String clube;
	private String descricao;
	
	public ManualDTO(Manual manual) {
		this.setIdManual(manual.getId());
		this.clube = manual.getClube().getNome();
		this.descricao = manual.getDescricao();
	}
	
	public String getClube() {
		return clube;
	}
	public void setClube(String clube) {
		this.clube = clube;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdManual() {
		return idManual;
	}

	public void setIdManual(Long idManual) {
		this.idManual = idManual;
	}
	
}
