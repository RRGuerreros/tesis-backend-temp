package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;

@Data
public class DistritoDTO {

	private Integer distritoId;
	private String nombre;
	private ProvinciaDTO provincia;
	
}
