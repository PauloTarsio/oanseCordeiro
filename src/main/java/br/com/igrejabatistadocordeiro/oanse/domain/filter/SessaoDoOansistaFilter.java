package br.com.igrejabatistadocordeiro.oanse.domain.filter;

public class SessaoDoOansistaFilter {

    private Long idOansista;
    private Long idManual;
    private Long idTrilha;
    private Integer numeroDaSessao;
    private Boolean concluido;
    
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
	public Boolean getConcluido() {
		return concluido;
	}
	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}
	public Integer getNumeroDaSessao() {
		return numeroDaSessao;
	}
	public void setNumeroDaSessao(Integer numero) {
		this.numeroDaSessao = numero;
	}
	public Long getIdTrilha() {
		return idTrilha;
	}
	public void setIdTrilha(Long idTrilha) {
		this.idTrilha = idTrilha;
	}
}
