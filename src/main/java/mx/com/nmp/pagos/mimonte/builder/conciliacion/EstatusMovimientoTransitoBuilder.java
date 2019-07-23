/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusMovTransitoDTO;
import mx.com.nmp.pagos.mimonte.model.EstatusTransito;

/**
* @name MovimientosTransitoBuilder
* @description Clase que construye objetos en base a otros relacionados con
*              estatusMovimientoTransito
* @author Jose Rodríguez jgrodriguez@quarksoft.net
* @creationDate 29/04/2019 19:47 hrs.
* @version 0.1
*/
public abstract class EstatusMovimientoTransitoBuilder {

	public EstatusMovimientoTransitoBuilder() {
		super();
	}
	
	public static EstatusMovTransitoDTO buildEstatusMovTransitoDTOFromEstatusTransito(EstatusTransito estatusTransito) {
		EstatusMovTransitoDTO estatusMovTransitoDTO = null;
		if(estatusTransito != null) {
			estatusMovTransitoDTO = new EstatusMovTransitoDTO();
			estatusMovTransitoDTO.setId(estatusTransito.getId());
			estatusMovTransitoDTO.setDescripcion(estatusTransito.getDescripcion());
			estatusMovTransitoDTO.setEstatus(estatusTransito.getEstatus());
		}
		return estatusMovTransitoDTO;
	}
	
	public static EstatusTransito buildEstatusTransitoFromEstatusMovTransitoDTO(EstatusMovTransitoDTO estatusMovTransitoDTO) {
		EstatusTransito estatusTransito = null;
		if(estatusMovTransitoDTO != null) {
			estatusTransito = new EstatusTransito();
			estatusTransito.setId(estatusMovTransitoDTO.getId());
			estatusTransito.setDescripcion(estatusMovTransitoDTO.getDescripcion());
			estatusTransito.setEstatus(estatusMovTransitoDTO.getEstatus());
		}
		return estatusTransito;
	}
}