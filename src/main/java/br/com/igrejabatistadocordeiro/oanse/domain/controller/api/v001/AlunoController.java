package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.AlunoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaAlunoResumidoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers.AlunoMapper;
import br.com.igrejabatistadocordeiro.oanse.domain.model.aluno.Aluno;
import br.com.igrejabatistadocordeiro.oanse.domain.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name="Aluno")
public class AlunoController implements GenericController {

	private AlunoService service;
	private AlunoMapper mapper;
	
	public AlunoController(AlunoService service, AlunoMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping("api/v001/aluno/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Aluno carregado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@Operation(description = "Carrega um aluno pelo ID")
	public ResponseEntity<Object> carrega(@PathVariable Long id) {
		Aluno aluno = service.carrega(id);
		AlunoDTO alunoDTO = mapper.toDto(aluno);
		return ResponseEntity.ok(alunoDTO);
	}
	
	@GetMapping("api/v001/aluno")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@Operation(description = "Pesquisa alunos pelo nome ou parte do nome")
	public ResponseEntity<Object> pesquisa(@RequestParam(value = "descricao", required = false) String descricao) {
		List<Aluno> pesquisa = service.pesquisa(descricao);
		List<PesquisaAlunoResumidoDTO> dtos = mapper.toResumoDtoList(pesquisa);
		return ResponseEntity.ok(dtos);
	}
	
	@PostMapping("api/v001/aluno")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Aluno criado com sucesso"),
		@ApiResponse(responseCode = "422", description = "Erro de validação nos dados informados"),
		@ApiResponse(responseCode = "409", description = "Conflito ao tentar criar o aluno, verifique os dados informados"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@Operation(description = "Cria um novo aluno")
	public ResponseEntity<Object> inclui(@Valid @RequestBody AlunoDTO dto) {
		Aluno aluno = mapper.toEntity(dto);
		service.salva(aluno);
		URI uri = getLocation(aluno.getId());
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("api/v001/aluno/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Aluno atualizado com sucesso"),
		@ApiResponse(responseCode = "422", description = "Erro de validação nos dados informados"),
		@ApiResponse(responseCode = "409", description = "Conflito ao tentar atualizar o aluno, verifique os dados informados"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@Operation(description = "Atualiza um aluno por ID")
	public ResponseEntity<Object> edita(@Valid @RequestBody AlunoDTO dto, @PathVariable Long id) {
		Aluno aluno = mapper.toEntity(dto);
		aluno.setId(id);
		service.atualiza(aluno);
		return ResponseEntity.noContent().build();
	}
}
