package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sidet.idat.ws.medisalud.entity.Cama;

@Mapper
public interface CamaRepository {

	@Results( value = {
		@Result(property = "camaId", column = "cama_id")
	})
	@Select("SELECT cama_id, nombre, estado FROM tb_cama C ORDER BY C.nombre ASC;")
	List<Cama> listarTodos();
	
	@Update("UPDATE tb_cama SET estado = 'ocupado' WHERE cama_id = #{camaId}")
	void cambioEstado( Integer camaId );
	
}
