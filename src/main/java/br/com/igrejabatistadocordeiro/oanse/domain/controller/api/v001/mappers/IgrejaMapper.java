package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.IgrejaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaIgrejaResumidaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;

@Mapper(componentModel = "spring")
public interface IgrejaMapper {

	@Mapping(source = "dadosPessoais.descricao", target = "descricao") // mapeia dadosPessoais.descricao para campo descricao do DTO
	PesquisaIgrejaResumidaDTO toResumoDto(Igreja entity);

	Igreja toEntity(IgrejaDTO dto);	
	
	IgrejaDTO toDto(Igreja entity);
	
}