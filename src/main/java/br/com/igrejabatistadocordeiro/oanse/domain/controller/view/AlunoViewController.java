package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlunoViewController {

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@GetMapping("/aluno/formulario")
	public String alunoFormulario(Model model) {
		return "/aluno/formulario";
	}

	@GetMapping("/aluno/index")
	public String alunoIndex(Model model) {		
		return "/aluno/index";
	}
}