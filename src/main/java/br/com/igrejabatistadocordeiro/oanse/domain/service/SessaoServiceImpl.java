package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Sessao;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.SessaoRepository;

@Service
public class SessaoServiceImpl implements SessaoService {
	
	@Autowired
	private SessaoRepository repository;

	@Override
	public List<Sessao> listaTudo() {
		return repository.listaTudo();
	}

	@Override
	public List<Sessao> pesquisa(SessaoFilter filtro) {		
		return repository.pesquisa(filtro);
	}

}
