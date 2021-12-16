/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusEjecucionPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusEjecucionPreconciliacion;

/**
 * Nombre: EstatusEjecucionPreconciliacionBuilder Descripcion: Clase de capa de builder que
 * se encarga de convertir diferentes tipos de objetos y entidades relacionadas
 * con el Estatus de ejecución del proceso de pre-conciliación.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 05/11/2021 11:18 hrs.
 * @version 0.1
 */
public abstract class EstatusEjecucionPreconciliacionBuilder {

	public EstatusEjecucionPreconciliacionBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo EstatusEjecucionPreconciliacionDTO a partir de la entidad
	 * EstatusEjecucionPreconciliacion.
	 * 
	 * @param estatusEjecucionPreconciliacion
	 * @return estatusEjecucionPreconciliacionDTO
	 */
	public static EstatusEjecucionPreconciliacionDTO buildEstatusEjecucionConciliacionDTOFromEstatusEjecucionConciliacion(EstatusEjecucionPreconciliacion estatusEjecucionPreconciliacion) {
		EstatusEjecucionPreconciliacionDTO estatusEjecucionPreconciliacionDTO = null;
		if (estatusEjecucionPreconciliacion != null) {
			estatusEjecucionPreconciliacionDTO = new EstatusEjecucionPreconciliacionDTO();
			estatusEjecucionPreconciliacionDTO.setId(estatusEjecucionPreconciliacion.getId());
			estatusEjecucionPreconciliacionDTO.setDescripcionCorta(estatusEjecucionPreconciliacion.getDescripcionCorta());
			estatusEjecucionPreconciliacionDTO.setDescripcion(estatusEjecucionPreconciliacion.getDescripcion());
			estatusEjecucionPreconciliacionDTO.setOrderNumber(estatusEjecucionPreconciliacion.getOrderNumber());
		}
		return estatusEjecucionPreconciliacionDTO;
	}

	/**
	 * Construye una entidad EstatusEjecucionPreconciliacion a partir de un objeto de tipo
	 * EstatusEjecucionPreconciliacionDTO.
	 * 
	 * @param estatusEjecucionPreconciliacionDTO
	 * @return estatusEjecucionPreconciliacion
	 */
	public static EstatusEjecucionPreconciliacion buildEstatusEjecucionConciliacionFromEstatusEjecucionConciliacionDTO( EstatusEjecucionPreconciliacionDTO estatusEjecucionPreconciliacionDTO) {
		EstatusEjecucionPreconciliacion estatusEjecucionPreconciliacion = null;
		if (estatusEjecucionPreconciliacionDTO != null) {
			estatusEjecucionPreconciliacion.setId(estatusEjecucionPreconciliacionDTO.getId());
			estatusEjecucionPreconciliacion.setDescripcionCorta(estatusEjecucionPreconciliacionDTO.getDescripcionCorta());
			estatusEjecucionPreconciliacion.setDescripcion(estatusEjecucionPreconciliacionDTO.getDescripcion());
			estatusEjecucionPreconciliacion.setOrderNumber(estatusEjecucionPreconciliacionDTO.getOrderNumber());
		}
		return estatusEjecucionPreconciliacion;
	}

}
