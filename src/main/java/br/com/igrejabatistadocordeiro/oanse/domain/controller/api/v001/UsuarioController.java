package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.PesquisaUsuarioResumidoDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.UsuarioDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.mappers.UsuarioMapper;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;
import br.com.igrejabatistadocordeiro.oanse.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioMapper mapper;
	
	@GetMapping("/api/v001/usuario/{id}")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Carregamento de usuario realizada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
	public ResponseEntity<UsuarioDTO> carrega(@PathVariable UUID id) {
		Usuario usuario = usuarioService.carrega(id);
		if (usuario == null)
			return ResponseEntity.notFound().build();
		UsuarioDTO dto = mapper.toDto(usuario);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/api/v001/usuario")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Carregamento de usuario realizada com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	public ResponseEntity<List<PesquisaUsuarioResumidoDTO>> pesquisa (
			@RequestParam(value = "descricao", required = false) String descricao) {
		List<Usuario> pesquisa = usuarioService.pesquisa(descricao);
		if (pesquisa.isEmpty())
			return ResponseEntity.notFound().build();
		List<PesquisaUsuarioResumidoDTO> dtos = pesquisa.stream().map(mapper::toPesquisaUsuarioResumidoDTO).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@PostMapping("/api/v001/usuario")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	@Operation(description = "Cria um novo usuário")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SECRETARIO')")
	public void salva(@RequestBody @Valid UsuarioDTO dto) {
		Usuario usuario = mapper.toEntity(dto);
		usuarioService.salvar(usuario);
	}
	
	@PutMapping("/api/v001/usuario/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	@Operation(description = "Atualiza dados de um usuário")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SECRETARIO')")
	public void atualiza(@RequestBody @Valid UsuarioDTO dto, @PathVariable UUID id) {
		Usuario usuario = mapper.toEntity(dto);
		usuario.setId(id);
		usuarioService.salvar(usuario);
	}
}