package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;

public interface IgrejaRepository extends JpaRepository<Igreja, Long>, QueryByExampleExecutor<Igreja> {

	public Optional<Igreja> findByDadosPessoaisRg(String rg);
	public Optional<Igreja> findByDadosPessoaisCpf(String cpf);
	public Optional<Igreja> findByDadosPessoaisCnpj(String cnpj);
	public Collection<Igreja> findByAtivo(boolean ativo);
}
