package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers;

import org.mapstruct.Mapper;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.AlunoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Aluno;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

	Aluno toEntity(AlunoDTO dto);
	
	AlunoDTO toDto(Aluno entity);
	
}
