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

import br.com.ss.alevino.model.Usuario;
import br.com.ss.alevino.repositorio.dao.UsuarioDAO;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class UsuarioService {

	@Inject
	private Logger log;

	@Inject
	private UsuarioDAO dao;

	@Inject
	private Event<Usuario> usuarioEventSrc;

	public void register(Usuario usuario) throws Exception {
		log.info("Registering " + usuario.getNome());
		dao.save(usuario);
		usuarioEventSrc.fire(usuario);
	}

	public void update(Usuario usuario) throws Exception {
		log.info("Registering " + usuario.getNome());
		dao.update(usuario);
		usuarioEventSrc.fire(usuario);
	}

	public Usuario findById(Long id) throws Exception {
		log.info("Registering " + id);
		Usuario usuario = dao.find(id);
		usuarioEventSrc.fire(usuario);
		return usuario;
	}

	public void remove(Long id) throws Exception {
		Usuario usuario = dao.find(id);
		log.info("Registering " + usuario.getNome());
		dao.remove(usuario);
		usuarioEventSrc.fire(usuario);
	}

	public List<Usuario> findAllOrderedByNome() {
		return dao.findAllOrderedByNome();
	}
}