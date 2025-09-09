package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.AlunoManualDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers.AlunoManualMapper;
import br.com.igrejabatistadocordeiro.oanse.domain.model.AlunoManual;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.AlunoManualRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.AlunoRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.LivroRepository;

@Service
public class AlunoManualServiceImpl implements AlunoManualService {
    
	@Autowired
    private AlunoManualRepository alunoManualRepository;	
	@Autowired
    private AlunoRepository alunoRepository;
	@Autowired
	private LivroRepository livroRepository;

    @Override
    public void salva(Long alunoId, Long livroId) {
		var aluno = alunoRepository.findById(alunoId)
				.orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com ID: " + alunoId));		
		var livro = livroRepository.findById(livroId)
				.orElseThrow(() -> new IllegalArgumentException("Livro não encontrado com ID: " + livroId));		
		if (alunoManualRepository.existsByAlunoAndLivro(aluno, livro))
			throw new IllegalArgumentException("Associação entre Aluno e Livro já existe!");		
		alunoManualRepository.save(new AlunoManual(aluno, livro));        
    }

    @Override
    public List<AlunoManualDTO> pesquisa(Long alunoId, Long livroId) {
        List<AlunoManual> lista;
        if (alunoId == null && livroId == null) {
            lista = alunoManualRepository.findAll();
        } else if (alunoId != null && livroId == null) {
            var aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com ID: " + alunoId));
            lista = alunoManualRepository.findByAluno(aluno);
        } else if (alunoId == null && livroId != null) {
            var livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado com ID: " + livroId));
            lista = alunoManualRepository.findByLivro(livro);
        } else {
            var aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com ID: " + alunoId));
            var livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado com ID: " + livroId));
            lista = alunoManualRepository.findByAlunoAndLivro(aluno, livro);
        }
        return AlunoManualMapper.toDTOList(lista);
    }

    @Override
    public void conclui(Long id) {
        var associacao = alunoManualRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Associação não encontrada com ID: " + id));
        if (associacao.isConcluido())
            throw new IllegalArgumentException("Esta associação entre aluno e manual já está concluída!");
        associacao.setDataConclusao(java.time.LocalDate.now());
        alunoManualRepository.save(associacao);
    }
    
}