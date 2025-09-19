package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.AlunoSecao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoSecaoRepository extends JpaRepository<AlunoSecao, Long> {
    // Métodos customizados podem ser adicionados aqui
}
