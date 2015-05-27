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

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ss.alevino.model.Member;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MemberRegistration {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Member> memberEventSrc;

	public void register(Member member) throws Exception {
		log.info("Registering " + member.getName());
		em.persist(member);
		memberEventSrc.fire(member);
	}
	
	public void update(Member member) throws Exception {
		log.info("Registering " + member.getName());
		em.merge(member);
		memberEventSrc.fire(member);
	}

	public void remove(Long id) throws Exception {
		Member member = em.find(Member.class, id);
		log.info("Registering " + member.getName());
		em.remove(member);
		memberEventSrc.fire(member);
	}
}
