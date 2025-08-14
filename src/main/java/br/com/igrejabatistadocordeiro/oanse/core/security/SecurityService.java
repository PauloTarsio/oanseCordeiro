package br.com.igrejabatistadocordeiro.oanse.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
import br.com.igrejabatistadocordeiro.oanse.domain.service.UsuarioService;

/**
 * 
 * Classe responsável por fornecer informações de segurança relacionadas ao usuário logado.
 * Esta classe utiliza o contexto de segurança do Spring Security para obter detalhes do usuário autenticado.
 * 
 */

@Component
public class SecurityService {
	
	private final UsuarioService usuarioService;	

	public SecurityService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Usuario getUsuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String login = userDetails.getUsername();
		return usuarioService.buscarPorLogin(login);
	}
}
