package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sidet.idat.ws.medisalud.entity.HorarioMedico;
import com.sidet.idat.ws.medisalud.entity.dto.HorarioMedicoDTO;


@Mapper(componentModel = "spring", uses = {PersonalMedicoMapper.class})
public interface HorarioMedicoMapper {

	List<HorarioMedicoDTO> asHorarioMedicoDTOs( List<HorarioMedico> horarioMedico );

	@Mapping(target = "personalMedicoId", source = "personalMedico.personalMedicoId")
	HorarioMedicoDTO asHorarioMedicoDTO( HorarioMedico horarioMedico );
	
	@Mapping(target = "personalMedico.personalMedicoId", source = "personalMedicoId")
	HorarioMedico asHorarioMedico( HorarioMedicoDTO horarioMedicoDTO );
	
}
