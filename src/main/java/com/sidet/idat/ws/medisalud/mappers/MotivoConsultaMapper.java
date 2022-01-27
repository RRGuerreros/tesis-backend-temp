package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sidet.idat.ws.medisalud.entity.MotivoConsulta;
import com.sidet.idat.ws.medisalud.entity.dto.MotivoConsultaDTO;

@Mapper(componentModel = "spring")
public interface MotivoConsultaMapper {

	MotivoConsultaDTO asMotivoConsultaDTO( MotivoConsulta motivoConsulta );
	
	@Mapping(target = "historiaClinica.historiaClinicaId", source = "historiaClinicaId")
	MotivoConsulta asMotivoConsulta( MotivoConsultaDTO motivoConsultaDTO );
	
	List<MotivoConsulta> asMotivoConsultas( List<MotivoConsultaDTO> motivoConsultaDTOs );
	
	List<MotivoConsultaDTO> asMotivoConsultaDTOs( List<MotivoConsulta> motivoConsultas );
}
