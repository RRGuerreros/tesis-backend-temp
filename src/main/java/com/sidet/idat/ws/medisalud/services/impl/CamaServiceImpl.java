package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.Cama;
import com.sidet.idat.ws.medisalud.entity.dto.CamaDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.mappers.CamaMapper;
import com.sidet.idat.ws.medisalud.repository.CamaRepository;
import com.sidet.idat.ws.medisalud.services.CamaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CamaServiceImpl implements CamaService {

	@Autowired
	private CamaRepository camaRepository;
	
	@Autowired
	private CamaMapper camaMapper;
	
	@Override
	public List<CamaDTO> listarTodos() {
		
		log.info("CamaServiceImpl.listarTodos()");
		
		try {
			  List<Cama> camas = camaRepository.listarTodos();
			  return camaMapper.asCamaDTOs(camas);
		} catch (Exception e) {
			throw new DatabaseException( "Error al listar las camas.", e );
		}
	}
	
}
