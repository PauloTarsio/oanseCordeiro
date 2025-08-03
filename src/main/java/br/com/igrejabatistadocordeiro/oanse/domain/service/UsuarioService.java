package br.com.igrejabatistadocordeiro.oanse.domain.service;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;

public interface UsuarioService {

	void salvar(Usuario usuario);

	Usuario buscarPorLogin(String login);

}
