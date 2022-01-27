package com.sidet.idat.ws.medisalud.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.sidet.idat.ws.medisalud.entity.DetalleMedicacion;
import com.sidet.idat.ws.medisalud.entity.Medicacion;
import com.sidet.idat.ws.medisalud.entity.Medicamento;
import com.sidet.idat.ws.medisalud.entity.dto.DetalleMedicacionDTO;
import com.sidet.idat.ws.medisalud.entity.dto.MedicacionDTO;
import com.sidet.idat.ws.medisalud.entity.dto.MedicamentoDTO;

@Mapper(componentModel = "spring")
public interface MedicacionMapper {
		
	@Mapping(target = "historiaClinicaId", source = "historiaClinica.historiaClinicaId")
	@Mapping(target = "detalleMedicacion", source = "detalle", qualifiedByName = "asDetalleMedicacionDTOsCustom")
	MedicacionDTO asMedicacionDTO( Medicacion medicacion );
	
	List<MedicacionDTO> asMedicacionDTOs( List<Medicacion> medicacions );
	
	List<DetalleMedicacionDTO> asDetalleMedicacionDTOs( List<DetalleMedicacion> medicaciones );
	
	@Mapping( target = "historiaClinica.historiaClinicaId", source = "historiaClinicaId")
	@Mapping( target = "personalMedico.personaId", source = "personaId")
	@Mapping( target = "detalle", source = "detalleMedicacion", qualifiedByName = "asDetalleMedicacionsCustom")
	Medicacion asMedicacion( MedicacionDTO medicacionDTO );
	
	@Named("asDetalleMedicacionsCustom")
	public static List<DetalleMedicacion> asDetalleMedicacionsCustom( List<DetalleMedicacionDTO> detalleMedicacionDTOs ){
		
		if(detalleMedicacionDTOs == null || detalleMedicacionDTOs.isEmpty())
			return null;
		
		List<DetalleMedicacion> detalleMedicacions = detalleMedicacionDTOs.stream().map( dm -> {
			
			DetalleMedicacion dme = new DetalleMedicacion();
			
			dme.setDosis( dm.getDosis());
			dme.setDuracion( dm.getDuracion());
			dme.setFrecuencia( dm.getFrecuencia());
			dme.setIndicaciones( dm.getIndicaciones());
			dme.setMedicamento( new Medicamento());
			dme.getMedicamento().setMedicamentoId( dm.getMedicamentoId() );
			dme.setSiguienteDosis(1);
			dme.setVia( dm.getVia() );
			
			return dme;
			
		}).collect(Collectors.toList());
		
		return detalleMedicacions;		
	}
	
	@Named("asDetalleMedicacionDTOsCustom")
	public static List<DetalleMedicacionDTO> asDetalleMedicacionDTOsCustom( List<DetalleMedicacion> detalleMedicacion ){
		
		if(detalleMedicacion == null || detalleMedicacion.isEmpty())
			return null;
		
		List<DetalleMedicacionDTO> detalleMedicacions = detalleMedicacion.stream().map( dm -> {
			
			DetalleMedicacionDTO dme = new DetalleMedicacionDTO();
			
			dme.setDosis( dm.getDosis());
			dme.setDuracion( dm.getDuracion());
			dme.setFrecuencia( dm.getFrecuencia());
			dme.setIndicaciones( dm.getIndicaciones());
			dme.setMedicamento( new MedicamentoDTO());
			dme.getMedicamento().setMedicamentoId( dm.getMedicamento().getMedicamentoId() );
			dme.getMedicamento().setNombre( dm.getMedicamento().getNombre() );
			dme.setSiguienteDosis( dm.getSiguienteDosis() );
			dme.setVia( dm.getVia() );
			dme.setDetalleMedicacionId( dm.getDetalleMedicacionId() );
			
			return dme;
			
		}).collect(Collectors.toList());
		
		return detalleMedicacions;		
	}
}
