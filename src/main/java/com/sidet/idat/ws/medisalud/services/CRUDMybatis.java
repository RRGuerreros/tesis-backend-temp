package com.sidet.idat.ws.medisalud.services;

import java.util.List;

import com.sidet.idat.ws.medisalud.entity.dto.PaginadorDTO;

public interface CRUDMybatis<T> {
	
	List<T> listarTodos();
	
	T buscarPorId( Integer id );
	
	T registrar( T t );
	
	T actualizar( T t );
	
	Boolean eliminar( Integer id );
	
	PaginadorDTO<T> listarPaginado( PaginadorDTO<T> paginador );
}
