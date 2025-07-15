package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.DadosPessoais;

public interface DadosPessoaisRepository extends JpaRepository<DadosPessoais, Long> {
}
