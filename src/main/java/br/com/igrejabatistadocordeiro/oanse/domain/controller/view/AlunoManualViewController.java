package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlunoManualViewController {
	
	@GetMapping("/aluno-manual/index")
	public String alunoManualIndex() {
		return "/aluno-manual/index";
	}
	
	@GetMapping("/aluno-manual/formulario")
	public String alunoManualFormulario() {
		return "/aluno-manual/formulario";
	}

}
