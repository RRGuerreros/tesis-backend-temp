package com.sidet.idat.ws.medisalud.entity;

import java.util.Date;

import lombok.Data;

@Data
public class CitaMedica {

	private Integer citaMedicaId;
	private Date fechaCita;
	private String horaCita;
	private String estado;
	private PersonalMedico personalMedico;
	private Paciente paciente;
	private Servicio servicio;
	
}
