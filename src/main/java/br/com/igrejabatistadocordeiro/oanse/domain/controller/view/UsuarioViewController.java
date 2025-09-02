package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioViewController {

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@GetMapping("/usuario/formulario")
	public String usuarioFormulario() {
		return "/usuario/formulario";
	}

	@GetMapping("/usuario/index")
	public String usuarioIndex() {
		return "/usuario/index";
	}
	
}
