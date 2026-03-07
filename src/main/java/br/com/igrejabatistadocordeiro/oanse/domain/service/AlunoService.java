package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.model.aluno.Aluno;

public interface AlunoService {

	Aluno carrega(Long id);
	
	List<Aluno> pesquisa(String nome);	
	
	void salva(Aluno aluno);
	
	void atualiza(Aluno aluno);
	
}
