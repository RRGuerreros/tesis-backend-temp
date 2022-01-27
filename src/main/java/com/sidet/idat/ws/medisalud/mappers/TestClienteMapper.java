package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.sidet.idat.ws.medisalud.entity.TestCliente;
import com.sidet.idat.ws.medisalud.entity.dto.TestClienteDTO;

@Mapper(componentModel = "spring")
public interface TestClienteMapper {
	
	List<TestClienteDTO> asClienteDTOs( List<TestCliente> testClientes );
	
	TestClienteDTO asClienteDTO( TestCliente testCliente );
	
	TestCliente asTestCliente( TestClienteDTO testClienteDTO );

}
