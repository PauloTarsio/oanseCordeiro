package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
	private static final String CONFLITO_DADOS_PESSOAIS = "Número de documento já cadastrado. Pesquise RG, CPF ou CNPJ.";
	
	private IgrejaRepository repository;
	
	public IgrejaServiceImpl(IgrejaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Igreja carrega(Long id) {
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(MSG_IGREJA_NAO_ENCONTRADA));
	}
	
	@Override
	public List<Igreja> pesquisa(String descricao, String rg, String cpf, String cnpj, boolean ativo) {
		Igreja igreja = new Igreja();
		igreja.setAtivo(ativo);
		DadosPessoais dadosPessoais = new DadosPessoais();
		dadosPessoais.setDescricao(descricao);
		dadosPessoais.setRg(rg);
		dadosPessoais.setCpf(cpf);
		dadosPessoais.setCnpj(cnpj);
		igreja.setDadosPessoais(dadosPessoais);		
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreNullValues()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);		
		Example<Igreja> example = Example.of(igreja, matcher);		
		return repository.findAll(example);
		
	}	

	@Override
	public void salva(Igreja igreja) {
		if (igreja.getId() != null)
			throw new IllegalArgumentException(O_ID_NÃO_DEVE_SER_INFORMADO_AO_SALVAR);		
		Igreja igrejaEncontrada = buscarPorRgOuCpfOuCnpj(igreja.getDadosPessoais());		
		if (igrejaEncontrada != null)
			throw new RegistroDuplicadoException(CONFLITO_DADOS_PESSOAIS);		
		repository.save(igreja);
	}

	@Override
	public void atualiza(Igreja igreja) {
		if (igreja.getId() == null)
			throw new IllegalArgumentException(O_ID_DEVE_SER_INFORMADO_AO_ATUALIZAR);		
		Igreja igrejaEncontrada = buscarPorRgOuCpfOuCnpj(igreja.getDadosPessoais());		
		if (igrejaEncontrada != null && igrejaEncontrada.getId() != igreja.getId())
			throw new RegistroDuplicadoException(CONFLITO_DADOS_PESSOAIS);		
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
		else if (igrejaEncontrada == null && StringUtils.isNotBlank(dadosPessoais.getCpf()))
			igrejaEncontrada = repository.findByDadosPessoaisCpf(dadosPessoais.getCpf()).orElse(null);
		else if (igrejaEncontrada == null && StringUtils.isNotBlank(dadosPessoais.getCnpj()))
			igrejaEncontrada = repository.findByDadosPessoaisCnpj(dadosPessoais.getCnpj()).orElse(null);
		return igrejaEncontrada;
	}

}
