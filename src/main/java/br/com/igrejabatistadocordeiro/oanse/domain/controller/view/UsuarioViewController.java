package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioViewController {

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/usuario/formulario")
	public String usuarioFormulario(Model model) {
		return "/usuario/formulario";
	}

	@GetMapping("/usuario/index")
	public String usuarioIndex(Model model) {
		return "/usuario/index";
	}
}
