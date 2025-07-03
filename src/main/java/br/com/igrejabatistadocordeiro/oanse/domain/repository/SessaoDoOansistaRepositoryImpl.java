package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoDoOansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.SessaoDoOansista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class SessaoDoOansistaRepositoryImpl extends CrudRepositoryImpl<SessaoDoOansista> implements SessaoDoOansistaRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	public SessaoDoOansistaRepositoryImpl() {
		super(SessaoDoOansista.class);
	}

	@Override
	public List<SessaoDoOansista> pesquisa(SessaoDoOansistaFilter filter) {
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<SessaoDoOansista> cq = cb.createQuery(SessaoDoOansista.class);
	    Root<SessaoDoOansista> root = cq.from(SessaoDoOansista.class);
	    
	    root.fetch("manualDoOansista", JoinType.LEFT).fetch("manual", JoinType.LEFT);
	    root.fetch("sessao", JoinType.LEFT).fetch("trilha", JoinType.LEFT);

	    List<Predicate> predicates = new ArrayList<>();

	    if (filter.getIdOansista() != null) {
	        predicates.add(cb.equal(root.get("oansista").get("id"), filter.getIdOansista()));
	    }
	    
	    if (filter.getIdManual() != null) {
	        predicates.add(cb.equal(root.get("manualDoOansista").get("manual").get("id"), filter.getIdManual()));
	    }
	    
	    if (filter.getIdTrilha() != null) {
	        predicates.add(cb.equal(root.get("sessao").get("trilha").get("id"), filter.getIdTrilha()));
	    }
	    
	    if (filter.getNumeroDaSessao() != null) {
	    	predicates.add(cb.equal(root.get("sessao").get("numero"), filter.getNumeroDaSessao()));
	    }

	    if (filter.getConcluido() != null) {
	        if (filter.getConcluido()) {
	            predicates.add(cb.isNotNull(root.get("dataConclusao")));
	        } else {
	            predicates.add(cb.isNull(root.get("dataConclusao")));
	        }
	    }
	    
	    List<Order> orderList = new ArrayList<>();
	    orderList.add(cb.asc(root.get("manualDoOansista").get("manual").get("id")));
	    orderList.add(cb.asc(root.get("sessao").get("trilha").get("id")));
	    orderList.add(cb.asc(root.get("sessao").get("numero")));
	    cq.orderBy(orderList);
	    
	    cq.where(predicates.toArray(new Predicate[0]));

	    TypedQuery<SessaoDoOansista> query = entityManager.createQuery(cq);
	    return query.getResultList();
	}

	@Override
	public SessaoDoOansista carrega(Long idOansista, Long idManual, Long idTrilha, Integer numeroDaSessao) {
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<SessaoDoOansista> cq = cb.createQuery(SessaoDoOansista.class);
	    Root<SessaoDoOansista> root = cq.from(SessaoDoOansista.class);

	    root.fetch("manualDoOansista", JoinType.LEFT).fetch("manual", JoinType.LEFT);
	    root.fetch("sessao", JoinType.LEFT).fetch("trilha", JoinType.LEFT);

	    List<Predicate> predicates = new ArrayList<>();

	    if (idOansista != null) {
	        predicates.add(cb.equal(root.get("oansista").get("id"), idOansista));
	    }

	    if (idManual != null) {
	        predicates.add(cb.equal(root.get("manualDoOansista").get("manual").get("id"), idManual));
	    }

	    if (idTrilha != null) {
	        predicates.add(cb.equal(root.get("sessao").get("trilha").get("id"), idTrilha));
	    }

	    if (numeroDaSessao != null) {
	        predicates.add(cb.equal(root.get("sessao").get("numero"), numeroDaSessao));
	    }

	    cq.where(predicates.toArray(new Predicate[0]));

	    TypedQuery<SessaoDoOansista> query = entityManager.createQuery(cq);
	    List<SessaoDoOansista> resultados = query.getResultList();

	    return resultados.isEmpty() ? null : resultados.get(0);
	}

}
