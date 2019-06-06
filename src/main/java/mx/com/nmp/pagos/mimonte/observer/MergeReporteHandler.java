package mx.com.nmp.pagos.mimonte.observer;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoProveedorRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosConciliacionWrapper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionGlobalProcessor;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionReporteEstadoCuentaProcessor;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionReporteMidasProcessor;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionReporteProveedorProcessor;
import mx.com.nmp.pagos.mimonte.services.conciliacion.MovimientosProveedorService;

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


	/**
	 * Se encarga de generar la cadena de procesadores de acuerdo al tipo de reporte
	 * @param reportesActualizados
	 */
	public void handle(List<Reporte> reportes) {

		MovimientosConciliacionWrapper wrapper = new MovimientosConciliacionWrapper();
		
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

				ConciliacionReporteEstadoCuentaProcessor reporteEstadoCuenta = new ConciliacionReporteEstadoCuentaProcessor();
				reporteEstadoCuenta.setNextProcessor(globalProcessor);
				reporteEstadoCuenta.process(reportesActualizados);
				break;

			}			
		}
		
		
		
		ConciliacionGlobalProcessor globalProcessor = new ConciliacionGlobalProcessor();
		
		
		
		switch (reportesActualizados.getTipo()) {
		
			case MIDAS:  // Procesa reporte midas y adicionalmente la seccion global

				ConciliacionReporteMidasProcessor reporteMidasProcessor = new ConciliacionReporteMidasProcessor();
				reporteMidasProcessor.setNextProcessor(globalProcessor);
				reporteMidasProcessor.process(reportesActualizados);
				break;

			case PROVEEDOR: // Procesa el reporte open pay y adicionalmente la seccion global

				ConciliacionReporteProveedorProcessor reporteProveedorProcessor = new ConciliacionReporteProveedorProcessor();
				reporteProveedorProcessor.setNextProcessor(globalProcessor);
				reporteProveedorProcessor.process(reportesActualizados);
				break;

			case ESTADO_CUENTA: // Procesa el reporte de estado de cuenta y adicionalmente la seccion global

				ConciliacionReporteEstadoCuentaProcessor reporteEstadoCuenta = new ConciliacionReporteEstadoCuentaProcessor();
				reporteEstadoCuenta.setNextProcessor(globalProcessor);
				reporteEstadoCuenta.process(reportesActualizados);
				break;

		}
	}

}
