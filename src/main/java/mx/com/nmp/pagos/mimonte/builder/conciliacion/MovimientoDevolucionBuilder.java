/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;

/**
* @name MovimientoDevolucionBuilder
* @description Clase que construye objetos en base a otros relacionados con
*              movimientoDevolucion
* @author Jose Rodríguez jgrodriguez@quarksoft.net
* @creationDate 29/04/2019 19:47 hrs.
* @version 0.1
*/
public abstract class MovimientoDevolucionBuilder {

	public MovimientoDevolucionBuilder() {
		super();
	}
	
	/**
	 * Construye un objeto de tipo DevolucionConDTO a partir de un entitie de tipo MovimientoDevolucion.
	 * @param movimientoDevolucion
	 * @return devolucionConDTO
	 */
	public static DevolucionConDTO buildDevolucionDTOFromDevolucion(MovimientoDevolucion movimientoDevolucion) {
		DevolucionConDTO devolucionConDTO = null;
		if(movimientoDevolucion != null) {
			devolucionConDTO = new DevolucionConDTO();
			devolucionConDTO.setId(movimientoDevolucion.getId());
			devolucionConDTO.setFecha(movimientoDevolucion.getFecha()); 
			devolucionConDTO.setEstatus(EstatusDevolucionesBuilder.buildEstatusDevolucionDTOFromEstatusDevolucion(movimientoDevolucion.getEstatus()));
			devolucionConDTO.setMonto(movimientoDevolucion.getMonto());
			devolucionConDTO.setEsquemaTarjeta(movimientoDevolucion.getEsquemaTarjeta());
			devolucionConDTO.setIdentificacionCuenta(movimientoDevolucion.getIdentificadorCuenta());
			devolucionConDTO.setTitular(movimientoDevolucion.getTitular());
			devolucionConDTO.setCodigiAutorizacion(movimientoDevolucion.getCodigoAutorizacion());
			devolucionConDTO.setSucursal(movimientoDevolucion.getSucursal());
			
		}
		return devolucionConDTO;
	}
	
	/**
	 * Construye un entitie de tipo MovimientoDevolucion a partir de un objeto de tipo DevolucionConDTO.
	 * @param devolucionConDTO
	 * @return movimientoDevolucion
	 */
	public static MovimientoDevolucion buildMovimientoDevolucionFromDevolucionConDTO(DevolucionConDTO devolucionConDTO) {
		MovimientoDevolucion movimientoDevolucion = null;
		if(devolucionConDTO != null) {
			movimientoDevolucion = new MovimientoDevolucion();
			movimientoDevolucion.setId(devolucionConDTO.getId());
			movimientoDevolucion.setFecha(devolucionConDTO.getFecha());
			movimientoDevolucion.setEstatus(EstatusDevolucionesBuilder.buildEstatusDevolucionFromEstatusDevolucionDTO(devolucionConDTO.getEstatus()));
			movimientoDevolucion.setMonto(devolucionConDTO.getMonto());
			movimientoDevolucion.setEsquemaTarjeta(devolucionConDTO.getEsquemaTarjeta());
			movimientoDevolucion.setIdentificadorCuenta(devolucionConDTO.getIdentificacionCuenta());
			movimientoDevolucion.setTitular(devolucionConDTO.getTitular());
			movimientoDevolucion.setCodigoAutorizacion(devolucionConDTO.getCodigiAutorizacion());
			movimientoDevolucion.setSucursal(devolucionConDTO.getSucursal());
		}
		return movimientoDevolucion;
	}

	/**
	 * Construye un set de objetos de tipo ReporteEstadoCuentaDTO a partir de un set de entities de tipo Reporte.
	 * @param reporteSet
	 * @return reporteEstadoCuentaDTOSet
	 */
	public static Set<DevolucionConDTO> buildReporteEstadoCuentaDTOSetFromReporteSet(Set<MovimientoDevolucion> movimientoDevolucionSet){
		Set<DevolucionConDTO> DevolucionConDTOSet = null;
		if(movimientoDevolucionSet != null && !movimientoDevolucionSet.isEmpty()) {
			DevolucionConDTOSet = new TreeSet<>();
			for(  MovimientoDevolucion mD : movimientoDevolucionSet) {
				DevolucionConDTOSet.add(buildDevolucionDTOFromDevolucion(mD));
			}
		}
		return DevolucionConDTOSet;
	}
	
	/**
	 * Construye un set de objetos de tipo ReporteEstadoCuentaDTO a partir de un set de entities de tipo Reporte.
	 * @param reporteSet
	 * @return reporteEstadoCuentaDTOSet
	 */
	public static List<DevolucionConDTO> buildReporteEstadoCuentaDTOListFromReporteList(List<MovimientoDevolucion> movimientoDevolucionList){
		List<DevolucionConDTO> devolucionConDTOList = null;
		if(movimientoDevolucionList != null && !movimientoDevolucionList.isEmpty()) {
			devolucionConDTOList = new ArrayList<>();
			for(  MovimientoDevolucion mD : movimientoDevolucionList) {
				devolucionConDTOList.add(buildDevolucionDTOFromDevolucion(mD));
			}
		}
		return devolucionConDTOList;
	}
}
