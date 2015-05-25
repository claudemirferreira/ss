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
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ss.alevino.model.Usuario;

@ApplicationScoped
public class UsuarioRepository {

	@Inject
	private EntityManager em;

	public Usuario findById(Long id) {
		return em.find(Usuario.class, id);
	}

	/**
	 * metodo utilizada para validar login e senha do usuario
	 * @param login
	 * @param senha
	 * @return usuario autenticado
	 */
	public Usuario autenticar(String login, String senha) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> Usuario = criteria.from(Usuario.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Usuario).where(cb.equal(Usuario.get(Usuario_.email),
		// email));
		criteria.select(Usuario).where(cb.equal(Usuario.get("login"), login));
		criteria.select(Usuario).where(cb.equal(Usuario.get("senha"), senha));

		Usuario usuario = new Usuario();

		try {
			return em.createQuery(criteria).getSingleResult();

		} catch (NoResultException ne) {
			System.out.println("Login ou senha invalida");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ocorreu um erro");
		}

		return null;
	}

	public Usuario findByNome(String nome) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> Usuario = criteria.from(Usuario.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Usuario).where(cb.equal(Usuario.get(Usuario_.email),
		// email));
		criteria.select(Usuario).where(cb.equal(Usuario.get("nome"), nome));
		return em.createQuery(criteria).getSingleResult();
	}

	public List<Usuario> findAllOrderedByNome() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> Usuario = criteria.from(Usuario.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Usuario).orderBy(cb.asc(Usuario.get(Usuario_.nome)));
		criteria.select(Usuario).orderBy(cb.asc(Usuario.get("nome")));
		return em.createQuery(criteria).getResultList();
	}
}