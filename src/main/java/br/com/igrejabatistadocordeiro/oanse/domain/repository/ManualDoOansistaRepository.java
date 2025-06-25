package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.ManualDoOansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.ManualDoOansista;

public interface ManualDoOansistaRepository extends CrudRepository<ManualDoOansista> {

	public List<ManualDoOansista> pesquisa(ManualDoOansistaFilter filtro);

	public ManualDoOansista carrega(Long idOansista, Long idManual);
}
