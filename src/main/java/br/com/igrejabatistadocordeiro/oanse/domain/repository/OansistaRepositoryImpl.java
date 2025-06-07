package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.OansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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
		if (StringUtils.isNotBlank(filtro.getNome()))
		    cq.where(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
		return entityManager.createQuery(cq).getResultList();
	}
	
}
