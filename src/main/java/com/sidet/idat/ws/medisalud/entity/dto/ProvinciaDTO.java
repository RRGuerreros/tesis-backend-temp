package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;

@Data
public class ProvinciaDTO {

	private Integer provinciaId;
	private String nombre;
	private DepartamentoDTO departamento;
	
}
