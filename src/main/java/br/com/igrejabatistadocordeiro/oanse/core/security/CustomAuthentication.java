package br.com.igrejabatistadocordeiro.oanse.core.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;

public class CustomAuthentication implements Authentication {

	private static final long serialVersionUID = -2250369293148911979L;
	
	private final Usuario usuario;
	
	public CustomAuthentication(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String getName() {
		return this.usuario.getLogin();
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().name()));
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return this.usuario;
	}

	@Override
	public Object getPrincipal() {
		return this.usuario;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}