/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;

/**
* @name MovimientoComisionBuilder
* @description Clase que construye objetos en base a otros relacionados con
*              movimientoComision
* @author Jose Rodríguez jgrodriguez@quarksoft.net
* @creationDate 29/04/2019 19:47 hrs.
* @version 0.1
*/
public abstract class MovimientoComisionBuilder {

	public MovimientoComisionBuilder() {
		super();
	}
	
	public static ComisionesDTO buildComisionesDTOFromMovimientoComision(MovimientoComision movimientoComision) {
		ComisionesDTO comisionesDTO = null;
		if(movimientoComision != null) {
			comisionesDTO = new ComisionesDTO();
			comisionesDTO.setId(movimientoComision.getId());
			comisionesDTO.setFecha(movimientoComision.getFechaOperacion());
			comisionesDTO.setFechaCargo(movimientoComision.getFechaCargo());
			comisionesDTO.setMonto(movimientoComision.getMonto());
			comisionesDTO.setDescripcion(movimientoComision.getDescripcion());
		}
		return comisionesDTO;
	}
	
	public static MovimientoComision buildMovimientoComisionFromComisionesDTO(ComisionesDTO comisionesDTO) {
		MovimientoComision movimientoComision = null;
		if(comisionesDTO != null) {
			movimientoComision = new MovimientoComision();
			movimientoComision.setId(comisionesDTO.getId());
			movimientoComision.setFechaOperacion(comisionesDTO.getFecha());
			movimientoComision.setFechaCargo(comisionesDTO.getFechaCargo());
			movimientoComision.setMonto(comisionesDTO.getMonto());
			movimientoComision.setDescripcion(comisionesDTO.getDescripcion());
		}
		return movimientoComision;
	}
	
	/**
	 * Construye un set de objetos de tipo ReporteEstadoCuentaDTO a partir de un set de entities de tipo Reporte.
	 * @param reporteSet
	 * @return reporteEstadoCuentaDTOSet
	 */
	public static List<ComisionesDTO> buildComisionesDTOListFromMovimientoComisionList(List<MovimientoComision> MovimientoComisionList){
		List<ComisionesDTO> ComisionesDTOList = null;
		if(MovimientoComisionList != null && !MovimientoComisionList.isEmpty()) {
			ComisionesDTOList = new ArrayList<>();
			for(  MovimientoComision mC : MovimientoComisionList) {
				ComisionesDTOList.add(buildComisionesDTOFromMovimientoComision(mC));
			}
		}
		return ComisionesDTOList;
	}

}