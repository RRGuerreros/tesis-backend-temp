package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sidet.idat.ws.medisalud.entity.Departamento;

@Mapper
public interface DepartamentoRepository {

	@Results( value = {
		@Result( property = "departamentoId", column = "departamento_id" )
	})
	@Select("SELECT departamento_id, nombre FROM tb_departamento;")
	List<Departamento> listarTodos();
	
}
