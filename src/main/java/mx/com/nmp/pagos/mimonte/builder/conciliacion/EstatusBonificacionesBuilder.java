/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusBonificacionDTO;
import mx.com.nmp.pagos.mimonte.model.EstatusBonificacion;

/**
* @name EstatusBonificacionesBuilder
* @description Clase que construye objetos en base a otros relacionados con
*              EstatusBonificacion
* @author Jorge Galvez
* @creationDate 11/11/2020 19:47 hrs.
* @version 0.1
*/
public abstract class EstatusBonificacionesBuilder {

	public EstatusBonificacionesBuilder() {
		super();
	}
	
	/**
	 * Construye un objeto de tipo EstatusBonificacionDTO a partir de un entitie de tipo EstatusBonificacion.
	 * @param estatusBonificacion
	 * @return
	 */
	public static EstatusBonificacionDTO buildDTO(EstatusBonificacion estatusBonificacion) {
		EstatusBonificacionDTO dto = null;
		if(estatusBonificacion != null) {
			dto = new EstatusBonificacionDTO();
			dto.setId(estatusBonificacion.getId() != null ? estatusBonificacion.getId().longValue() : null);
			dto.setDescripcion(estatusBonificacion.getDescripcion());
			dto.setEstatus(estatusBonificacion.getEstatus());
		}
		return dto;
	}
	
	/**
	 * Construye un entitie de tipo EstatusBonificacion a partir de un objeto de tipo EstatusBonificacionDTO.
	 * @param dto
	 * @return
	 */
	public static EstatusBonificacion build(EstatusBonificacionDTO dto) {
		EstatusBonificacion bonificacion = null;
		if(dto != null) {
			bonificacion = new EstatusBonificacion();
			bonificacion.setId(dto.getId() != null ? dto.getId().intValue() : null);
			bonificacion.setDescripcion(dto.getDescripcion());
		}
		return bonificacion;
	}

}
