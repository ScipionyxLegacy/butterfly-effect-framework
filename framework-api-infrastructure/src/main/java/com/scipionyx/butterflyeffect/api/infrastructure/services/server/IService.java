package com.scipionyx.butterflyeffect.api.infrastructure.services.server;

import java.io.Serializable;

import org.springframework.web.client.RestClientException;

/**
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IService<ENTITY, ENTITY_ID_TYPE extends Serializable> extends Serializable {

	public String ping() throws RestClientException, Exception;

	public String health() throws RestClientException, Exception;

}
