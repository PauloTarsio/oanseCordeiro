package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.AlunoManualDTO;

public interface AlunoManualService {
	
	List<AlunoManualDTO> pesquisa(Long alunoId, Long livroId);

	public void salva(Long alunoId, Long livroId);

	void conclui(Long id);
}