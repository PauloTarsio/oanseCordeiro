package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;


import java.util.List;


public class Response {
	
	private StatusIntegracao status;
	private List<String> erros;
	
	public Response(StatusIntegracao statusIntegracao) {
		this.status = statusIntegracao;
	}
	
	public Response(StatusIntegracao statusIntegracao, List<String> erros) {
        this.status = statusIntegracao;
        this.erros = erros;
	}
	
	public StatusIntegracao getStatus() {
		return status;
	}

	public List<String> getErros() {
		return erros;
	}
}