package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

	@GetMapping("/login")
	public String login() {
		return "login"; // Retorna o nome da view de login
	}

	@GetMapping("/")
	public String home(Authentication authentication) {
		return "/index/index";
	}
}
