package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AntecedentesDTO {

	private Integer antecedentesId;
	private String prenatalesFisiologicos;
	private String partoFisiologicos;
	private String gradoInstruccionFisiologicos;
	private String comunicacionesFisiologicos;
	private String alimentacionGenerales;
	private String 	ejerciciosGenerales;
	private String habitosNocivosGenerales;
	private Boolean alergiasGenerales;
	private String descripcionAlergiaGenerales;
	private Boolean hipertencionArterialPatologicos;
	private Boolean hepatitisViralPatologicos;
	private Boolean enfermedadesCardiacasPatologicos;
	private Boolean fiebreMaltaPatologicos;
	private Boolean asmaPatologicos;
	private Boolean enfermedadTiroideaPatologicos;
	private Boolean gastritisUlceraPatologicos;
	private String otrasEnfermedadesPatologicos;
	private String cirugiasPatologicos;
	private String medicinaPatologicos;
	private Boolean enfermedadesMentalesFamiliares;
	private Boolean tuberculosisFamiliares;
	private Boolean diabetesMellitusFamiliares;
	private Boolean enfermedadesCardiacasFamiliares;
	private Boolean hipertencionArterialFamiliares;
	private String otrasEnfermedadesFamiliares;
	private HistoriaClinicaDTO historiaClinicaDTO;
	private Integer historiaClinicaId;

	
}
