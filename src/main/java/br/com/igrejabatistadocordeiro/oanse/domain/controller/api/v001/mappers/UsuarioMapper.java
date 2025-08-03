package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers;

import org.mapstruct.Mapper;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.UsuarioDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

	UsuarioDTO toDto(Usuario entity);
	
	Usuario toEntity(UsuarioDTO dto);
}
