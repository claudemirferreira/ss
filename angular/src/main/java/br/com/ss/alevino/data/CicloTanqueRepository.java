/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.ss.alevino.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ss.alevino.model.Ciclo;
import br.com.ss.alevino.model.CicloTanque;

@ApplicationScoped
public class CicloTanqueRepository {

	@Inject
	private EntityManager em;

	public CicloTanque findById(Long id) {
		return em.find(CicloTanque.class, id);
	}

	public CicloTanque findByCiclo(Ciclo ciclo) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CicloTanque> criteria = cb.createQuery(CicloTanque.class);
		Root<CicloTanque> CicloTanque = criteria.from(CicloTanque.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(CicloTanque).where(cb.equal(CicloTanque.get(CicloTanque_.email),
		// email));
		criteria.select(CicloTanque).where(
				cb.equal(CicloTanque.get("ciclo"), ciclo));
		return em.createQuery(criteria).getSingleResult();
	}

	public List<CicloTanque> findAllOrderedByCiclo() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CicloTanque> criteria = cb.createQuery(CicloTanque.class);
		Root<CicloTanque> cicloTanque = criteria.from(CicloTanque.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(CicloTanque).orderBy(cb.asc(CicloTanque.get(CicloTanque_.nome)));
//		criteria.select(cicloTanque).orderBy(cb.asc(cicloTanque.get("ciclo")));
		return em.createQuery(criteria).getResultList();
	}
}
