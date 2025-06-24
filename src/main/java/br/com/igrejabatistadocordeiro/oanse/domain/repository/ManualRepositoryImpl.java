package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.ManualFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class ManualRepositoryImpl extends CrudRepositoryImpl<Manual> implements ManualRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public ManualRepositoryImpl() {
		super(Manual.class);
	}

	@Override
	public List<Manual> pesquisa(ManualFilter filtro) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Manual> cq = cb.createQuery(Manual.class);
		Root<Manual> root = cq.from(Manual.class);
		List<Predicate> predicates = new ArrayList<>();
		if (filtro.getClube() != null)
			predicates.add(cb.like(cb.lower(root.get("clube")),"%" + filtro.getClube().toLowerCase() + "%"));
		if (!predicates.isEmpty())
	        cq.where(cb.and(predicates.toArray(new Predicate[0])));
		return entityManager.createQuery(cq).getResultList();
	}

}
