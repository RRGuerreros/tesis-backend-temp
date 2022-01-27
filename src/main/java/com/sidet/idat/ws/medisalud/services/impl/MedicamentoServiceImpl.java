package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.dto.MedicamentoDTO;
import com.sidet.idat.ws.medisalud.mappers.MedicamentoMapper;
import com.sidet.idat.ws.medisalud.repository.MedicamentoRepository;
import com.sidet.idat.ws.medisalud.services.MedicamentoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicamentoServiceImpl implements MedicamentoService {

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private MedicamentoMapper medicamentoMapper;
	
	@Override
	public List<MedicamentoDTO> listarTodos() {
		log.info("MedicamentoServiceImpl.listarTodos()");
		return medicamentoMapper.asMedicamentoDTOs(medicamentoRepository.listarTodos());
	}

}
