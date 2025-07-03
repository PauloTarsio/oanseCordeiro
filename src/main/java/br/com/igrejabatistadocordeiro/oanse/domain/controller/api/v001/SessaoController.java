package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.SessaoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Sessao;
import br.com.igrejabatistadocordeiro.oanse.domain.service.SessaoService;

@RestController
public class SessaoController extends GeneralController {
	
	@Autowired
	private SessaoService service;
	
	@GetMapping("api/v001/sessao")
	public ResponseEntity<?> pesquisa(@ModelAttribute SessaoFilter filter) {
        List<Sessao> resultado = service.pesquisa(filter);
        if (resultado == null || resultado.isEmpty())
            return mensagemDeErro(MSG_NAO_ENCONTRADO);
        List<SessaoDTO> dtos = resultado.stream().map(SessaoDTO::new).toList();
        return ResponseEntity.ok(dtos);
	}

}
