package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.HistoriaClinica;
import com.sidet.idat.ws.medisalud.entity.dto.HistoriaClinicaDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.mappers.HistoriaClinicaMapper;
import com.sidet.idat.ws.medisalud.repository.HistoriaClinicaRepository;
import com.sidet.idat.ws.medisalud.services.HistoriaClinicaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService<HistoriaClinicaDTO> {

	@Autowired
	private HistoriaClinicaRepository historiaClinicaRepository;
	
	@Autowired
	private HistoriaClinicaMapper historiaClinicaMapper;
	
	@Override
	public List<HistoriaClinicaDTO> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HistoriaClinicaDTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HistoriaClinicaDTO registrar(HistoriaClinicaDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HistoriaClinicaDTO actualizar(HistoriaClinicaDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginadorDTO<HistoriaClinicaDTO> listarPaginado(PaginadorDTO<HistoriaClinicaDTO> paginador) {
		log.info("HistoriaClinicaServiceImpl.listarPaginado( paginador => {})", paginador );
		
		Integer inicio = 0;

		if( paginador.getNumeroPagina() == 0 || paginador.getNumeroPagina() == 1 ) {
			inicio = 0;
		} else {
			inicio = (paginador.getNumeroPagina() - 1) * paginador.getTotalFilasPagina();
		}
		
		try {
			
			List<HistoriaClinica> datos = historiaClinicaRepository.listarPaginado( inicio, paginador.getTotalFilasPagina() );
			Integer totalFilas = historiaClinicaRepository.contarTotalFilas();
			
			paginador.setTotalFilas(totalFilas);
			paginador.setDatos(historiaClinicaMapper.asHistoriaClinicaDTOs(datos));
			
		} catch (Exception e) {
			throw new DatabaseException( "No se pudo consultar los datos de pacientes.", e );
		}
		
		return paginador;
	}

}
