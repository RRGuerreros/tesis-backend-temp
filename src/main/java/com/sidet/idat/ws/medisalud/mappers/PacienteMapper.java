package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sidet.idat.ws.medisalud.entity.Paciente;
import com.sidet.idat.ws.medisalud.entity.dto.PacienteDTO;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

	@Mapping(target = "distrito.distritoId", source = "distritoId")
	Paciente asPaciente( PacienteDTO pacienteDTO );
	
	@Mapping(target = "distritoId", source = "distrito.distritoId")
	PacienteDTO asPacienteDTO( Paciente paciente );
	
	List<PacienteDTO> asPacienteDTOs( List<Paciente> pacientes );
	
}
