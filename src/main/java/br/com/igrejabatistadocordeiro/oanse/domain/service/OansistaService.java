package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.OansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;

public interface OansistaService {

	public Oansista carrega(Long id);
	public List<Oansista> pesquisa(OansistaFilter filtro);
	public List<Oansista> listaTudo();	
	public void salva(Oansista oansista);
	public void atualiza(Long id, Oansista oansista);
	public void remove(Long id);
	
}
