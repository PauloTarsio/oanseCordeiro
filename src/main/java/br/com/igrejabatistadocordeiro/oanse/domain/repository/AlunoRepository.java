package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {	

}
