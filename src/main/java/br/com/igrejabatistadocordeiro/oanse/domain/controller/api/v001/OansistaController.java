package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.OansistaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.OanseValildationException;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.OansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.service.OansistaService;

@RestController
public class OansistaController extends GeneralController {
	
	@Autowired
	private OansistaService service;
	
	@GetMapping("/api/v001/oansista/{id}")
	public ResponseEntity<?> carrega(@PathVariable Long id) {
		Oansista oansistaBase = service.carrega(id);
		if (oansistaBase == null)
			return adicionaMensagemDeErro("Oansista não encontrado.");
		return ResponseEntity.ok(new OansistaDTO(oansistaBase));
	}
	
	@GetMapping("/api/v001/oansista")
	public ResponseEntity<List<OansistaDTO>> pesquisa(
			@RequestParam(name = "filtro.id", required = false) Long id,
			@RequestParam(name = "filtro.descricao", required = false) String nome) {
		OansistaFilter filtro = new OansistaFilter();
		filtro.setId(id);
		filtro.setNome(nome);
		List<Oansista> pesquisa = service.pesquisa(filtro);
		if (pesquisa.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(pesquisa.stream().map(value -> new OansistaDTO(value)).collect(Collectors.toList()));
	}

	@PostMapping("/api/v001/oansista")
	public ResponseEntity<Response> novo(@RequestBody OansistaDTO dto) {
	    try {
	        Oansista oansista = dto.toOansista();
	        service.salva(oansista);
	        return ResponseEntity.status(HttpStatus.CREATED).body(new Response(StatusIntegracao.SUCESSO));
	    } catch (OanseValildationException e) {
	        return ResponseEntity.badRequest().body(new Response(StatusIntegracao.FALHA, e.getErros()));
	    }
	}
	
	@PutMapping("/api/v001/oansista/{id}")
	public ResponseEntity<Response> edita(@PathVariable Long id, @RequestBody OansistaDTO dto) {
	    try {
	        Oansista oansista = dto.toOansista();
	        oansista.setId(id);
	        service.atualiza(oansista);
	        return ResponseEntity.ok(new Response(StatusIntegracao.SUCESSO));
	    } catch (OanseValildationException e) {
	        return ResponseEntity.badRequest().body(new Response(StatusIntegracao.FALHA, e.getErros()));
	    }
	}
	
	@DeleteMapping("/api/v001/oansista/{id}")
	public ResponseEntity<Response> remove(@PathVariable Long id) {
	    try {
	        service.remove(id);
	        return ResponseEntity.noContent().build();
	    } catch (OanseValildationException e) {
	        return ResponseEntity.badRequest().body(new Response(StatusIntegracao.FALHA, e.getErros()));
	    }
	}

}
