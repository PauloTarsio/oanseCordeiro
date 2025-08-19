package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import java.util.List;
import java.util.UUID;

public record PesquisaUsuarioResumidoDTO(UUID id, String login, List<String> roles) {

}
