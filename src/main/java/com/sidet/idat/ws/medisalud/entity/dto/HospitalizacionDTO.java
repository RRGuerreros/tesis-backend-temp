package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;

@Data
public class HospitalizacionDTO {

	private Integer hospitalizacionId;
	private String fechaRegistro;
	private String preDiagnostico;
	private String procedencia;
	private String observacion;
	private String alta;
	private String estado;
	private Integer pacienteId;
	private Integer camaId;
	private Integer personalMedicoId;
	private PacienteDTO paciente;
	private PersonalMedicoDTO personalMedico;
	private CamaDTO cama;
	
}
