package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.sidet.idat.ws.medisalud.entity.Servicio;
import com.sidet.idat.ws.medisalud.entity.dto.ServicioDTO;

@Mapper(componentModel = "spring")
public interface ServicioMapper {

	List<ServicioDTO> asServiciosDTOs( List<Servicio> servicios );
	
}
