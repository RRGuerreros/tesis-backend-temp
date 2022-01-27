package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;

@Data
public class MotivoConsultaDTO {

	private Integer motivoConsultaId;
	private String tipoEnfermedad;
	private String sintomasPrincipales;
	private String relato;
	private String apetito;
	private String sedantes;
	private String orina;
	private Integer historiaClinicaId;
}
