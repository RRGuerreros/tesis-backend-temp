package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;

@Data
public class CitaMedicaDTO {

	private Integer citaMedicaId;
	private String numeroCitaMedica;
	private String fechaCita;
	private String horaCita;
	private String estado;
	private Integer personalMedicoId;
	private Integer pacienteId;
	private Integer servicioId;
	private PersonalMedicoDTO personalMedico;
	private PacienteDTO paciente;
	private ServicioDTO servicio;
	
}
