package com.sidet.idat.ws.medisalud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.CitaMedicaDTO;
import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;
import com.sidet.idat.ws.medisalud.services.CitaMedicaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/citas-medicas")
public class CitaMedicaController {

	@Autowired
	private CitaMedicaService<CitaMedicaDTO> citaMedicaService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CREAR_CITA_MEDICA')")
	public CitaMedicaDTO registrar( @RequestBody CitaMedicaDTO citaMedicaDTO ) {
		log.info( "CitaMedicaController.registrar( citaMedicaDTO => {})", citaMedicaDTO );
		return citaMedicaService.registrar(citaMedicaDTO);
	}
	
	@PostMapping("/servicio/fecha")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_LISTAR_CITA_MEDICA')")
	public List<CitaMedicaDTO> listarPorServicioIdAndFechaCita( @RequestBody CitaMedicaDTO citaMedicaDTO ) {
		log.info( "CitaMedicaController.listarPorServicioIdAndFechaCita( citaMedicaDTO => {})", citaMedicaDTO );
		return citaMedicaService.listarPorServicioIdAndFechaCita(citaMedicaDTO);
	}
	
	@PostMapping("/paginado")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_LISTAR_CITA_MEDICA')")
	public PaginadorDTO<CitaMedicaDTO> listarPaginado( @RequestBody PaginadorDTO<CitaMedicaDTO> paginador ){
		log.info("CitaMedicaController.listarPaginado( paginador => {})", paginador );
		return citaMedicaService.listarPaginado(paginador);
	}
	
	@DeleteMapping("/{idCitaMedica}")
	@ResponseStatus(code = HttpStatus.OK)
	public Boolean eliminar( @PathVariable("idCitaMedica") Integer id ) {
		log.info("PacienteController.eliminar( citaMedicaId => {})", id );
		return citaMedicaService.eliminar(id);
	}
}
