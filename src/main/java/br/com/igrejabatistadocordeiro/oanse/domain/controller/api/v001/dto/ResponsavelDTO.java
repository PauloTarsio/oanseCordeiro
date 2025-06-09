package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Responsavel;

public class ResponsavelDTO {
	private Long id;
	private String nome;
	private String telefone;
	private String email;
	
	public ResponsavelDTO() {}
	
	public ResponsavelDTO(Responsavel responsavel) {
		this.id = responsavel.getId() != null ? responsavel.getId() : null;
		this.nome = responsavel.getNome();
		this.telefone = responsavel.getTelefone();
		this.email = responsavel.getEmail();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
		responsavel.setId(this.id);
		responsavel.setNome(this.nome);
		responsavel.setTelefone(this.telefone);
		responsavel.setEmail(this.email);
		return responsavel;		
	}	
}
