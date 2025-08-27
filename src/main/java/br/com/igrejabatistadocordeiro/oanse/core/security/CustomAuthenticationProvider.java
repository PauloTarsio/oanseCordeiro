package br.com.igrejabatistadocordeiro.oanse.core.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
import br.com.igrejabatistadocordeiro.oanse.domain.service.UsuarioService;

/**
 * Class que faz a autenticação do usuário.
 * 
 * @see https://docs.spring.io/spring-security/reference/servlet/authentication/providers.html
 * 
 *      Fonte de autenticação do usuário...
 * 
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final UsuarioService usuarioService;
	private final PasswordEncoder passwordEncoder;

	public CustomAuthenticationProvider(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
		this.usuarioService = usuarioService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String login = authentication.getName();
		String senhaDigitado = authentication.getCredentials().toString();
		
		Usuario usuarioEncontrado = usuarioService.carrega(login);
		
		if (usuarioEncontrado == null) {
			throw getErroUsuarioNaoEncontrado();
		}
		
		String senhaArmazenada = usuarioEncontrado.getSenha();
		if (passwordEncoder.matches(senhaDigitado, senhaArmazenada)) {			
			return new CustomAuthentication(usuarioEncontrado); // Autenticação bem-sucedida
		}
		
		throw getErroUsuarioNaoEncontrado();
	}

	private UsernameNotFoundException getErroUsuarioNaoEncontrado() {
		return new UsernameNotFoundException("Usuário e/ou senha incorretos!");
	}

	/**
	 * Verifica se este provedor suporta o tipo de autenticação.
	 * 
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

}
