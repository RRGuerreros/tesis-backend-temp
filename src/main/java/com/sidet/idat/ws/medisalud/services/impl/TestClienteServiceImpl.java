package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.TestCliente;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.entity.dto.TestClienteDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.exceptions.MedisaludSidetException;
import com.sidet.idat.ws.medisalud.mappers.TestClienteMapper;
import com.sidet.idat.ws.medisalud.repository.TestClienteRepository;
import com.sidet.idat.ws.medisalud.services.TestClienteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TestClienteServiceImpl implements TestClienteService<TestClienteDTO>{

	@Autowired
	private TestClienteRepository testClienteRepository;
	@Autowired
	private TestClienteMapper testClienteMapper;
	
	@Override
	public List<TestClienteDTO> listarTodos() {
		log.info("TestClienteServiceImpl.listarTodos()");
		try {
			List<TestCliente> testClientes = testClienteRepository.listarTodos();
			return testClienteMapper.asClienteDTOs(testClientes);
		} catch (Exception e) {
			throw new DatabaseException("Error al listar los clientes dtos", e);
		}
	}

	@Override
	public TestClienteDTO buscarPorId( Integer id ) {
		
		log.info("TestClienteServiceImpl.buscarPorId( id => {})", id );
		
		try {
			TestCliente testCliente = testClienteRepository.buscarPorId(id);
			return testClienteMapper.asClienteDTO(testCliente);
		} catch (Exception e) {
			throw new DatabaseException("Error al buscar por id al cliente dto", e);
		}
	}

	@Override
	public TestClienteDTO registrar(TestClienteDTO t ) {
		log.info("TestClienteServiceImpl.registrar( testClienteDTO => {})", t );

		TestCliente testCliente = testClienteMapper.asTestCliente(t);
		try {
			testClienteRepository.registrar(testCliente);	
		} catch (Exception e) {
			throw new DatabaseException("Error al registrar el cliente dto", e );
		}
		
		t.setTestClienteId(testCliente.getTestClienteId());
		
		return t;
	}

	@Override
	public TestClienteDTO actualizar(TestClienteDTO t) {
		log.info("TestClienteServiceImpl.actualizar( testClienteDTO => {})", t );
		
		TestCliente testClienteBD = testClienteRepository.buscarPorId(t.getTestClienteId());
		
		if( testClienteBD == null )
			throw new MedisaludSidetException("Error: el test clienteDTO no existe");
		
		testClienteBD.setNombre(t.getNombre());
		testClienteBD.setNumeroDocumento(t.getNumeroDocumento());
		
		try {
			testClienteRepository.actualizar(testClienteBD);
		} catch (Exception e) {
			throw new DatabaseException("Error: no se pudo actualizar el cliente DTO", e);
		}
		
		return testClienteMapper.asClienteDTO(testClienteBD);
	}

	@Override
	public Boolean eliminar(Integer id) {
		log.info("TestClienteServiceImpl.eliminar( id => {})", id );
		
		try {
			testClienteRepository.eliminar(id);
		} catch (Exception e) {
			throw new DatabaseException("Error: no se pudo eliminar el cliente DTO", e );
		}
		
		return true;
	}

	@Override
	public PaginadorDTO<TestClienteDTO> listarPaginado(PaginadorDTO<TestClienteDTO> paginador) {
		log.info("TestClienteServiceImpl.listarPaginado( paginador => {})", paginador );
		
		Integer inicio = 0;

		if( paginador.getNumeroPagina() == 0 || paginador.getNumeroPagina() == 1 ) {
			inicio = 0;
		} else {
			inicio = (paginador.getNumeroPagina() - 1) * paginador.getTotalFilasPagina();
		}
		
		try {
			
			List<TestCliente> datos = testClienteRepository.listarPaginado(inicio, paginador.getTotalFilasPagina());
			
			Integer totalFilas = testClienteRepository.contarTotalFilas();
			
			paginador.setTotalFilas(totalFilas);
			paginador.setDatos(testClienteMapper.asClienteDTOs(datos));
			
		} catch (Exception e) {
			throw new DatabaseException("Error: no se pudo consultar los datos de clientes DTO", e);
		}
		
		return paginador;
	}
}
