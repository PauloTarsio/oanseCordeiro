package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Lider;

public interface LiderRepository extends JpaRepository<Lider, Long> {
}
