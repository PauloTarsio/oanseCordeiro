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

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.DadosPessoaisDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.EnderecoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.ErroResposta;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.IgrejaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.IgrejaResumoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.RegistroDuplicadoException;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;
import br.com.igrejabatistadocordeiro.oanse.domain.service.IgrejaService;
import jakarta.validation.Valid;

@RestController
public class IgrejaController {
	
	private IgrejaService igrejaService;
	
	public IgrejaController(IgrejaService igrejaService) {
		this.igrejaService = igrejaService;
	}
	
	@GetMapping("api/v001/igreja/{id}")
	public ResponseEntity<Object> carrega(@PathVariable Long id) {
		IgrejaDTO igrejaDTO = igrejaService.carrega(id);
		if (igrejaDTO == null) {
			ErroResposta erroResposta = ErroResposta.naoEncontrado("Igreja não encontrada");
			return ResponseEntity.status(erroResposta.Status()).body(erroResposta);
		}
		return ResponseEntity.ok(igrejaDTO);			
	}
	
	@GetMapping("api/v001/igreja/resumo")
	public ResponseEntity<List<IgrejaResumoDTO>> listarResumo(@RequestParam(value = "ativo", required = false) Boolean ativo) {
	    List<IgrejaResumoDTO> resultado = igrejaService.pesquisaResumo(ativo == null ? true : ativo);
	    return ResponseEntity.ok(resultado);
	}
	
	@GetMapping("api/v001/igreja")
	public ResponseEntity<List<IgrejaDTO>> pesquisa(
				@RequestParam(value = "descricao", required = false) String descricao,
				@RequestParam(value = "cnpj", required = false) String cnpj,
				@RequestParam(value = "cpf", required = false) String cpf,
				@RequestParam(value = "rg", required = false) String rg,
				@RequestParam(value = "ativo", required = false) Boolean ativo) {
		
		List<Igreja> pesquisa = igrejaService.pesquisaByExample(descricao, rg, cpf, cnpj, ativo == null ? true : ativo);
		List<IgrejaDTO> dtos = pesquisa.stream()
	            .map(igreja -> new IgrejaDTO(
	                    igreja.getId(),
	                    igreja.getAtivo(),
	                    new DadosPessoaisDTO (
	                        igreja.getDadosPessoais().getId(),
	                        igreja.getDadosPessoais().getDescricao(),
	                        igreja.getDadosPessoais().getRg(),
	                        igreja.getDadosPessoais().getCpf(),
	                        igreja.getDadosPessoais().getCnpj(),	                        
	                        igreja.getDadosPessoais().getDataNascimento(),
	                        igreja.getDadosPessoais().getTelefone1(),
	                        igreja.getDadosPessoais().getTelefone2(),
	                        igreja.getDadosPessoais().getTelefone3(),
	                        igreja.getDadosPessoais().getEmail(),
	                        new EnderecoDTO(igreja.getDadosPessoais().getEndereco()))
	            ))
	            .collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@PostMapping("api/v001/igreja")
	public ResponseEntity<Object> salva(@Valid @RequestBody IgrejaDTO dto) {
		try {
			Igreja igreja = dto.toIgreja();
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
			Igreja igreja = dto.toIgreja();
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
