package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.AlunoManualDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.model.AlunoManual;

public class AlunoManualMapper {
    
	public static AlunoManualDTO toDTO(AlunoManual entity) {
        return new AlunoManualDTO(
            entity.getId(),
            entity.getAluno().getId(),
            entity.getAluno().getDadosPessoais().getDescricao(),
            entity.getLivro().getId(),
            entity.getLivro().getDescricao(),
            entity.isConcluido(),
            entity.isIniciado()
        );
    }

    public static List<AlunoManualDTO> toDTOList(List<AlunoManual> entities) {
        return entities.stream().map(AlunoManualMapper::toDTO).collect(Collectors.toList());
    }
}
