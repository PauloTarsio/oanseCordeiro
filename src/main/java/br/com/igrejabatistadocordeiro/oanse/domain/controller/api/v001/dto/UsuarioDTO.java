package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Usuario")
public record UsuarioDTO(UUID id, String login, String senha, String perfil) {
}
