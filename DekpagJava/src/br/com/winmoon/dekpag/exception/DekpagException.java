package br.com.winmoon.dekpag.exception;

/**
 * @author Diego M. Rodrigues
 * @date 2011/09/09
 * 
 * @description: Dekpag exception 
 */
public class DekpagException extends Exception {

	private static final long serialVersionUID = 1L;

	public DekpagException(String message) {
		super("Dekpag error: " + message);
	}

}