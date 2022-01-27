package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sidet.idat.ws.medisalud.entity.PersonalMedico;
import com.sidet.idat.ws.medisalud.entity.dto.PersonalMedicoDTO;

@Mapper(componentModel = "spring")
public interface PersonalMedicoMapper {

	@Mapping(target = "servicio.areaMedica.areaMedicaId", source = "servicio.areaMedica.areaMedicaId")
	@Mapping(target = "servicio.servicioId", source = "servicioId")
	PersonalMedico asPersonalMedico(PersonalMedicoDTO medicoDTO);
	

	List<PersonalMedicoDTO> asPersonaMedicoDTOs( List<PersonalMedico> personalMedico );
	
	@Mapping(target = "servicioId", source = "servicio.servicioId")
	@Mapping(target = "servicio.areaMedica.areaMedicaId", source = "servicio.areaMedica.areaMedicaId")
	PersonalMedicoDTO asPersonaMedicoDTO( PersonalMedico personalMedico );
	
}
