package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;

@Data
public class TestClienteDTO {

	private Integer testClienteId;
	private String nombre;
	private String numeroDocumento;
	private TestTipoClienteDTO testTipoCliente;
	private TestDireccionClienteDTO testDireccionClienteDTO;
}
