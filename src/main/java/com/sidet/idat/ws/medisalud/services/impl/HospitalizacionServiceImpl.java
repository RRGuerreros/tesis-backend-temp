package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sidet.idat.ws.medisalud.entity.Hospitalizacion;
import com.sidet.idat.ws.medisalud.entity.dto.HospitalizacionDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.mappers.HospitalizacionMapper;
import com.sidet.idat.ws.medisalud.repository.CamaRepository;
import com.sidet.idat.ws.medisalud.repository.HospitalizacionRepository;
import com.sidet.idat.ws.medisalud.services.HospitalizacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HospitalizacionServiceImpl implements HospitalizacionService<HospitalizacionDTO>{

	@Autowired
	private CamaRepository camaRepository;
	
	@Autowired
	private HospitalizacionRepository hospitalizacionRepository;
	
	@Autowired
	private HospitalizacionMapper hospitalizacionMapper;
	
	@Override
	@Transactional
	public HospitalizacionDTO registrar( HospitalizacionDTO hospitalizacionDTO ) {
		
		log.info("HospitalizacionServiceImpl.registrar( HospitalizacionDTO => {})", hospitalizacionDTO );
		Hospitalizacion hospitalizacion = hospitalizacionMapper.asHospitalizacion(hospitalizacionDTO);
		
		try {
				camaRepository.cambioEstado(hospitalizacionDTO.getCamaId());
				hospitalizacionRepository.registrar(hospitalizacion);
				
				hospitalizacionDTO.setHospitalizacionId( hospitalizacion.getHospitalizacionId() );
				hospitalizacionDTO.setAlta( hospitalizacion.getAlta() );
				hospitalizacionDTO.setEstado( hospitalizacion.getEstado() );
				
		} catch (Exception e) {
			throw new DatabaseException( "Error al registrar la hospitalizacion.", e );
		}
		
		return hospitalizacionDTO;
	}
	
	@Override
	public List<HospitalizacionDTO> listarTodos() {

		log.info("HospitalizacionServiceImpl.listarTodos()");
		
		try {
			  List<Hospitalizacion> hospitalizaciones = hospitalizacionRepository.listarTodos();
			  return hospitalizacionMapper.asHospitalizacionDTOs(hospitalizaciones);
		} catch (Exception e) {
			throw new DatabaseException( "Error al listar las hospitalizaciones.", e );
		}
	}

	@Override
	public HospitalizacionDTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HospitalizacionDTO actualizar(HospitalizacionDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginadorDTO<HospitalizacionDTO> listarPaginado(PaginadorDTO<HospitalizacionDTO> paginador) {
		// TODO Auto-generated method stub
		return null;
	}

}
