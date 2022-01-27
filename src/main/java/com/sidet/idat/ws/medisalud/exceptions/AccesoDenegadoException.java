package com.sidet.idat.ws.medisalud.exceptions;

public class AccesoDenegadoException extends RuntimeException {

	private static final long serialVersionUID = 673205946602293425L;

	public AccesoDenegadoException( String mensaje ) {
		super(mensaje);
	}
	
	public AccesoDenegadoException( String mensaje, Throwable cause ) {
		super(mensaje, cause);
	}
}
