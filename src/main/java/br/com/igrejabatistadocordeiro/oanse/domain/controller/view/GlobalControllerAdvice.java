package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.context.SecurityContextHolder;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("login")
    public String getLoginUsuarioLogadoModel() {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (details instanceof Usuario usuario) {
            return usuario.getLogin();
        }
        return "";
    }
}
