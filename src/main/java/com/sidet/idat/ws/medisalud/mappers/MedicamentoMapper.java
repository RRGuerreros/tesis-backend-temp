package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.sidet.idat.ws.medisalud.entity.Medicamento;
import com.sidet.idat.ws.medisalud.entity.dto.MedicamentoDTO;

@Mapper(componentModel = "spring")
public interface MedicamentoMapper {

	MedicamentoDTO asMedicamentoDTO( Medicamento medicamento );
	
	List<MedicamentoDTO> asMedicamentoDTOs( List<Medicamento> medicamentos );
}
