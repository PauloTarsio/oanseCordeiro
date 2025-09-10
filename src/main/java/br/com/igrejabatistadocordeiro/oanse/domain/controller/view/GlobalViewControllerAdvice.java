package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;

@ControllerAdvice
public class GlobalViewControllerAdvice {

    @ModelAttribute("login")
    public String getLoginUsuarioLogadoModel() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (authentication != null && authentication.isAuthenticated() && authentication.getDetails() != null)
			return ((Usuario) authentication.getDetails()).getLogin();
    	return "";
    }

    @ModelAttribute("perfil")
    public String getPerfilUsuarioLogadoModel() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (authentication != null && authentication.isAuthenticated() && authentication.getDetails() != null)
			return ((Usuario) authentication.getDetails()).getPerfil().name();
    	return "";
    }
    
    @ModelAttribute("igrejaIdUsuarioLogado")
    public String getIgrejaUsuarioLogadoModel() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (authentication != null && authentication.isAuthenticated() && authentication.getDetails() != null) {
    		Usuario usuario = (Usuario) authentication.getDetails();
    		return (usuario.isSecretario() ? String.valueOf(usuario.getIgreja().getId()) : "");    		
    	}
    	return "";
    }
}