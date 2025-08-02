package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public interface GenericController {

	default URI getLocation(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
	}
}
