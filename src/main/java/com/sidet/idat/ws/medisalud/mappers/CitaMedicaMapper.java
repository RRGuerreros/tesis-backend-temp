package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.sidet.idat.ws.medisalud.entity.CitaMedica;
import com.sidet.idat.ws.medisalud.entity.dto.CitaMedicaDTO;

@Mapper(componentModel = "spring", uses = {PacienteMapper.class, PersonalMedicoMapper.class})
public interface CitaMedicaMapper {

	@Mapping(target = "fechaCita", dateFormat = "yyyy-MM-dd")
	@Mapping(target = "personalMedico.personalMedicoId", source = "personalMedicoId")
	@Mapping(target = "paciente.pacienteId", source = "pacienteId")
	@Mapping(target = "servicio.servicioId", source = "servicioId")
	@Mapping(target = "estado", source = "estado", defaultValue = "registrado" )
	CitaMedica asCitaMedica( CitaMedicaDTO citaMedicaDTO );
	
	@Mapping(target = "personalMedicoId", ignore = true)
	@Mapping(target = "pacienteId", ignore = true)
	@Mapping(target = "servicioId", ignore = true)
	@Mapping(target = "fechaCita", dateFormat = "yyyy-MM-dd")
	@Mapping(target = "numeroCitaMedica", ignore = true )
	CitaMedicaDTO asCitaMedicaDTO( CitaMedica citaMedica );
	
	List<CitaMedicaDTO> asCitaMedicaDTOs( List<CitaMedica> citasMedicas );
	
	@AfterMapping
	default void afterasCitaMedicaDTO( @MappingTarget CitaMedicaDTO citaMedicaDTO ) {
		String numeroCita = "0000000000" + citaMedicaDTO.getCitaMedicaId();
		numeroCita = numeroCita.substring( ( numeroCita.length()-10 ) , numeroCita.length() );
		numeroCita = "CT" + numeroCita;
		citaMedicaDTO.setNumeroCitaMedica(numeroCita);
	}
	
}
