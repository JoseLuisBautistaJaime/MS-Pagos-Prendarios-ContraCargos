/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import java.math.BigDecimal;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Global;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;
import mx.com.nmp.pagos.mimonte.observer.MergeReporteHandler;

/**
 * Nombre: ConciliacionProcessor
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

		// Genera la seccion global si al menos se encuentran 2 reportes
		if (reportesWrapper.contains(TipoReporteEnum.MIDAS) && reportesWrapper.contains(TipoReporteEnum.PROVEEDOR)) {
			
			// Obtener seccion global
			Global global = this.mergeReporteHandler.getGlobalRepository().findByIdConciliacion(reportesWrapper.getIdConciliacion());
			if (global == null) {
				global = new Global();
				global.setConciliacion(new Conciliacion(reportesWrapper.getIdConciliacion()));
			}

			// Reporte
			Reporte reporteMidas = reportesWrapper.getReporteMidas();
			Reporte reporteProveedor = reportesWrapper.getReporteProveedor();
			
			List<MovimientoMidas> movimientosMidas = reportesWrapper.getByTipoReporte(TipoReporteEnum.MIDAS);
			List<MovimientoProveedor> movimientosProveedor = reportesWrapper.getByTipoReporte(TipoReporteEnum.PROVEEDOR);

			// Actualiza seccion global
			// Reporte de proveedor transaccional / Consulta reporte de procesos nocturnos - formato: DD/MM/AA)
			global.setFecha(reporteProveedor.getCreatedDate());
			//(Total de movimientos reportados en el reporte del proveedor transaccional)
			global.setMovmientos(movimientosProveedor.size());
			// (Total de partidas obtenidas del reporte de procesos nocturnos)
			global.setPartidas(movimientosMidas.size());
			// (Suma total de montos pagados en las partidas MIDAS, pagos exitosos y pagos erróneos)
			global.setImporteMidas(getImporteMidas(movimientosMidas));
			// (Suma total de montos del reporte del proveedor transaccional)
			global.setImporteProveedor(getImporteProveedor(movimientosProveedor));
		}

		processNext(reportesWrapper);
	}


	private BigDecimal getImporteMidas(List<MovimientoMidas> movimientosMidas) {
		BigDecimal importeMidas = new BigDecimal(0);
		if (movimientosMidas != null) {
			for (MovimientoMidas movMidas : movimientosMidas) {
				importeMidas = importeMidas.add(movMidas.getMonto());
			}
		}
		return importeMidas;
	}

	private BigDecimal getImporteProveedor(List<MovimientoProveedor> movimientosProveedor) {
		BigDecimal importeProveedor = new BigDecimal(0);
		if (movimientosProveedor != null) {
			for (MovimientoProveedor movProveedor : movimientosProveedor) {
				importeProveedor = importeProveedor.add(movProveedor.getAmount());
			}
		}
		return importeProveedor;
	}

}
