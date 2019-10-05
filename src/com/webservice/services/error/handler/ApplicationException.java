
package com.webservice.services.error.handler;
/**
 * 
 * GGamage
 *
 */
public class ApplicationException extends RuntimeException{

	private static final long serialVersionUID = -3012729043714453037L;

	public ApplicationException(String msg) {
		super(msg);
	}

	public ApplicationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}


