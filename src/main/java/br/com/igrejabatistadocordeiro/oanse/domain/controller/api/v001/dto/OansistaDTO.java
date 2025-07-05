package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.OanseValidationException;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.validations.IdadeOansista;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OansistaDTO {
	
	private Long id;
	
	@NotBlank(message = "O nome do Oansista é obrigatório.")
    @Size(min = 3, max = 255, message = "O nome do Oansista deve ter entre 3 e 255 caracteres.")
	private String nome;

	@IdadeOansista
	private LocalDate dataNascimento;
	
	private String rua;
	
	private Integer numero;
	
	private String bairro;
	
	@Valid
	private List<ResponsavelDTO> responsaveis = new ArrayList<ResponsavelDTO>();

	public OansistaDTO() {}

	public OansistaDTO(Oansista oansista) {
		this.id = oansista.getId() != null ? oansista.getId() : null;
		this.nome = oansista.getNome() != null ? oansista.getNome() : null;
		this.dataNascimento = oansista.getDataNascimento() != null ? LocalDate.parse(oansista.getDataNascimento().toString()) : null;
		this.rua = oansista.getRua() != null ? oansista.getRua() : null;
		this.numero = oansista.getNumero() != null ? oansista.getNumero() : null;
		this.bairro = oansista.getBairro() != null ? oansista.getBairro() : null;
		if (oansista.getResponsaveis() != null && !oansista.getResponsaveis().isEmpty()) {
			this.setResponsaveis(oansista.getResponsaveis()
				.stream()
				.map(ResponsavelDTO::new)
				.collect(Collectors.toList()));
		} else {
			this.setResponsaveis(new ArrayList<>());
		}
	}
	
	public Oansista toOansista() {
		Oansista oansista = new Oansista();
		oansista.setId(this.id);
		oansista.setNome(this.nome);	
		if (this.dataNascimento != null) {
			try {
				oansista.setDataNascimento(Date.valueOf(this.dataNascimento));
			} catch (DateTimeParseException e) {
				throw new OanseValidationException(String.format("Erro ao analisar a data '%s'. Por favor, utilize o formato yyyy-MM-dd.", this.dataNascimento));
			}
		}			
		oansista.setRua(this.rua);
		oansista.setNumero(this.numero);
		oansista.setBairro(this.bairro);
		if (!this.responsaveis.isEmpty())
			oansista.setResponsaveis(this.responsaveis.stream().map(ResponsavelDTO::toResponsavel).collect(Collectors.toList()));
		return oansista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
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

	public List<ResponsavelDTO> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<ResponsavelDTO> responsaveis) {
		this.responsaveis = responsaveis;
	}
}
