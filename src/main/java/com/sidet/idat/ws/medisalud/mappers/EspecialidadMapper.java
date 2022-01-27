package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.sidet.idat.ws.medisalud.entity.Especialidad;
import com.sidet.idat.ws.medisalud.entity.dto.EspecialidadDTO;

@Mapper(componentModel = "spring")
public interface EspecialidadMapper {
	
	List<EspecialidadDTO> asEspecialidadDTOs( List<Especialidad> espeList );
	
	EspecialidadDTO asEspecialidadDTO( Especialidad testCliente );
	
	Especialidad asEspecialidad( EspecialidadDTO testClienteDTO );
	
}
