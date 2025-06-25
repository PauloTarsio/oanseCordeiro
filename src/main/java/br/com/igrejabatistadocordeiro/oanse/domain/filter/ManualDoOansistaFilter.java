package br.com.igrejabatistadocordeiro.oanse.domain.filter;

public class ManualDoOansistaFilter {

	private Long idOansista;
	private Long idManual;
	private String clube;
	private Boolean concluido;

	public Long getIdOansista() {
		return idOansista;
	}

	public void setIdOansista(Long idOansista) {
		this.idOansista = idOansista;
	}

	public Boolean getConcluido() {
		return concluido;
	}

	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}

	public Long getIdManual() {
		return idManual;
	}

	public void setIdManual(Long idManual) {
		this.idManual = idManual;
	}

	public String getClube() {
		return clube;
	}

	public void setClube(String clube) {
		this.clube = clube;
	}
}
