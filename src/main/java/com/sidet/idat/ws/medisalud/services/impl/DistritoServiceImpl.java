package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.Distrito;
import com.sidet.idat.ws.medisalud.entity.dto.DistritoDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.mappers.DistritoMapper;
import com.sidet.idat.ws.medisalud.repository.DistritoRepository;
import com.sidet.idat.ws.medisalud.services.DistritoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DistritoServiceImpl implements DistritoService{

	@Autowired
	private DistritoRepository distritoRepository;
	
	@Autowired
	private DistritoMapper distritoMapper;
	
	@Override
	public List<DistritoDTO> listarPorProvinciaId( Integer provinciaId ) {
		
		log.info("DistritoServiceImpl.listarTodos( provinciaId => {} )" , provinciaId );
		
		try {
			  List<Distrito> distritos = distritoRepository.listarPorProvinciaId(provinciaId);
			  return distritoMapper.asDistritoDTOs(distritos);
		} catch (Exception e) {
			throw new DatabaseException( "Error al listar los distritos.", e );
		}
	}

}
