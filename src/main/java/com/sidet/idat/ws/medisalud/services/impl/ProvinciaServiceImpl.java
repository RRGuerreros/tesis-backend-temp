package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.Provincia;
import com.sidet.idat.ws.medisalud.entity.dto.ProvinciaDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.mappers.ProvinciaMapper;
import com.sidet.idat.ws.medisalud.repository.ProvinciaRepository;
import com.sidet.idat.ws.medisalud.services.ProvinciaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProvinciaServiceImpl implements ProvinciaService {
	
	@Autowired
	private ProvinciaRepository provinciaRepository;

	@Autowired
	private ProvinciaMapper provinciaMapper;
	
	@Override
	public List<ProvinciaDTO> listarPorDepartamentoId( Integer departamentoId ) {
		
		log.info("ProvinciaServiceImpl.listarPorDepartamentoId( departamentoId => {} )" , departamentoId );
		
		try {
			  List<Provincia> provincias = provinciaRepository.listarPorDepartamentoId(departamentoId);
			  return provinciaMapper.asProvinciaDTOs(provincias);
		} catch (Exception e) {
			throw new DatabaseException( "Error al listar las provincias.", e );
		}
	}

}
