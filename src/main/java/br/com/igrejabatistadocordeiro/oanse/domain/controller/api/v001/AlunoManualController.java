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

@RestController
@RequestMapping
public class AlunoManualController {

	@Autowired
	private AlunoManualService alunoManualService;

    @Autowired
    private AlunoSecaoService alunoSecaoService;
	
	@GetMapping("/api/v001/aluno-manual/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	public ResponseEntity<?> carrega(@PathVariable Long id) {
		AlunoManualTrilhasDTO alunoManualDTO = alunoManualService.carrega(id);
		return ResponseEntity.ok(alunoManualDTO);
	}

	@GetMapping("/api/v001/aluno-manual")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	public ResponseEntity<?> pesquisa(@RequestParam(required = false) String descricao) {
		List<PesquisaAlunoManualDTO> resultado = alunoManualService.pesquisa(descricao);
		return ResponseEntity.ok(resultado);
	}
	
	@PostMapping("/api/v001/aluno-manual")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	public ResponseEntity<?> associa(@RequestParam Long alunoId, @RequestParam Long livroId) {
		alunoManualService.salva(alunoId, livroId);
		return ResponseEntity.created(URI.create("/api/aluno-manual")).build();
	}

	@PutMapping("/api/v001/aluno-manual/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	public ResponseEntity<?> conclui(@PathVariable Long id) {
		alunoManualService.conclui(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/api/v001/aluno-manual/secao")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	public ResponseEntity<?> carregaSecaoAluno(
			@RequestParam(required = false) Long alunoManualId,
			@RequestParam(required = false) Long secaoId) {
		List<PesquisaAlunoSecaoDTO> resultado = alunoSecaoService.carrega(alunoManualId, secaoId);
		return ResponseEntity.ok(resultado);
	}

    @PutMapping("/api/001/aluno-manual/secao/conclui")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
    public ResponseEntity<?> concluiSecao(@RequestBody ConcluiSecaoRequest request) {
    	alunoSecaoService.concluiSecao(request.alunoManualId(), request.secaoId());
        return ResponseEntity.ok().build();
    }
	
}