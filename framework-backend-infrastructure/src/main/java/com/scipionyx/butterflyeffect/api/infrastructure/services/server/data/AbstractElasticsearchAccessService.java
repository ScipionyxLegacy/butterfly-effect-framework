package com.scipionyx.butterflyeffect.api.infrastructure.services.server.data;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.IRepositoryService;
import com.scipionyx.butterflyeffect.api.infrastructure.services.server.IService;

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
public abstract class AbstractElasticsearchAccessService<REPOSITORY, ENTITY, ENTITY_ID_TYPE extends Serializable>
		implements IService<ENTITY, ENTITY_ID_TYPE>, IRepositoryService<ENTITY, ENTITY_ID_TYPE> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired(required = true)
	private ElasticsearchRepository<ENTITY, ENTITY_ID_TYPE> repository;

	/**
	 * 
	 */
	@Override
	public String ping() throws RestClientException, Exception {
		return "I'm good, and what about u ? " + (repository != null);
	}

	/**
	 * 
	 */
	@Override
	public String health() throws RestClientException, Exception {
		return "ok";
	}

	/**
	 * 
	 */
	public ElasticsearchRepository<ENTITY, ENTITY_ID_TYPE> getRepository() {
		return repository;
	}

}
