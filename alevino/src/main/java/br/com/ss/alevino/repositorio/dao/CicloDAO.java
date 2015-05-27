package br.com.ss.alevino.repositorio.dao;

import javax.ejb.Stateless;

import br.com.ss.alevino.repositorio.model.Ciclo;

@Stateless
public class CicloDAO extends GenericDAO<Ciclo> {
	public CicloDAO() {
		super(Ciclo.class);
	}
}