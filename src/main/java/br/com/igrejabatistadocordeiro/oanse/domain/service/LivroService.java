package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Clubes;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Livro;

public interface LivroService {

	public List<Livro> listarPorClube(Clubes clube);

}
