package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.UsuarioRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.util.StringUtils;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Usuario carrega(String login) {
		return repository.findByLogin(login).orElse(null);
	}
	
	@Override
	public List<Usuario> pesquisa(String login) {
		Specification<Usuario> spec = Specification.anyOf();
		if (StringUtils.isNotBlank(login)) {
			spec = spec.and((root, query, cb) -> 
				cb.like(
					cb.lower(root.get("login")),
					"%" + login.toLowerCase() + "%"
				)
			);
		}
		return  repository.findAll(spec);
	}
	
	@Override
	public void salvar(Usuario usuario) {
		var senha = usuario.getSenha();
		usuario.setSenha(encoder.encode(senha));
		repository.save(usuario);
	}

}
