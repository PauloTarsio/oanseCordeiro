package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.List;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Livro;
import br.com.igrejabatistadocordeiro.oanse.domain.model.clube.Clubes;

public interface LivroService {

	public List<Livro> listarPorClube(Clubes clube);

}
