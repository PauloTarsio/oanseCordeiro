package br.com.igrejabatistadocordeiro.oanse.domain.model;

import java.util.List;

public enum PerfilDoUsuario {
	ADMIN, SECRETARIO, LIDER;
	
	public List<String> getAuthorities() {
		return List.of("ROLE_" + this.name());
	}
}

