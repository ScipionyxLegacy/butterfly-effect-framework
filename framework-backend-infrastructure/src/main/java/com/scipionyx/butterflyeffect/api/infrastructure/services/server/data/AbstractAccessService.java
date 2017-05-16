package com.scipionyx.butterflyeffect.api.infrastructure.services.server.data;

import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.IRepositoryService;

/**
 * 
 * This is the abstract Data Access services.
 * 
 * 
 * @author Renato Mendes
 *
 * @param <ENTITY>
 *            class for the entity
 * @param <REPOSITORY>
 *            class for the repository
 */
public abstract class AbstractAccessService<ENTITY, REPOSITORY> implements IRepositoryService<ENTITY> {

	private static final long serialVersionUID = 1L;

	public abstract String doPing() throws RestClientException, Exception;

	@Override
	public final String ping() throws RestClientException, Exception {
		return doPing();
	}

	@Override
	public final String health() throws RestClientException, Exception {
		return "ok";
	}

}
