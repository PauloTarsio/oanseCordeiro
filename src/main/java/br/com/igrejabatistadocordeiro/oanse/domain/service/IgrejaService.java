package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;

public interface IgrejaService {

	Igreja carrega(Long id);

	List<Igreja> pesquisa(String descricao, String rg, String cpf, String cnpj, boolean ativo);

	void salva(Igreja igreja);

	void atualiza(Igreja igreja);

	void inativa(Long id);

	void ativa(Long id);

}
