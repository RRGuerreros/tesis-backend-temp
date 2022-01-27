package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.Servicio;
import com.sidet.idat.ws.medisalud.entity.dto.ServicioDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.mappers.ServicioMapper;
import com.sidet.idat.ws.medisalud.repository.ServicioRepository;
import com.sidet.idat.ws.medisalud.services.ServicioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServicioServiceImpl implements ServicioService{

	@Autowired
	private ServicioRepository servicioRepository;
	
	@Autowired
	private ServicioMapper servicioMapper;
	
	@Override
	public List<ServicioDTO> listarPorAreaMedicaId( Integer areaMedicaId ) {
		
		log.info("ServicioServiceImpl.listarPorAreaMedicaId( areaMedicaId => {} )" , areaMedicaId );
		
		try {
			List<Servicio> servicios = servicioRepository.listarPorAreaMedicaId(areaMedicaId);
			  return servicioMapper.asServiciosDTOs(servicios);
		} catch (Exception e) {
			throw new DatabaseException( "Error al listar los servicios.", e );
		}
		
	}

}
