package com.sidet.idat.ws.medisalud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.AreaMedicaDTO;
import com.sidet.idat.ws.medisalud.services.AreaMedicaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/areas-medicas")
public class AreaMedicaController {

	@Autowired
	private AreaMedicaService areaMedicaService;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<AreaMedicaDTO> listarTodos(){
		log.info( "AreaMedicaController.listarTodos()" );
		return areaMedicaService.listarTodos();
	}
	
}
