package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IgrejaViewController {

	@GetMapping("/igreja/formulario")
	public String igrejaFormulario() {
		System.out.println("passou aqui na igrejaViewController");
		return "/igreja/formulario";
	}

	@GetMapping("/igreja/index")
	public String igrejaIndex() {
		return "/igreja/index";
	}
}
