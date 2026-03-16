package br.com.igrejabatistadocordeiro.oanse.domain.model;

import java.util.UUID;

import br.com.igrejabatistadocordeiro.oanse.domain.model.recursos.PerfilDoUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "u_perfil", nullable = false)
	private PerfilDoUsuario perfil;

	@ManyToOne
	@JoinColumn(name = "u_igreja_id")
	private Igreja igreja;

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

	public PerfilDoUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilDoUsuario perfil) {
		this.perfil = perfil;
	}

	public Igreja getIgreja() {
		return igreja;
	}

	public void setIgreja(Igreja igreja) {
		this.igreja = igreja;
	}
	
	public boolean isAdministrador() {
		return PerfilDoUsuario.ADMIN.equals(this.perfil);
	}
	
	public boolean isSecretario() {
		return PerfilDoUsuario.SECRETARIO.equals(this.perfil);
	}
	
}