package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
