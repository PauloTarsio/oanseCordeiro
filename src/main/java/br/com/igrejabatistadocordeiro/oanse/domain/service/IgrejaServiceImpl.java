package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.RegistroDuplicadoException;
import br.com.igrejabatistadocordeiro.oanse.domain.model.DadosPessoais;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.IgrejaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.util.StringUtils;

@Service
public class IgrejaServiceImpl implements IgrejaService {

	private static final String O_ID_NÃO_DEVE_SER_INFORMADO_AO_SALVAR = "O ID da igreja não deve ser informado ao salvar.";
	private static final String O_ID_DEVE_SER_INFORMADO_AO_ATUALIZAR = "O ID da igreja deve ser informado ao atualizar.";
	private static final String MSG_IGREJA_NAO_ENCONTRADA = "Igreja não encontrada.";

	private IgrejaRepository repository;

	public IgrejaServiceImpl(IgrejaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Igreja carrega(Long id) {
		if (getUsuarioLogado().isSecretario() && id.compareTo(getUsuarioLogado().getIgreja().getId()) != 0)
			throw new IllegalArgumentException("Não tem permissão para carregar igreja diferente.");
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(MSG_IGREJA_NAO_ENCONTRADA));
	}

	@SuppressWarnings("removal")
	@Override
	public List<Igreja> pesquisa(String descricao) {
		Usuario usuarioLogado = getUsuarioLogado();
		Specification<Igreja> spec = Specification.where(null);
		if (StringUtils.isNotBlank(descricao))
			spec = spec.and((root, query, cb) ->cb.like(cb.lower(root.get("dadosPessoais").get("descricao")),"%" + descricao.toLowerCase() + "%"));
		if (getUsuarioLogado().isSecretario())
			spec = spec.and((root, query, cb) -> cb.equal(root.get("id"), usuarioLogado.getIgreja().getId()));
		return  repository.findAll(spec);
	}


	@Override
	public void salva(Igreja igreja) {
		if (igreja.getId() != null)
			throw new IllegalArgumentException(O_ID_NÃO_DEVE_SER_INFORMADO_AO_SALVAR);
		if (igreja.getDadosPessoais() == null || StringUtils.isBlank(igreja.getDadosPessoais().getDescricao()))
			throw new IllegalArgumentException("Dados pessoais da igreja são obrigatórios.");
		if (igreja.getDadosPessoais().getEndereco() == null)
			throw new IllegalArgumentException("Endereço da igreja é obrigatório.");

		Igreja igrejaEncontrada = buscarPorRgOuCpfOuCnpj(igreja.getDadosPessoais());
		if (igrejaEncontrada != null) {
			String descricao = igrejaEncontrada.getDadosPessoais().getDescricao();
			String rg = igrejaEncontrada.getDadosPessoais().getRg();
			String cpf = igrejaEncontrada.getDadosPessoais().getCpf();
			String cnpj = igrejaEncontrada.getDadosPessoais().getCnpj();
			throw new RegistroDuplicadoException("CONFLITO: Já existe uma igreja com os dados pessoais: "
					+ descricao.toUpperCase() + (StringUtils.isNotBlank(rg) ? ", RG: " + rg : "")
					+ (StringUtils.isNotBlank(cpf) ? ", CPF: " + cpf : "")
					+ (StringUtils.isNotBlank(cnpj) ? ", CNPJ: " + cnpj : ""));
		}
		repository.save(igreja);
	}

	@Override
	public void atualiza(Igreja igreja) {
		if (igreja.getId() == null)
			throw new IllegalArgumentException(O_ID_DEVE_SER_INFORMADO_AO_ATUALIZAR);
		if (igreja.getDadosPessoais() == null || StringUtils.isBlank(igreja.getDadosPessoais().getDescricao()))
			throw new IllegalArgumentException("Dados pessoais da igreja são obrigatórios.");
		if (igreja.getDadosPessoais().getEndereco() == null)
			throw new IllegalArgumentException("Endereço da igreja é obrigatório.");

		Igreja igrejaEncontrada = buscarPorRgOuCpfOuCnpj(igreja.getDadosPessoais());
		if (igrejaEncontrada != null && !igrejaEncontrada.getId().equals(igreja.getId())) {
			String descricao = igrejaEncontrada.getDadosPessoais().getDescricao();
			String rg = igrejaEncontrada.getDadosPessoais().getRg();
			String cpf = igrejaEncontrada.getDadosPessoais().getCpf();
			String cnpj = igrejaEncontrada.getDadosPessoais().getCnpj();
			throw new RegistroDuplicadoException("CONFLITO: Já existe uma igreja com os dados pessoais: "
					+ descricao.toUpperCase() + (StringUtils.isNotBlank(rg) ? ", RG: " + rg : "")
					+ (StringUtils.isNotBlank(cpf) ? ", CPF: " + cpf : "")
					+ (StringUtils.isNotBlank(cnpj) ? ", CNPJ: " + cnpj : ""));
		}

		Igreja igrejaBase = carrega(igreja.getId());
		if (temMudancaNoTipoPessoa(igreja, igrejaBase))
			throw new IllegalArgumentException("Não é permitido alterar o tipo de pessoa (Física/Jurídica) da igreja.");

		// Atualiza os IDs dos dados pessoais e endereço para garantir integridade
		igreja.getDadosPessoais().setId(igrejaBase.getDadosPessoais().getId());
		igreja.getDadosPessoais().getEndereco().setId(igrejaBase.getDadosPessoais().getEndereco().getId());

		repository.save(igreja);
	}

	private Igreja buscarPorRgOuCpfOuCnpj(DadosPessoais dadosPessoais) {
		Igreja igrejaEncontrada = null;
		if (StringUtils.isNotBlank(dadosPessoais.getRg()))
			igrejaEncontrada = repository.findByDadosPessoaisRg(dadosPessoais.getRg()).orElse(null);
		if (igrejaEncontrada == null && StringUtils.isNotBlank(dadosPessoais.getCpf()))
			igrejaEncontrada = repository.findByDadosPessoaisCpf(dadosPessoais.getCpf()).orElse(null);
		if (igrejaEncontrada == null && StringUtils.isNotBlank(dadosPessoais.getCnpj()))
			igrejaEncontrada = repository.findByDadosPessoaisCnpj(dadosPessoais.getCnpj()).orElse(null);
		return igrejaEncontrada;
	}
	
	private boolean temMudancaNoTipoPessoa(Igreja igreja, Igreja igrejaBase) {
		boolean igrejaTemCPFouRG = StringUtils.isNotBlank(igreja.getDadosPessoais().getRg())
					|| StringUtils.isNotBlank(igreja.getDadosPessoais().getCpf());
		boolean igrejaTemCNPJ = StringUtils.isNotBlank(igreja.getDadosPessoais().getCnpj());
		boolean igrejaBaseTemCPFouRG = StringUtils.isNotBlank(igrejaBase.getDadosPessoais().getRg())
					|| StringUtils.isNotBlank(igrejaBase.getDadosPessoais().getCpf());
		boolean igrejaBaseTemCNPJ = StringUtils.isNotBlank(igrejaBase.getDadosPessoais().getCnpj());

		// Mudança ocorre se um é física e o outro jurídica
		return (igrejaTemCPFouRG && igrejaBaseTemCNPJ) || (igrejaTemCNPJ && igrejaBaseTemCPFouRG || !igreja.getDadosPessoais().getTipo().equals(igrejaBase.getDadosPessoais().getTipo()));
	}
	
	private Usuario getUsuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (Usuario) authentication.getDetails();
	}

}