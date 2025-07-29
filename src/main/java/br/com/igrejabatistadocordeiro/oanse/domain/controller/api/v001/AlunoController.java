package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.AlunoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.AlunoResumoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers.AlunoMapper;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Aluno;
import br.com.igrejabatistadocordeiro.oanse.domain.service.AlunoService;
import jakarta.validation.Valid;

@RestController
public class AlunoController {

	private AlunoService service;
	private AlunoMapper mapper;
	
	public AlunoController(AlunoService service, AlunoMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping("api/v001/aluno/{id}")
	public ResponseEntity<Object> carrega(@PathVariable Long id) {
		Aluno aluno = service.carrega(id);
		AlunoDTO alunoDTO = mapper.toDto(aluno);
		return ResponseEntity.ok(alunoDTO);
	}
	
	@GetMapping("api/v001/aluno")
	public ResponseEntity<Object> pesquisa(
				@RequestParam(value = "descricao", required = false) String descricao,
				@RequestParam(value = "cnpj", required = false) String cnpj,
				@RequestParam(value = "cpf", required = false) String cpf,
				@RequestParam(value = "rg", required = false) String rg,
				@RequestParam(value = "ativo", required = false) Boolean ativo) {
		
		List<Aluno> pesquisa = service.pesquisa(descricao, rg, cpf, cnpj, ativo == null ? true : ativo);
//		List<AlunoResumoDTO> dtos = pesquisa.stream().map(mapper::toResumoDto).toList();
//		fazer for manual pra identificar problema...
		List<AlunoResumoDTO> dtos = new ArrayList<AlunoResumoDTO>();
		for (Aluno aluno : pesquisa) {
			AlunoResumoDTO dto = new AlunoResumoDTO(aluno.getId(),
													aluno.getDadosPessoais().getDescricao(),
													aluno.getIgreja().getDadosPessoais().getDescricao(),
													aluno.getAtivo());
			dtos.add(dto);
		}
		
		return ResponseEntity.ok(dtos);
	}
	
	@PostMapping("api/v001/aluno")
	public ResponseEntity<Object> salva(@Valid @RequestBody AlunoDTO dto) {
		Aluno aluno = mapper.toEntity(dto);
		service.salva(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("api/v001/aluno/{id}")
	public ResponseEntity<Object> atualiza(@Valid @RequestBody AlunoDTO dto, Long id) {
		Aluno aluno = mapper.toEntity(dto);
		aluno.setId(id);
		service.atualiza(aluno);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("api/v001/aluno/{id}/inativa")
	public ResponseEntity<Object> inativa(@PathVariable Long id) {
		service.inativa(id);
		return ResponseEntity.noContent().build();
	}
}
