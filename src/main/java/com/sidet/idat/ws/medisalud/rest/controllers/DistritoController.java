package com.sidet.idat.ws.medisalud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sidet.idat.ws.medisalud.entity.dto.DistritoDTO;
import com.sidet.idat.ws.medisalud.services.DistritoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/distritos")
public class DistritoController {
	
	@Autowired
	private DistritoService distritoService;
	
	@GetMapping("/{idProvincia}/provincias")
	@ResponseStatus(code = HttpStatus.OK)
	public List<DistritoDTO> listarTodos( @PathVariable("idProvincia") Integer id ){
		log.info( "DistritoController.listarPorProvinciaId( provinciaId => {} )", id );
		return distritoService.listarPorProvinciaId( id );
	}

}
