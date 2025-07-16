package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
		
		Long id,
		
		@NotBlank(message = "campo obrigatória")
		@Size(max = 255, message = "Quantidade de caracteres acima do permitido")
		String rua,
		
		@Size(max = 20, message = "Quantidade de caracteres acima do permitido")
		String numero,
		
		@NotBlank(message = "campo  obrigatório")
		@Size(max = 100, message = "Quantidade de caracteres acima do permitido")
		String bairro,
		
		@NotBlank(message = "campo obrigatória")
		@Size(max = 100, message = "Quantidade de caracteres acima do permitido")
		String cidade,
		
		@NotBlank(message = "campo obrigatória")
		@Size(max = 2, message = "Quantidade de caracteres acima do permitido")
		String uf
    ) {
	
	public EnderecoDTO(Endereco endereco) {
		this(
				endereco.getId(),
				endereco.getRua(),
				endereco.getNumero(),
				endereco.getBairro(),
				endereco.getCidade(),
				endereco.getUf()
			);
	}

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
