package br.com.igrejabatistadocordeiro.oanse.domain.controller.view;

import br.com.igrejabatistadocordeiro.oanse.domain.repository.ClubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlunoViewController {

	@Autowired
	private ClubeRepository clubeRepository;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@GetMapping("/aluno/formulario")
	public String alunoFormulario(Model model) {
		return "/aluno/formulario";
	}

	@GetMapping("/aluno/index")
	public String alunoIndex(Model model) {
		model.addAttribute("clubes", clubeRepository.findAll());
		return "/aluno/index";
	}
}