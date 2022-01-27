package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sidet.idat.ws.medisalud.entity.Hospitalizacion;
import com.sidet.idat.ws.medisalud.entity.dto.HospitalizacionDTO;

@Mapper(componentModel = "spring", uses = {PacienteMapper.class, PersonalMedicoMapper.class})
public interface HospitalizacionMapper {

	@Mapping(target = "paciente.pacienteId", source = "pacienteId")
	@Mapping(target = "personalMedico.personalMedicoId", source = "personalMedicoId")
	@Mapping(target = "cama.camaId", source = "camaId")
	@Mapping(target = "estado" , source = "estado", defaultValue = "activo" )
	@Mapping(target = "alta" , source = "alta", defaultValue = "NO" )
	Hospitalizacion asHospitalizacion( HospitalizacionDTO hospitalizacion );
	
	@Mapping(target = "personalMedicoId", ignore = true)
	@Mapping(target = "pacienteId", ignore = true)
	@Mapping(target = "camaId", ignore = true)
	HospitalizacionDTO asHospitalizacionDTO( Hospitalizacion hospitalizacion );
	
	List<HospitalizacionDTO> asHospitalizacionDTOs( List<Hospitalizacion> hospitalizaciones );
	
}
