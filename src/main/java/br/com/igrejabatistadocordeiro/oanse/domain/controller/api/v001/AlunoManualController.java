package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.AlunoManualTrilhasDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.ConcluiSecaoRequest;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaAlunoManualDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaAlunoSecaoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.service.AlunoManualService;
import br.com.igrejabatistadocordeiro.oanse.domain.service.AlunoSecaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping
@Tag(name = "AlunoManual", description = "Gerencia as associações entre Alunos e Manuais (Livros)")
public class AlunoManualController {

	@Autowired
	private AlunoManualService alunoManualService;

    @Autowired
    private AlunoSecaoService alunoSecaoService;
	
	@GetMapping("/api/aluno-manual/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Associação carregada com sucesso"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	@Operation(description = "Carrega as trilhas do Manual pelo ID da associação entre Aluno e Manual")
	public ResponseEntity<?> carrega(@PathVariable Long id) {
		AlunoManualTrilhasDTO alunoManualDTO = alunoManualService.carrega(id);
		return ResponseEntity.ok(alunoManualDTO);
	}

	@GetMapping("/api/aluno-manual")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	@Operation(description = "Pesquisa associação entre Aluno e Manual, filtrando por alunoId e/ou livroId")
	public ResponseEntity<?> pesquisa(@RequestParam(required = false) Long alunoId,
			@RequestParam(required = false) Long livroId) {
		List<PesquisaAlunoManualDTO> resultado = alunoManualService.pesquisa(alunoId, livroId);
		return ResponseEntity.ok(resultado);
	}

	@PostMapping("/api/aluno-manual")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Associação criada com sucesso"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	@Operation(description = "Associa um Aluno a um Manual")
	public ResponseEntity<?> associa(@RequestParam Long alunoId, @RequestParam Long livroId) {
		alunoManualService.salva(alunoId, livroId);
		return ResponseEntity.created(URI.create("/api/aluno-manual")).build();
	}

	@PutMapping("/api/aluno-manual/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Associação concluída com sucesso"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	@Operation(description = "Marca a associação entre Aluno e Manual como concluída")
	public ResponseEntity<?> conclui(@PathVariable Long id) {
		alunoManualService.conclui(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/api/aluno-manual/secao")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	@Operation(description = "Pesquisa seções do Aluno referente ao Manual")
	public ResponseEntity<?> carregaSecaoAluno(
			@RequestParam(required = false) Long alunoManualId,
			@RequestParam(required = false) Long secaoId) {
		List<PesquisaAlunoSecaoDTO> resultado = alunoSecaoService.carrega(alunoManualId, secaoId);
		return ResponseEntity.ok(resultado);
	}

    @PutMapping("/api/aluno-manual/secao/conclui")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
    @Operation(description = "Marca uma seção como concluída para um aluno")
    public ResponseEntity<?> concluiSecao(@RequestBody ConcluiSecaoRequest request) {
    	alunoSecaoService.concluiSecao(request.alunoManualId(), request.secaoId());
        return ResponseEntity.ok().build();
    }
	
}