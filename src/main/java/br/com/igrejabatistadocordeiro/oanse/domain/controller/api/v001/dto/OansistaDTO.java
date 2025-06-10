package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.OanseValildationException;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;

public class OansistaDTO {
	
	private Long id;
	private String nome;
	private String dataNascimento;
	private String rua;
	private Integer numero;
	private String bairro;
	private ResponsavelDTO responsavel;

	public OansistaDTO() {
	}

	public OansistaDTO(Oansista oansista) {
		this.id = oansista.getId() != null ? oansista.getId() : null;
		this.nome = oansista.getNome() != null ? oansista.getNome() : null;
		this.dataNascimento = oansista.getDataNascimento() != null ? oansista.getDataNascimento().toString() : null;
		this.rua = oansista.getRua() != null ? oansista.getRua() : null;
		this.numero = oansista.getNumero() != null ? oansista.getNumero() : null;
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
		if (this.dataNascimento != null) {
			try {
				oansista.setDataNascimento(LocalDate.parse(this.dataNascimento));
			} catch (DateTimeParseException e) {
				throw new OanseValildationException(String.format("Erro ao analisar a data '%s'. Por favor, utilize o formato yyyy-MM-dd.", this.dataNascimento));
			}
		}			
		oansista.setRua(this.rua);
		oansista.setNumero(this.numero);
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
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
