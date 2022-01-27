package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.AreaMedica;
import com.sidet.idat.ws.medisalud.entity.dto.AreaMedicaDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.mappers.AreaMedicaMapper;
import com.sidet.idat.ws.medisalud.repository.AreaMedicaRepository;
import com.sidet.idat.ws.medisalud.services.AreaMedicaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AreaMedicaServiceImpl implements AreaMedicaService {

	@Autowired
	private AreaMedicaRepository areaMedicaRepository;
	
	@Autowired
	private AreaMedicaMapper areaMedicaMapper;
	
	@Override
	public List<AreaMedicaDTO> listarTodos() {
		
		log.info("AreaMedicaServiceImpl.listarTodos()");
		
		try {
			  List<AreaMedica> areaMedicas = areaMedicaRepository.listarTodos();
			  return areaMedicaMapper.asAreaMedicaDTOs(areaMedicas);
		} catch (Exception e) {
			throw new DatabaseException( "Error al listar las areas medicas.", e );
		}
		
	}

}
