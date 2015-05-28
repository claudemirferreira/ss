package br.com.ss.alevino.repositorio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ss.alevino.model.Racao;

@Stateless
public class RacaoDAO extends GenericDAO<Racao> {
	public RacaoDAO() {
		super(Racao.class);
	}

	public List<Racao> findAllOrderedByNome() {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Racao> criteria = cb.createQuery(Racao.class);
		Root<Racao> entity = criteria.from(Racao.class);
		criteria.select(entity).orderBy(cb.asc(entity.get("nome")));
		return em.createQuery(criteria).getResultList();
	}
}