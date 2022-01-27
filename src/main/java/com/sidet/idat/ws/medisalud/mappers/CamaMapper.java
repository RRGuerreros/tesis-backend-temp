package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.sidet.idat.ws.medisalud.entity.Cama;
import com.sidet.idat.ws.medisalud.entity.dto.CamaDTO;

@Mapper(componentModel = "spring")
public interface CamaMapper {

	List<CamaDTO> asCamaDTOs( List<Cama> camas );
	
}
