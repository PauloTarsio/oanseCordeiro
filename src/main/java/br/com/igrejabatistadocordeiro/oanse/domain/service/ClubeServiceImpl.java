package br.com.igrejabatistadocordeiro.oanse.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.model.clube.Clube;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.ClubeRepository;

@Service
public class ClubeServiceImpl implements ClubeService {
	
	@Autowired
    private ClubeRepository clubeRepository;

	@Override
	public Clube carrega(Long id) {
		return clubeRepository.findById(id).orElse(null);                
	}

	@Override
	public Clube carregaPorAluno(Long alunoId) {		
		return clubeRepository.findByAlunosId(alunoId).orElse(null);
	}

}
