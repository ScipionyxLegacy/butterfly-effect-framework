package com.scipionyx.butterflyeffect.api.infrastructure.services.server;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Renato Mendes
 *
 * @param <ENTITY>
 *            This is the class that will be attached to the repository service
 */
public interface IRepositoryService<ENTITY, ENTITY_ID_TYPE extends Serializable>
		extends IService<ENTITY, ENTITY_ID_TYPE> {

	public CrudRepository<ENTITY, ENTITY_ID_TYPE> getRepository();

}
