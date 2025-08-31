package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.model.PerfilDoUsuario;
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
		Usuario usuarioLogado = getUsuarioLogado();
		Usuario usuarioBase = repository.findById(uuid).orElse(null);
		if (PerfilDoUsuario.ADMIN.equals(usuarioBase.getPerfil()) && !PerfilDoUsuario.ADMIN.equals(usuarioLogado.getPerfil()))
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
		if (!PerfilDoUsuario.ADMIN.equals(usuarioLogado.getPerfil()))
			spec = spec.and((root, query, cb) -> cb.equal(root.get("igreja"), usuarioLogado.getIgreja()));
		return repository.findAll(spec);
	}

	@Override
	public void salvar(Usuario usuario) {
		temPermissaoNaIgreja(usuario);
		temLoginUnico(usuario);
		temPermissaoParaGerenciarADMIN(usuario);
		var senha = usuario.getSenha();
		usuario.setSenha(encoder.encode(senha));
		repository.save(usuario);
	}

	private void temPermissaoParaGerenciarADMIN(Usuario usuario) {
		if (PerfilDoUsuario.ADMIN.equals(usuario.getPerfil())) {
			Usuario usuarioLogado = getUsuarioLogado();
			if (!PerfilDoUsuario.ADMIN.equals(usuarioLogado.getPerfil()))
				throw new IllegalArgumentException("Usuário logado no sistema não tem permissão para incluir ou editar usuário com perfil ADMIN.");
		}		
	}

	private void temLoginUnico(Usuario usuario) {		
		Usuario existente = repository.findByLogin(usuario.getLogin()).orElse(null);
		if (existente != null && !existente.getId().equals(usuario.getId()))
			throw new IllegalArgumentException("Já existe um usuário com este login.");
	}

	private void temPermissaoNaIgreja(Usuario usuario) {
		Usuario usuarioLogado = getUsuarioLogado();
		if (PerfilDoUsuario.SECRETARIO.equals(usuarioLogado.getPerfil()) && usuario.getIgreja().getId().compareTo(usuarioLogado.getIgreja().getId()) != 0)
			throw new IllegalArgumentException("Não é permitido incluir ou editar usuário de outra igreja.");
	}
	
	private Usuario getUsuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (Usuario) authentication.getDetails();
	}

}