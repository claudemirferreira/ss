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

import br.com.ss.alevino.model.Tanque;

@ApplicationScoped
public class TanqueRepository {

	@Inject
	private EntityManager em;

	public Tanque findById(Long id) {
		return em.find(Tanque.class, id);
	}

	public Tanque findByNome(String nome) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tanque> criteria = cb.createQuery(Tanque.class);
		Root<Tanque> Tanque = criteria.from(Tanque.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Tanque).where(cb.equal(Tanque.get(Tanque_.email),
		// email));
		criteria.select(Tanque).where(cb.equal(Tanque.get("nome"), nome));
		return em.createQuery(criteria).getSingleResult();
	}

	public List<Tanque> findAllOrderedByNome() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tanque> criteria = cb.createQuery(Tanque.class);
		Root<Tanque> Tanque = criteria.from(Tanque.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Tanque).orderBy(cb.asc(Tanque.get(Tanque_.nome)));
		criteria.select(Tanque).orderBy(cb.asc(Tanque.get("nome")));
		return em.createQuery(criteria).getResultList();
	}
}
