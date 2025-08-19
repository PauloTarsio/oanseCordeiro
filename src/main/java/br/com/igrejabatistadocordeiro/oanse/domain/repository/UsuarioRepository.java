package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>, JpaSpecificationExecutor<Usuario> {

	Optional<Usuario> findByLogin(String login);

}
