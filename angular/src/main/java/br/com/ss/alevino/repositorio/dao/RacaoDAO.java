package br.com.ss.alevino.repositorio.dao;

import javax.ejb.Stateless;

import br.com.ss.alevino.model.Racao;

@Stateless
public class RacaoDAO extends GenericDAO<Racao> {
	public RacaoDAO() {
		super(Racao.class);
	}
}