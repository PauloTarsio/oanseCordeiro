package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.UsuarioRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.util.StringUtils;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository repository;
	private final PasswordEncoder encoder;

	public UsuarioServiceImpl(UsuarioRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

	@Override
	public Usuario carrega(UUID uuid) {
		return repository.findById(uuid).orElse(null);
	}

	@Override
	public Usuario carrega(String login) {
		Specification<Usuario> spec = Specification.anyOf();
		if (StringUtils.isNotBlank(login)) {
			spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("login")), "%" + login.toLowerCase() + "%"));
		}
		return repository.findOne(spec).orElse(null);
	}

	@Override
	public List<Usuario> pesquisa(String login) {
		Specification<Usuario> spec = Specification.anyOf();
		if (StringUtils.isNotBlank(login)) {
			spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("login")), "%" + login.toLowerCase() + "%"));
		}
		return repository.findAll(spec);
	}

	@Override
	public void salvar(Usuario usuario) {
		var senha = usuario.getSenha();
		usuario.setSenha(encoder.encode(senha));
		repository.save(usuario);
	}

}
