package com.sidet.idat.ws.medisalud.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.CitaMedica;
import com.sidet.idat.ws.medisalud.entity.dto.CitaMedicaDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.mappers.CitaMedicaMapper;
import com.sidet.idat.ws.medisalud.repository.CitaMedicaRepository;
import com.sidet.idat.ws.medisalud.services.CitaMedicaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CitaMedicaServiceImpl implements CitaMedicaService<CitaMedicaDTO> {

	@Autowired
	private CitaMedicaMapper citaMedicaMapper;
	
	@Autowired
	private CitaMedicaRepository citaMedicaRepository;
	
	@Override
	public CitaMedicaDTO registrar( CitaMedicaDTO citaMedicaDTO ) {
		
		log.info("CitaMedicaServiceImpl.registrar( citaMedicaDTO => {})", citaMedicaDTO );
		CitaMedica citaMedica = citaMedicaMapper.asCitaMedica(citaMedicaDTO);
		
		try {
				citaMedicaRepository.registrar( citaMedica );
				
				String numeroCita = "0000000000" + citaMedica.getCitaMedicaId();
				numeroCita = numeroCita.substring( ( numeroCita.length()-10 ) , numeroCita.length() );
				numeroCita = "CT" + numeroCita;
				
				citaMedicaDTO.setCitaMedicaId( citaMedica.getCitaMedicaId() );
				citaMedicaDTO.setNumeroCitaMedica( numeroCita );
				
		} catch (Exception e) {
			throw new DatabaseException( "Error al registrar la cita médica.", e );
		}
		
		return citaMedicaDTO;
	}

	@Override
	public List<CitaMedicaDTO> listarPorServicioIdAndFechaCita( CitaMedicaDTO citaMedicaDTO ) {
		
		log.info("CitaMedicaServiceImpl.listarPorServicioIdAndFechaCita( citaMedicaDTO => {})", citaMedicaDTO );
		List<CitaMedica> citas = new ArrayList<>();
		
		try {
				citas = citaMedicaRepository.listarPorServicioIdAndFechaCita( 
													citaMedicaDTO.getServicioId(), citaMedicaDTO.getFechaCita() );
		} catch (Exception e) {
			throw new DatabaseException( "No se pudo consultar los datos de las citas médicas.", e );
		}
		
		return citaMedicaMapper.asCitaMedicaDTOs(citas);
	}
	
	@Override
	public PaginadorDTO<CitaMedicaDTO> listarPaginado( PaginadorDTO<CitaMedicaDTO> paginador ) {
		
		log.info("CitaMedicaServiceImpl.listarPaginado( paginador => {})", paginador );
		
		Integer inicio = 0;

		if( paginador.getNumeroPagina() == 0 || paginador.getNumeroPagina() == 1 ) {
			inicio = 0;
		} else {
			inicio = (paginador.getNumeroPagina() - 1) * paginador.getTotalFilasPagina();
		}
		
		try {
				List<CitaMedica> datos = citaMedicaRepository.listarPaginado( inicio, paginador.getTotalFilasPagina() );
				Integer totalFilas = citaMedicaRepository.contarTotalFilas();
				
				paginador.setTotalFilas(totalFilas);
				paginador.setDatos(citaMedicaMapper.asCitaMedicaDTOs(datos));
			
		} catch (Exception e) {
			throw new DatabaseException( "No se pudo consultar los datos de las citas medicas.", e );
		}
		
		return paginador;
	}

	@Override
	public Boolean eliminar( Integer citaMedicaId ) {
		
		log.info("CitaMedicaServiceImpl.eliminar( citaMedicaId => {})", citaMedicaId );
		
		try {
				citaMedicaRepository.eliminar(citaMedicaId);
		} catch (Exception e) {
			throw new DatabaseException("Error no se pudo eliminar la cita médica.", e );
		}
		
		return true;
	}
	
	@Override
	public List<CitaMedicaDTO> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CitaMedicaDTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CitaMedicaDTO actualizar(CitaMedicaDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

}
