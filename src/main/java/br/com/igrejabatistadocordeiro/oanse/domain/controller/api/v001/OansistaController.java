package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.OansistaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.OanseValildationException;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.service.OansistaService;

@RestController
public class OansistaController extends GeneralController {
	
	@Autowired
	private OansistaService service;
	
	@GetMapping("/api/v001/oansista/{id}")
	public ResponseEntity<OansistaDTO> carrega(@PathVariable Long id) {
		Oansista oansistaBase = service.carrega(id);
		OansistaDTO dto = new OansistaDTO(oansistaBase);
		return ResponseEntity.ok(dto);
	}

	@PostMapping("/api/v001/oansista")
	public ResponseEntity<Response> novo(@RequestBody OansistaDTO dto) {
		try {
			Oansista oansista = dto.toOansista();
			service.salva(oansista);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (OanseValildationException e) {
			return ResponseEntity.badRequest().body(new Response(StatusIntegracao.FALHA, e.getErros()));
		}
	}

}
