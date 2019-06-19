/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import java.util.List;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.GlobalBuilder;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Global;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;
import mx.com.nmp.pagos.mimonte.observer.MergeReporteHandler;

/**
 * Nombre: ConciliacionGlobalProcessor
 * Descripcion: Clase que se encarga de procesar todos los reportes y generar datos en la seccion global
 *
 * @author JGALVEZ
 * Fecha: 04/06/2019 9:44 PM
 * @version 0.1
 */
public class ConciliacionGlobalProcessor extends ConciliacionProcessorChain {


	public ConciliacionGlobalProcessor(MergeReporteHandler mergeReporteHandler) {
		super(mergeReporteHandler);
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.processor.ConciliacionProcessorChain#process(mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte)
	 */
	public void process(ReportesWrapper reportesWrapper) throws ConciliacionException {

		// Obtener seccion global
		Global global = this.mergeReporteHandler.getGlobalRepository().findByIdConciliacion(reportesWrapper.getIdConciliacion());
		
		// Obtiene los movimientos de los reportes
		List<MovimientoMidas> movsMidas = getMovimientosMidas(reportesWrapper.getIdReporte(TipoReporteEnum.MIDAS));
		List<MovimientoProveedor> movsProveedor = getMovimientosProveedor(reportesWrapper.getIdReporte(TipoReporteEnum.PROVEEDOR));
		List<MovimientoEstadoCuenta> movsEstadoCuenta = getMovimientosEstadoCuenta(reportesWrapper.getIdReporte(TipoReporteEnum.ESTADO_CUENTA));

		// Actualizar seccion global
		global = GlobalBuilder.updateGlobal(global, reportesWrapper, movsMidas, movsProveedor, movsEstadoCuenta);

		// Guardar global en la bd
		this.mergeReporteHandler.getGlobalRepository().saveAndFlush(global);

		// Siguiente procesador
		processNext(reportesWrapper);
	}

}
