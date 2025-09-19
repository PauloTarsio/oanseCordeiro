package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.AlunoManualTrilhasDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaAlunoManualDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers.AlunoManualMapper;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Aluno;
import br.com.igrejabatistadocordeiro.oanse.domain.model.AlunoManual;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Livro;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
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
		Aluno aluno = getAluno(alunoId);
		Livro livro = getLivro(livroId);
		if (alunoManualRepository.existsByAlunoAndLivro(aluno, livro))
			throw new IllegalArgumentException("Associação entre Aluno e Livro já existe!");
		alunoManualRepository.save(new AlunoManual(aluno, livro));
    }
    

	@Override
	public AlunoManualTrilhasDTO carrega(Long id) {
		AlunoManual alunoManual = alunoManualRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Associação não encontrada com ID: " + id));		
		return AlunoManualMapper.toAlunoManualDTO(alunoManual);
	}

    @Override
    public List<PesquisaAlunoManualDTO> pesquisa(Long alunoId, Long livroId) {
        boolean admin = getUsuarioLogado().isAdministrador();
        boolean secretario = getUsuarioLogado().isSecretario();

        List<AlunoManual> lista;

        if (admin) {
            lista = pesquisaAdmin(alunoId, livroId);
        } else if (secretario) {
            lista = pesquisaSecretario(alunoId, livroId);
        } else {
            throw new IllegalArgumentException("Usuário sem permissão para consultar manuais de alunos.");
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
    
    private List<AlunoManual> pesquisaAdmin(Long alunoId, Long livroId) {
        if (alunoId == null && livroId == null) {
            return alunoManualRepository.findAll();
        }
        if (alunoId != null && livroId == null) {
            return alunoManualRepository.findByAluno(getAluno(alunoId));
        }
        if (alunoId == null) {
            return alunoManualRepository.findByLivro(getLivro(livroId));
        }
        return alunoManualRepository.findByAlunoAndLivro(getAluno(alunoId), getLivro(livroId));
    }

    private List<AlunoManual> pesquisaSecretario(Long alunoId, Long livroId) {
        Long igrejaId = getUsuarioLogado().getIgreja().getId();

        if (alunoId == null && livroId == null) {
            return alunoManualRepository.findByAlunoIgrejaId(igrejaId);
        }
        if (alunoId != null && livroId == null) {
            return alunoManualRepository.findByAlunoIdAndAlunoIgrejaId(alunoId, igrejaId);
        }
        if (alunoId == null) {
            return alunoManualRepository.findByAlunoIgrejaIdAndLivroId(igrejaId, livroId);
        }
        return alunoManualRepository.findByAlunoIdAndLivroIdAndAlunoIgrejaId(alunoId, livroId, igrejaId);
    }

    private Aluno getAluno(Long alunoId) {
        return alunoRepository.findById(alunoId)
            .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com ID: " + alunoId));
    }

    private Livro getLivro(Long livroId) {
        return livroRepository.findById(livroId)
            .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado com ID: " + livroId));
    }
    
	private Usuario getUsuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (Usuario) authentication.getDetails();
	}
    
}