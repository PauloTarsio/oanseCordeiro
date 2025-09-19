package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Trilha;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AlunoManualTrilhas")
public record AlunoManualTrilhasDTO(
    Long id,    
    List<Trilha> trilhas
) {}
