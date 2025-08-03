package br.com.igrejabatistadocordeiro.oanse.domain.model;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Type;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "u_id")
	private UUID id;
	
	@Column(name = "u_login", nullable = false, unique = true)
	private String login;
	
	@Column(name = "u_senha", nullable = false)
	private String senha;
	
	@Type(ListArrayType.class)
	@Column(name = "u_roles", columnDefinition = "text[]")
	private List<String> roles;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
