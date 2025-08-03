package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Usuario")
public record UsuarioDTO(String login, String senha, List<String> roles) {
}
