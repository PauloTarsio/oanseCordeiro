package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;

public class ManualDTO {

	private Long id;
	private String clube;
	private String descricao;
	
	public ManualDTO(Manual manual) {
		this.id = manual.getId();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
