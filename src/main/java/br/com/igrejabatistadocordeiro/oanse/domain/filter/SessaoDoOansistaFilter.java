package br.com.igrejabatistadocordeiro.oanse.domain.filter;

public class SessaoDoOansistaFilter {

    private Long idOansista;
    private Long idSessao;
    private Long idManual;
    private Boolean concluido;
    private Long numero;
    
	public Long getIdOansista() {
		return idOansista;
	}
	public void setIdOansista(Long idOansista) {
		this.idOansista = idOansista;
	}
	public Long getIdSessao() {
		return idSessao;
	}
	public void setIdSessao(Long idSessao) {
		this.idSessao = idSessao;
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
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
}
