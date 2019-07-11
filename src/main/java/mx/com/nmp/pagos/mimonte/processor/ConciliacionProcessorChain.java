/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.CodigoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.observer.MergeReporteHandler;

/**
 * Nombre: ConciliacionMergeProcessorChain Descripcion: Clase obstracta que
 * representa un proceso dentro de la cadena de procesamiento de la conciliacion
 *
 * @author JGALVEZ Fecha: 04/06/2019 9:44 PM
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
	 * 
	 * @param next
	 */
	public void setNextProcessor(ConciliacionProcessorChain next) {
		this.next = next;
	}

	/**
	 * Se encarga de procesar los reportes
	 * 
	 * @param reportesWrapper
	 * @throws ConciliacionException
	 */
	public abstract void process(ReportesWrapper reportesWrapper) throws ConciliacionException;

	public void processNext(ReportesWrapper reportesWrapper) {
		if (next != null) {
			next.process(reportesWrapper);
		}
	}

	protected List<MovimientoMidas> getMovimientosMidas(Integer idReporte) throws ConciliacionException {
		List<MovimientoMidas> movimientosMidas = null;
		try {
			movimientosMidas = this.mergeReporteHandler.getMovimientosMidasRepository()
					.findByReporteId(idReporte.intValue());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al consultar movimientos midas",
					CodigoError.NMP_PMIMONTE_BUSINESS_068);
		}
		return movimientosMidas;
	}

	protected List<MovimientoProveedor> getMovimientosProveedor(Integer idReporte) throws ConciliacionException {
		List<MovimientoProveedor> movimientosProveedor = null;
		try {
			movimientosProveedor = this.mergeReporteHandler.getMovimientoProveedorRepository()
					.findByReporte(idReporte.intValue());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al consultar movimientos proveedor",
					CodigoError.NMP_PMIMONTE_BUSINESS_069);
		}
		return movimientosProveedor;
	}

	protected List<MovimientoEstadoCuenta> getMovimientosEstadoCuenta(Integer idReporte) throws ConciliacionException {
		List<MovimientoEstadoCuenta> movimientosEstadoCuenta = null;
		try {
			movimientosEstadoCuenta = this.mergeReporteHandler.getMovimientoEstadoCuentaRepository()
					.findByReporte(idReporte);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al consultar movimientos estado cuenta",
					CodigoError.NMP_PMIMONTE_BUSINESS_070);
		}
		return movimientosEstadoCuenta;
	}


	/**
	 * Obtiene el estado de cuenta por id reporte y fecha estado cuenta
	 * @param idReporte
	 * @param fechaEstadoCuenta
	 * @return
	 * @throws ConciliacionException
	 */
	protected EstadoCuenta getEstadoCuenta(Long idReporte, Date fechaEstadoCuenta) throws ConciliacionException {
		EstadoCuenta estadoCuenta = null;
		try {
			estadoCuenta = this.mergeReporteHandler.getEstadoCuentaRepository().findOneByIdReporteAndFechaCarga(idReporte, fechaEstadoCuenta);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al consultar estado cuenta", CodigoError.NMP_PMIMONTE_BUSINESS_071);
		}
		return estadoCuenta;
	}

	/**
	 * Regresa el listado de codigos de estado de cuenta para la categoria indicada
	 * 
	 * @return
	 */
	protected List<String> getCodigosEstadoCuenta(Long categoriaEdoCuenta) throws ConciliacionException {

		List<CodigoEstadoCuenta> codigosComisiones = null;
		try {
			codigosComisiones = this.mergeReporteHandler.getCodigoEstadoCuentaRepository()
					.findByCategoriaIdAndEstatus(categoriaEdoCuenta, true);
		} catch (Exception ex) {
			throw new ConciliacionException("Error al obtener los codigos de estado de cuenta",
					CodigoError.NMP_PMIMONTE_BUSINESS_072);
		}

		if (CollectionUtils.isEmpty(codigosComisiones)) {
			throw new ConciliacionException(
					"No existen codigos de estado de cuenta para la categoria de comisiones configurados",
					CodigoError.NMP_PMIMONTE_BUSINESS_073);
		}

		List<String> codigosEstadoCuenta = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(codigosComisiones)) {
			for (CodigoEstadoCuenta codigoComision : codigosComisiones) {
				codigosEstadoCuenta.add(codigoComision.getCodigo());
			}
		}

		return codigosEstadoCuenta;
	}

}
