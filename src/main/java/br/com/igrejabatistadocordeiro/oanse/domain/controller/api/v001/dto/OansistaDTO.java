package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;

public class OansistaDTO {

	private Long id;
	private String nome;
	private String dataNascimento;
	private String rua;
	private String numero;
	private String bairro;
	private ResponsavelDTO responsavel;

	public OansistaDTO() {
	}

	public OansistaDTO(Oansista oansista) {
		this.id = oansista.getId() != null ? oansista.getId() : null;
		this.nome = oansista.getNome() != null ? oansista.getNome() : null;
		this.dataNascimento = oansista.getDataNascimento() != null ? oansista.getDataNascimento().toString() : null;
		this.rua = oansista.getRua() != null ? oansista.getRua() : null;
		this.numero = oansista.getNumero() != null ? oansista.getNumero().toString() : null;
		this.bairro = oansista.getBairro() != null ? oansista.getBairro() : null;
		if (oansista.getResponsavel() != null) {
			this.responsavel = new ResponsavelDTO(oansista.getResponsavel());
		} else {
			this.responsavel = null;
		}
	}
	
	public Oansista toOansista() {
		Oansista oansista = new Oansista();
		oansista.setId(this.id);
		oansista.setNome(this.nome);
		oansista.setDataNascimento(this.dataNascimento != null ? java.time.LocalDate.parse(this.dataNascimento) : null);
		oansista.setRua(this.rua);
		oansista.setNumero(this.numero != null ? Integer.parseInt(this.numero) : null);
		oansista.setBairro(this.bairro);
		if (this.responsavel != null) {
			oansista.setResponsavel(this.responsavel.toResponsavel());
		}
		return oansista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public ResponsavelDTO getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(ResponsavelDTO responsavel) {
		this.responsavel = responsavel;
	}
}
