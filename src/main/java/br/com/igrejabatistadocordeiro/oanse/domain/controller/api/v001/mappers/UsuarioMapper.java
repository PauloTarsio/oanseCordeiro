package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaUsuarioResumidoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.UsuarioDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.IgrejaRepository;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {

    @Autowired
    protected IgrejaRepository igrejaRepository;
	
    @Mapping(source = "igreja.id", target = "igrejaId")
	@Mapping(source = "igreja.dadosPessoais.descricao", target = "igrejaDescricao")
	public abstract UsuarioDTO toDto(Usuario entity);
	
	@Mapping(target = "igreja", ignore = true)
	public abstract Usuario toEntity(UsuarioDTO dto);
	
	@Mapping(source = "igreja.id", target = "igrejaId")
	@Mapping(source = "igreja.dadosPessoais.descricao", target = "igrejaDescricao")
	public abstract PesquisaUsuarioResumidoDTO toPesquisaUsuarioResumidoDTO(Usuario entity);
	
	@AfterMapping
    protected void afterMapping(@MappingTarget Usuario usuario, UsuarioDTO dto) {
        if (dto.igrejaId() != null) {
            Igreja igreja = igrejaRepository.findById(dto.igrejaId())
                .orElseThrow(() -> new IllegalArgumentException("Igreja com ID " + dto.igrejaId() + " não encontrada"));
            usuario.setIgreja(igreja);
        }
    }
}
