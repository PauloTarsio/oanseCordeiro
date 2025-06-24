package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.ManualFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.ManualRepository;

@Service
public class ManualServiceImpl implements ManualService {

	@Autowired
	private ManualRepository repository;
	
	@Override
	public List<Manual> listaTudo() {
		return repository.listaTudo();
	}

	@Override
	public List<Manual> pesquisa(ManualFilter filtro) {
		return repository.pesquisa(filtro);
	}

}
