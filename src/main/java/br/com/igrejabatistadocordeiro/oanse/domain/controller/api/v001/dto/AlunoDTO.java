package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AlunoDTO(
		
		Long id,
		
		@NotNull(message = "Campo obrigatório")
		boolean ativo,
		
		@Valid
		@NotNull(message = "Dados pessoais obrigatórios")
		DadosPessoaisDTO dadosPessoais,
		
		@Valid
		@NotNull(message = "Igreja obrigatória")
		IgrejaDTO igreja) {}
