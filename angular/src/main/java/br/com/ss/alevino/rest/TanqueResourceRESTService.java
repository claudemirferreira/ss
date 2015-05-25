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
package br.com.ss.alevino.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.ss.alevino.data.TanqueRepository;
import br.com.ss.alevino.model.Tanque;
import br.com.ss.alevino.service.TanqueRegistration;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the
 * tanques table.
 */
@Path("/tanques")
@RequestScoped
public class TanqueResourceRESTService {

	@Inject
	private Logger log;

	@Inject
	private Validator validator;

	@Inject
	private TanqueRepository repository;

	@Inject
	TanqueRegistration registration;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tanque> listAllTanques() {
		return repository.findAllOrderedByNome();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Tanque lookupTanqueById(@PathParam("id") long id) {
		Tanque tanque = repository.findById(id);
		if (tanque == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return tanque;
	}

	/**
	 * metodo para deletar um tanque da base de dados
	 * 
	 * @param id
	 * @return
	 */

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteById(@PathParam("id") long id) {
		Tanque tanque = new Tanque(id);
		try {
			registration.delete(tanque);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new tanque from the values provided. Performs validation, and
	 * will return a JAX-RS response with either 200 ok, or with a map of
	 * fields, and related errors.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTanque(Tanque tanque) {

		Response.ResponseBuilder builder = null;

		try {
			// Validates tanque using bean validation
			validateTanque(tanque);

			registration.register(tanque);

			// Create an "ok" response
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (ValidationException e) {
			// Handle the unique constrain violation
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("email", "Email taken");
			builder = Response.status(Response.Status.CONFLICT).entity(
					responseObj);
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(
					responseObj);
		}

		return builder.build();
	}

	/**
	 * <p>
	 * Validates the given Tanque variable and throws validation exceptions
	 * based on the type of error. If the error is standard bean validation
	 * errors then it will throw a ConstraintValidationException with the set of
	 * the constraints violated.
	 * </p>
	 * <p>
	 * If the error is caused because an existing tanque with the same email is
	 * registered it throws a regular validation exception so that it can be
	 * interpreted separately.
	 * </p>
	 * 
	 * @param tanque
	 *            Tanque to be validated
	 * @throws ConstraintViolationException
	 *             If Bean Validation errors exist
	 * @throws ValidationException
	 *             If tanque with the same email already exists
	 */
	private void validateTanque(Tanque tanque)
			throws ConstraintViolationException, ValidationException {
		// Create a bean validator and check for issues.
		Set<ConstraintViolation<Tanque>> violations = validator
				.validate(tanque);

		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(
					new HashSet<ConstraintViolation<?>>(violations));
		}

		// Check the uniqueness of the email address
		if (nomeAlreadyExists(tanque.getNome())) {
			throw new ValidationException("Unique Email Violation");
		}
	}

	/**
	 * Creates a JAX-RS "Bad Request" response including a map of all violation
	 * fields, and their message. This can then be used by clients to show
	 * violations.
	 * 
	 * @param violations
	 *            A set of violations that needs to be reported
	 * @return JAX-RS response containing all violations
	 */
	private Response.ResponseBuilder createViolationResponse(
			Set<ConstraintViolation<?>> violations) {
		log.fine("Validation completed. violations found: " + violations.size());

		Map<String, String> responseObj = new HashMap<String, String>();

		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(),
					violation.getMessage());
		}

		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}

	/**
	 * Checks if a tanque with the same email address is already registered.
	 * This is the only way to easily capture the
	 * "@UniqueConstraint(columnNames = "email")" constraint from the Tanque
	 * class.
	 * 
	 * @param email
	 *            The email to check
	 * @return True if the email already exists, and false otherwise
	 */
	public boolean nomeAlreadyExists(String nome) {
		Tanque tanque = null;
		try {
			tanque = repository.findByNome(nome);
		} catch (NoResultException e) {
			// ignore
		}
		return tanque != null;
	}
}