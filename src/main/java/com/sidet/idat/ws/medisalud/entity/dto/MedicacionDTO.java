package com.sidet.idat.ws.medisalud.entity.dto;

import java.util.List;

import lombok.Data;

@Data
public class MedicacionDTO {
	
	private Integer medicacionId;
	private Integer historiaClinicaId;
	private List<DetalleMedicacionDTO> detalleMedicacion;
	private String fechaRegistro;
	private Integer personaId;
	private PersonalMedicoDTO personalMedico;
}
