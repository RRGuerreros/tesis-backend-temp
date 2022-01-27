package com.sidet.idat.ws.medisalud.entity.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ModuloDTO {

	private String nombre;
	private String codigo;
	private List<String> roles;
	
	public void construirRoles( String rolesAgrupados ) {
		this.roles = new ArrayList<String>();
		String[] rolesString = rolesAgrupados.split(",");
		for (String rolString: rolesString) {
			roles.add(  rolString );
		}
	}
}
