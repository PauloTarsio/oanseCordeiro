package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.DadosPessoais;

public interface DadosPessoaisRepository extends JpaRepository<DadosPessoais, Long> {

	Optional<DadosPessoais> findByCpf(String cpf);

	Optional<DadosPessoais> findByCnpj(String cnpj);
}
