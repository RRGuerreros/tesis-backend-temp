package com.sidet.idat.ws.medisalud.services.impl;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sidet.idat.ws.medisalud.entity.DetalleMedicacion;
import com.sidet.idat.ws.medisalud.entity.Medicacion;
import com.sidet.idat.ws.medisalud.entity.dto.DetalleMedicacionDTO;
import com.sidet.idat.ws.medisalud.entity.dto.MedicacionDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.mappers.MedicacionMapper;
import com.sidet.idat.ws.medisalud.repository.DetalleMedicacionRepository;
import com.sidet.idat.ws.medisalud.repository.DosisMedicacionRepository;
import com.sidet.idat.ws.medisalud.repository.MedicacionRepository;
import com.sidet.idat.ws.medisalud.repository.PersonalMedicoRepository;
import com.sidet.idat.ws.medisalud.services.MedicacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicacionServiceImpl implements MedicacionService {

	private static final Integer HORAS_DIA = 24;
	
	@Autowired
	private MedicacionRepository medicacionRepository;
	
	@Autowired
	private MedicacionMapper medicacionMapper;
	
	@Autowired
	private DetalleMedicacionRepository detalleMedicacionRepository;
	
	@Autowired
	private DosisMedicacionRepository dosisMedicacionRepository;
	
	@Autowired
	private PersonalMedicoRepository personalMedicoRepository;
	
	@Override
	public List<DetalleMedicacionDTO> listarUltimaPorHistoriaClinicaId( Integer historiaClinicaId ) {
		
	log.info("MedicacionServiceImpl.listarUltimaPorHistoriaClinicaId( historiaClinicaId => {} )", historiaClinicaId );
		
		try {
			  List<DetalleMedicacion> medicacionPaciente = medicacionRepository.listarUltimaPorHistoriaClinicaId(historiaClinicaId);
			  return medicacionMapper.asDetalleMedicacionDTOs(medicacionPaciente);
		} catch (Exception e) {
			throw new DatabaseException( "Error al listar la última medicación registrada por el médico.", e );
		}
		
	}

	@Override
	@Transactional
	public MedicacionDTO registrar( MedicacionDTO medicacionDTO ) {
		
		log.info("MedicacionServiceImpl.registrar( medicacionDTO = {})", medicacionDTO);
		
		Medicacion newMedicacion = medicacionMapper.asMedicacion(medicacionDTO);
		
		newMedicacion.setFechaRegistro( new SimpleDateFormat("yyyy-MM-dd").format( new GregorianCalendar().getTime() ));
		newMedicacion.setPersonalMedico(personalMedicoRepository.buscarPorPersonaId(medicacionDTO.getPersonaId()));
		
		medicacionRepository.registrar(newMedicacion);
		
		newMedicacion.getDetalle().forEach( detalleMedicacion -> {
			
			detalleMedicacion.setMedicacion( new Medicacion() );
			detalleMedicacion.getMedicacion().setMedicacionId( newMedicacion.getMedicacionId() );
			
			detalleMedicacionRepository.registrar(detalleMedicacion);
			
			Integer duracionHoras = HORAS_DIA * detalleMedicacion.getDuracion();
			Integer cantidadTomas = duracionHoras / detalleMedicacion.getFrecuencia();
			
			log.info("duracion dias = {}", detalleMedicacion.getDuracion());
			log.info("frecuencia = {}", detalleMedicacion.getFrecuencia());
			log.info("duracionHoras = {}", duracionHoras);
			log.info("cantidadTomas = {}", cantidadTomas);
			log.info("--------------------------------------");
			
			for (int i = 0; i < cantidadTomas; i++) {
				dosisMedicacionRepository.registrar((i+1), "Pendiente", detalleMedicacion.getDetalleMedicacionId());
			}
		});
		
		return medicacionMapper.asMedicacionDTO(newMedicacion);
	}

	@Override
	public PaginadorDTO<MedicacionDTO> listarPaginado(PaginadorDTO<MedicacionDTO> paginador) {
		
		log.info("MedicacionServiceImpl.listarPaginado( paginador => {})", paginador );
		
		Integer inicio = 0;

		if( paginador.getNumeroPagina() == 0 || paginador.getNumeroPagina() == 1 ) {
			inicio = 0;
		} else {
			inicio = (paginador.getNumeroPagina() - 1) * paginador.getTotalFilasPagina();
		}
		
		try {
			
			List<Medicacion> datos = medicacionRepository.listarPaginado( 
				inicio, paginador.getTotalFilasPagina(), paginador.getObjeto().getHistoriaClinicaId() );
			Integer totalFilas = medicacionRepository.contarTotalFilas(paginador.getObjeto().getHistoriaClinicaId());
			
			paginador.setTotalFilas(totalFilas);
			paginador.setDatos(medicacionMapper.asMedicacionDTOs(datos));
			
		} catch (Exception e) {
			throw new DatabaseException( "No se pudo consultar los datos de medicacion.", e );
		}
		
		return paginador;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
