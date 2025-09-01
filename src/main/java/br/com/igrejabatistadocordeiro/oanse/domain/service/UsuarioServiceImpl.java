package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		Usuario usuarioBase = repository.findById(uuid).orElse(null);
		if (usuarioBase.isAdministrador() && !getUsuarioLogado().isAdministrador())
			throw new IllegalArgumentException("Usuário logado no sistema não tem permissão para carregar usuário com perfil ADMIN.");
		return repository.findById(uuid).orElse(null);
	}

	@Override
	public Usuario carrega(String login) {
		Specification<Usuario> spec = Specification.anyOf();
		if (StringUtils.isNotBlank(login))
			spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("login")), "%" + login.toLowerCase() + "%"));
		return repository.findOne(spec).orElse(null);
	}

	@Override
	public List<Usuario> pesquisa(String login) {
		Usuario usuarioLogado = getUsuarioLogado();
		Specification<Usuario> spec = Specification.anyOf();
		if (StringUtils.isNotBlank(login))
			spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("login")), "%" + login.toLowerCase() + "%"));
		if (!usuarioLogado.isAdministrador())
			spec = spec.and((root, query, cb) -> cb.equal(root.get("igreja"), usuarioLogado.getIgreja()));
		return repository.findAll(spec);
	}

	@Override
	public void salvar(Usuario usuario) {
		temLoginUnico(usuario);
		usuarioLogadoEhSecretario(usuario);		
		resolveIgreja(usuario);
		resolveSenha(usuario);
		repository.save(usuario);
	}

	private void resolveSenha(Usuario usuario) {
		var senha = usuario.getSenha();
		usuario.setSenha(encoder.encode(senha));		
	}

	private void resolveIgreja(Usuario usuario) {
		Usuario usuarioLogado = getUsuarioLogado();
		if (usuarioLogado.isAdministrador() && usuario.isAdministrador())
			usuario.setIgreja(null);
		if (usuarioLogado.isSecretario())
			usuario.setIgreja(usuarioLogado.getIgreja());
	}

	private void usuarioLogadoEhSecretario(Usuario usuario) {
		boolean secretario = getUsuarioLogado().isSecretario();
		if (secretario && usuario.isAdministrador())
			throw new IllegalArgumentException("Usuário logado no sistema não tem permissão para incluir ou editar usuário com perfil ADMIN.");
		if (secretario && usuario.getIgreja().getId().compareTo(getUsuarioLogado().getIgreja().getId()) != 0)
			throw new IllegalArgumentException("Não é permitido incluir ou editar usuário de outra igreja.");
	}

	private void temLoginUnico(Usuario usuario) {
		Usuario existente = repository.findByLogin(usuario.getLogin()).orElse(null);
		if (existente != null && !existente.getId().equals(usuario.getId()))
			throw new IllegalArgumentException("Já existe um usuário com este login.");
	}

	private Usuario getUsuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (Usuario) authentication.getDetails();
	}

}