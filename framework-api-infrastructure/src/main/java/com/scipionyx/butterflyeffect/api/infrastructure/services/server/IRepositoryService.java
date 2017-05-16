package com.scipionyx.butterflyeffect.api.infrastructure.services.server;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Renato Mendes
 *
 * @param <ENTITY>
 *            This is the class that will be attached to the repository service
 */
public interface IRepositoryService<ENTITY> extends IService<ENTITY> {

	public CrudRepository<ENTITY, Long> getRepository();

}
