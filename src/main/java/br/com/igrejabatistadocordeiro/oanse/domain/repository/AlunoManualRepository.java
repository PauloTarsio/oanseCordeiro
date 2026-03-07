package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Livro;
import br.com.igrejabatistadocordeiro.oanse.domain.model.aluno.Aluno;
import br.com.igrejabatistadocordeiro.oanse.domain.model.aluno.AlunoManual;

public interface AlunoManualRepository extends JpaRepository<AlunoManual, Long> {

	boolean existsByAlunoAndLivro(Aluno aluno, Livro livro);

	List<AlunoManual> findByAluno(Aluno aluno);

	List<AlunoManual> findByLivro(Livro livro);

	List<AlunoManual> findByAlunoAndLivro(Aluno aluno, Livro livro);
	
	List<AlunoManual> findByAlunoIgrejaId(Long igrejaId);
	
	List<AlunoManual> findByAlunoIgrejaIdAndLivroId(Long igrejaId, Long livroId);

	List<AlunoManual> findByAlunoIdAndAlunoIgrejaId(Long alunoId, Long igrejaId);

	List<AlunoManual> findByAlunoIdAndLivroIdAndAlunoIgrejaId(Long alunoId, Long livroId, Long igrejaId);
	
	List<AlunoManual> findByAlunoDadosPessoaisDescricaoContainingIgnoreCase(String descricao);
	
	List<AlunoManual> findByAlunoDadosPessoaisDescricaoContainingIgnoreCaseAndAlunoIgrejaId(String descricao, Long igrejaId);
}