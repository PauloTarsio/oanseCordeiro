package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.ManualDoOansistaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.OanseValidationException;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.ManualDoOansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.ManualDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.service.ManualDoOansistaService;

@RestController
public class ManualDoOansistaController extends GeneralController {

	@Autowired
	private ManualDoOansistaService service;
	
	@GetMapping("/api/v001/manualDoOansista")
	public ResponseEntity<?> pesquisa(@ModelAttribute ManualDoOansistaFilter filter) {
		List<ManualDoOansista> pesquisa = service.pesquisa(filter);
		if (pesquisa.isEmpty())
			return adicionaMensagem(MSG_NAO_ENCONTRADO);
		List<ManualDoOansistaDTO> dtos = pesquisa.stream().map(ManualDoOansistaDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
	
	@PostMapping("/api/v001/manualDoOansista")
	public ResponseEntity<Response> iniciarManualParaOansista(@RequestBody ManualDoOansistaDTO dto) {
		try {			
			service.salvar(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response(StatusIntegracao.SUCESSO));
	    } catch (OanseValidationException e) {
	        return ResponseEntity.badRequest().body(new Response(StatusIntegracao.FALHA, e.getErros()));
	    }
	}
	
	@PutMapping("/api/v001/manualDoOansista")
	public ResponseEntity<Response> concluir(@RequestBody ManualDoOansistaDTO dto) {
		try {			
			service.atualizar(dto);
			return ResponseEntity.ok(new Response(StatusIntegracao.SUCESSO));
		} catch (OanseValidationException e) {
			return ResponseEntity.badRequest().body(new Response(StatusIntegracao.FALHA, e.getErros()));
		}
	}
}
