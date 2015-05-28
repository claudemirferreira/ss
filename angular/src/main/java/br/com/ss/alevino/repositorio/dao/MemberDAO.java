package br.com.ss.alevino.repositorio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ss.alevino.model.Member;

@Stateless
public class MemberDAO extends GenericDAO<Member> {
	public MemberDAO() {
		super(Member.class);
	}

	public List<Member> findAllOrderedByName() {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
		Root<Member> entity = criteria.from(Member.class);
		criteria.select(entity).orderBy(cb.asc(entity.get("name")));
		return em.createQuery(criteria).getResultList();
	}

	public Member findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
		Root<Member> member = criteria.from(Member.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(member).where(cb.equal(member.get(Member_.email),
		// email));
		criteria.select(member).where(cb.equal(member.get("email"), email));
		return em.createQuery(criteria).getSingleResult();
	}
}