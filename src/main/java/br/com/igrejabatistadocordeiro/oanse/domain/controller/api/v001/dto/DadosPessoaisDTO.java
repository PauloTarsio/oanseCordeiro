package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import java.time.LocalDate;

import br.com.igrejabatistadocordeiro.oanse.domain.model.DadosPessoais;
import br.com.igrejabatistadocordeiro.oanse.domain.util.StringUtils;

public record DadosPessoaisDTO(
			Long id,
			String descricao,
			String rg,
			String cpf,
			String cnpj,
			EnderecoDTO endereco,
			String dataNascimento,
			String telefone1,
			String telefone2,
			String telefone3,
			String email	
		) {
	
	public DadosPessoais toDadosPessoais() {
		DadosPessoais dadosPessoais = new DadosPessoais();
		dadosPessoais.setId(id);
		dadosPessoais.setDescricao(descricao);
		dadosPessoais.setRg(rg);
		dadosPessoais.setCpf(cpf);
		dadosPessoais.setCnpj(cnpj);
		dadosPessoais.setEndereco(endereco.toEndereco());
		dadosPessoais.setDataNascimento(StringUtils.isNotBlank(dataNascimento) ? LocalDate.parse(dataNascimento) : null);
		dadosPessoais.setTelefone1(telefone1);
		dadosPessoais.setTelefone2(telefone2);
		dadosPessoais.setTelefone3(telefone3);
		dadosPessoais.setEmail(email);
		return dadosPessoais;
	}
}
