package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
