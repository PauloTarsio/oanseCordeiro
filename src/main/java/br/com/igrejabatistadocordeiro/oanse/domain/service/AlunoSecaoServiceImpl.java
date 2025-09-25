package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaAlunoSecaoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.model.AlunoManual;
import br.com.igrejabatistadocordeiro.oanse.domain.model.AlunoSecao;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Livro;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.AlunoSecaoRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.LivroRepository;

@Service
public class AlunoSecaoServiceImpl implements AlunoSecaoService {
	
	@Autowired
	private AlunoSecaoRepository alunoSecaoRepository;
	@Autowired
	private LivroRepository livroRepository;
	
	@Override
	public void populaSecoes(AlunoManual alunoManual) {
		Livro livro = livroRepository.findById(alunoManual.getLivro().getId())
			.orElseThrow(() -> new IllegalArgumentException("Livro não encontrado com ID: " + alunoManual.getLivro().getId()));

		livro.getTrilhas().forEach(trilha -> {
			trilha.getSecoes().forEach(secao -> {
				AlunoSecao novaSecao = new AlunoSecao();
				novaSecao.setAlunoManualId(alunoManual.getId());
				novaSecao.setTrilhaId(trilha.getId());
				novaSecao.setSecaoId(secao.getId());
				novaSecao.setDataConclusao(null); // Inicialmente, a seção não está concluída
				novaSecao.setLivroDescricao(livro.getDescricao());
				novaSecao.setTrilhaDescricao(trilha.getDescricao());
				novaSecao.setSecaoDescricao(String.valueOf(secao.getNumero()));
				alunoSecaoRepository.save(novaSecao);
			});
		});		
	}

	@Override
	public void concluiSecao(Long alunoManualId, Long secaoId) {
		AlunoSecao alunoSecao = alunoSecaoRepository.findByAlunoManualIdAndSecaoId(alunoManualId, secaoId)
			.orElseThrow(() -> new IllegalArgumentException("Associação entre AlunoManual e Seção não encontrada!"));
		alunoSecao.setDataConclusao(LocalDate.now());
		alunoSecaoRepository.save(alunoSecao);
	}

	@Override
	public List<PesquisaAlunoSecaoDTO> carrega(Long alunoManualId, Long secaoId) {
		List<PesquisaAlunoSecaoDTO> resultado = new ArrayList<PesquisaAlunoSecaoDTO>();
		
		if (alunoManualId == null && secaoId == null)
			alunoSecaoRepository.findAll().forEach(as -> resultado.add(new PesquisaAlunoSecaoDTO(as)));

		if (alunoManualId != null && secaoId == null)
			alunoSecaoRepository.findByAlunoManualId(alunoManualId)
				.stream()
				.map(as -> resultado.add(new PesquisaAlunoSecaoDTO(as)))
				.collect(Collectors.toList());
		
		if (alunoManualId == null && secaoId != null)
			alunoSecaoRepository.findBySecaoId(secaoId)
				.stream()
				.map(as -> resultado.add(new PesquisaAlunoSecaoDTO(as)))
				.collect(Collectors.toList());	

		if (alunoManualId != null && secaoId != null)
			alunoSecaoRepository.findByAlunoManualIdAndSecaoId(alunoManualId, secaoId)
				.ifPresent(as -> resultado.add(new PesquisaAlunoSecaoDTO(as)));			
		
		return resultado;
	}

}