package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.OansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Responsavel;

public interface OansistaRepository extends CrudRepository<Oansista> {

	public List<Oansista> pesquisa(OansistaFilter filtro);

	public Boolean existe(Oansista oansista);
	
	public Responsavel carregaPor(String telefone, String email);

}
