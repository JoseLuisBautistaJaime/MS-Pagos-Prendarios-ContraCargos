/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusEjecucionConciliacionDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusEjecucionConciliacion;

/**
 * Nombre: EstatusEjecucionConciliacionBuilder Descripcion: Clase de capa de builder que
 * se encarga de convertir diferentes tipos de objetos y entidades relacionadas
 * con el Estatus de ejecución del proceso de conciliación.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 04/11/2021 11:48 hrs.
 * @version 0.1
 */
public abstract class EstatusEjecucionConciliacionBuilder {

	public EstatusEjecucionConciliacionBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo EstatusEjecucionConciliacionDTO a partir de la entidad
	 * EstatusEjecucionConciliacion.
	 * 
	 * @param estatusEjecucionConciliacion
	 * @return estatusEjecucionConciliacionDTO
	 */
	public static EstatusEjecucionConciliacionDTO buildEstatusEjecucionConciliacionDTOFromEstatusEjecucionConciliacion(EstatusEjecucionConciliacion estatusEjecucionConciliacion) {
		EstatusEjecucionConciliacionDTO estatusEjecucionConciliacionDTO = null;
		if (estatusEjecucionConciliacion != null) {
			estatusEjecucionConciliacionDTO = new EstatusEjecucionConciliacionDTO();
			estatusEjecucionConciliacionDTO.setId(estatusEjecucionConciliacion.getId());
			estatusEjecucionConciliacionDTO.setDescripcionCorta(estatusEjecucionConciliacion.getDescripcionCorta());
			estatusEjecucionConciliacionDTO.setDescripcion(estatusEjecucionConciliacion.getDescripcion());
			estatusEjecucionConciliacionDTO.setOrderNumber(estatusEjecucionConciliacion.getOrderNumber());
		}
		return estatusEjecucionConciliacionDTO;
	}

	/**
	 * Construye una entidad EstatusEjecucionConciliacion a partir de un objeto de tipo
	 * EstatusEjecucionConciliacionDTO.
	 * 
	 * @param estatusEjecucionConciliacionDTO
	 * @return estatusEjecucionConciliacion
	 */
	public static EstatusEjecucionConciliacion buildEstatusEjecucionConciliacionFromEstatusEjecucionConciliacionDTO( EstatusEjecucionConciliacionDTO estatusEjecucionConciliacionDTO) {
		EstatusEjecucionConciliacion estatusEjecucionConciliacion = null;
		if (estatusEjecucionConciliacionDTO != null) {
			estatusEjecucionConciliacion = new EstatusEjecucionConciliacion();
			estatusEjecucionConciliacion.setId(estatusEjecucionConciliacionDTO.getId());
			estatusEjecucionConciliacion.setDescripcionCorta(estatusEjecucionConciliacionDTO.getDescripcionCorta());
			estatusEjecucionConciliacion.setDescripcion(estatusEjecucionConciliacionDTO.getDescripcion());
			estatusEjecucionConciliacion.setOrderNumber(estatusEjecucionConciliacionDTO.getOrderNumber());
		}
		return estatusEjecucionConciliacion;
	}

}
