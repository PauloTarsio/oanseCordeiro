package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.RegistroDuplicadoException;
import br.com.igrejabatistadocordeiro.oanse.domain.model.DadosPessoais;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;
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
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(MSG_IGREJA_NAO_ENCONTRADA));
	}

	@SuppressWarnings("removal")
	@Override
	public List<Igreja> pesquisa(String descricao) {
		Specification<Igreja> spec = Specification.where(null);
		if (StringUtils.isNotBlank(descricao)) {
			spec = spec.and((root, query, cb) -> 
				cb.like(
					cb.lower(root.get("dadosPessoais").get("descricao")),
					"%" + descricao.toLowerCase() + "%"
				)
			);
		}
		return  repository.findAll(spec);
	}


	@Override
	public void salva(Igreja igreja) {
		if (igreja.getId() != null)
			throw new IllegalArgumentException(O_ID_NÃO_DEVE_SER_INFORMADO_AO_SALVAR);
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
		Igreja igrejaEncontrada = buscarPorRgOuCpfOuCnpj(igreja.getDadosPessoais());
		if (igrejaEncontrada != null && igrejaEncontrada.getId() != igreja.getId()) {
			String descricao = igrejaEncontrada.getDadosPessoais().getDescricao();
			String rg = igrejaEncontrada.getDadosPessoais().getRg();
			String cpf = igrejaEncontrada.getDadosPessoais().getCpf();
			String cnpj = igrejaEncontrada.getDadosPessoais().getCnpj();
			throw new RegistroDuplicadoException("CONFLITO: Já existe uma igreja com os dados pessoais: "
					+ descricao.toUpperCase() + (StringUtils.isNotBlank(rg) ? ", RG: " + rg : "")
					+ (StringUtils.isNotBlank(cpf) ? ", CPF: " + cpf : "")
					+ (StringUtils.isNotBlank(cnpj) ? ", CNPJ: " + cnpj : ""));
		}
		if (igrejaEncontrada == null)
			igrejaEncontrada = carrega(igreja.getId());
		igreja.getDadosPessoais().setId(igrejaEncontrada.getDadosPessoais().getId());
		igreja.getDadosPessoais().getEndereco().setId(igrejaEncontrada.getDadosPessoais().getEndereco().getId());
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

}
