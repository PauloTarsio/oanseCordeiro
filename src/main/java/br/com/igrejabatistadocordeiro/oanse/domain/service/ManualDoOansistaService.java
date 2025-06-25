package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.ManualDoOansistaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.ManualDoOansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.ManualDoOansista;

public interface ManualDoOansistaService {

	public List<ManualDoOansista> listaTudo();
	public List<ManualDoOansista> pesquisa(ManualDoOansistaFilter filtro);
	public void salvar(ManualDoOansistaDTO dto);
	public void atualizar(ManualDoOansistaDTO dto);
	
}
