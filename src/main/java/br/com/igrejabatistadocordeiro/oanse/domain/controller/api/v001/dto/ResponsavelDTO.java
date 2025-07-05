package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Responsavel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ResponsavelDTO {

	@NotBlank(message = "O nome do responsável é obrigatório.")
	@Size(min = 3, max = 255, message = "O nome do responsável deve ter entre 3 e 255 caracteres.")
	private String nome;

	@Pattern(regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}", message = "O telefone do responsável deve ser válido, use (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
	private String telefone;

    @Email(message = "O email do responsável deve ser válido.")
	private String email;

	public ResponsavelDTO() {}

	public ResponsavelDTO(Responsavel responsavel) {
		this.nome = responsavel.getNome();
		this.telefone = responsavel.getTelefone();
		this.email = responsavel.getEmail();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Responsavel toResponsavel() {
		Responsavel responsavel = new Responsavel();
		responsavel.setNome(this.nome);
		responsavel.setTelefone(this.telefone);
		responsavel.setEmail(this.email);
		return responsavel;
	}
}
