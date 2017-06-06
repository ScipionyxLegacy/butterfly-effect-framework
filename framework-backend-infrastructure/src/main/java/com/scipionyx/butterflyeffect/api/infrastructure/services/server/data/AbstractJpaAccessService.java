package com.scipionyx.butterflyeffect.api.infrastructure.services.server.data;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.client.RestClientException;

/**
 * 
 * This is the abstract Data Access services.
 * 
 * 
 * @author Renato Mendes
 *
 * @param <ENTITY>
 *            Business entity target by the Service
 * @param <REPOSITORY>
 *            Spring Data service
 * @param <ENTITY_ID_TYPE>
 *            Class for the Business entity id field
 */
public abstract class AbstractJpaAccessService<REPOSITORY, ENTITY, ENTITY_ID_TYPE extends Serializable>
		extends AbstractAccessService<REPOSITORY, ENTITY, ENTITY_ID_TYPE> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired(required = true)
	private CrudRepository<ENTITY, ENTITY_ID_TYPE> repository;

	/**
	 * 
	 */
	@Override
	public String doPing() throws RestClientException, Exception {
		return "I'm good, and what about u ? " + (repository != null);
	}

	/**
	 * 
	 */
	public CrudRepository<ENTITY, ENTITY_ID_TYPE> getRepository() {
		return repository;
	}

}
