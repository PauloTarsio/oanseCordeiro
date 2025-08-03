package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.IgrejaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaIgrejaResumidaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers.IgrejaMapper;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;
import br.com.igrejabatistadocordeiro.oanse.domain.service.IgrejaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Igrejas", description = "Gerenciamento de igrejas")
public class IgrejaController implements GenericController {
	
	private IgrejaService service;
	private IgrejaMapper mapper;
	
	public IgrejaController(IgrejaService service, IgrejaMapper igrejaMapper) {
		this.service = service;
		this.mapper = igrejaMapper;
	}
	
	@GetMapping("api/v001/igreja/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LIDER')")
	@Operation(summary = "Carrega uma igreja pelo ID")
	public ResponseEntity<Object> carrega(@PathVariable Long id) {
		Igreja igreja = service.carrega(id);
		IgrejaDTO igrejaDTO = mapper.toDto(igreja);
		return ResponseEntity.ok(igrejaDTO);
	}
	
	@GetMapping("api/v001/igreja")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LIDER')")
	@Operation(summary = "Pesquisa igrejas por critérios")
	public ResponseEntity<Object> pesquisa(
				@RequestParam(value = "descricao", required = false) String descricao,
				@RequestParam(value = "cnpj", required = false) String cnpj,
				@RequestParam(value = "cpf", required = false) String cpf,
				@RequestParam(value = "rg", required = false) String rg,
				@RequestParam(value = "ativo", required = false) Boolean ativo) {

		List<Igreja> pesquisa = service.pesquisa(descricao, rg, cpf, cnpj, ativo == null ? true : ativo);
		List<PesquisaIgrejaResumidaDTO> dtos = pesquisa.stream().map(mapper::toResumoDto).collect(Collectors.toList());		
		return ResponseEntity.ok(dtos);
	}

	@PostMapping("api/v001/igreja")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Salva uma nova igreja")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Igreja criado com sucesso"),
		@ApiResponse(responseCode = "422", description = "Erro de validação nos dados informados"),
		@ApiResponse(responseCode = "409", description = "Conflito ao tentar criar igreja, verifique os dados informados"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public ResponseEntity<Object> salva(@Valid @RequestBody IgrejaDTO dto) {
		Igreja igreja = mapper.toEntity(dto);
		service.salva(igreja);
		URI uri = getLocation(igreja.getId());
		return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping("api/v001/igreja/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Atualiza uma igreja existente")
	public ResponseEntity<Object> atualiza(@Valid @RequestBody IgrejaDTO dto, @PathVariable Long id) {
		Igreja igreja = mapper.toEntity(dto);
		igreja.setId(id);
		service.atualiza(igreja);
		return ResponseEntity.noContent().build();
	}
}
