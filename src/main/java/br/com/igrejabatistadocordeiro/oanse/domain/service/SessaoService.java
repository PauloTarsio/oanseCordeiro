package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Sessao;

public interface SessaoService {

	public List<Sessao> listaTudo();
	public List<Sessao> pesquisa(SessaoFilter filtro);
	
}
