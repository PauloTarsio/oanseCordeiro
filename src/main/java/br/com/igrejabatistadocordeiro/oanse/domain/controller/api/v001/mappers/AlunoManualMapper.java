package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.AlunoManualTrilhasDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaAlunoManualDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.model.aluno.AlunoManual;

public class AlunoManualMapper {
    
	public static PesquisaAlunoManualDTO toPesquisaManualAlunoDTO(AlunoManual entity) {
        return new PesquisaAlunoManualDTO(
            entity.getId(),
            entity.getAluno().getDadosPessoais().getDescricao(),
            entity.getLivro().getDescricao(),
            entity.isConcluido(),
            entity.getAluno().getClube() != null ? entity.getAluno().getClube().getNome().getNome() : null,
            entity.getAluno().getIgreja() != null ? entity.getAluno().getIgreja().getDescricao() : null
        );
    }
	
	public static AlunoManualTrilhasDTO toAlunoManualDTO(AlunoManual entity) {
		return new AlunoManualTrilhasDTO(
			entity.getId(),
			entity.getLivro().getTrilhas()
		);
	}

    public static List<PesquisaAlunoManualDTO> toDTOList(List<AlunoManual> entities) {
        return entities.stream().map(AlunoManualMapper::toPesquisaManualAlunoDTO).collect(Collectors.toList());
    }
}
