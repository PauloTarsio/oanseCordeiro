package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlunoViewController {

	@GetMapping("/aluno/formulario")
	public String alunoFormulario() {
		return "/aluno/formulario";
	}

	@GetMapping("/aluno/index")
	public String alunoIndex() {
		return "/aluno/index";
	}
}
