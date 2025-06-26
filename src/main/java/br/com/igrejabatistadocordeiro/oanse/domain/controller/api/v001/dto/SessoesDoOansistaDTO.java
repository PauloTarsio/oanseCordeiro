package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import java.util.List;

public class SessoesDoOansistaDTO {

	private List<SessaoDoOansistaDTO> sessoesDoOansista;

	public List<SessaoDoOansistaDTO> getSessoesDoOansista() {
		return sessoesDoOansista;
	}

	public void setSessoesDoOansista(List<SessaoDoOansistaDTO> sessoesDoOansista) {
		this.sessoesDoOansista = sessoesDoOansista;
	}

}
