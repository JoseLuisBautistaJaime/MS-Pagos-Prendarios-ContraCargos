/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;
import mx.com.nmp.pagos.mimonte.observer.MergeReporteHandler;

/**
 * Nombre: ConciliacionCompletarMovsTransitoProcessor
 * Descripcion: Clase que verifica si existen movimientos en transito de conciliaciones anteriores
 *
 * @author JGALVEZ
 * Fecha: 20/10/2020 9:44 PM
 * @version 0.1
 */
@Component
@Service("conciliacionMovsTransitoProcessor")
public class ConciliacionCompletarMovsTransitoProcessor extends ConciliacionProcessorChain {

	public ConciliacionCompletarMovsTransitoProcessor(MergeReporteHandler mergeReporteHandler) {
		super(mergeReporteHandler);
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.processor.ConciliacionProcessorChain#process(mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper)
	 */
	public void process(ReportesWrapper reportesWrapper) throws ConciliacionException {
	
		if (reportesWrapper.contains(TipoReporteEnum.PROVEEDOR)) {
			
			// Verificar si ya se cargaron al menos 2 reportes (incluyendo el recibido)
			if (isReportesCargados(reportesWrapper)) {

				// Se obtienen los movimientos en transito de la conciliacion actual
				List<MovimientoTransito> movsTransitoNoIdentificadosMidas = listMovimientosTransito(reportesWrapper.getIdConciliacion());
	
				if (CollectionUtils.isNotEmpty(movsTransitoNoIdentificadosMidas)) {
					
					// Se obtienen los movimientos no identificados en oxxo de conciliaciones de dias anteriores
					List<MovimientoTransito> movsTransitoNoIdentificadosProveedor = getMovsTransitoConciliacionesDiasAnteriores(null);

					// Se verifica si existen movimientos que se puedan complementar
					if (movsTransitoNoIdentificadosProveedor != null && movsTransitoNoIdentificadosProveedor.size() > 0) {
						// TODO: Complementar movs transito y actualizar seccion global
					}
				}
			}
		}
		processNext(reportesWrapper);
	}


	/**
	 * Se obtiene los movimientos en transito y se filtran los que estan en proveedor pero no en Midas
	 * @param movsMidas
	 * @return
	 */
	private List<MovimientoTransito> listMovimientosTransito(Long idConciliacion) throws ConciliacionException {

		List<MovimientoTransito> movsTransito = mergeReporteHandler.getMovimientoTransitoRepository().findByIdConciliacion(idConciliacion);

		// Exiten movimientos oxxo que no se encuentran en midas
		List<MovimientoTransito> movsTransitoFiltrados = new ArrayList<MovimientoTransito>();
		if (movsTransito != null && movsTransito.size() > 0) {
			for (MovimientoTransito movTransito : movsTransito) {
				if (movTransito.getEstatus().getId() == ConciliacionConstants.ESTATUS_TRANSITO_NO_IDENTIFICADO_MIDAS) {
					movsTransitoFiltrados.add(movTransito);
				}
			}
		}
		return movsTransitoFiltrados;
	}


	/**
	 * Obtiene los movimientos en transito no identificados en open pay de dias anteriores considerando conciliaciones pendientes
	 * @param movsMidas
	 * @return
	 */
	private List<MovimientoTransito> getMovsTransitoConciliacionesDiasAnteriores(Date fechaConciliacion) throws ConciliacionException {

		// Se consideran conciliaciones de 7 dias atras
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaConciliacion);
		cal.add(Calendar.DAY_OF_WEEK, 7);

		Date fechaInicio = cal.getTime();
		Date fechaFin = fechaConciliacion;

		List<MovimientoTransito> movsTransito = mergeReporteHandler.getMovimientoTransitoRepository()
				.findMovsTransitoNoIdentificadosMidas(fechaInicio, fechaFin,
						ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO,
						ConciliacionConstants.ESTATUS_TRANSITO_NO_IDENTIFICADO_OPEN_PAY
		);

		return movsTransito;
	}



	/**
	 * Valida que se haya cargado al menos 2 reportes (midas/proveedor)
	 * @param reportesWrapper
	 * @return
	 */
	private boolean isReportesCargados(ReportesWrapper reportesWrapper) {
		boolean midasCargado= false;
		boolean proveedorCargado = false;
		List<Reporte> reportes = this.mergeReporteHandler.getReporteRepository().findByIdConciliacion(reportesWrapper.getIdConciliacion());
		if (CollectionUtils.isNotEmpty(reportes)) {
			for (Reporte reporte : reportes) {
				switch (reporte.getTipo()) {
					case MIDAS:
						midasCargado = true;
						break;
					case PROVEEDOR:
						proveedorCargado = true;
						break;
					default:
						break;
				}
			}
		}
		return midasCargado && proveedorCargado;
	}

}
