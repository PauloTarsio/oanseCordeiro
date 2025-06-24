package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.TrilhaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.TrilhaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Trilha;
import br.com.igrejabatistadocordeiro.oanse.domain.service.TrilhaService;

@RestController
public class TrilhaController extends GeneralController {
	
	@Autowired
	private TrilhaService service;
	
	@GetMapping("api/v001/trilha")
	public ResponseEntity<?> pesquisa(@ModelAttribute TrilhaFilter filter) {
        List<Trilha> resultado = service.pesquisa(filter);
        if (resultado == null || resultado.isEmpty())
            return adicionaMensagemDeErro(MSG_NAO_ENCONTRADO);
        List<TrilhaDTO> dtos = resultado.stream().map(TrilhaDTO::new).toList();
        return ResponseEntity.ok(dtos);
	}

}
