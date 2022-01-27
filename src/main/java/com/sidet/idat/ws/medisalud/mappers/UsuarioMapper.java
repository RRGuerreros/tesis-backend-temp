package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sidet.idat.ws.medisalud.entity.Usuario;
import com.sidet.idat.ws.medisalud.entity.dto.UsuarioDTO;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	
	UsuarioDTO asUsuarioDTO( Usuario usuario );
	
	@Mapping( target = "perfil.perfilId", source = "perfilId")
	@Mapping( target = "persona.personaId", source = "personaId")
	Usuario asUsuario( UsuarioDTO usuarioDTO );
	
	List<UsuarioDTO> asUsuarioDTOs( List<Usuario> usuarios );
}
