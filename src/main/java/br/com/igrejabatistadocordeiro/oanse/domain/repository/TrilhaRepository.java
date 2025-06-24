package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.TrilhaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Trilha;

public interface TrilhaRepository extends CrudRepository<Trilha> {

	public List<Trilha> pesquisa(TrilhaFilter filtro);
}
