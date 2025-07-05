package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.OansistaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.OanseValidationException;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.OansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.service.OansistaService;
import jakarta.validation.Valid;

@RestController
public class OansistaController extends GeneralController {
	
	@Autowired
	private OansistaService service;
	
	@GetMapping("/api/v001/oansista/{id}")
	public ResponseEntity<?> carrega(@PathVariable Long id) {
		Oansista oansistaBase = service.carrega(id);
		if (oansistaBase == null)
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(new OansistaDTO(oansistaBase));
	}
	
	@GetMapping("/api/v001/oansista")
	public ResponseEntity<?> pesquisa(@ModelAttribute OansistaFilter filter) {
		List<Oansista> resultado = service.pesquisa(filter);
		if (resultado == null || resultado.isEmpty())
			return ResponseEntity.noContent().build();
		List<OansistaDTO> dtos = resultado.stream().map(value -> new OansistaDTO(value)).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@PostMapping("/api/v001/oansista")
	public ResponseEntity<Response> novo(@Valid @RequestBody OansistaDTO dto) {
	    try {
	        Oansista oansista = dto.toOansista();
	        service.salva(oansista);
	        return ResponseEntity.status(HttpStatus.CREATED).build();
	    } catch (OanseValidationException e) {
	        return mensagemDeErro(e.getErros());
	    }
	}
	
	@PutMapping("/api/v001/oansista/{id}")
	public ResponseEntity<Response> edita(@PathVariable Long id, @Valid @RequestBody OansistaDTO dto) {
	    try {
	        Oansista oansista = dto.toOansista();
	        oansista.setId(id);
	        service.atualiza(oansista);
	        return ResponseEntity.ok().build();
	    } catch (OanseValidationException e) {
	    	return mensagemDeErro(e.getErros());
	    }
	}
	
	@DeleteMapping("/api/v001/oansista/{id}")
	public ResponseEntity<Response> remove(@PathVariable Long id) {
	    try {
	        service.remove(id);
	        return ResponseEntity.noContent().build();
	    } catch (OanseValidationException e) {
	    	return mensagemDeErro(e.getErros());
	    }
	}

}
