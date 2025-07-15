package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.IgrejaDTO;

@RestController
public class IgrejaController {

	@PostMapping("api/v001/igreja")
	public void novo(@RequestBody IgrejaDTO dto) {
		
	}
}
