package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioViewController {

	@GetMapping("/usuario/formulario")
	public String usuarioFormulario() {
		return "/usuario/formulario";
	}

	@GetMapping("/usuario/index")
	public String usuarioIndex() {
		return "/usuario/index";
	}
}
