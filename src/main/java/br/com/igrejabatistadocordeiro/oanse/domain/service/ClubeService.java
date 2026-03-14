package br.com.igrejabatistadocordeiro.oanse.domain.service;

import br.com.igrejabatistadocordeiro.oanse.domain.model.clube.Clube;

public interface ClubeService {

	public Clube carrega(Long id);
	public Clube carregaPorAluno(Long alunoId);
}
