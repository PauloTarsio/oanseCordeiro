package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Responsavel;

@Repository
public class ResponsavelRepositoryImpl extends CrudRepositoryImpl<Responsavel> implements ResponsavelRepository {

	public ResponsavelRepositoryImpl() {
		super(Responsavel.class);
	}

}
