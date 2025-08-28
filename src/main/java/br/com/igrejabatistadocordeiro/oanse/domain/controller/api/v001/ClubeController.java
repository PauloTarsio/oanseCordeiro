package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Clube;
import br.com.igrejabatistadocordeiro.oanse.domain.service.ClubeService;

@RestController
public class ClubeController {

	@Autowired
	private ClubeService clubeService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@GetMapping("/api/v001/clube/{id}")
	public ResponseEntity<Clube> getClubeById(@PathVariable Long id) {
		Clube clube = clubeService.carrega(id);
		if (clube != null) {
			return ResponseEntity.ok(clube);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
