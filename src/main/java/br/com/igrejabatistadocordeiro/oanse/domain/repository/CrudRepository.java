package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;

public interface CrudRepository<T> {
	
	public T carrega(Long id);
	public List<T> listaTudo();
	public void salva(T t);
	public void atualiza(T t);
	public void deleta(Long id);
	
}
