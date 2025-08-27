package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IgrejaViewController {

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/igreja/formulario")
	public String igrejaFormulario(Model model) {				
		return "/igreja/formulario";
	}

	@GetMapping("/igreja/index")
	public String igrejaIndex(Model model) {
		return "/igreja/index";
	}
}