package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;

@Data
public class ReporteDTO {

	private int mes;
	private int cantidad;
	private String etiqueta;
	private String servicio;
	
}
