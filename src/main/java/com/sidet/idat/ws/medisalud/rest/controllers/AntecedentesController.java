package com.sidet.idat.ws.medisalud.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.AntecedentesDTO;
import com.sidet.idat.ws.medisalud.services.impl.AntecedentesServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/antecedentes")
public class AntecedentesController {
	
	@Autowired
	private AntecedentesServiceImpl antecedentesServiceImpl;
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public AntecedentesDTO buscarPorId(@PathVariable("id") Integer id) {
		log.info("AntecedentesController.buscarPorId( id => {})",id);
		return antecedentesServiceImpl.buscarPorId(id);
	}
	
	@PutMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public AntecedentesDTO actualizar(@RequestBody AntecedentesDTO antecedentesDTO) {
		log.info("AntecedentesController.actualizar( antecedentesDTO => {})",antecedentesDTO);
		return antecedentesServiceImpl.actualizar(antecedentesDTO);
	}
}
