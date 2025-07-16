package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;

public record IgrejaResumoDTO(Long id, String descricao, boolean ativo) {

	public static IgrejaResumoDTO from(Long id, String descricao, boolean ativo) {
		return new IgrejaResumoDTO(id, descricao, ativo);
	}

	public static IgrejaResumoDTO from(IgrejaDTO dto) {
		return new IgrejaResumoDTO(dto.id(), dto.dadosPessoais().descricao(), dto.ativo());
	}

	public static IgrejaResumoDTO from(Igreja igreja) {
		return new IgrejaResumoDTO(igreja.getId(), igreja.getDadosPessoais().getDescricao(), igreja.getAtivo());
	}
	
}
