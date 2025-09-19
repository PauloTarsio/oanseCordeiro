package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PesquisaAlunoManual")
public record PesquisaAlunoManualDTO(
    Long id,
    Long alunoId,
    String alunoDescricao,
    Long livroId,
    String livroDescricao,
    Boolean concluido,
    Boolean iniciado
) {}