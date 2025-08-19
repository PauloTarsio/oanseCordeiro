package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;

public interface UsuarioService {

	public Usuario carrega(String login);
	
	public List<Usuario> pesquisa(String login);

	public void salvar(Usuario usuario);

}
