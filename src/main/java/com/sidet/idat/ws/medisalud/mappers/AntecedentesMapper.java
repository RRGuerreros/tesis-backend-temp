package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sidet.idat.ws.medisalud.entity.Antecedentes;
import com.sidet.idat.ws.medisalud.entity.dto.AntecedentesDTO;

@Mapper(componentModel = "spring")
public interface AntecedentesMapper {

	List<AntecedentesDTO> asAntecedentesDTOs( List<Antecedentes> antecedentesList );
	
	@Mapping(target = "historiaClinicaId", source = "historiaClinica.historiaClinicaId")
	AntecedentesDTO asAntecedentesDTO( Antecedentes antecedentes );
	
	@Mapping(target = "historiaClinica.historiaClinicaId", source = "historiaClinicaId")
	Antecedentes asAntecedentes( AntecedentesDTO antecedentesDTO );
	
}
