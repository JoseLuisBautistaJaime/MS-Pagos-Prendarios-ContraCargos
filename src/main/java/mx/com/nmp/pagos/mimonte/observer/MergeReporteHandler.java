/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.observer;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.dao.CodigoEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstadoCuentaCabeceraRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.GlobalRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoComisionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoDevolucionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoProveedorRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoTransitoRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ReporteRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionGlobalProcessor;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionProcessorChain;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionReporteEstadoCuentaProcessor;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionReporteMidasProcessor;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionReporteProveedorProcessor;
import mx.com.nmp.pagos.mimonte.processor.ConciliacionUpdateReporteProcessor;

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
	private EstadoCuentaCabeceraRepository estadoCuentaCabeceraRepository;

	@Inject
	private MovimientoTransitoRepository movimientoTransitoRepository;

	@Inject
	private MovimientoComisionRepository movimientoComisionRepository;

	@Inject
	private MovimientoDevolucionRepository movimientoDevolucionRepository;

	@Inject
	private ReporteRepository reporteRepository;

	@Inject
	private GlobalRepository globalRepository;

	@Inject
	private ConciliacionRepository conciliacionRepository;

	@Inject
	private CodigoEstadoCuentaRepository codigoEstadoCuentaRepository;

	@Inject
	private EstadoCuentaRepository estadoCuentaRepository;




	/**
	 * Se encarga de generar la cadena de procesadores de acuerdo al tipo de reporte
	 * @param reportes
	 * @param idConciliacion
	 */
	public void handle(List<Reporte> reportes, Long idConciliacion) {

		ReportesWrapper wrapper = new ReportesWrapper(idConciliacion);

		for (Reporte reporte : reportes) {
			switch (reporte.getTipo()) {

				case MIDAS:  // Reporte midas
					wrapper.setReporteMidas(reporte);
					break;
	
				case PROVEEDOR: // Reporte open pay
					wrapper.setReporteProveedor(reporte);
					break;
	
				case ESTADO_CUENTA: // Reporte de estado de cuenta
					wrapper.setReporteEstadoCuenta(reporte);
					break;

			}			
		}

		// Se configuran los procesadores
		ConciliacionProcessorChain chainProcessor = configurarProcesadores(wrapper);

		// Se ejecutan los procesadores
		chainProcessor.process(wrapper);
	}



	/**
	 * Se encarga de configurar la cadena de procesadores
	 * @param wrapper 
	 * @return
	 */
	private ConciliacionProcessorChain configurarProcesadores(ReportesWrapper wrapper) {
		ConciliacionReporteMidasProcessor reporteMidasProcessor = new ConciliacionReporteMidasProcessor(this);
		ConciliacionReporteProveedorProcessor reporteProveedorProcessor = new ConciliacionReporteProveedorProcessor(this);
		ConciliacionReporteEstadoCuentaProcessor reporteEstadoCuentaProcessor = new ConciliacionReporteEstadoCuentaProcessor(this);
		ConciliacionGlobalProcessor globalProcessor = new ConciliacionGlobalProcessor(this);
		ConciliacionUpdateReporteProcessor updateProcessor = new ConciliacionUpdateReporteProcessor(this);
		
		reporteMidasProcessor.setNextProcessor(reporteProveedorProcessor);
		reporteProveedorProcessor.setNextProcessor(reporteEstadoCuentaProcessor);
		reporteEstadoCuentaProcessor.setNextProcessor(globalProcessor);
		globalProcessor.setNextProcessor(updateProcessor);
		return reporteMidasProcessor;
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

	public MovimientoTransitoRepository getMovimientoTransitoRepository() {
		return movimientoTransitoRepository;
	}

	public GlobalRepository getGlobalRepository() {
		return globalRepository;
	}

	public ReporteRepository getReporteRepository() {
		return reporteRepository;
	}

	public ConciliacionRepository getConciliacionRepository() {
		return conciliacionRepository;
	}

	public CodigoEstadoCuentaRepository getCodigoEstadoCuentaRepository() {
		return codigoEstadoCuentaRepository;
	}

	public MovimientoComisionRepository getMovimientoComisionRepository() {
		return movimientoComisionRepository;
	}

	public EstadoCuentaRepository getEstadoCuentaRepository() {
		return estadoCuentaRepository;
	}



	public MovimientoDevolucionRepository getMovimientoDevolucionRepository() {
		return movimientoDevolucionRepository;
	}



	public EstadoCuentaCabeceraRepository getEstadoCuentaCabeceraRepository() {
		return estadoCuentaCabeceraRepository;
	}

}
