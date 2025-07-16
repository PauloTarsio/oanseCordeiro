package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatusCode;
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
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.erro.ErroResposta;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers.IgrejaMapper;
import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.RegistroDuplicadoException;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;
import br.com.igrejabatistadocordeiro.oanse.domain.service.IgrejaService;
import jakarta.validation.Valid;

@RestController
public class IgrejaController {
	
	private IgrejaService igrejaService;
	private IgrejaMapper igrejaMapper;
	
	public IgrejaController(IgrejaService igrejaService, IgrejaMapper igrejaMapper) {
		this.igrejaService = igrejaService;
		this.igrejaMapper = igrejaMapper;
	}
	
	@GetMapping("api/v001/igreja/{id}")
	public ResponseEntity<Object> carrega(@PathVariable Long id) {
		try {
			Igreja igreja = igrejaService.carrega(id);			
			IgrejaDTO igrejaDTO = igrejaMapper.toDto(igreja);		
			return ResponseEntity.ok(igrejaDTO);			
		} catch (IllegalArgumentException e) {
			ErroResposta erroResposta = ErroResposta.respostaPadrao(e.getMessage());
			return ResponseEntity.status(erroResposta.Status()).body(erroResposta);
		}
	}
	
	@GetMapping("api/v001/igreja")
	public ResponseEntity<Object> pesquisa(
				@RequestParam(value = "descricao", required = false) String descricao,
				@RequestParam(value = "cnpj", required = false) String cnpj,
				@RequestParam(value = "cpf", required = false) String cpf,
				@RequestParam(value = "rg", required = false) String rg,
				@RequestParam(value = "ativo", required = false) Boolean ativo) {

		List<Igreja> pesquisa = igrejaService.pesquisa(descricao, rg, cpf, cnpj, ativo == null ? true : ativo);
		List<IgrejaResumoDTO> dtos = pesquisa.stream().map(igreja -> igrejaMapper.toResumoDto(igreja)).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@PostMapping("api/v001/igreja")
	public ResponseEntity<Object> salva(@Valid @RequestBody IgrejaDTO dto) {
		try {
			Igreja igreja = igrejaMapper.toEntity(dto);
			igrejaService.salva(igreja);
			return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
		} catch (RegistroDuplicadoException e) {
			ErroResposta erroResposta = ErroResposta.conflito(e.getMessage());
			return ResponseEntity.status(erroResposta.Status()).body(erroResposta);
		} catch (Exception e) {
			ErroResposta erroResposta = ErroResposta.respostaPadrao(e.getMessage());
			return ResponseEntity.status(erroResposta.Status()).body(erroResposta);
		}
	}
	
	@PutMapping("api/v001/igreja/{id}")
	public ResponseEntity<Object> atualiza(@Valid @RequestBody IgrejaDTO dto, @PathVariable Long id) {
		try {
			Igreja igreja = igrejaMapper.toEntity(dto);
			igreja.setId(id);
			igrejaService.atualiza(igreja);
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
		} catch (RegistroDuplicadoException e) {
			ErroResposta erroResposta = ErroResposta.conflito(e.getMessage());
			return ResponseEntity.status(erroResposta.Status()).body(erroResposta);
		}  catch (Exception e) {
			ErroResposta erroResposta = ErroResposta.respostaPadrao(e.getMessage());
			return ResponseEntity.status(erroResposta.Status()).body(erroResposta);
		}
	}
	
	@PutMapping("api/v001/igreja/{id}/inativa")
	public ResponseEntity<Object> inativa(@PathVariable Long id) {
		try {
			igrejaService.inativa(id);
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
		} catch (Exception e) {
			ErroResposta erroResposta = ErroResposta.respostaPadrao(e.getMessage());
			return ResponseEntity.status(erroResposta.Status()).body(erroResposta);
		} 
	}
}
