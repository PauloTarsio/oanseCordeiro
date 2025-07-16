package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.validators.Telefone;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record DadosPessoaisDTO(
		
			Long id,
			
			@NotBlank(message = "campo obrigatório")
			@Size(max = 255, message = "Quantidade de caracteres, max 255")
			String descricao,
			
			@Size(max = 20, message = "Quantidade de caracteres, max 20")
			String rg,
			
			@Size(max = 20, message = "Quantidade de caracteres, max 20")
			@CPF(message = "CPF inválido")
			String cpf,
			
			@Size(max = 20, message = "Quantidade de caracteres, max 20")
			@CNPJ(message = "CNPJ inválido")
			String cnpj,
			
			@JsonFormat(pattern = "yyyy-MM-dd")
			@NotNull(message = "campo obrigatório")
			@Past(message = "Data de nascimento deve ser no passado")
			LocalDate dataNascimento,
			
			@NotBlank(message = "campo obrigatório")
			@Size(max = 20, message = "Quantidade de caracteres, max 20")
			@Telefone(message = "Telefone inválido")
			String telefone1,
			
			@Size(max = 20, message = "Quantidade de caracteres, max 20")
			@Telefone(message = "Telefone inválido")
			String telefone2,
			
			@Size(max = 20, message = "Quantidade de caracteres, max 20")
			@Telefone(message = "Telefone inválido")
			String telefone3,
			
			@Size(max = 255, message = "Quantidade de caracteres, max 255")
			@Email(message = "Email inválido")
			String email,
			
			@Valid
			@NotNull(message = "Endereco obrigatório")
			EnderecoDTO endereco
			
		) {}
