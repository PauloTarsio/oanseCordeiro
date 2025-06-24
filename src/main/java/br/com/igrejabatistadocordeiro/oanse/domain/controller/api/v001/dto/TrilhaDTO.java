package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Trilha;

public class TrilhaDTO {

	private Long idTrilha;
	private String nome;
	private Long idManual;
	private String nomeManual;
	
	public TrilhaDTO(Trilha trilha) {
		this.idTrilha = trilha.getId();
        this.nome = trilha.getNome();
        this.idManual = trilha.getManual() != null ? trilha.getManual().getId() : null;
        this.nomeManual = trilha.getManual() != null ? trilha.getManual().getDescricao() : null;
	}
	
	public Long getIdTrilha() {
		return idTrilha;
	}
	public void setIdTrilha(Long id) {
		this.idTrilha = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	
	
}
