package com.sidet.idat.ws.medisalud.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sidet.idat.ws.medisalud.entity.Antecedentes;

@Mapper
public interface AntecedentesRepository {

	@Results(value = {
			@Result(property = "antecedentesId", column = "antecedentes_id"),
			@Result(property = "prenatalesFisiologicos", column = "prenatales_fisiologicos"),
			@Result(property = "partoFisiologicos", column = "parto_fisiologicos"),
			@Result(property = "gradoInstruccionFisiologicos", column = "grado_instruccion_fisiologicos"),
			@Result(property = "comunicacionesFisiologicos", column = "comunicaciones_fisiologicos"),
			@Result(property = "alimentacionGenerales", column = "alimentacion_generales"),
			@Result(property = "ejerciciosGenerales", column = "ejercicios_generales"),
			@Result(property = "habitosNocivosGenerales", column = "habitos_nocivos_generales"),
			@Result(property = "alergiasGenerales", column = "alergias_generales"),
			@Result(property = "descripcionAlergiaGenerales", column = "descripcion_alergia_generales"),
			@Result(property = "hipertencionArterialPatologicos", column = "hipertencion_arterial_patologicos"),
			@Result(property = "hepatitisViralPatologicos", column = "hepatitis_viral_patologicos"),
			@Result(property = "enfermedadesCardiacasPatologicos", column = "enfermedades_cardiacas_patologicos"),
			@Result(property = "fiebreMaltaPatologicos", column = "fiebre_malta_patologicos"),
			@Result(property = "asmaPatologicos", column = "asma_patologicos"),
			@Result(property = "enfermedadTiroideaPatologicos", column = "enfermedad_tiroidea_patologicos"),
			@Result(property = "gastritisUlceraPatologicos", column = "gastritis_ulcera_patologicos"),
			@Result(property = "otrasEnfermedadesPatologicos", column = "otras_enfermedades_patologicos"),
			@Result(property = "cirugiasPatologicos", column = "cirugias_patologicos"),
			@Result(property = "medicinaPatologicos", column = "medicina_patologicos"),
			@Result(property = "enfermedadesMentalesFamiliares", column = "enfermedades_mentales_familiares"),
			@Result(property = "tuberculosisFamiliares", column = "tuberculosis_familiares"),
			@Result(property = "diabetesMellitusFamiliares", column = "diabetes_mellitus_familiares"),
			@Result(property = "enfermedadesCardiacasFamiliares", column = "enfermedades_cardiacas_familiares"),
			@Result(property = "hipertencionArterialFamiliares", column = "hipertencion_arterial_familiares"),
			@Result(property = "otrasEnfermedadesFamiliares", column = "otras_enfermedades_familiares"),
			@Result(property = "historiaClinica.historiaClinicaId", column = "historia_clinica_id")
	})
	@Select("select * from tb_antecedentes tba where tba.historia_clinica_id = #{hospitalizacionId}")
	Antecedentes buscarPorId(Integer hospitalizacionId);
	
	@Select("UPDATE db_medisalud_sidet.tb_antecedentes SET prenatales_fisiologicos=#{prenatalesFisiologicos}, parto_fisiologicos=#{partoFisiologicos}, grado_instruccion_fisiologicos=#{gradoInstruccionFisiologicos},"
			+ " comunicaciones_fisiologicos=#{comunicacionesFisiologicos}, alimentacion_generales=#{alimentacionGenerales}, ejercicios_generales=#{ejerciciosGenerales}, habitos_nocivos_generales=#{habitosNocivosGenerales}, alergias_generales=#{alergiasGenerales},"
			+ " descripcion_alergia_generales=#{descripcionAlergiaGenerales}, hipertencion_arterial_patologicos=#{hipertencionArterialPatologicos}, hepatitis_viral_patologicos=#{hepatitisViralPatologicos}, enfermedades_cardiacas_patologicos=#{enfermedadesCardiacasPatologicos},"
			+ " fiebre_malta_patologicos=#{fiebreMaltaPatologicos}, asma_patologicos=#{asmaPatologicos}, enfermedad_tiroidea_patologicos=#{enfermedadTiroideaPatologicos}, gastritis_ulcera_patologicos=#{gastritisUlceraPatologicos}, otras_enfermedades_patologicos=#{otrasEnfermedadesPatologicos},"
			+ " cirugias_patologicos=#{cirugiasPatologicos}, medicina_patologicos=#{medicinaPatologicos}, enfermedades_mentales_familiares=#{enfermedadesMentalesFamiliares}, tuberculosis_familiares=#{tuberculosisFamiliares}, diabetes_mellitus_familiares=#{diabetesMellitusFamiliares},"
			+ " enfermedades_cardiacas_familiares=#{enfermedadesCardiacasFamiliares}, hipertencion_arterial_familiares=#{hipertencionArterialFamiliares}, otras_enfermedades_familiares=#{otrasEnfermedadesFamiliares}"
			+ " WHERE historia_clinica_id=#{historiaClinica.historiaClinicaId}")
	void actualizar(Antecedentes antecedentes);
	
	@Insert("INSERT INTO tb_antecedentes (historia_clinica_id) VALUES(#{historiaClinicaId})")
	void registrar( Integer historiaClinicaId );
}
