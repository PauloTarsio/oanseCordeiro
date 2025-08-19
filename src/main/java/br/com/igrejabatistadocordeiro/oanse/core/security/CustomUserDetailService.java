package br.com.igrejabatistadocordeiro.oanse.core.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
import br.com.igrejabatistadocordeiro.oanse.domain.service.UsuarioService;

public class CustomUserDetailService implements UserDetailsService {
	
	private UsuarioService usuarioService;	

	public CustomUserDetailService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@Override
	public UserDetails loadUserByUsername(String login) {
		
		Usuario usuario = usuarioService.carrega(login);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado: " + login);
		}
		
		return User
				.withUsername(usuario.getLogin())
				.password(usuario.getSenha())
				.roles(usuario.getRoles().toArray(new String[usuario.getRoles().size()])) // Converte a lista de roles para um array
				.build();
	}
}
