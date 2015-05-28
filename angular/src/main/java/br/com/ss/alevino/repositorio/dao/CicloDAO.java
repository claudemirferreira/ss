package br.com.ss.alevino.repositorio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ss.alevino.model.Ciclo;

@Stateless
public class CicloDAO extends GenericDAO<Ciclo> {
	public CicloDAO() {
		super(Ciclo.class);
	}

	public List<Ciclo> findAllOrderedByPeriodo() {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Ciclo> criteria = cb.createQuery(Ciclo.class);
		Root<Ciclo> ciclo = criteria.from(Ciclo.class);
		criteria.select(ciclo).orderBy(cb.asc(ciclo.get("periodo")));
		return em.createQuery(criteria).getResultList();
	}
}