package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
