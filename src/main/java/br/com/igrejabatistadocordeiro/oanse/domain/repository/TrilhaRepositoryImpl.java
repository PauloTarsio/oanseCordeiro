package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.TrilhaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Trilha;
import br.com.igrejabatistadocordeiro.oanse.domain.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class TrilhaRepositoryImpl extends CrudRepositoryImpl<Trilha> implements TrilhaRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public TrilhaRepositoryImpl() {
		super(Trilha.class);
	}

	@Override
	public List<Trilha> pesquisa(TrilhaFilter filtro) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Trilha> cq = cb.createQuery(Trilha.class);
		Root<Trilha> root = cq.from(Trilha.class);
		
	    root.fetch("sessoes", JoinType.LEFT);

	    List<Predicate> predicates = new ArrayList<>();

	    if (filtro.getIdTrilha() != null) {
	        predicates.add(cb.equal(root.get("id"), filtro.getIdTrilha()));
	    }

	    if (StringUtils.isNotBlank(filtro.getNome())) {
	        predicates.add(cb.like(
	            cb.lower(root.get("nome")),
	            "%" + filtro.getNome().toLowerCase() + "%"
	        ));
	    }

	    if (filtro.getIdManual() != null) {
	        predicates.add(cb.equal(root.get("manual").get("id"), filtro.getIdManual()));
	    }

	    cq.select(root).distinct(true);

	    if (!predicates.isEmpty()) {
	        cq.where(cb.and(predicates.toArray(new Predicate[0])));
	    }

	    return entityManager.createQuery(cq).getResultList();
	}


}
