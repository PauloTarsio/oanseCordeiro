package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Clube;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;

public interface ManualRepository extends CrudRepository<Manual> {

	public List<Manual> pesquisa(Clube clube);
	
}
