package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;

public interface IgrejaRepository extends JpaRepository<Igreja, Long>, QueryByExampleExecutor<Igreja>, JpaSpecificationExecutor<Igreja>{

	public Optional<Igreja> findByDadosPessoaisRg(String rg);
	public Optional<Igreja> findByDadosPessoaisCpf(String cpf);
	public Optional<Igreja> findByDadosPessoaisCnpj(String cnpj);
	public List<Igreja> findByAtivo(boolean ativo);
}
