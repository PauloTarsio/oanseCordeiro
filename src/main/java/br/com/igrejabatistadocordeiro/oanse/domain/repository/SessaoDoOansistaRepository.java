package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoDoOansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.SessaoDoOansista;

public interface SessaoDoOansistaRepository extends CrudRepository<SessaoDoOansista> {

	public List<SessaoDoOansista> pesquisa(SessaoDoOansistaFilter filter);
}
