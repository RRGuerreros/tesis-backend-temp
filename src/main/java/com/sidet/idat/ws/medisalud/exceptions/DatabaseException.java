package com.sidet.idat.ws.medisalud.exceptions;

public class DatabaseException extends RuntimeException{

	private static final long serialVersionUID = -3821347568684307238L;
	
	public DatabaseException( String mensaje ) {
		super(mensaje);
	}
	
	public DatabaseException( String mensaje, Throwable cause ) {
		super(mensaje, cause);
	}
}
