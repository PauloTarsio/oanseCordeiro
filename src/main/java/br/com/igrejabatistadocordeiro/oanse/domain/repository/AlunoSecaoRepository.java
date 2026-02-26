package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.aluno.AlunoSecao;

public interface AlunoSecaoRepository extends JpaRepository<AlunoSecao, Long> {

	Optional<AlunoSecao> findByAlunoManualIdAndSecaoId(Long alunoManualId, Long secaoId);

	List<AlunoSecao> findByAlunoManualId(Long alunoManualId);

	Optional<AlunoSecao> findBySecaoId(Long secaoId);

    // Métodos customizados podem ser adicionados aqui
}
