package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>, JpaSpecificationExecutor<Aluno> {

	Optional<Aluno> findByDadosPessoaisRg(String rg);
	Optional<Aluno> findByDadosPessoaisCpf(String cpf);
	Optional<Aluno> findByDadosPessoaisCnpj(String cnpj);	

}
