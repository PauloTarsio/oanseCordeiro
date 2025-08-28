package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Aluno;

public interface AlunoService {

	Aluno carrega(Long id);
	
	List<Aluno> pesquisa(String nome, Long clubeId);	
	
	void salva(Aluno aluno);
	
	void atualiza(Aluno aluno);
	
}
