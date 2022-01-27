package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.MotivoConsulta;
import com.sidet.idat.ws.medisalud.entity.dto.MotivoConsultaDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.mappers.MotivoConsultaMapper;
import com.sidet.idat.ws.medisalud.repository.MotivoConsultaRepository;
import com.sidet.idat.ws.medisalud.services.MotivoConsultaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MotivoConsultaServiceImpl implements MotivoConsultaService {
	
	@Autowired
	private MotivoConsultaRepository motivoConsultaRepository;
	
	@Autowired
	private MotivoConsultaMapper motivoConsultaMapper;
	
	@Override
	public MotivoConsultaDTO registrar(MotivoConsultaDTO motivoConsultaDTO) {
		log.info("MotivoConsultaServiceImpl.registrar( motivoConsultaDTO = {})", motivoConsultaDTO);
		
		MotivoConsulta motivoConsulta = motivoConsultaMapper.asMotivoConsulta(motivoConsultaDTO);
		
		try {
			motivoConsultaRepository.registrar(motivoConsulta);
			motivoConsultaDTO.setMotivoConsultaId(motivoConsulta.getMotivoConsultaId());
		} catch (Exception e) {
			throw new DatabaseException("Error al registrar el motivo de consulta", e);
		}
		return motivoConsultaDTO;
	}
	
	@Override
	public PaginadorDTO<MotivoConsultaDTO> listarPaginado(PaginadorDTO<MotivoConsultaDTO> paginador) {
		
		log.info("MotivoConsultaServiceImpl.listarPaginado( paginador => {})", paginador );
		
		Integer inicio = 0;

		if( paginador.getNumeroPagina() == 0 || paginador.getNumeroPagina() == 1 ) {
			inicio = 0;
		} else {
			inicio = (paginador.getNumeroPagina() - 1) * paginador.getTotalFilasPagina();
		}
		
		try {
			
			List<MotivoConsulta> datos = motivoConsultaRepository.listarPaginado( 
					inicio, paginador.getTotalFilasPagina(), paginador.getObjeto().getHistoriaClinicaId() );
			Integer totalFilas = motivoConsultaRepository.contarTotalFilas(paginador.getObjeto().getHistoriaClinicaId());
			
			paginador.setTotalFilas(totalFilas);
			paginador.setDatos(motivoConsultaMapper.asMotivoConsultaDTOs(datos));
			
		} catch (Exception e) {
			throw new DatabaseException( "No se pudo consultar los datos de motivo consulta.", e );
		}
		
		return paginador;
	}
	
	@Override
	public List<MotivoConsultaDTO> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MotivoConsultaDTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MotivoConsultaDTO actualizar(MotivoConsultaDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
