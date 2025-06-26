package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.SessaoDoOansistaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.SessoesDoOansistaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoDoOansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.SessaoDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.service.SessaoDoOansistaService;

@RestController
public class SessoesDoOansistaController extends GeneralController {
	
	@Autowired
	private SessaoDoOansistaService service;
	
	@GetMapping("/api/v001/sessoesDoOansista")
	public ResponseEntity<?> pesquisa(@ModelAttribute SessaoDoOansistaFilter filter) {
	    List<SessaoDoOansista> sessoes = service.pesquisa(filter);

	    if (sessoes.isEmpty()) {
	        return adicionaMensagem(MSG_NAO_ENCONTRADO);
	    }

	    List<SessaoDoOansistaDTO> sessoesDTO = sessoes.stream()
	        .map(SessaoDoOansistaDTO::new)
	        .collect(Collectors.toList());

	    SessoesDoOansistaDTO resposta = new SessoesDoOansistaDTO();
	    resposta.setSessoesDoOansista(sessoesDTO);

	    return ResponseEntity.ok(resposta);
	}

}
