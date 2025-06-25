package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.ManualDoOansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.ManualDoOansista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class ManualDoOansistaRepositoryImpl extends CrudRepositoryImpl<ManualDoOansista> implements ManualDoOansistaRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public ManualDoOansistaRepositoryImpl() {
		super(ManualDoOansista.class);
	}

	@Override
	public List<ManualDoOansista> pesquisa(ManualDoOansistaFilter filtro) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ManualDoOansista> criteriaQuery = criteriaBuilder.createQuery(ManualDoOansista.class);		
		Root<ManualDoOansista> root = criteriaQuery.from(ManualDoOansista.class);
		root.fetch("manual", JoinType.LEFT);
		root.fetch("oansista", JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<>();		
	    
		if (filtro.getIdOansista() != null)
	        predicates.add(criteriaBuilder.equal(root.get("oansista").get("id"), filtro.getIdOansista()));
		
		if (filtro.getConcluido() != null)
			predicates.add(criteriaBuilder.equal(root.get("concluido"), filtro.getConcluido()));
		
		if (filtro.getIdManual() != null)
			predicates.add(criteriaBuilder.equal(root.get("manual").get("id"), filtro.getIdManual()));
		
		if (filtro.getClube() != null && !filtro.getClube().isEmpty())
			predicates.add(criteriaBuilder.equal(root.get("manual").get("clube"), filtro.getClube()));

	    criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));
	    return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public ManualDoOansista carrega(Long idOansista, Long idManual) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ManualDoOansista> criteriaQuery = criteriaBuilder.createQuery(ManualDoOansista.class);		
		Root<ManualDoOansista> root = criteriaQuery.from(ManualDoOansista.class);
		root.fetch("manual", JoinType.LEFT);
		root.fetch("oansista", JoinType.LEFT);		
		List<Predicate> predicates = new ArrayList<>();	    
	    predicates.add(criteriaBuilder.equal(root.get("oansista").get("id"), idOansista));
	    predicates.add(criteriaBuilder.equal(root.get("manual").get("id"), idManual));
	    criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));
		return entityManager.createQuery(criteriaQuery)
			    .getResultStream()
			    .findFirst()
			    .orElse(null);

	}

}
