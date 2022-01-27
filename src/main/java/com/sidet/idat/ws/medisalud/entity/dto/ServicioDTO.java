package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;

 @Data
public class ServicioDTO {

	private Integer servicioId;
	private String nombre;
	private AreaMedicaDTO areaMedica;
	
}
