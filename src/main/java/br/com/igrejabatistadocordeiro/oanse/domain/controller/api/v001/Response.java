package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;


import java.util.ArrayList;
import java.util.List;


public class Response {
	
	private StatusIntegracao status;
	private List<String> erros;
	
	public Response(StatusIntegracao statusIntegracao) {
		this.status = statusIntegracao;
		this.erros = new ArrayList<>();
	}
	
	public Response(StatusIntegracao statusIntegracao, String erro) {
		this.status = statusIntegracao;
		if (this.erros == null)
			this.erros = new ArrayList<>();
		this.erros.add(erro);
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