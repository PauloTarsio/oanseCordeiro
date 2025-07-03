package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import java.util.Date;

import br.com.igrejabatistadocordeiro.oanse.domain.model.SessaoDoOansista;

public class SessaoDoOansistaDTO {

	private Long idOansista;
	private String nomeDoOansista;
	private String clube;
	private Long idManual;
	private String nomeDoManual;
	private Long idTrilha;
	private String nomeDaTrilha;
	private Integer numeroDaSessao;
	private Date dataConlusao;
	private Boolean concluido = false;
	
	public SessaoDoOansistaDTO() {}
	
	public SessaoDoOansistaDTO(SessaoDoOansista sessaoDoOansista) {		
		this.idOansista = sessaoDoOansista.getOansista().getId();
		this.nomeDoOansista = sessaoDoOansista.getOansista().getNome();
		this.clube = sessaoDoOansista.getManualDoOansista().getManual().getClube().getNome();
		this.idManual = sessaoDoOansista.getManualDoOansista().getManual().getId();
		this.nomeDoManual = sessaoDoOansista.getManualDoOansista().getManual().getDescricao();
		this.idTrilha = sessaoDoOansista.getSessao().getTrilha().getId();
		this.nomeDaTrilha = sessaoDoOansista.getSessao().getTrilha().getNome();
		this.numeroDaSessao = sessaoDoOansista.getSessao().getNumero();
		this.dataConlusao = sessaoDoOansista.getDataConclusao(); 
		this.concluido = sessaoDoOansista.getConcluido();
	}

	public Boolean getConcluido() {
		return concluido;
	}
	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}
	public Long getIdOansista() {
		return idOansista;
	}
	public void setIdOansista(Long idOansista) {
		this.idOansista = idOansista;
	}
	public Long getIdManual() {
		return idManual;
	}
	public void setIdManual(Long idManual) {
		this.idManual = idManual;
	}
	public Long getIdTrilha() {
		return idTrilha;
	}
	public void setIdTrilha(Long idTrilha) {
		this.idTrilha = idTrilha;
	}
	public Integer getNumeroDaSessao() {
		return numeroDaSessao;
	}
	public void setNumeroDaSessao(Integer numeroDaSessao) {
		this.numeroDaSessao = numeroDaSessao;
	}
	public String getNomeDoOansista() {
		return nomeDoOansista;
	}
	public void setNomeDoOansista(String nomeDoOansista) {
		this.nomeDoOansista = nomeDoOansista;
	}
	public String getNomeDoManual() {
		return nomeDoManual;
	}
	public void setNomeDoManual(String nomeDoManual) {
		this.nomeDoManual = nomeDoManual;
	}
	public String getNomeDaTrilha() {
		return nomeDaTrilha;
	}
	public void setNomeDaTrilha(String nomeDaTrilha) {
		this.nomeDaTrilha = nomeDaTrilha;
	}
	public String getClube() {
		return clube;
	}
	public void setClube(String clube) {
		this.clube = clube;
	}
	public Date getDataConlusao() {
		return dataConlusao;
	}
	public void setDataConlusao(Date dataConlusao) {
		this.dataConlusao = dataConlusao;
	}
}
