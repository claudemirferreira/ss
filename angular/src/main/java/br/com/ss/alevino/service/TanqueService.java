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
package br.com.ss.alevino.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.ss.alevino.model.Tanque;
import br.com.ss.alevino.repositorio.dao.TanqueDAO;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class TanqueService {

	@Inject
	private Logger log;

	@Inject
	private TanqueDAO dao;

	@Inject
	private Event<Tanque> tanqueEventSrc;

	public void register(Tanque tanque) throws Exception {
		log.info("Registering " + tanque.getNome());
		dao.save(tanque);
		tanqueEventSrc.fire(tanque);
	}

	public void update(Tanque tanque) throws Exception {
		log.info("Registering " + tanque.getNome());
		dao.update(tanque);
		tanqueEventSrc.fire(tanque);
	}

	public Tanque findById(Long id) throws Exception {
		log.info("Registering " + id);
		Tanque tanque = dao.find(id);
		tanqueEventSrc.fire(tanque);
		return tanque;
	}

	public void remove(Long id) throws Exception {
		Tanque tanque = dao.find(id);
		log.info("Registering " + tanque.getNome());
		dao.remove(tanque);
		tanqueEventSrc.fire(tanque);
	}

	public List<Tanque> findAllOrderedByNome() {
		return dao.findAllOrderedByNome();
	}
}