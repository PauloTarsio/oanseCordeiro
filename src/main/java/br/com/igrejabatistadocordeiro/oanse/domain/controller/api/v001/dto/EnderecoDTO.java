package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
		
		Long id,
		
		@NotBlank(message = "campo obrigatória")
		@Size(max = 255, message = "Quantidade de caracteres acima do permitido")
		String rua,
		
		@Size(max = 20, message = "Quantidade de caracteres acima do permitido")
		String numero,
		
		@NotBlank(message = "campo  obrigatório")
		@Size(max = 100, message = "Quantidade de caracteres acima do permitido")
		String bairro,
		
		@NotBlank(message = "campo obrigatória")
		@Size(max = 100, message = "Quantidade de caracteres acima do permitido")
		String cidade,
		
		@NotBlank(message = "campo obrigatória")
		@Size(max = 2, message = "Quantidade de caracteres acima do permitido")
		String uf
    ) {}
