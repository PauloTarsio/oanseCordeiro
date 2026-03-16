package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.AlunoManualTrilhasDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaAlunoManualDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers.AlunoManualMapper;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Livro;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
import br.com.igrejabatistadocordeiro.oanse.domain.model.aluno.Aluno;
import br.com.igrejabatistadocordeiro.oanse.domain.model.aluno.AlunoManual;
import br.com.igrejabatistadocordeiro.oanse.domain.model.recursos.PerfilDoUsuario;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.AlunoManualRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.AlunoRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.LivroRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.util.StringUtils;

@Service
public class AlunoManualServiceImpl implements AlunoManualService {
    
	@Autowired
    private AlunoManualRepository alunoManualRepository;
	@Autowired
	private AlunoSecaoService alunoSecaoService;
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
		try {
			AlunoManual alunoManual = alunoManualRepository.save(new AlunoManual(aluno, livro));
			alunoSecaoService.populaSecoes(alunoManual);
		} catch (Exception e) {
			throw new IllegalArgumentException("Ocorreu um erro durante a associação entre Aluno e Livro!");
		}
    }    

	@Override
	public AlunoManualTrilhasDTO carrega(Long id) {
		AlunoManual alunoManual = alunoManualRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Associação não encontrada com ID: " + id));		
		return AlunoManualMapper.toAlunoManualDTO(alunoManual);
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

	@Override
	public List<PesquisaAlunoManualDTO> pesquisa(String nomeAluno) {
		List<AlunoManual> lista;
        if (PerfilDoUsuario.ADMIN.equals(getUsuarioLogado().getPerfil())) {
        	if (StringUtils.isBlank(nomeAluno))
        		lista = alunoManualRepository.findAll();
        	else
        		lista = alunoManualRepository.findByAlunoDadosPessoaisDescricaoContainingIgnoreCase(nomeAluno);
        } else if (PerfilDoUsuario.SECRETARIO.equals(getUsuarioLogado().getPerfil())) {
        	if (StringUtils.isBlank(nomeAluno))
        		lista = alunoManualRepository.findByAlunoIgrejaId(getUsuarioLogado().getIgreja().getId());
        	else
        		lista = alunoManualRepository.findByAlunoDadosPessoaisDescricaoContainingIgnoreCaseAndAlunoIgrejaId(nomeAluno, getUsuarioLogado().getIgreja().getId());
        } else {
            throw new IllegalArgumentException("Usuário sem permissão para consultar manuais de alunos.");
        }
        return AlunoManualMapper.toDTOList(lista);
	}
    
}