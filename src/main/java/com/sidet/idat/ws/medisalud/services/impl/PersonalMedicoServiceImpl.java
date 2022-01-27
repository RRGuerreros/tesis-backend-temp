package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.PersonalMedico;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PersonalMedicoDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.exceptions.MedisaludSidetException;
import com.sidet.idat.ws.medisalud.mappers.PersonalMedicoMapper;
import com.sidet.idat.ws.medisalud.repository.PersonalMedicoRepository;
import com.sidet.idat.ws.medisalud.services.PersonalMedicoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonalMedicoServiceImpl implements PersonalMedicoService<PersonalMedicoDTO>{

	@Autowired
	private PersonalMedicoRepository personalMedicoRepository;
	
	@Autowired
	private PersonalMedicoMapper medicoMapper;

	@Override
	public List<PersonalMedicoDTO> listarTodos() {
		log.info("PersonalMedicoServiceImpl.listarTodos()");
		return medicoMapper.asPersonaMedicoDTOs(personalMedicoRepository.listarTodos());
	}

	@Override
	public PersonalMedicoDTO buscarPorId(Integer id) {
		log.info("PersonalMedicoServiceImpl.buscarPorId( id => {})",id);
		try {
			PersonalMedico medico = personalMedicoRepository.buscarPodId(id);
			if (medico == null) {
				throw new MedisaludSidetException("El personal médico ya se encuentra eliminado");
			}
			return medicoMapper.asPersonaMedicoDTO(medico);
		} catch (Exception e) {
			throw new DatabaseException("Error al consultar al Personal Médico ",e);
		}
	}

	@Override
	public PersonalMedicoDTO registrar(PersonalMedicoDTO t) {
		log.info("PersonalMedicoServiceImpl.registrar( t => {})", t );
		
		PersonalMedico medicoValida = personalMedicoRepository.validaDniExistente(t.getNumeroDocumento());
		
		if (medicoValida != null) {
			throw new MedisaludSidetException("El personal médico ya fue registrado");
		}
		
		PersonalMedico medico = medicoMapper.asPersonalMedico(t);
		try {
			personalMedicoRepository.registrarPersona(medico);
			personalMedicoRepository.registrarPersonalMedico(medico);
		} catch (Exception e) {
			throw new DatabaseException("Error al registrar al Personal medico",e);
		}
		
		try {
			personalMedicoRepository.registrarHorarioMedico(medico.getPersonalMedicoId());
		} catch (Exception e) {
			throw new DatabaseException("Error al registrar el horario Laboral",e);
		}
		
		t.setPersonaId(medico.getPersonaId());
		t.setPersonalMedicoId(medico.getPersonalMedicoId());
		return t;
	}

	@Override
	public PersonalMedicoDTO actualizar(PersonalMedicoDTO t) {
		log.info("PersonalMedicoServiceImpl.actualizar( id => {})",t);
		PersonalMedico medico = personalMedicoRepository.buscarPodId(t.getPersonalMedicoId());
		if (medico == null) {
			throw new MedisaludSidetException("No existe el Personal Médico a actualizar");
		}
		medico = medicoMapper.asPersonalMedico(t);
		try {
			personalMedicoRepository.actualizar(medico);
		} catch (Exception e) {
			throw new DatabaseException("Error al actualizar al Personal Medico ",e);
		}
		return medicoMapper.asPersonaMedicoDTO(medico);
	}

	@Override
	public Boolean eliminar(Integer id) {
		log.info("PersonalMedicoServiceImpl.eliminar( id => {})",id);
		try {
			personalMedicoRepository.eliminar(id);
		} catch (Exception e) {
			throw new DatabaseException("Error no se puede eliminar al Personal Medico ",e);
		}
		return true;
	}

	@Override
	public PaginadorDTO<PersonalMedicoDTO> listarPaginado(PaginadorDTO<PersonalMedicoDTO> paginador) {
		log.info("PersonalMedicoServiceImpl.listarPaginado( paginador => {})",paginador);
		Integer inicio = 0;
		if (paginador.getNumeroPagina() == 0 || paginador.getNumeroPagina() == 1) {
			inicio = 0;
		}else {
			inicio = (paginador.getNumeroPagina() - 1) * paginador.getTotalFilasPagina();
		}
		
		try {
			List<PersonalMedico> datos = personalMedicoRepository.listarPaginado(inicio, paginador.getTotalFilasPagina());
			
			Integer totalFilas = personalMedicoRepository.contarTotalFilas();
			
			paginador.setTotalFilas(totalFilas);
			paginador.setDatos(medicoMapper.asPersonaMedicoDTOs(datos));
		} catch (Exception e) {
			e.printStackTrace();
			throw new DatabaseException("Error: no se pudo consultar los datos del Personal Medico ");
		}
		return paginador;
	}
}
