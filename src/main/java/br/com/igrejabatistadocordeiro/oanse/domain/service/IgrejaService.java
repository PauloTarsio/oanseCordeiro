package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;

public interface IgrejaService {

	Igreja carrega(Long id);

	List<Igreja> pesquisa(String descricao);

	void salva(Igreja igreja);

	void atualiza(Igreja igreja);
}
