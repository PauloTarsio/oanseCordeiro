package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.IgrejaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.IgrejaResumoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers.IgrejaMapper;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;
import br.com.igrejabatistadocordeiro.oanse.domain.service.IgrejaService;
import jakarta.validation.Valid;

@RestController
public class IgrejaController {
	
	private IgrejaService service;
	private IgrejaMapper mapper;
	
	public IgrejaController(IgrejaService service, IgrejaMapper igrejaMapper) {
		this.service = service;
		this.mapper = igrejaMapper;
	}
	
	@GetMapping("api/v001/igreja/{id}")
	public ResponseEntity<Object> carrega(@PathVariable Long id) {
		Igreja igreja = service.carrega(id);			
		IgrejaDTO igrejaDTO = mapper.toDto(igreja);		
		return ResponseEntity.ok(igrejaDTO);		
	}
	
	@GetMapping("api/v001/igreja")
	public ResponseEntity<Object> pesquisa(
				@RequestParam(value = "descricao", required = false) String descricao,
				@RequestParam(value = "cnpj", required = false) String cnpj,
				@RequestParam(value = "cpf", required = false) String cpf,
				@RequestParam(value = "rg", required = false) String rg,
				@RequestParam(value = "ativo", required = false) Boolean ativo) {

		List<Igreja> pesquisa = service.pesquisa(descricao, rg, cpf, cnpj, ativo == null ? true : ativo);
		List<IgrejaResumoDTO> dtos = pesquisa.stream().map(mapper::toResumoDto).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@PostMapping("api/v001/igreja")
	public ResponseEntity<Object> salva(@Valid @RequestBody IgrejaDTO dto) {
		Igreja igreja = mapper.toEntity(dto);
		service.salva(igreja);
		return ResponseEntity.status(HttpStatus.CREATED).build();		
	}
	
	@PutMapping("api/v001/igreja/{id}")
	public ResponseEntity<Object> atualiza(@Valid @RequestBody IgrejaDTO dto, @PathVariable Long id) {
		Igreja igreja = mapper.toEntity(dto);
		igreja.setId(id);
		service.atualiza(igreja);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("api/v001/igreja/{id}/ativa")
	public ResponseEntity<Object> ativa(@PathVariable Long id) {
		service.ativa(id);
		return ResponseEntity.noContent().build();		
	}
	
	@PutMapping("api/v001/igreja/{id}/inativa")
	public ResponseEntity<Object> inativa(@PathVariable Long id) {
		service.inativa(id);
		return ResponseEntity.noContent().build();		
	}
}
