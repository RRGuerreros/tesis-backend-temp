package com.sidet.idat.ws.medisalud.services.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sidet.idat.ws.medisalud.entity.DetalleMedicacion;
import com.sidet.idat.ws.medisalud.entity.DosisMedicacion;
import com.sidet.idat.ws.medisalud.entity.dto.DosisMedicacionDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.mappers.DosisMedicacionMapper;
import com.sidet.idat.ws.medisalud.repository.DosisMedicacionRepository;
import com.sidet.idat.ws.medisalud.repository.MedicacionRepository;
import com.sidet.idat.ws.medisalud.services.DosisMedicacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DosisMedicacionServiceImpl implements DosisMedicacionService {

	@Autowired
	private DosisMedicacionRepository dosisMedicacionRepository;
	
	@Autowired
	private MedicacionRepository medicacionRepository;
	
	@Autowired
	private DosisMedicacionMapper dosisMedicacionMapper;
	
	private static final Integer PRIMERA_DOSIS = 1;
	
	@Override
	public List<DosisMedicacionDTO> listarSiguienteMedicacionPorHistoriaClinicaId( Integer historiaClinicaId ) {
		
		log.info("DosisMedicacionServiceImpl.listarSiguienteMedicacionPorHistoriaClinicaId( historiaClinicaId => {} )", historiaClinicaId );
			
		try {
			  List<DosisMedicacion> listDosis = dosisMedicacionRepository.listarSiguienteMedicacionPorHistoriaClinicaId(historiaClinicaId);
			  return dosisMedicacionMapper.asDosisMedicacionDTOs(listDosis);
		} catch (Exception e) {
			throw new DatabaseException( "Error al listar las siguiente medicación del Paciente.", e );
		}
		
	}

	@Override
	@Transactional
	public void actualizarEstado( Integer dosisMedicacionId ) {
		
		log.info("DosisMedicacionServiceImpl.actualizarEstado( dosisMedicacionId => {} )", dosisMedicacionId );
		
		try {
				DosisMedicacion dosis = dosisMedicacionRepository.buscarPorId( dosisMedicacionId );
				
				String fechaPrimeraDosis = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new GregorianCalendar().getTime() );
				
				dosisMedicacionRepository.actualizarEstado( dosis.getDosisMedicacionId(), fechaPrimeraDosis );
				
				DetalleMedicacion detalleMedicacion = medicacionRepository.buscarDetalleMedicacionPorId( dosis.getDetalleMedicacion().getDetalleMedicacionId() );
				Integer siguienteDosis = detalleMedicacion.getSiguienteDosis()+1;
				medicacionRepository.establecerSiguienteDosis( siguienteDosis, detalleMedicacion.getDetalleMedicacionId() );
				
				if( dosis.getNumeroDosis() == PRIMERA_DOSIS ) {
					List<DosisMedicacion> dosisMedicacions = dosisMedicacionRepository.listarTodasSiguientesDosis(
							dosis.getDetalleMedicacion().getDetalleMedicacionId(), dosis.getDosisMedicacionId());
					
					SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					Date fechaDosisProx = formatFecha.parse(fechaPrimeraDosis);
					
					Integer frecuencia = dosis.getDetalleMedicacion().getFrecuencia();
					
					log.info("fecha de la primera dosis realizada = {}", fechaDosisProx );
					log.info("frecuencia de la dosis = {}", frecuencia );
					
					Calendar calendar = Calendar.getInstance();
					calendar.setTime( fechaDosisProx );
					
					dosisMedicacions.forEach( dm -> {
						
						calendar.add(Calendar.HOUR_OF_DAY, frecuencia );
						
						dosisMedicacionRepository.actualizarFechaDosis( formatFecha.format(calendar.getTime()), dm.getDosisMedicacionId());
					});
				}
			
		} catch (Exception e) {
			throw new DatabaseException( "Error al actualizar el estado de la dosis de la medicación.", e );
		}
		
	}

	@Override
	public List<Integer> alertarDosisPacientes(List<Integer> pacientesIds) {
		log.info("DosisMedicacionServiceImpl.alertarDosisPacientes( pacientesIds = {} )", pacientesIds);
		return dosisMedicacionRepository.alertarDosisPacientes(pacientesIds);
	}

}
