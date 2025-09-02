package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;

@ControllerAdvice
public class GlobalViewControllerAdvice {

    @ModelAttribute("login")
    public String getLoginUsuarioLogadoModel() {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (details instanceof Usuario usuario) {
            return usuario.getLogin();
        }
        return "";
    }

    @ModelAttribute("perfil")
    public String getPerfilUsuarioLogadoModel() {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (details instanceof Usuario usuario) {
            return usuario.getPerfil().name();
        }
        return "";
    }
    
    @ModelAttribute("igrejaIdUsuarioLogado")
    public String getIgrejaUsuarioLogadoModel() {
    	Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
    	if (details instanceof Usuario usuario) {
    		return ((Usuario) details).isSecretario() ? String.valueOf(usuario.getIgreja().getId()) : "";
    	}
    	return "";
    }
}