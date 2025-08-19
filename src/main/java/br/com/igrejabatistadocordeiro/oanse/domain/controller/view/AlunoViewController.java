package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

@Controller
public class AlunoViewController {

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@GetMapping("/aluno/formulario")
	public String alunoFormulario(Model model, Authentication authentication) {
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
		model.addAttribute("isAdmin", isAdmin);
		return "/aluno/formulario";
	}

	@GetMapping("/aluno/index")
	public String alunoIndex(Model model, Authentication authentication) {
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
		model.addAttribute("isAdmin", isAdmin);
		return "/aluno/index";
	}
}