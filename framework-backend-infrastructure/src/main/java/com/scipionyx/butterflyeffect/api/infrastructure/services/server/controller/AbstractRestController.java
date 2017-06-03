package com.scipionyx.butterflyeffect.api.infrastructure.services.server.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.IRepositoryService;

/**
 * 
 * @author Renato Mendes
 *
 * @param <T>
 *            Service service class
 * @param <ENTITY>
 *            Entity entity class
 */
public abstract class AbstractRestController<ENTITY, ENTITY_ID_TYPE extends Serializable, REPOSITORY extends IRepositoryService<ENTITY, ENTITY_ID_TYPE>> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractRestController.class);

	@Autowired
	protected transient REPOSITORY service;

	@RequestMapping(path = "/ping", method = { RequestMethod.GET, RequestMethod.POST })
	public final ResponseEntity<String> ping() throws RestClientException, Exception {
		LOGGER.debug("Ping request");
		return (new ResponseEntity<>(service.ping(), HttpStatus.OK));
	}

	@RequestMapping(path = "/health", method = { RequestMethod.GET, RequestMethod.POST })
	public final ResponseEntity<String> health() throws RestClientException, Exception {
		LOGGER.debug("Health request");
		return (new ResponseEntity<>(service.ping(), HttpStatus.OK));
	}

}
