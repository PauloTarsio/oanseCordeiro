package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Clube;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class ManualRepositoryImpl extends CrudRepositoryImpl<Manual> implements ManualRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public ManualRepositoryImpl() {
		super(Manual.class);
	}

	@Override
	public List<Manual> pesquisa(Clube clube) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Manual> cq = cb.createQuery(Manual.class);
		Root<Manual> root = cq.from(Manual.class);
		if (clube != null)
			cq.where(cb.equal(root.get("clube"), clube));
		return entityManager.createQuery(cq).getResultList();
	}

}
