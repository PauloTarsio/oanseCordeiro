package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import java.util.UUID;

import br.com.igrejabatistadocordeiro.oanse.domain.model.recursos.PerfilDoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Usuario")
public record UsuarioDTO(
		UUID id,
		@NotBlank
		String login,
		@NotBlank
		String senha,
		@NotNull
		PerfilDoUsuario perfil,
		@NotNull
		Long igrejaId,
		String igrejaDescricao
		) {}