package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record IgrejaDTO(
		Long id,
		
		@NotNull(message = "Campo obrigatório")
		boolean ativo,

		@Valid
		@NotNull(message = "Dados pessoais obrigatórios")
		DadosPessoaisDTO dadosPessoais) {

	public Igreja toIgreja() {
		Igreja igreja = new Igreja();
		igreja.setId(id);
		igreja.setAtivo(ativo);
		igreja.setDadosPessoais(dadosPessoais != null ? dadosPessoais.toDadosPessoais() : null);
		return igreja;
	}
}