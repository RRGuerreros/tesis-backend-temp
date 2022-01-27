package com.sidet.idat.ws.medisalud.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidet.idat.ws.medisalud.entity.dto.ReporteDTO;
import com.sidet.idat.ws.medisalud.exceptions.DatabaseException;
import com.sidet.idat.ws.medisalud.repository.ReporteRepository;
import com.sidet.idat.ws.medisalud.services.ReporteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReporteServiceImpl implements ReporteService {

	@Autowired
	private ReporteRepository reporteRepository;
	
	@Override
	public List<ReporteDTO> reportePacientesRegistradosAnualmente(int year) {
		
		log.info( "ReporteServiceImpl.reportePacientesRegistradosAnualmente( year => {} )" , year );
		
		try {
				return reporteRepository.reportePacientesRegistradosAnualmente(year);
		} catch (Exception e) {
			throw new DatabaseException( "Error al generar el reporte pacientes registrados anualmente.", e );
		}
		
	}

	@Override
	public List<ReporteDTO> reportePacientesRecomendadosCanalAnualmente(int year) {
		
		log.info( "ReporteServiceImpl.reportePacientesRecomendadosCanalAnualmente( year => {} )" , year );
		
		try {
				return reporteRepository.reportePacientesRecomendadosCanalAnualmente(year);
		} catch (Exception e) {
			throw new DatabaseException( "Error al generar el reporte pacientes recomendados canal anualmente.", e );
		}
		
	}

	@Override
	public List<ReporteDTO> reporteCitasGeneradasAnualmente(int year) {
		
		log.info( "ReporteServiceImpl.reporteCitasGeneradasAnualmente( year => {} )" , year );
		
		try {
				return reporteRepository.reporteCitasGeneradasAnualmente(year);
		} catch (Exception e) {
			throw new DatabaseException( "Error al generar el reporte citas generadas anualmente.", e );
		}
		
	}

	@Override
	public List<ReporteDTO> reportePacientesHospitalizadosAnualmente(int year) {
		
		log.info( "ReporteServiceImpl.reportePacientesHospitalizadosAnualmente( year => {} )" , year );
		
		try {
				return reporteRepository.reportePacientesHospitalizadosAnualmente(year);
		} catch (Exception e) {
			throw new DatabaseException( "Error al generar el reporte citas generadas anualmente.", e );
		}
		
	}

	@Override
	public List<ReporteDTO> reporteServiciosMasUtilizadosCitasAnualmente(Integer year, Integer areaMedicaId) {
		log.info( "ReporteServiceImpl.reportePacientesHospitalizadosAnualmente( year => {} )" , year );
		try {
				return reporteRepository.reporteServiciosMasUtilizadosCitasAnualmente(year, areaMedicaId);
		} catch (Exception e) {
			throw new DatabaseException( "Error al generar el reporte servicios utilizados anualmente.", e );
		}
	}

	@Override
	public Integer reporteCantidadTotalPacientesAnualmente() {
		log.info( "ReporteServiceImpl.reporteCantidadPacientesAnualmente()");
		try {
				return reporteRepository.reporteCantidadTotalPacientesAnualmente();
		} catch (Exception e) {
			throw new DatabaseException( "Error al generar el reporte reporteCantidadPacientesAnualmente", e );
		}
	}

	@Override
	public Integer reporteCantidadTotalCitasAnualmente() {
		log.info( "ReporteServiceImpl.reporteCantidadTotalCitasAnualmente()");
		try {
				return reporteRepository.reporteCantidadTotalCitasAnualmente();
		} catch (Exception e) {
			throw new DatabaseException( "Error al generar el reporte reporteCantidadTotalCitasAnualmente", e );
		}
	}

	@Override
	public Integer reporteCantidadTotalPersonalMedicoAnualmente() {
		log.info( "ReporteServiceImpl.reporteCantidadTotalPersonalMedicoAnualmente()");
		try {
				return reporteRepository.reporteCantidadTotalPersonalMedicoAnualmente();
		} catch (Exception e) {
			throw new DatabaseException( "Error al generar el reporte reporteCantidadTotalPersonalMedicoAnualmente", e );
		}
	}

	@Override
	public Integer reporteCantidadTotalHospitalizacionAnualmente() {
		log.info( "ReporteServiceImpl.reporteCantidadTotalHospitalizacionAnualmente()");
		try {
				return reporteRepository.reporteCantidadTotalHospitalizacionAnualmente();
		} catch (Exception e) {
			throw new DatabaseException( "Error al generar el reporte reporteCantidadTotalHospitalizacionAnualmente", e );
		}
	}
}
