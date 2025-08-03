package br.com.igrejabatistadocordeiro.oanse.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void salvar(Usuario usuario) {
		var senha = usuario.getSenha();
		usuario.setSenha(encoder.encode(senha));
		repository.save(usuario);
	}

	@Override
	public Usuario buscarPorLogin(String login) {
		return repository.findByLogin(login).orElse(null);
	}
}
