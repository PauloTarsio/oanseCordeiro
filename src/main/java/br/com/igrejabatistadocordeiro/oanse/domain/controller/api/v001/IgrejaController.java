package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
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
@Tag(name="Igreja")
public class IgrejaController implements GenericController {
	
	private IgrejaService service;
	private IgrejaMapper mapper;
	
	public IgrejaController(IgrejaService service, IgrejaMapper igrejaMapper) {
		this.service = service;
		this.mapper = igrejaMapper;
	}
	
	@GetMapping("api/v001/igreja/{id}")	
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Igreja carregada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Igreja não encontrada"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@Operation(description = "Carrega uma igreja pelo ID")
	public ResponseEntity<IgrejaDTO> carrega(@PathVariable Long id) {
		Igreja igreja = service.carrega(id);
		IgrejaDTO igrejaDTO = mapper.toDto(igreja);
		return ResponseEntity.ok(igrejaDTO);
	}
	
	@GetMapping("api/v001/igreja")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@Operation(description = "Pesquisa igrejas pelo nome ou parte do nome")
	public ResponseEntity<List<PesquisaIgrejaResumidaDTO>> pesquisa(
				@RequestParam(value = "descricao", required = false) String descricao) {

		List<Igreja> pesquisa = service.pesquisa(descricao);
		List<PesquisaIgrejaResumidaDTO> dtos = pesquisa.stream().map(mapper::toResumoDto).collect(Collectors.toList());		
		return ResponseEntity.ok(dtos);
	}

	@PostMapping("api/v001/igreja")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Igreja criado com sucesso"),
		@ApiResponse(responseCode = "422", description = "Erro de validação nos dados informados"),
		@ApiResponse(responseCode = "409", description = "Conflito ao tentar criar igreja, verifique os dados informados"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@Operation(description = "Cria uma nova igreja")
	public ResponseEntity<URI> salva(@Valid @RequestBody IgrejaDTO dto) {
		Igreja igreja = mapper.toEntity(dto);
		service.salva(igreja);
		URI uri = getLocation(igreja.getId());
		return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping("api/v001/igreja/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Igreja atualizado com sucesso"),
		@ApiResponse(responseCode = "422", description = "Erro de validação nos dados informados"),
		@ApiResponse(responseCode = "409", description = "Conflito ao tentar atualizar igreja, verifique os dados informados"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@Operation(description = "Atualiza uma igreja pelo ID")
	public ResponseEntity<Object> atualiza(@Valid @RequestBody IgrejaDTO dto, @PathVariable Long id) {
		Igreja igreja = mapper.toEntity(dto);
		igreja.setId(id);
		service.atualiza(igreja);
		return ResponseEntity.noContent().build();
	}
}
