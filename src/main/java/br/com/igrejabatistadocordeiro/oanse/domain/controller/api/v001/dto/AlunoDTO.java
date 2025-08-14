package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Aluno")
public record AlunoDTO(
	    Long id,
	    
	    @NotNull
	    boolean ativo,
	    
	    @Valid
	    @NotNull
	    DadosPessoaisDTO dadosPessoais,
	    
	    @NotNull
	    Long igrejaId,
	    
	    String igrejaDescricao
	    
	) {}
