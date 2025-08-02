package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AlunoDTO(
	    Long id,
	    
	    @NotNull
	    boolean ativo,
	    
	    @Valid
	    @NotNull
	    DadosPessoaisDTO dadosPessoais,
	    
	    @NotNull
	    Long igrejaId
	    
	) {}
