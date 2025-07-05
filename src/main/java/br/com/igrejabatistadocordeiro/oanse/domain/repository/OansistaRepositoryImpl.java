package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.OansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Responsavel;
import br.com.igrejabatistadocordeiro.oanse.domain.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class OansistaRepositoryImpl extends CrudRepositoryImpl<Oansista> implements OansistaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public OansistaRepositoryImpl() {
		super(Oansista.class);
	}

	@Override
	public List<Oansista> pesquisa(OansistaFilter filtro) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Oansista> cq = cb.createQuery(Oansista.class);
		Root<Oansista> root = cq.from(Oansista.class);
		List<Predicate> predicates = new ArrayList<>();
		root.fetch("responsaveis", JoinType.LEFT);
        if (filtro.getId() != null)
            predicates.add(cb.equal(root.get("id"), filtro.getId()));
        if (StringUtils.isNotBlank(filtro.getNome()))
            predicates.add(cb.like(cb.lower(root.get("nome")),"%" + filtro.getNome().toLowerCase() + "%"));
	    if (!predicates.isEmpty())
	        cq.where(cb.and(predicates.toArray(new Predicate[0])));
	    cq.orderBy(cb.asc(root.get("id")));
	    return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public Boolean existe(Oansista oansista) {
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Long> cq = cb.createQuery(Long.class);
	    Root<Oansista> root = cq.from(Oansista.class);
	    Predicate nomeDataNascimento = cb.and(
	        cb.equal(cb.lower(root.get("nome")), oansista.getNome().toLowerCase()),
	        cb.equal(root.get("dataNascimento"), oansista.getDataNascimento())
	    );
	    if (oansista.getId() != null) {
	        Predicate notSameId = cb.notEqual(root.get("id"), oansista.getId());
	        cq.select(cb.count(root)).where(cb.and(nomeDataNascimento, notSameId));
	    } else {
	        cq.select(cb.count(root)).where(nomeDataNascimento);
	    }
	    Long count = entityManager.createQuery(cq).getSingleResult();
	    return count > 0;
	}

	@Override
	public Responsavel carregaPor(String telefone, String email) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Responsavel> cq = cb.createQuery(Responsavel.class);
		Root<Responsavel> root = cq.from(Responsavel.class);
		Predicate predicate = cb.and(
			cb.equal(cb.lower(root.get("telefone")), telefone.toLowerCase()),
			cb.equal(cb.lower(root.get("email")), email.toLowerCase())
		);
		cq.select(root).where(predicate);
		List<Responsavel> resultado = entityManager.createQuery(cq).getResultList();
		return resultado.isEmpty() ? null : resultado.get(0);
	}
	
}