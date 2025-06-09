package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;


import java.util.ArrayList;
import java.util.List;


public class Response {
	
	private StatusIntegracao status;
	private List<String> detalhe;
	
	public Response(StatusIntegracao statusIntegracao) {
		this.status = statusIntegracao;
	}
	
	public Response(StatusIntegracao statusIntegracao, String detalhe) {
        this.status = statusIntegracao;
        this.detalhe = new ArrayList<String>();
        this.detalhe.add(detalhe);
	}
	
	public Response(StatusIntegracao statusIntegracao, List<String> detalhes) {
		this.status = statusIntegracao;
		this.detalhe = detalhes;
	}

	public StatusIntegracao getStatus() {
		return status;
	}

	public List<String> getDetalhes() {
		return detalhe;
	}
}