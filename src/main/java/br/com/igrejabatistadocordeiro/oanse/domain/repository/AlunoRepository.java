package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	Optional<Aluno> findByDadosPessoaisRg(String rg);

	Optional<Aluno> findByDadosPessoaisCpf(String cpf);

	Optional<Aluno> findByDadosPessoaisCnpj(String cnpj);	

}
