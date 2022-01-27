package com.sidet.idat.ws.medisalud.exceptions;

public class MedisaludSidetException extends RuntimeException {

	private static final long serialVersionUID = -3710243696409634471L;

	public MedisaludSidetException( String mensaje ) {
		super(mensaje);
	}
	public MedisaludSidetException( String mensaje, Throwable cause ) {
		super(mensaje, cause);
	}
}
