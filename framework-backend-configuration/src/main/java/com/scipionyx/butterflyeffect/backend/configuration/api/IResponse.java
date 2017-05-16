package com.scipionyx.butterflyeffect.backend.configuration.api;

import java.io.Serializable;

/**
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IResponse<T> extends Serializable {

	/**
	 * The system was able to verify the correctness of the process
	 * 
	 * @return true if is verified
	 */
	public boolean isVerified();

	public T getResponse();

	public Exception getException();

}
