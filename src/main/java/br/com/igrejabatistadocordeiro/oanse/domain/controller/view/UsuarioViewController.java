package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.igrejabatistadocordeiro.oanse.domain.model.PerfilDoUsuario;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;

@Controller
public class UsuarioViewController {

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@GetMapping("/usuario/formulario")
	public String usuarioFormulario(Model model) {
		model.addAttribute("perfil", getPerfilUsuarioLogado().name());
		return "/usuario/formulario";
	}

	@GetMapping("/usuario/index")
	public String usuarioIndex(Model model) {
		model.addAttribute("perfil", getPerfilUsuarioLogado().name());
		return "/usuario/index";
	}
	
	private PerfilDoUsuario getPerfilUsuarioLogado() {
		Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getDetails();
		return usuarioLogado.getPerfil();
	}
}
