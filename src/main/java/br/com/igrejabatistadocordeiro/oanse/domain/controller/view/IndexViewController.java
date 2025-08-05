package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexViewController {

    @GetMapping("/lider/index")
    public String lider() {
        return "/lider/index";
    }

    @GetMapping("/aluno/index")
    public String cadastroAluno() {
        return "/aluno/index";
    }
}
