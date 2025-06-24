package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Sessao;

public class SessaoDTO {

	private Long idSessao;
	private Integer numero;
	private Long idManual;
	private String nomeManual;
	private Long idTrilha;
	private String nomeTrilha;
	
	public SessaoDTO(Sessao sessao) {
		this.idSessao = sessao.getId();
		this.numero = sessao.getNumero();
		this.idTrilha = sessao.getTrilha() != null ? sessao.getTrilha().getId() : null;
		this.nomeTrilha = sessao.getTrilha() != null ? sessao.getTrilha().getNome() : null;
		this.idManual = sessao.getTrilha() != null && sessao.getTrilha().getManual() != null ? sessao.getTrilha().getManual().getId() : null;
		this.nomeManual = sessao.getTrilha() != null && sessao.getTrilha().getManual() != null ? sessao.getTrilha().getManual().getDescricao() : null;
	}
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
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
	public Long getIdTrilha() {
		return idTrilha;
	}
	public void setIdTrilha(Long idTrilha) {
		this.idTrilha = idTrilha;
	}
	public String getNomeTrilha() {
		return nomeTrilha;
	}
	public void setNomeTrilha(String nomeTrilha) {
		this.nomeTrilha = nomeTrilha;
	}

	public Long getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(Long idSessao) {
		this.idSessao = idSessao;
	}
	
	
}
