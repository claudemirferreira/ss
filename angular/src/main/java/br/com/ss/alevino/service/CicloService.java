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

import br.com.ss.alevino.model.Ciclo;
import br.com.ss.alevino.repositorio.dao.CicloDAO;

@Stateless
public class CicloService {

	@Inject
	private Logger log;

	@Inject
	private CicloDAO dao;

	@Inject
	private Event<Ciclo> cicloEventSrc;

	public void register(Ciclo ciclo) throws Exception {
		log.info("Registering " + ciclo.getPeriodo());
		dao.save(ciclo);
		cicloEventSrc.fire(ciclo);
	}

	public List<Ciclo> findAllOrderedByPeriodo() {
		return dao.findAllOrderedByPeriodo();
	}

	public void update(Ciclo ciclo) throws Exception {
		log.info("Registering " + ciclo.getPeriodo());
		dao.update(ciclo);
		cicloEventSrc.fire(ciclo);
	}

	public Ciclo findById(Long id) throws Exception {
		log.info("Registering " + id);
		Ciclo ciclo = dao.find(id);
		cicloEventSrc.fire(ciclo);
		return ciclo;
	}

	public void remove(Long id) throws Exception {
		Ciclo ciclo = dao.find(id);
		log.info("Registering " + ciclo.getPeriodo());
		dao.remove(ciclo);
		cicloEventSrc.fire(ciclo);
	}
}