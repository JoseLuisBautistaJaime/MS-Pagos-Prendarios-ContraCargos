/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.observer.MergeReporteHandler;

/**
 * Nombre: ConciliacionMergeProcessorChain
 * Descripcion: Clase obstracta que representa un proceso dentro de la cadena de procesamiento de la conciliacion
 *
 * @author JGALVEZ
 * Fecha: 04/06/2019 9:44 PM
 * @version 0.1
 */
public abstract class ConciliacionProcessorChain {

	protected MergeReporteHandler mergeReporteHandler;
	protected ConciliacionProcessorChain next;


	public ConciliacionProcessorChain(MergeReporteHandler mergeReporteHandler) {
		this.mergeReporteHandler = mergeReporteHandler;
	}

	/**
	 * Setea el siguiente procesador dentro de la cadena
	 * @param next
	 */
	public void setNextProcessor(ConciliacionProcessorChain next) {
		this.next = next;
	}


	/**
	 * Se encarga de procesar los reportes
	 * @param reportesWrapper
	 * @throws ConciliacionException
	 */
	public abstract void process(ReportesWrapper reportesWrapper) throws ConciliacionException;

	public void processNext(ReportesWrapper reportesWrapper) {
		if (next != null) {
			next.process(reportesWrapper);
		}
	}

	protected List<MovimientoMidas> getMovimientosMidas(Long idReporte) throws ConciliacionException {
		List<MovimientoMidas> movimientosMidas = null;
		try {
			movimientosMidas = this.mergeReporteHandler.getMovimientosMidasRepository().findByReporteId(idReporte.intValue());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al consultar movimientos midas");
		}
		return movimientosMidas;
	}
	
	protected List<MovimientoProveedor> getMovimientosProveedor(Long idReporte) throws ConciliacionException {
		List<MovimientoProveedor> movimientosProveedor = null;
		try {
			movimientosProveedor = this.mergeReporteHandler.getMovimientoProveedorRepository().findByReporte(idReporte.intValue());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al consultar movimientos proveedor");
		}
		return movimientosProveedor;
	}

	protected List<MovimientoEstadoCuenta> getMovimientosEstadoCuenta(Long idReporte) throws ConciliacionException {
		List<MovimientoEstadoCuenta> movimientosEstadoCuenta = null;
		try {
			movimientosEstadoCuenta = this.mergeReporteHandler.getMovimientoEstadoCuentaRepository().findByReporte(idReporte);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al consultar movimientos estado cuenta");
		}
		return movimientosEstadoCuenta;
	}

	protected EstadoCuenta getEstadoCuenta(Long idReporte) throws ConciliacionException {
		EstadoCuenta estadoCuenta = null;
		try {
			estadoCuenta = this.mergeReporteHandler.getEstadoCuentaRepository().findOneByIdReporte(idReporte);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al consultar estado cuenta");
		}
		return estadoCuenta;
	}

}
