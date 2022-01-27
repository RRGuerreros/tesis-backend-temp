package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.sidet.idat.ws.medisalud.entity.Provincia;
import com.sidet.idat.ws.medisalud.entity.dto.ProvinciaDTO;

@Mapper(componentModel = "spring")
public interface ProvinciaMapper {

	List<ProvinciaDTO> asProvinciaDTOs( List<Provincia> provincias );
	
}
