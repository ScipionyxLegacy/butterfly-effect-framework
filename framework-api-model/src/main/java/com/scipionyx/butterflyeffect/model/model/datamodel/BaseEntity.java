package com.scipionyx.butterflyeffect.model.model.datamodel;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

/**
 * 
 * @author Renato Mendes
 *
 * @param <TYPE>
 *            type of the id
 */
public abstract class BaseEntity<TYPE extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private TYPE id;

	private String entityId;

	public TYPE getId() {
		return id;
	}

	public void setId(TYPE id) {
		this.id = id;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

}
