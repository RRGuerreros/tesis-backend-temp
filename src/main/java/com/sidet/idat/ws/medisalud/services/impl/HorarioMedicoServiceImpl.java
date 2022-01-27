package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.HorarioMedico;
import com.sidet.idat.ws.medisalud.entity.dto.HorarioMedicoDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.exceptions.MedisaludSidetException;
import com.sidet.idat.ws.medisalud.mappers.HorarioMedicoMapper;
import com.sidet.idat.ws.medisalud.repository.HorarioMedicoRepository;
import com.sidet.idat.ws.medisalud.services.HorarioMedicoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HorarioMedicoServiceImpl implements HorarioMedicoService<HorarioMedicoDTO>{
	
	@Autowired
	private HorarioMedicoMapper horarioMedicoMapper;
	
	@Autowired
	private HorarioMedicoRepository horarioMedicoRepository;
	
	@Override
	public List<HorarioMedicoDTO> listarTodos() {
		log.info("HorarioMedicoServiceImpl.listarTodos()");
		try {
			List<HorarioMedico> horarioMedicos = horarioMedicoRepository.listarTodos();
			return horarioMedicoMapper.asHorarioMedicoDTOs(horarioMedicos);
		} catch (Exception e) {
			throw new DatabaseException("Error al listar los Horarios del Personal Médico.",e);
		}
	}

	@Override
	public HorarioMedicoDTO buscarPorId(Integer id) {
		log.info("HorarioMedicoServiceImpl.buscarPorId( id => {})", id);
		try {
			HorarioMedico horarioMedico = horarioMedicoRepository.buscarPorId(id);
			if (horarioMedico == null) {
				throw new MedisaludSidetException("El horario ya se encuentra eliminado");
			}
			return horarioMedicoMapper.asHorarioMedicoDTO(horarioMedico);
		} catch (Exception e) {
			throw new DatabaseException("Error al consulta el Horario del Medico",e);
		}
	}

	@Override
	public HorarioMedicoDTO registrar(HorarioMedicoDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HorarioMedicoDTO actualizar(HorarioMedicoDTO t) {
		log.info("HorarioMedicoServiceImpl.actualizar( t => {})",t);
		HorarioMedico horarioMedico = horarioMedicoRepository.buscarPorId(t.getPersonalMedicoId());
		if (horarioMedico == null) {
			throw new MedisaludSidetException("No existe horario medico para el Personal Medico");
		}
		horarioMedico = horarioMedicoMapper.asHorarioMedico(t);
		try {
			horarioMedicoRepository.actualizar(horarioMedico);
		} catch (Exception e) {
			throw new DatabaseException("Error al actualizar el horario del Persona Médico");
		}
		return horarioMedicoMapper.asHorarioMedicoDTO(horarioMedico);
	}

	@Override
	public Boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginadorDTO<HorarioMedicoDTO> listarPaginado(PaginadorDTO<HorarioMedicoDTO> paginador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HorarioMedicoDTO> listarPorServicioId( Integer servicioId ) {
		
		log.info( "HorarioMedicoServiceImpl.listarPorServicioId( servicioId => {} )", servicioId );
		
		try {
				List<HorarioMedico> horarioMedicos = horarioMedicoRepository.listarPorServicioId(servicioId);
				return horarioMedicoMapper.asHorarioMedicoDTOs(horarioMedicos);
		} catch (Exception e) {
			throw new DatabaseException("Error al listar los Horarios del Personal Médico",e);
		}
	}

}
