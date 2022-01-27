package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.dto.PerfilDTO;
import com.sidet.idat.ws.medisalud.exceptions.MedisaludSidetException;
import com.sidet.idat.ws.medisalud.mappers.PerfilMapper;
import com.sidet.idat.ws.medisalud.repository.PerfilRepository;
import com.sidet.idat.ws.medisalud.services.PerfilService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PerfilServiceImpl implements PerfilService{

	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private PerfilMapper perfilMapper;
	
	@Override
	public List<PerfilDTO> listarTodos() {
		log.info("PerfilServiceImpl.listarTodos()");
		try {
			return perfilMapper.asPerfilDTOs(perfilRepository.listarTodos());
		} catch (Exception e) {
			throw new MedisaludSidetException("Error al listar todos los perfiles", e);
		}
	}
}
