package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.Antecedentes;
import com.sidet.idat.ws.medisalud.entity.dto.AntecedentesDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.exceptions.MedisaludSidetException;
import com.sidet.idat.ws.medisalud.mappers.AntecedentesMapper;
import com.sidet.idat.ws.medisalud.repository.AntecedentesRepository;
import com.sidet.idat.ws.medisalud.services.AntecedentesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AntecedentesServiceImpl implements AntecedentesService<AntecedentesDTO>{

	@Autowired
	private AntecedentesRepository antecedentesRepository;
	
	@Autowired
	private AntecedentesMapper antecedentesMapper;
	
	@Override
	public List<AntecedentesDTO> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AntecedentesDTO buscarPorId(Integer id) {
		log.info("AntecedentesServiceImpl.buscarPorId( id =>{} ) ",id);
		try {
			Antecedentes antecedentes = antecedentesRepository.buscarPorId(id);
			if (antecedentes == null) {
				throw new MedisaludSidetException("El antecedente no existe");
			}
			return antecedentesMapper.asAntecedentesDTO(antecedentes);
		} catch (Exception e) {
			throw new DatabaseException("Error al consultar el Antecedente de la Historia Clínica");
		}
	}

	@Override
	public AntecedentesDTO registrar(AntecedentesDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AntecedentesDTO actualizar(AntecedentesDTO t) {
		log.info("AntecedentesServiceImpl.actualizar(t => {})",t);
		Antecedentes antecedentes = antecedentesRepository.buscarPorId(t.getHistoriaClinicaId());
		if (antecedentes == null) {
			throw new MedisaludSidetException("No existe Antecedente para la Historia Clínica");
		}
		antecedentes = antecedentesMapper.asAntecedentes(t);
		try {
			antecedentesRepository.actualizar(antecedentes);
		} catch (Exception e) {
			throw new DatabaseException("Error al actualizar el antecedente de la Historia Clínica");
		}
		return antecedentesMapper.asAntecedentesDTO(antecedentes);
	}

	@Override
	public Boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginadorDTO<AntecedentesDTO> listarPaginado(PaginadorDTO<AntecedentesDTO> paginador) {
		// TODO Auto-generated method stub
		return null;
	}

}
