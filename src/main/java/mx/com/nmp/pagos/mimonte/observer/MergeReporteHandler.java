/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.observer;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoProveedorRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoTransitoRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionGlobalProcessor;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionReporteEstadoCuentaProcessor;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionReporteMidasProcessor;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionReporteProveedorProcessor;

/**
 * Nombre: MergeReporteHandler
 * Descripcion: Clase que se encarga de proveer la logica para la delegacion del procesamiento de los reportes de movimientos
 *
 * @author JGALVEZ
 * Fecha: 04/06/2019 9:44 PM
 * @version 0.1
 */
@Service("mergeReporteHandler")
public class MergeReporteHandler {

	@Inject
	private MovimientosMidasRepository movimientosMidasRepository;

	@Inject
	private MovimientoProveedorRepository movimientoProveedorRepository;

	@Inject
	private MovimientoEstadoCuentaRepository movimientoEstadoCuentaRepository;

	@Inject
	private MovimientoTransitoRepository movimientoTransitoCuentaRepository;




	/**
	 * Se encarga de generar la cadena de procesadores de acuerdo al tipo de reporte
	 * @param reportesActualizados
	 */
	public void handle(List<Reporte> reportes) {

		ReportesWrapper wrapper = new ReportesWrapper();
		
		for (Reporte reporte : reportes) {
			switch (reporte.getTipo()) {
			
			case MIDAS:  // Procesa reporte midas y adicionalmente la seccion global

				List<MovimientoMidas> movimientosMidas = movimientosMidasRepository.findByReporteId(reporte.getId());
				wrapper.setMovimientosMidas(movimientosMidas);
				break;

			case PROVEEDOR: // Procesa el reporte open pay y adicionalmente la seccion global

				List<MovimientoProveedor> movimientosProveedor = movimientoProveedorRepository.findByReporte(reporte.getId());
				wrapper.setMovimientosProveedor(movimientosProveedor);
				break;

			case ESTADO_CUENTA: // Procesa el reporte de estado de cuenta y adicionalmente la seccion global

				List<MovimientoEstadoCuenta> movimientosEstadoCuenta = movimientoEstadoCuentaRepository.findByReporte(reporte.getId());
				wrapper.setMovimientosEstadoCuenta(movimientosEstadoCuenta);
				break;

			}			
		}
		
		ConciliacionReporteMidasProcessor reporteMidasProcessor = new ConciliacionReporteMidasProcessor(this);
		ConciliacionReporteProveedorProcessor reporteProveedorProcessor = new ConciliacionReporteProveedorProcessor(this);
		ConciliacionReporteEstadoCuentaProcessor reporteEstadoCuentaProcessor = new ConciliacionReporteEstadoCuentaProcessor(this);
		ConciliacionGlobalProcessor globalProcessor = new ConciliacionGlobalProcessor(this);
		
		reporteMidasProcessor.setNextProcessor(reporteProveedorProcessor);
		reporteProveedorProcessor.setNextProcessor(reporteEstadoCuentaProcessor);
		reporteEstadoCuentaProcessor.setNextProcessor(globalProcessor);

		reporteMidasProcessor.process(wrapper);
	}



	public MovimientosMidasRepository getMovimientosMidasRepository() {
		return movimientosMidasRepository;
	}

	public MovimientoProveedorRepository getMovimientoProveedorRepository() {
		return movimientoProveedorRepository;
	}

	public MovimientoEstadoCuentaRepository getMovimientoEstadoCuentaRepository() {
		return movimientoEstadoCuentaRepository;
	}

	public MovimientoTransitoRepository getMovimientoTransitoCuentaRepository() {
		return movimientoTransitoCuentaRepository;
	}

}
