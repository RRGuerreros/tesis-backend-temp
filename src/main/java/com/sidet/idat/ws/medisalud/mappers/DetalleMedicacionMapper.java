package com.sidet.idat.ws.medisalud.mappers;

import org.mapstruct.Mapper;

import com.sidet.idat.ws.medisalud.entity.DetalleMedicacion;
import com.sidet.idat.ws.medisalud.entity.dto.DetalleMedicacionDTO;

@Mapper( componentModel = "spring", uses = {MedicacionMapper.class} )
public interface DetalleMedicacionMapper {

	DetalleMedicacionDTO asDetalleMedicacionDTO( DetalleMedicacion detalle );
	
}
