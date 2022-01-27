package com.sidet.idat.ws.medisalud.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.sidet.idat.ws.medisalud.entity.CitaMedica;

@Mapper
public interface CitaMedicaRepository {

	@Insert( "INSERT INTO tb_cita_medica( fecha_cita, hora_cita, estado, personal_medico_id, paciente_id, servicio_id ) "+
			 "VALUES( #{fechaCita}, #{horaCita}, #{estado}, #{personalMedico.personalMedicoId}, #{paciente.pacienteId}, #{servicio.servicioId} );")
	@SelectKey( statement="SELECT LAST_INSERT_ID() as citaMedicaId", keyProperty="citaMedicaId", keyColumn = "cita_medica_id", 
	resultType = int.class, before = false )
	void registrar( CitaMedica citaMedica );
	
	@Results( value = {
		@Result(property = "citaMedicaId", column = "cita_medica_id"),
		@Result(property = "fechaCita", column = "fecha_cita"),
		@Result(property = "horaCita", column = "hora_cita"),
		@Result(property = "personalMedico.personalMedicoId", column = "personal_medico_id"),
		@Result(property = "paciente.pacienteId", column = "paciente_id"),
		@Result(property = "paciente.nombres", column = "paciente_nombres"),
		@Result(property = "paciente.apellidoPaterno", column = "paciente_apellido_paterno"),
		@Result(property = "paciente.apellidoMaterno", column = "paciente_apellido_materno")
	})
	@Select("SELECT CM.cita_medica_id, CM.fecha_cita, CM.hora_cita, PM.personal_medico_id, "+
			"P.paciente_id, Per.nombres as paciente_nombres, Per.apellido_paterno as paciente_apellido_paterno, "+
			"Per.apellido_materno as paciente_apellido_materno FROM tb_cita_medica CM "+
			"INNER JOIN tb_paciente P ON P.paciente_id = CM.paciente_id "+
			"INNER JOIN tb_persona Per ON Per.persona_id = P.persona_id "+
			"INNER JOIN tb_personal_medico PM ON PM.personal_medico_id = CM.personal_medico_id "+
			"WHERE CM.servicio_id = #{ServicioId} AND CM.fecha_cita = #{fechaCita} AND CM.estado = 'registrado' ")
	List<CitaMedica> listarPorServicioIdAndFechaCita( Integer ServicioId, String fechaCita );
	
	@Results( value = {
		@Result(property = "citaMedicaId", column = "cita_medica_id"),
		@Result(property = "fechaCita", column = "fecha_cita"),
		@Result(property = "horaCita", column = "hora_cita"),
		@Result(property = "estado", column = "estado_cita"),
		@Result(property = "paciente.pacienteId", column = "paciente_id"),
		@Result(property = "paciente.nombres", column = "paciente_nombres"),
		@Result(property = "paciente.apellidoPaterno", column = "paciente_apellido_paterno"),
		@Result(property = "paciente.apellidoMaterno", column = "paciente_apellido_materno"),
		@Result(property = "paciente.numeroDocumento", column = "paciente_numero_documento"),
		@Result(property = "personalMedico.personalMedicoId", column = "personal_medico_id"),
		@Result(property = "personalMedico.nombres", column = "personal_medico_nombres"),
		@Result(property = "personalMedico.apellidoPaterno", column = "personal_medico_apellido_paterno"),
		@Result(property = "personalMedico.apellidoMaterno", column = "personal_medico_apellido_Materno"),
		@Result(property = "servicio.servicioId", column = "servicio_id"),
		@Result(property = "servicio.nombre", column = "servicio_nombre")
	})
	@Select( "SELECT CM.cita_medica_id, CM.fecha_cita, CM.hora_cita, CM.estado as estado_cita, P.paciente_id, Per.nombres as paciente_nombres, "+
			 "Per.apellido_paterno as paciente_apellido_paterno, Per.apellido_materno as paciente_apellido_materno, "+
			 "Per.numero_documento as paciente_numero_documento, PM.personal_medico_id, Per1.nombres as personal_medico_nombres, "+
			 "Per1.apellido_paterno as personal_medico_apellido_paterno, Per1.apellido_materno as personal_medico_apellido_materno, "+
			 "S.servicio_id, S.nombre as servicio_nombre FROM tb_cita_medica CM "+
			 "INNER JOIN tb_paciente P ON P.paciente_id = CM.paciente_id "+
			 "INNER JOIN tb_persona Per ON Per.persona_id = P.persona_id "+
			 "INNER JOIN tb_personal_medico PM ON PM.personal_medico_id = CM.personal_medico_id "+
			 "INNER JOIN tb_persona Per1 ON Per1.persona_id = PM.persona_id "+
			 "INNER JOIN tb_servicio S ON S.servicio_id = PM.servicio_id "+
			 "WHERE CM.estado = 'registrado' "+
			 "ORDER BY CM.cita_medica_id DESC LIMIT #{inicio},#{fin};" )
	List<CitaMedica> listarPaginado( Integer inicio, Integer fin );
	
	@Select("SELECT COUNT(CM.cita_medica_id) as total_filas FROM tb_cita_medica CM;")
	Integer contarTotalFilas();
	
	@Delete("UPDATE tb_cita_medica SET estado = 'cancelado' WHERE cita_medica_id = #{citaMedicaId}")
	void eliminar( Integer citaMedicaId );
	
}
