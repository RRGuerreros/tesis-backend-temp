package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;

@Data
public class HistoriaClinicaDTO {

	private Integer historiaClinicaId;
	private PacienteDTO paciente;
	private String fechaRegistro;
	private String numeroHistoriaClinica;
}
