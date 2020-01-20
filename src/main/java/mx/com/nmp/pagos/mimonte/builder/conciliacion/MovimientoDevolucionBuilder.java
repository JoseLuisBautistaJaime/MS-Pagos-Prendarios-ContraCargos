/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.model.EstatusDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuentaCabecera;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;

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
	 * Construye un objeto de tipo DevolucionConDTO a partir de un entitie de tipo
	 * MovimientoDevolucion.
	 * 
	 * @param movimientoDevolucion
	 * @return devolucionConDTO
	 */
	public static DevolucionConDTO buildDevolucionConDTOFromMovimientoDevolucion(MovimientoDevolucion movimientoDevolucion) {
		DevolucionConDTO devolucionConDTO = null;
		if (movimientoDevolucion != null) {
			devolucionConDTO = new DevolucionConDTO();
			devolucionConDTO.setId(movimientoDevolucion.getId());
			devolucionConDTO.setFecha(movimientoDevolucion.getFecha());
			devolucionConDTO.setEstatus(EstatusDevolucionesBuilder
					.buildEstatusDevolucionDTOFromEstatusDevolucion(movimientoDevolucion.getEstatus()));
			devolucionConDTO.setMonto(movimientoDevolucion.getMonto());
			devolucionConDTO.setEsquemaTarjeta(movimientoDevolucion.getEsquemaTarjeta());
			devolucionConDTO.setIdentificacionCuenta(movimientoDevolucion.getIdentificadorCuenta());
			devolucionConDTO.setTitular(movimientoDevolucion.getTitular());
			devolucionConDTO.setCodigoAutorizacion(movimientoDevolucion.getCodigoAutorizacion());
			devolucionConDTO.setSucursal(movimientoDevolucion.getSucursal());
			devolucionConDTO.setFechaLiquidacion(movimientoDevolucion.getFechaLiquidacion());

		}
		return devolucionConDTO;
	}

	/**
	 * Construye un set de objetos de tipo ReporteEstadoCuentaDTO a partir de un set
	 * de entities de tipo Reporte.
	 * 
	 * @param reporteSet
	 * @return reporteEstadoCuentaDTOSet
	 */
	public static List<DevolucionConDTO> buildDevolucionConDTOListFromMovimientoDevolucionList(
			List<MovimientoDevolucion> movimientoDevolucionList) {
		List<DevolucionConDTO> devolucionConDTOList = null;
		if (movimientoDevolucionList != null && !movimientoDevolucionList.isEmpty()) {
			devolucionConDTOList = new ArrayList<>();
			for (MovimientoDevolucion mD : movimientoDevolucionList) {
				devolucionConDTOList.add(buildDevolucionConDTOFromMovimientoDevolucion(mD));
			}
		}
		return devolucionConDTOList;
	}


	/**
	 * Crea el movimiento devolucion en base al movimiento en transito
	 * @param movimientoTransito
	 * @return
	 */
	public static MovimientoDevolucion buildMovimientoFromMovimientoTransito(MovimientoTransito mt, EstatusDevolucion edPendiente, String createdBy) {
		MovimientoDevolucion movimientoDevolucion = new MovimientoDevolucion();
		movimientoDevolucion.setIdConciliacion(mt.getIdConciliacion());
		movimientoDevolucion.setCreatedBy(createdBy);
		movimientoDevolucion.setCreatedDate(null != mt.getCreatedDate() ? mt.getCreatedDate() : new Date());
		movimientoDevolucion.setLastModifiedBy(null);
		movimientoDevolucion.setLastModifiedDate(null);
		movimientoDevolucion.setNuevo(true);
		movimientoDevolucion.setEstatus(edPendiente);
		movimientoDevolucion.setFecha(mt.getFecha());
		movimientoDevolucion.setMonto(mt.getMonto());
		movimientoDevolucion.setEsquemaTarjeta(mt.getEsquemaTarjeta());
		movimientoDevolucion.setIdentificadorCuenta(mt.getCuenta());
		movimientoDevolucion.setTitular(mt.getTitular());
		movimientoDevolucion.setCodigoAutorizacion(mt.getNumAutorizacion());
		movimientoDevolucion.setSucursal(mt.getSucursal());
		movimientoDevolucion.setFechaLiquidacion(null);
		movimientoDevolucion.setMovimientoMidas(mt.getMovimientoMidas());
		
		return movimientoDevolucion;
	}


	/**
	 * Crea el movimiento devolucion en base al movimiento estado cuenta
	 * @param movEstadoCuenta
	 * @return
	 */
	public static MovimientoDevolucion buildMovimientoFromMovEstadoCuenta(MovimientoEstadoCuenta movEstadoCuenta, Long idConciliacion, String createdBy, EstadoCuentaCabecera cabecera) {
		MovimientoDevolucion movimientoDevolucion = new MovimientoDevolucion();
		movimientoDevolucion.setIdConciliacion(idConciliacion);
		movimientoDevolucion.setCreatedDate(new Date());
		movimientoDevolucion.setCreatedBy(createdBy);
		movimientoDevolucion.setLastModifiedBy(null);
		movimientoDevolucion.setLastModifiedDate(null);
		movimientoDevolucion.setNuevo(false);
		movimientoDevolucion.setEstatus(new EstatusDevolucion(ConciliacionConstants.ESTATUS_DEVOLUCION_PENDIENTE));
		movimientoDevolucion.setFecha(movEstadoCuenta.getFechaOperacion());
		movimientoDevolucion.setMonto(movEstadoCuenta.getImporte());
		movimientoDevolucion.setEsquemaTarjeta(""); // TODO: Estado de cuenta no cuenta con esquema
		movimientoDevolucion.setIdentificadorCuenta(""); // TODO: Identificador de la cuenta es = no cuenta ?
		movimientoDevolucion.setTitular(cabecera.getTitularCuenta());
		movimientoDevolucion.setCodigoAutorizacion(null); // TODO: ??
		//movimientoDevolucion.setSucursal(); // TODO: 
		movimientoDevolucion.setFechaLiquidacion(movEstadoCuenta.getFechaOperacion());

		return movimientoDevolucion;
	}

}
