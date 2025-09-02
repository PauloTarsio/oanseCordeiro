package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.RegistroDuplicadoException;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Aluno;
import br.com.igrejabatistadocordeiro.oanse.domain.model.DadosPessoais;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.AlunoRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.IgrejaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.util.StringUtils;

@Service
public class AlunoServiceImpl implements AlunoService {

	private static final String O_ID_NÃO_DEVE_SER_INFORMADO_AO_SALVAR = "O ID do aluno não deve ser informado ao salvar.";
	private static final String O_ID_DEVE_SER_INFORMADO_AO_ATUALIZAR = "O ID do aluno deve ser informado ao atualizar.";
	private static final String MSG_ALUNO_NAO_ENCONTRADO = "Aluno não encontrado.";
	private static final String MSG_IGREJA_NAO_ENCONTRADA = "Igreja não encontrada.";
	private static final String CONFLITO_DADOS_PESSOAIS = "Número de documento já cadastrado.";

	private AlunoRepository repository;
	private IgrejaRepository igrejaRepository;

	public AlunoServiceImpl(AlunoRepository repository, IgrejaRepository igrejaRepository) {
		this.repository = repository;
		this.igrejaRepository = igrejaRepository;
	}

	@Override
	public Aluno carrega(Long id) {
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(MSG_ALUNO_NAO_ENCONTRADO));
	}

	@Override
	public List<Aluno> pesquisa(String descricao, Long idClube) {
		Usuario usuarioLogado = getUsuarioLogado();
		Specification<Aluno> spec = Specification.anyOf();
		if (StringUtils.isNotBlank(descricao))
			spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("dadosPessoais").get("descricao")),"%" + descricao.toLowerCase() + "%"));
		if (idClube != null)
			spec = spec.and((root, query, cb) -> cb.equal(root.get("clube").get("id"), idClube));
		if (!usuarioLogado.isAdministrador())
			spec = spec.and((root, query, cb) -> cb.equal(root.get("igreja"), usuarioLogado.getIgreja()));
		return repository.findAll(spec);
	}

	@Override
	public void salva(Aluno aluno) {
		if (aluno.getId() != null)
			throw new IllegalArgumentException(O_ID_NÃO_DEVE_SER_INFORMADO_AO_SALVAR);
		Aluno alunoEncontrado = pesquisaDocumentos(aluno.getDadosPessoais());
		if (alunoEncontrado != null) {
			throw new RegistroDuplicadoException(CONFLITO_DADOS_PESSOAIS);
		}
		Igreja igrejaEncontrada = pesquisaIgreja(aluno.getIgreja().getId());
		if (igrejaEncontrada == null)
			throw new IllegalArgumentException(MSG_IGREJA_NAO_ENCONTRADA);
		aluno.setIgreja(igrejaEncontrada);
		repository.save(aluno);
	}

	@Override
	public void atualiza(Aluno aluno) {
		if (aluno.getId() == null)
			throw new IllegalArgumentException(O_ID_DEVE_SER_INFORMADO_AO_ATUALIZAR);
		Aluno alunoEncontrado = pesquisaDocumentos(aluno.getDadosPessoais());
		if (alunoEncontrado != null && alunoEncontrado.getId() != aluno.getId())
			throw new RegistroDuplicadoException(CONFLITO_DADOS_PESSOAIS);
		alunoEncontrado = carrega(aluno.getId());
		aluno.getDadosPessoais().setId(alunoEncontrado.getDadosPessoais().getId());
		aluno.getDadosPessoais().getEndereco().setId(alunoEncontrado.getDadosPessoais().getEndereco().getId());
		repository.save(aluno);
	}

	private Aluno pesquisaDocumentos(DadosPessoais dadosPessoais) {
		Aluno alunoEncontrado = null;
		if (StringUtils.isNotBlank(dadosPessoais.getRg()))
			alunoEncontrado = repository.findByDadosPessoaisRg(dadosPessoais.getRg()).orElse(null);
		if (alunoEncontrado == null && StringUtils.isNotBlank(dadosPessoais.getCpf()))
			alunoEncontrado = repository.findByDadosPessoaisCpf(dadosPessoais.getCpf()).orElse(null);
		if (alunoEncontrado == null && StringUtils.isNotBlank(dadosPessoais.getCnpj()))
			alunoEncontrado = repository.findByDadosPessoaisCnpj(dadosPessoais.getCnpj()).orElse(null);
		return alunoEncontrado;
	}

	private Igreja pesquisaIgreja(Long id) {
		return igrejaRepository.findById(id).orElse(null);
	}

	private Usuario getUsuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (Usuario) authentication.getDetails();
	}
}
