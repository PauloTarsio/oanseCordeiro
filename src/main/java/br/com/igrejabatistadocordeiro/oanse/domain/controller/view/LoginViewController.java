package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginViewController {

	@GetMapping("/login")
	public String login() {
		return "login"; // Retorna o nome da view de login
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
	    request.getSession().invalidate();
	    return "login";
	}

	@GetMapping("/")
	public String home(Model model, Authentication authentication) {
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
		model.addAttribute("isAdmin", isAdmin);
		return "/index/index";
	}
}
