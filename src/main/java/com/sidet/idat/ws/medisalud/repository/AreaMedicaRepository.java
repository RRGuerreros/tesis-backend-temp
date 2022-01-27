package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sidet.idat.ws.medisalud.entity.AreaMedica;

@Mapper
public interface AreaMedicaRepository {

	@Results( value = {
		@Result(property = "areaMedicaId", column = "area_medica_id")
	})
	@Select("SELECT area_medica_id, nombre FROM tb_area_medica AM WHERE AM.estado = 1 ORDER BY AM.nombre ASC;")
	List<AreaMedica> listarTodos();
		
}
