package br.com.ss.alevino.repositorio.dao;

import javax.ejb.Stateless;

import br.com.ss.alevino.model.Member;

@Stateless
public class MemberDAO extends GenericDAO<Member> {
	public MemberDAO() {
		super(Member.class);
	}
}