/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosTransitoBuilder;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;
import mx.com.nmp.pagos.mimonte.observer.MergeReporteHandler;

/**
 * Nombre: ConciliacionProcessor
 * Descripcion: Clase que se encarga de procesar el reporte midas y generar los movimientos correspondientes (transito)
 *
 * @author JGALVEZ
 * Fecha: 04/06/2019 9:44 PM
 * @version 0.1
 */
public class ConciliacionReporteMidasProcessor extends ConciliacionProcessorChain {

	public ConciliacionReporteMidasProcessor(MergeReporteHandler mergeReporteHandler) {
		super(mergeReporteHandler);
	}

	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.processor.ConciliacionProcessorChain#process(mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte)
	 */
	public void process(ReportesWrapper reportesWrapper) throws ConciliacionException {
	
		if (reportesWrapper.contains(TipoReporteEnum.MIDAS)) {
			
			// Obtiene los movimientos midas
			List<MovimientoMidas> movimientosMidas = reportesWrapper.getByTipoReporte(TipoReporteEnum.MIDAS);
			if (CollectionUtils.isNotEmpty(movimientosMidas)) {
				
				// Por cada movimiento verifica si se encuentra en error o no identificado en midas
				List<MovimientoTransito> movsTransito = extraerMovimientosNoIdentificadosMidas(reportesWrapper);
				if (CollectionUtils.isNotEmpty(movsTransito)) {
					mergeReporteHandler.getMovimientoTransitoRepository().saveAll(movsTransito);
				}
			}
			
		}
		processNext(reportesWrapper);
	}

	
	private List<MovimientoTransito> extraerMovimientosNoIdentificadosMidas(ReportesWrapper reportesWrapper) {
		
		List<MovimientoTransito> transito = new ArrayList<MovimientoTransito>();

		// Iterar sobre los movimientos del proveedor para obtener los movimientos no identificados en midas
		if (reportesWrapper.contains(TipoReporteEnum.PROVEEDOR)) {
			List<MovimientoProveedor> movimientosProveedor = reportesWrapper.getByTipoReporte(TipoReporteEnum.PROVEEDOR);
			for (MovimientoProveedor movimientoProveedor : movimientosProveedor) {
				
				// Se verifica que la transaccion exista en midas
				String transaccionId = movimientoProveedor.getOrderId();
				List<MovimientoMidas> movimientosMidas = reportesWrapper.getMovimientosMidasByTransaccion(Long.valueOf(transaccionId));
				if (CollectionUtils.isEmpty(movimientosMidas)) {
					transito.add(MovimientosTransitoBuilder.buildMovTransitoFromMovProveedor(movimientoProveedor));
				}
			}

			// TODO: Verificar los movimientos con error
			List<MovimientoMidas> movimientosMidas = reportesWrapper.getByTipoReporte(TipoReporteEnum.MIDAS);
			for (MovimientoMidas movimientoMidas : movimientosMidas) {
				if (!movimientoMidas.getEstatus()) {
					MovimientoTransito movTransito = MovimientosTransitoBuilder.buildMovTransitoFromMovMidas(movimientoMidas);
					movTransito.setIdConciliacion(reportesWrapper.getIdConciliacion());
					
					
					// Verificar si ya existe el movimiento de transito con anteriodad
					// TODO: Remover comentario
//					this.mergeReporteHandler.getMovimientoTransitoCuentaRepository()
					
					
					transito.add(movTransito);
				}
			}
		}

		return transito;
	}

}
