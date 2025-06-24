package br.com.igrejabatistadocordeiro.oanse.domain.filter;

public class SessaoFilter {

	private Long idManual;
	private Long idTrilha;
	private Long idSessao;
	private Integer numero;
	
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
	public Long getIdSessao() {
		return idSessao;
	}
	public void setIdSessao(Long idSessao) {
		this.idSessao = idSessao;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}
