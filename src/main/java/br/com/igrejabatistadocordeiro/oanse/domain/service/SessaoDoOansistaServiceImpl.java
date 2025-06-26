package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoDoOansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.SessaoDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.SessaoDoOansistaRepository;

@Service
public class SessaoDoOansistaServiceImpl implements SessaoDoOansistaService {

	@Autowired
	private SessaoDoOansistaRepository sessaoDoOansistaRepository;
	
	@Override
	public List<SessaoDoOansista> listaTudo() {
		return sessaoDoOansistaRepository.listaTudo();
	}

	@Override
	public List<SessaoDoOansista> pesquisa(SessaoDoOansistaFilter filtro) {
		return sessaoDoOansistaRepository.pesquisa(filtro);
	}

}
