package com.sidet.idat.ws.medisalud.exceptions;

public class ValidacionException extends RuntimeException{
	
	private static final long serialVersionUID = -1236017492927341914L;

	public ValidacionException( String mensaje ) {
		super(mensaje);
	}
	public ValidacionException( String mensaje, Throwable cause ) {
		super(mensaje, cause);
	}
}
