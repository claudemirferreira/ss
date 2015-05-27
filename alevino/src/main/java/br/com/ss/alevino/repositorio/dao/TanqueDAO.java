package br.com.ss.alevino.repositorio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ss.alevino.repositorio.model.Tanque;

@Stateless
public class TanqueDAO extends GenericDAO<Tanque> {
	public TanqueDAO() {
		super(Tanque.class);
	}

	public List<Tanque> findAllOrderedByName() {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Tanque> criteria = cb.createQuery(Tanque.class);
		Root<Tanque> tanque = criteria.from(Tanque.class);
		criteria.select(tanque).orderBy(cb.asc(tanque.get("name")));
		return em.createQuery(criteria).getResultList();
	}
}