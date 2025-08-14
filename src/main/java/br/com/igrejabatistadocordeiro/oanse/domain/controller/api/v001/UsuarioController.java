package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.UsuarioDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers.UsuarioMapper;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
import br.com.igrejabatistadocordeiro.oanse.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="Usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioMapper mapper;

	@PostMapping("/api/v001/usuario")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
	@Operation(description = "Cria um novo usuário")
	public void salvar(@RequestBody UsuarioDTO dto) {
		Usuario usuario = mapper.toEntity(dto);
		usuarioService.salvar(usuario);
	}
}