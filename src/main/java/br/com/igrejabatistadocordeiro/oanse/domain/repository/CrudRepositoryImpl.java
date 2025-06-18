package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class CrudRepositoryImpl<T> implements CrudRepository<T> {

	@PersistenceContext
	private EntityManager entityManager;
	
	private Class<T> entidade;	
	
	public CrudRepositoryImpl(Class<T> entidade) {
		this.entidade = entidade;
	}

	@Override
	public T carrega(Long id) {
		return entityManager.find(entidade, id);
	}

	@Override
	public void salva(T t) {
		entityManager.persist(t);
	}

	@Override
	public void atualiza(T t) {
        entityManager.merge(t);		
	}

	@Override
	public void deleta(Long t) {
		T t1 = carrega(t);
		if (t1 == null)
			return;
		entityManager.remove(t1);
	}

	@Override
	public List<T> listaTudo() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = cb.createQuery(entidade);
		criteriaQuery.from(entidade);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}
