package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers;

import org.mapstruct.Mapper;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaUsuarioResumidoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.UsuarioDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {

	public abstract UsuarioDTO toDto(Usuario entity);
	
	public abstract Usuario toEntity(UsuarioDTO dto);
	
	public abstract PesquisaUsuarioResumidoDTO toPesquisaUsuarioResumidoDTO(Usuario entity);
}
