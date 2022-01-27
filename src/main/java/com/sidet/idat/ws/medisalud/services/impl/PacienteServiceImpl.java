package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sidet.idat.ws.medisalud.entity.Distrito;
import com.sidet.idat.ws.medisalud.entity.HistoriaClinica;
import com.sidet.idat.ws.medisalud.entity.Paciente;
import com.sidet.idat.ws.medisalud.entity.dto.PacienteDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.exceptions.MedisaludSidetException;
import com.sidet.idat.ws.medisalud.exceptions.RecursoNoEncontradoException;
import com.sidet.idat.ws.medisalud.mappers.PacienteMapper;
import com.sidet.idat.ws.medisalud.repository.AntecedentesRepository;
import com.sidet.idat.ws.medisalud.repository.DistritoRepository;
import com.sidet.idat.ws.medisalud.repository.HistoriaClinicaRepository;
import com.sidet.idat.ws.medisalud.repository.PacienteRepository;
import com.sidet.idat.ws.medisalud.services.PacienteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteServiceImpl implements PacienteService<PacienteDTO> {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private PacienteMapper pacienteMapper;
	
	@Autowired
	private DistritoRepository distritoRepository;
	
	@Autowired
	private HistoriaClinicaRepository historiaClinicaRepository;
	
	@Autowired
	private AntecedentesRepository antecedentesRepository;
	
	@Override
	@Transactional
	public PacienteDTO registrar( PacienteDTO pacienteDTO ) {
		
		Paciente pacienteBuscado = pacienteRepository.buscarPorNumeroDocumento( pacienteDTO.getNumeroDocumento() );
		
		if( pacienteBuscado != null ) {
			throw new MedisaludSidetException( "El DNI "+ pacienteDTO.getNumeroDocumento()+ 
					" se encuentra registrado en el sistema." );
		}
		
		log.info("PacienteServiceImpl.registrar( pacienteDTO => {})", pacienteDTO );
		Paciente paciente = pacienteMapper.asPaciente( pacienteDTO );
		
		try {
				pacienteRepository.registrarPersona( paciente );
				pacienteRepository.registrarPaciente( paciente );	
				pacienteDTO.setPersonaId( paciente.getPersonaId() );
				pacienteDTO.setPacienteId( paciente.getPacienteId() );
				
				HistoriaClinica hc=new HistoriaClinica();
				hc.setPaciente( new Paciente());
				hc.getPaciente().setPacienteId(paciente.getPacienteId());
				
				historiaClinicaRepository.registrar(hc);
				
				antecedentesRepository.registrar(hc.getHistoriaClinicaId());
				
		} catch (Exception e) {
			throw new DatabaseException( "Error al registrar el paciente.", e );
		}
		
		return pacienteDTO;
	}
	
	@Override
	public PaginadorDTO<PacienteDTO> listarPaginado( PaginadorDTO<PacienteDTO> paginador ) {
		
		log.info("PacienteServiceImpl.listarPaginado( paginador => {})", paginador );
		
		Integer inicio = 0;

		if( paginador.getNumeroPagina() == 0 || paginador.getNumeroPagina() == 1 ) {
			inicio = 0;
		} else {
			inicio = (paginador.getNumeroPagina() - 1) * paginador.getTotalFilasPagina();
		}
		
		try {
			
			List<Paciente> datos = pacienteRepository.listarPaginado( inicio, paginador.getTotalFilasPagina() );
			Integer totalFilas = pacienteRepository.contarTotalFilas();
			
			paginador.setTotalFilas(totalFilas);
			paginador.setDatos(pacienteMapper.asPacienteDTOs(datos));
			
		} catch (Exception e) {
			throw new DatabaseException( "No se pudo consultar los datos de pacientes.", e );
		}
		
		return paginador;
	}

	@Override
	public PacienteDTO buscarPorId( Integer id ) {
		
		log.info("PacienteServiceImpl.buscarPorId( id => {})", id );
		Paciente paciente = null;
		
		try {
				paciente = pacienteRepository.buscarPorIdPaciente(id);
		} catch (Exception e) {
			throw new DatabaseException( "Error al buscar por id al paciente.", e );
		}
		
		if( paciente == null ) {
			throw new RecursoNoEncontradoException( "No se encontro coincidencia con el id " +id+" elegido." );  
		}
		
		return pacienteMapper.asPacienteDTO(paciente);
	}

	@Override
	@Transactional
	public PacienteDTO actualizar( PacienteDTO pacienteDTO ) {
		
		log.info("PacienteServiceImpl.actualizar( pacienteDTO => {})", pacienteDTO );
		Paciente paciente = pacienteRepository.buscarPorIdPaciente( pacienteDTO.getPacienteId() );
		
		if( paciente == null ) {
			throw new RecursoNoEncontradoException( "No se encontro coincidencia alguna, el paciente "
					+"con id " +pacienteDTO.getPacienteId()+ " no existe."  );  
		}
		
		Distrito distrito = distritoRepository.buscarPorId( pacienteDTO.getDistritoId() ); 
			
		paciente.setNombres( pacienteDTO.getNombres() );
		paciente.setApellidoPaterno( pacienteDTO.getApellidoPaterno() );
		paciente.setApellidoMaterno( pacienteDTO.getApellidoMaterno() );
		paciente.setTipoDocumento( pacienteDTO.getTipoDocumento() );
		paciente.setNumeroDocumento( pacienteDTO.getNumeroDocumento() );
		paciente.setFechaNacimiento( pacienteDTO.getFechaNacimiento() );
		paciente.setTelefonoFijo( pacienteDTO.getTelefonoFijo() );
		paciente.setCelular( pacienteDTO.getCelular() );
		paciente.setCorreo( pacienteDTO.getCorreo() );
		paciente.setGenero( pacienteDTO.getGenero() );
		paciente.setEstadoCivil( pacienteDTO.getEstadoCivil() );
		paciente.setGrupoSanguineo( pacienteDTO.getGrupoSanguineo() );
		paciente.setCanal( pacienteDTO.getCanal() );
		paciente.setReligion( pacienteDTO.getReligion() );
		paciente.setLugarNacimiento( pacienteDTO.getLugarNacimiento() );
		paciente.setTipoPaciente( pacienteDTO.getTipoPaciente() );
		paciente.setDistrito( distrito );
		paciente.setDireccion( pacienteDTO.getDireccion() );
		paciente.setResponsableNombresApellidos( pacienteDTO.getResponsableNombresApellidos() );
		paciente.setResponsableParentescoPaciente( pacienteDTO.getResponsableParentescoPaciente() );
		paciente.setResponsableTelefono( pacienteDTO.getResponsableTelefono() );
		
		try {
				pacienteRepository.actualizarPersona(paciente);
				pacienteRepository.actualizarPaciente(paciente);
				
		} catch (Exception e) {
			throw new DatabaseException( "Error no se pudo actualizar el paciente.", e );
		}
		
		return pacienteMapper.asPacienteDTO(paciente);
	}

	@Override
	public Boolean eliminar( Integer pacienteId ) {
		
		log.info("PacienteServiceImpl.eliminar( pacienteId => {})", pacienteId );
		
		try {
				pacienteRepository.eliminarPaciente(pacienteId);
		} catch (Exception e) {
			throw new DatabaseException("Error no se pudo eliminar el paciente.", e );
		}
		
		return true;
	}
	
	@Override
	public PacienteDTO buscarPorNumeroDocumento( String numeroDocumento ) {
		
		log.info("PacienteServiceImpl.buscarPorNumeroDocumento( numeroDocumento => {})", numeroDocumento );
		Paciente paciente = null;
		
		try {
				paciente = pacienteRepository.buscarPorNumeroDocumento(numeroDocumento);
		} catch (Exception e) {
			throw new DatabaseException( "Error al buscar por numero de Documento al paciente", e );
		}
		
		if( paciente == null ) {
			throw new RecursoNoEncontradoException( "No se encontro coincidencia con el numero de documento "
					+numeroDocumento+ " elegido." );  
		}
		
		return pacienteMapper.asPacienteDTO(paciente);
	}
	
	@Override
	public List<PacienteDTO> listarTodos() {
		return null;
	}

}
