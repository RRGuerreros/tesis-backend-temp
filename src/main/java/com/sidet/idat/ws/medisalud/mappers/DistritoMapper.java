package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.sidet.idat.ws.medisalud.entity.Distrito;
import com.sidet.idat.ws.medisalud.entity.dto.DistritoDTO;

@Mapper(componentModel = "spring")
public interface DistritoMapper {

	List<DistritoDTO> asDistritoDTOs( List<Distrito> distritos );
	
}
