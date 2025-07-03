package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.ManualDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.ManualFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;
import br.com.igrejabatistadocordeiro.oanse.domain.service.ManualService;

@RestController
public class ManualController extends GeneralController {

	@Autowired
	private ManualService service;
	
	@GetMapping("/api/v001/manual")
	public ResponseEntity<?> pesquisa(@ModelAttribute ManualFilter filter) {
		List<Manual> resultado;
		resultado  = service.pesquisa(filter);
		if (resultado == null || resultado.isEmpty())
            return mensagemDeErro(MSG_NAO_ENCONTRADO);		
		List<ManualDTO> dtos = resultado.stream().map(ManualDTO::new).toList();
		return ResponseEntity.ok(dtos);
	}
}
