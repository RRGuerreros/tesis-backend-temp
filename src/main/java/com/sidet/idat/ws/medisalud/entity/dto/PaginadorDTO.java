package com.sidet.idat.ws.medisalud.entity.dto;

import java.util.List;

import lombok.Data;

@Data
public class PaginadorDTO<T> {

	private Integer totalFilas;
	private Integer numeroPagina;
	private Integer totalFilasPagina;
	private List<T> datos;
	private T objeto;
}
