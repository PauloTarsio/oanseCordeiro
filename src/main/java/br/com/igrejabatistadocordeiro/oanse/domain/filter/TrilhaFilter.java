package br.com.igrejabatistadocordeiro.oanse.domain.filter;

public class TrilhaFilter {

	private Long idManual;
	private Long idTrilha;
	private String nome;
	
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
	public Long getIdTrilha() {
		return idTrilha;
	}
	public void setIdTrilha(Long idTrilha) {
		this.idTrilha = idTrilha;
	}
}
