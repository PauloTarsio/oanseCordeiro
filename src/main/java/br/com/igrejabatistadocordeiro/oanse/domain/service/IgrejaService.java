package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.DadosPessoaisDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.EnderecoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.IgrejaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.IgrejaResumoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.RegistroDuplicadoException;
import br.com.igrejabatistadocordeiro.oanse.domain.model.DadosPessoais;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.IgrejaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.util.StringUtils;

@Service
public class IgrejaService {
	
	private IgrejaRepository igrejaRepository;
	
	public IgrejaService(IgrejaRepository igrejaRepository) {
		this.igrejaRepository = igrejaRepository;
	}
	
	public List<Igreja> pesquisaByExample(String descricao, String rg, String cpf, String cnpj, boolean ativo) {
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
		
		return igrejaRepository.findAll(example);
		
	}

	public void salva(Igreja igreja) {
		if (igreja.getId() != null)
			throw new IllegalArgumentException("O ID da igreja não deve ser informado ao salvar uma nova igreja.");
		
		Igreja igrejaEncontrada = buscarPorRgOuCpfOuCnpj(igreja.getDadosPessoais());
		
		if (igrejaEncontrada != null)
			throw new RegistroDuplicadoException("Igreja com RG, CPF ou CNPJ já cadastrada.");
		
		igrejaRepository.save(igreja);
	}

	public void atualiza(Igreja igreja) {
		if (igreja.getId() == null)
			throw new IllegalArgumentException("O ID da igreja deve ser informado ao atualizar uma igreja.");
		
		Igreja igrejaEncontrada = buscarPorRgOuCpfOuCnpj(igreja.getDadosPessoais());
		
		if (igrejaEncontrada != null && igrejaEncontrada.getId() != igreja.getId())
			throw new RegistroDuplicadoException("Igreja com RG, CPF ou CNPJ já cadastrada.");
		
		if (igrejaEncontrada == null)
			igrejaEncontrada = buscarPorId(igreja.getId());
				
		igreja.getDadosPessoais().setId(igrejaEncontrada.getDadosPessoais().getId());
		igreja.getDadosPessoais().getEndereco().setId(igrejaEncontrada.getDadosPessoais().getEndereco().getId());
		
		igrejaRepository.save(igreja);
	}
	
	private Igreja buscarPorId(Long id) {
		return igrejaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Igreja não encontrada com o ID: " + id));
	}

	private Igreja buscarPorRgOuCpfOuCnpj(DadosPessoais dadosPessoais) {
		Igreja igrejaEncontrada = null;
		if (StringUtils.isNotBlank(dadosPessoais.getRg()))
			igrejaEncontrada = igrejaRepository.findByDadosPessoaisRg(dadosPessoais.getRg()).orElse(null);
		else if (igrejaEncontrada == null && StringUtils.isNotBlank(dadosPessoais.getCpf()))
			igrejaEncontrada = igrejaRepository.findByDadosPessoaisCpf(dadosPessoais.getCpf()).orElse(null);
		else if (igrejaEncontrada == null && StringUtils.isNotBlank(dadosPessoais.getCnpj()))
			igrejaEncontrada = igrejaRepository.findByDadosPessoaisCnpj(dadosPessoais.getCnpj()).orElse(null);
		return igrejaEncontrada;
	}

	public void inativa(Long id) {
		igrejaRepository.findById(id).map(igreja -> {
						igreja.setAtivo(false);
						return igrejaRepository.save(igreja);
				}).orElseThrow(() -> new IllegalArgumentException("Igreja não encontrada com o ID: " + id));		
	}

	public List<IgrejaResumoDTO> pesquisaResumo(boolean ativo) {
		Collection<Igreja> ativas = igrejaRepository.findByAtivo(ativo);
		return ativas.stream().map(igreja -> new IgrejaResumoDTO(
					igreja.getId(),
					igreja.getDadosPessoais().getDescricao(),
					igreja.getAtivo()
				)).toList();
	}

	public IgrejaDTO carrega(Long id) {
		return igrejaRepository.findById(id)
			    .map(igreja -> new IgrejaDTO(
			        igreja.getId(),
			        igreja.getAtivo(),
			        new DadosPessoaisDTO(
			            igreja.getDadosPessoais().getId(),
			            igreja.getDadosPessoais().getDescricao(),
			            igreja.getDadosPessoais().getRg(),
			            igreja.getDadosPessoais().getCpf(),
			            igreja.getDadosPessoais().getCnpj(),
			            igreja.getDadosPessoais().getDataNascimento(),
			            igreja.getDadosPessoais().getTelefone1(),
			            igreja.getDadosPessoais().getTelefone2(),
			            igreja.getDadosPessoais().getTelefone3(),
			            igreja.getDadosPessoais().getEmail(),
			            new EnderecoDTO(igreja.getDadosPessoais().getEndereco())
			        )
			    ))
			    .orElse(null);
	}	
}
