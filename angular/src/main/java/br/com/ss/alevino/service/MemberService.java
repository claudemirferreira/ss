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

import br.com.ss.alevino.model.Member;
import br.com.ss.alevino.repositorio.dao.MemberDAO;

@Stateless
public class MemberService {

	@Inject
	private Logger log;

	@Inject
	private MemberDAO dao;

	@Inject
	private Event<Member> memberEventSrc;

	public void register(Member member) throws Exception {
		log.info("Registering " + member.getName());
		dao.save(member);
		memberEventSrc.fire(member);
	}

	public void update(Member member) throws Exception {
		log.info("Registering " + member.getName());
		dao.update(member);
		memberEventSrc.fire(member);
	}

	public Member findById(Long id) throws Exception {
		log.info("Registering " + id);
		Member member = dao.find(id);
		memberEventSrc.fire(member);
		return member;
	}

	public void remove(Long id) throws Exception {
		Member member = dao.find(id);
		log.info("Registering " + member.getName());
		dao.remove(member);
		memberEventSrc.fire(member);
	}

	public List<Member> findAllOrderedByName() {
		return dao.findAllOrderedByName();
	}
	
	public Member findByEmail(String email){
		return dao.findByEmail(email);
	}
}