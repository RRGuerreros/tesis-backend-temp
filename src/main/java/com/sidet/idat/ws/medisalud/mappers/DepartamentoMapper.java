package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.sidet.idat.ws.medisalud.entity.Departamento;
import com.sidet.idat.ws.medisalud.entity.dto.DepartamentoDTO;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {

	List<DepartamentoDTO> asDepartamentoDTOs( List<Departamento> departamentos );

}
