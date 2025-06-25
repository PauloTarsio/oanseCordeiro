package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.ManualFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;

public interface ManualService {

	public List<Manual> listaTudo();
	public List<Manual> pesquisa(ManualFilter filtro);
	public Manual carrega(Long idManual);
	
}
