package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Igreja")
public record IgrejaDTO(
		
		Long id,
		
		@NotNull(message = "Campo obrigatório")
		boolean ativo,

		@Valid
		@NotNull(message = "Dados pessoais obrigatórios")
		DadosPessoaisDTO dadosPessoais) {}