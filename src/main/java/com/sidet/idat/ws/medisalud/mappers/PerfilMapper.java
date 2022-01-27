package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.sidet.idat.ws.medisalud.entity.Perfil;
import com.sidet.idat.ws.medisalud.entity.dto.PerfilDTO;

@Mapper(componentModel = "spring")
public interface PerfilMapper {

	PerfilDTO asPerfilDTO( Perfil perfil );
	
	List<PerfilDTO> asPerfilDTOs( List<Perfil> perfiles );
}
