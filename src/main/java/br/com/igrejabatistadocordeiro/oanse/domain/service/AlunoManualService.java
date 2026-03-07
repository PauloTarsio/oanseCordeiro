package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.AlunoManualTrilhasDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaAlunoManualDTO;

public interface AlunoManualService {
	
	public AlunoManualTrilhasDTO carrega(Long id);
	
	List<PesquisaAlunoManualDTO> pesquisa(String nomeAluno);

	public void salva(Long alunoId, Long livroId);

	void conclui(Long id);

}