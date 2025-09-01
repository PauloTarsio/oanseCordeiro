package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginViewController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "login";
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("login", getPerfilUsuarioLogado().getLogin());
		return "/index/index";
	}
	
	@ModelAttribute("login")
	public String getLoginUsuarioLogadoModel() {
		Usuario usuario = getPerfilUsuarioLogado();
		return usuario != null ? usuario.getLogin() : "";
	}
	
	private Usuario getPerfilUsuarioLogado() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getDetails();
	}
}