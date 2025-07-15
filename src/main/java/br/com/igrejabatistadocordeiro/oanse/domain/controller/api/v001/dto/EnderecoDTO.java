package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Endereco;

public record EnderecoDTO(
		Long id,
		String rua,
		String numero,
		String bairro,
		String cidade,
		String uf
    ) {
	
	public Endereco toEndereco() {
		Endereco endereco = new Endereco();
		endereco.setId(id);
		endereco.setRua(rua);
		endereco.setNumero(numero);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setUf(uf);
		return endereco;
	}
	
}
