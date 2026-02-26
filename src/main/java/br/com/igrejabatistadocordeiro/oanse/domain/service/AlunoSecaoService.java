package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaAlunoSecaoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.model.aluno.AlunoManual;

public interface AlunoSecaoService {

	public void populaSecoes(AlunoManual alunoManual);
	
	public void concluiSecao(Long alunoManualId, Long secaoId);

	public List<PesquisaAlunoSecaoDTO> carrega(Long alunoManualId, Long secaoId);

}
