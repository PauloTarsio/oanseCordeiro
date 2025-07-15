package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;

public record IgrejaDTO(Long id, DadosPessoaisDTO dadosPessoais) {
	
	public Igreja toIgreja() {
		Igreja igreja = new Igreja();
		igreja.setId(id);
		igreja.setDadosPessoais(dadosPessoais != null ? dadosPessoais.toDadosPessoais() : null);
		return igreja;
	}
}
