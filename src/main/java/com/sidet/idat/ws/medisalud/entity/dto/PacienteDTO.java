package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PacienteDTO extends PersonaDTO {

	private Integer pacienteId;
	private String genero;
	private String estadoCivil;
	private String grupoSanguineo;
	private String canal;
	private String religion;
	private String lugarNacimiento;
	private String tipoPaciente;
	private String direccion;
	private String responsableNombresApellidos;
	private String responsableParentescoPaciente;
	private String responsableTelefono;
	private Integer distritoId;
	private DistritoDTO distrito;
	private HistoriaClinicaDTO historiaClinica;
}
