package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Sessao;

public interface SessaoRepository extends CrudRepository<Sessao> {

	public List<Sessao> pesquisa(SessaoFilter filtro);
}
