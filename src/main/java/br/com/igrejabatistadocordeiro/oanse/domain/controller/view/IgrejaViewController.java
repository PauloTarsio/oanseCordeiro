package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IgrejaViewController {

	@GetMapping("/igreja/index")
	public String index() {
		return "/igreja/index";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@GetMapping("/igreja/formulario")
	public String formulario() {				
		return "/igreja/formulario";
	}
}