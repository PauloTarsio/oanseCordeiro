package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.IgrejaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.IgrejaResumoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;

@Mapper(componentModel = "spring")
public interface IgrejaMapper {

	@Mapping(source = "dadosPessoais.descricao", target = "descricao")
	IgrejaResumoDTO toResumoDto(Igreja entity);

	Igreja toEntity(IgrejaDTO dto);	
	
	IgrejaDTO toDto(Igreja entity);
	
}
