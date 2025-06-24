package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.TrilhaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Trilha;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.TrilhaRepository;

@Service
public class TrilhaServiceImpl implements TrilhaService {
	
	@Autowired
	private TrilhaRepository repository;

	@Override
	public List<Trilha> listaTudo() {
		return repository.listaTudo();
	}

	@Override
	public List<Trilha> pesquisa(TrilhaFilter filtro) {
		return repository.pesquisa(filtro);
	}

}
