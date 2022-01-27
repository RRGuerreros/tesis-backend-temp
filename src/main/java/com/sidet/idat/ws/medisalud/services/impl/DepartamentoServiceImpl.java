package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.Departamento;
import com.sidet.idat.ws.medisalud.entity.dto.DepartamentoDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.mappers.DepartamentoMapper;
import com.sidet.idat.ws.medisalud.repository.DepartamentoRepository;
import com.sidet.idat.ws.medisalud.services.DepartamentoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DepartamentoServiceImpl implements DepartamentoService{
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private DepartamentoMapper departamentoMapper;
	
	public List<DepartamentoDTO> listarTodos() {
		
		log.info("DepartamentoServiceImpl.listarTodos()");
		
		try {
			  List<Departamento> departamentos = departamentoRepository.listarTodos();
			  return departamentoMapper.asDepartamentoDTOs(departamentos);
		} catch (Exception e) {
			throw new DatabaseException( "Error al listar los departamentos.", e );
		}
	}

}
