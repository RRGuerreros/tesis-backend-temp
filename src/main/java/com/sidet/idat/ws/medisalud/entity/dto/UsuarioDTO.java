package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
	
	private Integer usuarioId;
	private String username;
	private String password;
	private Boolean enabled;
	//private List<Rol> roles;
	//private List<Modulo> modulos;
	private PersonaDTO persona;
	private PerfilDTO perfil;
	private Boolean activo;
	private Integer personaId;
	private Integer perfilId;
}
