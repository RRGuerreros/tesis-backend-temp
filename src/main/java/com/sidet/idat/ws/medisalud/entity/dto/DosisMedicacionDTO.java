package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;

@Data
public class DosisMedicacionDTO {

	private Integer dosisMedicacionId;
	private String fechaDosis;
	private Integer numeroDosis;
	private String estado;
	private DetalleMedicacionDTO detalleMedicacion;
	
}
