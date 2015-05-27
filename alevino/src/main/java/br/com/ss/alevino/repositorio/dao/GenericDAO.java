package br.com.ss.alevino.repositorio.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ss.alevino.repositorio.model.AbstractEntity;

public abstract class GenericDAO<T extends AbstractEntity> {

	@PersistenceContext
	protected EntityManager em;

	private Class<T> entityClass;

	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void save(T entity) {
		em.persist(entity);
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void remove(T entity) {
		entity = em.find(entityClass, entity.getId());
		em.remove(entity);
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public T update(T entity) {
		return em.merge(entity);
	}

	public T find(Long entityID) {
		return em.find(entityClass, entityID);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}

	// Using the unchecked because JPA does not have a
	// ery.getSingleResult()<T> method
	@SuppressWarnings("unchecked")
	protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
		T result = null;

		try {
			Query query = em.createNamedQuery(namedQuery);

			// Method that will populate parameters if they are passed not null
			// and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (T) query.getSingleResult();

		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	private void populateQueryParameters(Query query,
			Map<String, Object> parameters) {

		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
}