package com.sidet.idat.ws.medisalud.mappers;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.sidet.idat.ws.medisalud.entity.HistoriaClinica;
import com.sidet.idat.ws.medisalud.entity.dto.HistoriaClinicaDTO;

@Mapper(componentModel = "spring")
public interface HistoriaClinicaMapper {
	
	HistoriaClinica asHistoriaClinica( HistoriaClinicaDTO historiaClinicaDTO );
	
	@Mapping(target = "numeroHistoriaClinica", source = "historiaClinicaId", qualifiedByName = "formatNumeroHistoriaClinica")
	HistoriaClinicaDTO asHistoriaClinicaDTO( HistoriaClinica historiaClinica );
	
	List<HistoriaClinica> asHistoriaClinicas( List<HistoriaClinicaDTO> historiaClinicaDTOs );
	
	List<HistoriaClinicaDTO> asHistoriaClinicaDTOs( List<HistoriaClinica> historiaClinicas );
	
	@Named("formatNumeroHistoriaClinica")
	public static String formatNumeroHistoriaClinica( Integer historiaClinicaId ) {
		
		String prefijo = "HC";
		
		GregorianCalendar grego = new GregorianCalendar();
		
		String anio = new SimpleDateFormat("yy").format(grego.getTime());
		String mes = new SimpleDateFormat("MM").format(grego.getTime());
		String dia = new SimpleDateFormat("dd").format(grego.getTime());
		
		prefijo += anio +""+ mes +""+ dia;
		
		String numero = "00000" + historiaClinicaId;
		
		numero = numero.substring( numero.length() - 5, numero.length());
		
		return prefijo + numero;
	}
}
