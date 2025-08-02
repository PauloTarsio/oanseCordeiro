package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.AlunoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaAlunoResumidoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Aluno;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.IgrejaRepository;

@Mapper(componentModel = "spring")
public abstract class AlunoMapper {

    @Autowired
    protected IgrejaRepository igrejaRepository;

    @Mappings({
        @Mapping(source = "dadosPessoais.descricao", target = "descricao"),
        @Mapping(source = "igreja.dadosPessoais.descricao", target = "igreja"),
        @Mapping(source = "ativo", target = "ativo"),
        @Mapping(source = "id", target = "id")
    })
    public abstract PesquisaAlunoResumidoDTO toResumoDto(Aluno entity);

    public abstract List<PesquisaAlunoResumidoDTO> toResumoDtoList(List<Aluno> entityList);

    @Mapping(target = "igreja", ignore = true)
    @Mapping(target = "dadosPessoais", source = "dadosPessoais")
    public abstract Aluno toEntity(AlunoDTO dto);

    @Mapping(source = "igreja.id", target = "igrejaId")
    public abstract AlunoDTO toDto(Aluno entity);

    @AfterMapping
    protected void afterMapping(@MappingTarget Aluno aluno, AlunoDTO dto) {
        if (dto.igrejaId() != null) {
            Igreja igreja = igrejaRepository.findById(dto.igrejaId())
                .orElseThrow(() -> new IllegalArgumentException("Igreja com ID " + dto.igrejaId() + " não encontrada"));
            aluno.setIgreja(igreja);
        }
    }
}
