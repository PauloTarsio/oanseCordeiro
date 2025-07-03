package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;


import java.util.ArrayList;
import java.util.List;


public class Response {
	
	private StatusIntegracao status;
	private List<String> detalhes;
	
	public Response(StatusIntegracao statusIntegracao) {
		this.status = statusIntegracao;
		this.detalhes = new ArrayList<>();
	}
	
	public Response(StatusIntegracao statusIntegracao, String detalhes) {
		this.status = statusIntegracao;
		if (this.detalhes == null)
			this.detalhes = new ArrayList<>();
		this.detalhes.add(detalhes);
	}
	
	public Response(StatusIntegracao statusIntegracao, List<String> detalhes) {
        this.status = statusIntegracao;
        this.detalhes = detalhes;
	}

	public StatusIntegracao getStatus() {
		return status;
	}

	public List<String> getDetalhes() {
		return detalhes;
	}
}