package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PersonalMedicoDTO extends PersonaDTO{

	private Integer personalMedicoId;
	private String profesion;
	private String documentoProfesional;
	private String numeroDocumentoProfesional;
	private String estadoLaboral;
	private Integer servicioId;
	private ServicioDTO servicio;
	
}
