package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IgrejaViewController {

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@GetMapping("/igreja/formulario")
	public String igrejaFormulario(Model model, Authentication authentication) {
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
		model.addAttribute("isAdmin", isAdmin);
		return "/igreja/formulario";
	}

	@GetMapping("/igreja/index")
	public String igrejaIndex(Model model, Authentication authentication) {
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
		model.addAttribute("isAdmin", isAdmin);
		return "/igreja/index";
	}
}