package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Sessao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class SessaoRepositoryImpl extends CrudRepositoryImpl<Sessao> implements SessaoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public SessaoRepositoryImpl() {
		super(Sessao.class);
	}

	@Override
	public List<Sessao> pesquisa(SessaoFilter filtro) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Sessao> cq = cb.createQuery(Sessao.class);
		Root<Sessao> root = cq.from(Sessao.class);

	    List<Predicate> predicates = new ArrayList<>();
	    
	    if (filtro.getIdManual() != null) {
	    	predicates.add(cb.equal(root.get("trilha").get("manual").get("id"), filtro.getIdManual()));
	    }

	    if (filtro.getIdTrilha() != null) {
	        predicates.add(cb.equal(root.get("trilha").get("id"), filtro.getIdTrilha()));
	    }
	    
	    if (filtro.getIdSessao() != null) {
	    	predicates.add(cb.equal(root.get("id"), filtro.getIdSessao()));
	    }

	    if (filtro.getNumero() != null) {
	        predicates.add(cb.equal(root.get("numero"),filtro.getNumero()));
	    }

	    cq.select(root).distinct(true);

	    if (!predicates.isEmpty()) {
	        cq.where(cb.and(predicates.toArray(new Predicate[0])));
	    }

	    return entityManager.createQuery(cq).getResultList();
	}


}
