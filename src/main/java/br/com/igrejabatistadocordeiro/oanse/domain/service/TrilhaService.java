package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.TrilhaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Trilha;

public interface TrilhaService {

	public List<Trilha> listaTudo();
	public List<Trilha> pesquisa(TrilhaFilter filtro);
	
}
