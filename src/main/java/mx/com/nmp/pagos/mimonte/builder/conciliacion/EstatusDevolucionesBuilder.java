/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusDevolucionDTO;
import mx.com.nmp.pagos.mimonte.model.EstatusDevolucion;

/**
* @name EstatusDevolucionesBuilder
* @description Clase que construye objetos en base a otros relacionados con
*              estatusDevolucion
* @author Jose Rodríguez jgrodriguez@quarksoft.net
* @creationDate 29/04/2019 19:47 hrs.
* @version 0.1
*/
public abstract class EstatusDevolucionesBuilder {

	public EstatusDevolucionesBuilder() {
		super();
	}
	
	/**
	 * Construye un objeto de tipo EstatusDevolucionDTO a partir de un entitie de tipo EstatusDevolucion.
	 * @param estatusDevolucion
	 * @return
	 */
	public static EstatusDevolucionDTO buildEstatusDevolucionDTOFromEstatusDevolucion(EstatusDevolucion estatusDevolucion) {
		EstatusDevolucionDTO estatusDevolucionDTO = null;
		if(estatusDevolucion != null) {
			estatusDevolucionDTO = new EstatusDevolucionDTO();
			estatusDevolucionDTO.setId(estatusDevolucion.getId());
			estatusDevolucionDTO.setDescripcion(estatusDevolucion.getDescripcion());
//			estatusDevolucionDTO.setEstatus(estatusDevolucion.getEstatus());
		}
		return estatusDevolucionDTO;
	}
	
	/**
	 * Construye un entitie de tipo EstatusDevolucion a partir de un objeto de tipo EstatusDevolucionDTO.
	 * @param estatusDevolucionDTO
	 * @return
	 */
	public static EstatusDevolucion buildEstatusDevolucionFromEstatusDevolucionDTO(EstatusDevolucionDTO estatusDevolucionDTO) {
		EstatusDevolucion estatusDevolucion = null;
		if(estatusDevolucionDTO != null) {
			estatusDevolucion = new EstatusDevolucion();
			estatusDevolucion.setId(estatusDevolucionDTO.getId());
			estatusDevolucion.setDescripcion(estatusDevolucionDTO.getDescripcion());
//			estatusDevolucion.setEstatus(estatusDevolucionDTO.getEstatus());
		}
		return estatusDevolucion;
	}

}
