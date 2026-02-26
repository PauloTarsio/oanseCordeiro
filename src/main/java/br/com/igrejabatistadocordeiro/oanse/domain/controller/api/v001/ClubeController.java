package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.model.clube.Clube;
import br.com.igrejabatistadocordeiro.oanse.domain.service.ClubeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Clube")
public class ClubeController {

	@Autowired
	private ClubeService clubeService;

	@GetMapping("/api/v001/clube/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Clube carregado com sucesso"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Clube não encontrado"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	@Operation(description = "Carrega um clube pelo ID")
	public ResponseEntity<Clube> getClubeById(@PathVariable Long id) {
		Clube clube = clubeService.carrega(id);
		if (clube != null) {
			return ResponseEntity.ok(clube);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
