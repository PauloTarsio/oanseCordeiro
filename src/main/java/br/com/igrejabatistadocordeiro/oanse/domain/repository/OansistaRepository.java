package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.OansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;

public interface OansistaRepository extends CrudRepository<Oansista> {

	public List<Oansista> pesquisa(OansistaFilter filtro);

}
