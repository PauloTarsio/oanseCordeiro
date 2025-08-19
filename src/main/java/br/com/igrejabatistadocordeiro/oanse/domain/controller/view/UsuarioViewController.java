package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioViewController {

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/usuario/formulario")
	public String usuarioFormulario(Model model, Authentication authentication) {
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
		model.addAttribute("isAdmin", isAdmin);
		return "/usuario/formulario";
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/usuario/index")
	public String usuarioIndex(Model model, Authentication authentication) {
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
		model.addAttribute("isAdmin", isAdmin);
		return "/usuario/index";
	}
}
