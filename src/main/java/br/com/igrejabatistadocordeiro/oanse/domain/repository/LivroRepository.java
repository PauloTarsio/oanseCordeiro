package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Livro;
import br.com.igrejabatistadocordeiro.oanse.domain.model.clube.Clubes;

public interface LivroRepository extends JpaRepository<Livro, Long> {

	public List<Livro> findByClube(Clubes clube);

}
