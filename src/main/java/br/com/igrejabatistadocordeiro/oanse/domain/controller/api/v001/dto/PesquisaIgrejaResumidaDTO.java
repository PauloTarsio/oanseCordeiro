package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PesquisaIgrejaResumida")
public record PesquisaIgrejaResumidaDTO(Long id, String descricao, boolean ativo) {}
