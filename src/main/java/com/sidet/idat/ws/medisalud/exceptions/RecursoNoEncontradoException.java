package com.sidet.idat.ws.medisalud.exceptions;

public class RecursoNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 6517148991513060017L;
	
	public RecursoNoEncontradoException( String mensaje ) {
		super(mensaje);
	}

	public RecursoNoEncontradoException( String mensaje, Throwable cause ) {
		super(mensaje, cause);
	}
}
