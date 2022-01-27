package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.Especialidad;
import com.sidet.idat.ws.medisalud.entity.dto.EspecialidadDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.exceptions.MedisaludSidetException;
import com.sidet.idat.ws.medisalud.mappers.EspecialidadMapper;
import com.sidet.idat.ws.medisalud.repository.EspecialidadRepository;
import com.sidet.idat.ws.medisalud.services.EspecialidadService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EspecialidadServiceImpl implements EspecialidadService<EspecialidadDTO>{
	
	@Autowired
	private EspecialidadRepository especialidadRepository;
	
	@Autowired
	private EspecialidadMapper especialidadMapper;

	@Override
	public List<EspecialidadDTO> listarTodos() {
		log.info("EspecialidadServiceImpl.listarTodos");
		try {
			List<Especialidad> lista = especialidadRepository.listarTodos();
			return especialidadMapper.asEspecialidadDTOs(lista);
		} catch (Exception e) {
			throw new DatabaseException("Error al listar las Especialidades "+e);
		}
	}

	@Override
	public EspecialidadDTO buscarPorId(Integer id) {
		log.info("EspecialidadServiceImpl.listarTodos",id);
		try {
			Especialidad especialidad = especialidadRepository.buscarPorId(id);
			if (especialidad == null) {
				throw new MedisaludSidetException("El Personal Médico fue eliminado");
			}else {
				return especialidadMapper.asEspecialidadDTO(especialidad);
			}
		} catch (Exception e) {
			throw new DatabaseException("Error: al buscar por id la especialidad",e);
		}
	}

	@Override
	public EspecialidadDTO registrar(EspecialidadDTO t) {
		log.info("EspecialidadServiceImpl.listarTodos",t);
		Especialidad especialidad = especialidadMapper.asEspecialidad(t);
		try {
			especialidadRepository.registrar(especialidad);
		} catch (Exception e) {
			throw new DatabaseException("Error al registrar la Especialidad ",e);
		}
		t.setEspecialidadId(especialidad.getEspecialidadId());;
		return t;
	}

	@Override
	public EspecialidadDTO actualizar(EspecialidadDTO t) {
		log.info("EspecialidadServiceImpl.listarTodos",t);
		Especialidad especialidad = especialidadRepository.buscarPorId(t.getEspecialidadId());
		if (especialidad == null) {
			throw new MedisaludSidetException("No existe la especialidad a actualizar");
		}
		especialidad.setEspecialidad(t.getEspecialidad());;
		especialidad.setEspecialidadId(t.getEspecialidadId());
		
		try {
			especialidadRepository.actualizar(especialidad);
		} catch (Exception e) {
			throw new DatabaseException("Error: no se pudo actualizar al especialidad ",e);
		}
		return especialidadMapper.asEspecialidadDTO(especialidad);
	}

	@Override
	public Boolean eliminar(Integer id) {
		log.info("EspecialidadServiceImpl.listarTodos",id);
		try {
			especialidadRepository.eliminar(id);
		} catch (Exception e) {
			throw new DatabaseException("Error: no se pudo eliminar la especialidad ",e);
		}
		return true;
	}

	@Override
	public PaginadorDTO<EspecialidadDTO> listarPaginado(PaginadorDTO<EspecialidadDTO> paginador) {
		log.info("EspecialidadServiceImpl.listarTodos",paginador);
		Integer inicio = 0;
		if (paginador.getNumeroPagina() == 0 || paginador.getNumeroPagina() == 1) {
			inicio = 0;
		}else {
			inicio = (paginador.getNumeroPagina() - 1) * paginador.getTotalFilasPagina();
		}
		try {
			List<Especialidad> datos = especialidadRepository.listarPaginado(inicio, paginador.getTotalFilasPagina());
			Integer totalFilas = especialidadRepository.contarTotalFilas();
			
			paginador.setTotalFilas(totalFilas);
			paginador.setDatos(especialidadMapper.asEspecialidadDTOs(datos));
		} catch (Exception e) {
			throw new DatabaseException("Error: no se pudo consultar los datos de las Especialidades");
		}
		return paginador;
	}

}
