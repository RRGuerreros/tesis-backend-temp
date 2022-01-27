package com.sidet.idat.ws.medisalud.entity.dto;

import lombok.Data;

@Data
public class DetalleMedicacionDTO {

	private Integer detalleMedicacionId;
	private Integer frecuencia;
	private String dosis;
	private String via;
	private Integer siguienteDosis;
	private String indicaciones;
	private Integer duracion;
	private MedicacionDTO medicacion;
	private MedicamentoDTO medicamento;
	private Integer medicamentoId;
}
