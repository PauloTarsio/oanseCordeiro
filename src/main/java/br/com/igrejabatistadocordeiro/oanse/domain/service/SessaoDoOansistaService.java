package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.SessaoDoOansistaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoDoOansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.SessaoDoOansista;

public interface SessaoDoOansistaService {

	public List<SessaoDoOansista> listaTudo();
	public List<SessaoDoOansista> pesquisa(SessaoDoOansistaFilter filtro);	
	void concluirSessao(SessaoDoOansistaDTO dto);

}
