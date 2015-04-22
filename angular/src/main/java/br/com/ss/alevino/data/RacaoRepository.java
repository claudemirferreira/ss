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

import br.com.ss.alevino.model.Racao;

@ApplicationScoped
public class RacaoRepository {

	@Inject
	private EntityManager em;

	public Racao findById(Long id) {
		return em.find(Racao.class, id);
	}

	public Racao findByNome(String nome) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Racao> criteria = cb.createQuery(Racao.class);
		Root<Racao> Racao = criteria.from(Racao.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Racao).where(cb.equal(Racao.get(Racao_.email),
		// email));
		criteria.select(Racao).where(cb.equal(Racao.get("nome"), nome));
		return em.createQuery(criteria).getSingleResult();
	}

	public List<Racao> findAllOrderedByNome() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Racao> criteria = cb.createQuery(Racao.class);
		Root<Racao> Racao = criteria.from(Racao.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Racao).orderBy(cb.asc(Racao.get(Racao_.nome)));
		criteria.select(Racao).orderBy(cb.asc(Racao.get("nome")));
		return em.createQuery(criteria).getResultList();
	}
}
