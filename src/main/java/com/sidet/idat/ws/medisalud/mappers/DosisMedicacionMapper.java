package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.sidet.idat.ws.medisalud.entity.DosisMedicacion;
import com.sidet.idat.ws.medisalud.entity.dto.DosisMedicacionDTO;

@Mapper(componentModel = "spring", uses = {MedicacionMapper.class} )
public interface DosisMedicacionMapper {

	List<DosisMedicacionDTO> asDosisMedicacionDTOs( List<DosisMedicacion> listDosis ); 
	
}
