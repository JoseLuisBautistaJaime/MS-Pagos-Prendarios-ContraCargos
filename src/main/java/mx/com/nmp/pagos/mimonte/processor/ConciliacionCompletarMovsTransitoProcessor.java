/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
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

		/*if (reportesWrapper.contains(TipoReporteEnum.PROVEEDOR)) {

			// Verificar si ya se cargaron al menos 2 reportes (incluyendo el recibido)
			if (isReportesCargados(reportesWrapper)) {

				// Se obtienen los movimientos en transito de la conciliacion actual con estatus no identificados en midas
				// Esto significa que posiblemente un movimiento del proveedor corresponde a un movimiento en transito de un dia anterior
				List<MovimientoTransito> movsNoIdentificados = listMovimientosTransito(
						reportesWrapper.getIdConciliacion(),
						ConciliacionConstants.ESTATUS_TRANSITO_NO_IDENTIFICADO_MIDAS
					);

				// Se obtienen los movimientos no identificados en el proveedor de conciliaciones de dias anteriores
				// Y se completan si aplica
				if (CollectionUtils.isNotEmpty(movsNoIdentificados)) {
					List<MovimientoTransito> movsAnteriores = listMovimientosTransitoAnteriores(movsNoIdentificados);
					// Se verifica si existen movimientos que se puedan complementar
					List<Long> idsConciliacionesActualizar = completarTransaccionesPendientes(movsNoIdentificados, movsAnteriores);
					// Se agregan las conciliaciones que requieren ser actualizadas
					reportesWrapper.setIdsConciliacionesActualizar(idsConciliacionesActualizar);
				}
			}
		}*/
		processNext(reportesWrapper);
	}


	/**
	 * Se obtiene los movimientos en transito y se filtran por estatus
	 * @param movsMidas
	 * @param idEstatus
	 * @return
	 */
	private List<MovimientoTransito> listMovimientosTransito(Long idConciliacion, Integer idEstatus) throws ConciliacionException {
		List<MovimientoTransito> movsTransito = mergeReporteHandler.getMovimientoTransitoRepository().findByIdConciliacion(idConciliacion);
		// Se filtran movimientos por estatus
		List<MovimientoTransito> movsTransitoFiltrados = filtrarPorEstatus(movsTransito, idEstatus);
		return movsTransitoFiltrados;
	}


	/**
	 * Obtiene los movimientos en transito no identificados en open pay de dias anteriores considerando conciliaciones pendientes
	 * @param movsNoIdentificados
	 * @return
	 */
	private List<MovimientoTransito> listMovimientosTransitoAnteriores(List<MovimientoTransito> movsNoIdentificados) throws ConciliacionException {
		
		// Se obtienen las transacciones
		List<String> transacciones = movsNoIdentificados.stream()
				.map(MovimientoTransito::getTransaccion)
				.collect(Collectors.toList());

		// Se obtienen movimientos en transito correspondientes a las transacciones
		List<MovimientoTransito> movsTransito = mergeReporteHandler.getMovimientoTransitoRepository()
				.findMovsTransitoByTransaccion(transacciones, ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO,
						ConciliacionConstants.ESTATUS_TRANSITO_NO_IDENTIFICADO_OPEN_PAY
		);
		return movsTransito;
	}


	/**
	 * Filtra los movimientos en transito de acuerdo al estatus
	 * @param movsTransito
	 * @param idEstatus
	 * @return
	 */
	private List<MovimientoTransito> filtrarPorEstatus(List<MovimientoTransito> movsTransito, Integer idEstatus) {
		List<MovimientoTransito> movsTransitoFiltrados = new ArrayList<MovimientoTransito>();
		if (movsTransito != null && movsTransito.size() > 0) {
			for (MovimientoTransito movTransito : movsTransito) {
				if (movTransito.getEstatus().getId() == idEstatus) {
					movsTransitoFiltrados.add(movTransito);
				}
			}
		}
		return movsTransitoFiltrados;
	}


	/**
	 * Se complementan transacciones
	 * @param movsNoIdentificados
	 * @param movsAnteriores
	 */
	private List<Long> completarTransaccionesPendientes(List<MovimientoTransito> movsNoIdentificados,
			List<MovimientoTransito> movsAnteriores) {

		Set<Long> idsConciliacionesActualizar = new LinkedHashSet<Long>();
		
		// Por cada movimiento en transito que no esta identificado en midas,
		// se verifica si existe en alguna conciliacion anterior
		if (movsAnteriores != null && movsAnteriores.size() > 0 &&
				movsNoIdentificados != null && movsNoIdentificados.size() > 0) {
			
			List<Integer> movsEliminar = new ArrayList<Integer>();

			// Si se encuentra un movimiento transito a completar:
			// 1-Se eliminan los movs en transito
			// 2-Se asignan los movimientos proveedor a la conciliacion correspondiente
			for (MovimientoTransito movNoIdentificado : movsNoIdentificados) {
				for (MovimientoTransito movAnterior : movsAnteriores) {
					if (movAnterior.getTransaccion().equals(movNoIdentificado.getTransaccion())) {
						movsEliminar.add(movNoIdentificado.getId());
						movsEliminar.add(movAnterior.getId());
						moverMovimientoProveedor(movNoIdentificado.getTransaccion(), movNoIdentificado.getIdConciliacion(), movAnterior.getIdConciliacion()); //
						idsConciliacionesActualizar.add(movAnterior.getIdConciliacion());
						break;
					}
				}

				// Se eliminan los movimientos en transito
				eliminarMovimientosTransito(movsEliminar);
			}
		}

		return Arrays.asList(idsConciliacionesActualizar.toArray(new Long[0]));
	}


	/**
	 * Elimina los movimientos en transito completados
	 * @param movsEliminar
	 */
	private void eliminarMovimientosTransito(List<Integer> movsEliminar) throws ConciliacionException {
		// Se eliminan los movimientos transaccion
		if (movsEliminar.size() > 0) {
			for (Integer movTransitoEliminar : movsEliminar) {
				try {
					this.mergeReporteHandler.getMovimientoProveedorRepository().deleteById(movTransitoEliminar.longValue());
				}
				catch (Exception ex) {
					ex.printStackTrace();
					throw new ConciliacionException(ex.getMessage(), CodigoError.NMP_PMIMONTE_BUSINESS_PROCESAR_CONCILIACION);
				}
			}
		}
	}


	/**
	 * Reasigna el movimiento proveedor 
	 * @param idConciliacion
	 * @param transaccion
	 */
	private void moverMovimientoProveedor(String transaccion, Long idConciliacionOrigen, Long idConcilacionDestino) throws ConciliacionException {
		try {
			
			// Se obtiene el movimiento proveedor de la conciliacion actual usando el numero de transaccion
			MovimientoProveedor movProveedor = this.mergeReporteHandler.getMovimientoProveedorRepository()
				.findByOrderIdAndIdConciliacion(transaccion, idConciliacionOrigen);

			// Se obtiene el reporte de la conciliacion destino
			Reporte reporteConciliacionDestino = this.mergeReporteHandler.getReporteRepository().findLastByIdConciliacionAndTipo(idConcilacionDestino, TipoReporteEnum.PROVEEDOR);

			// Se reasigna el movimiento a la conciliacion anterior (reporte)
			movProveedor.setReporte(reporteConciliacionDestino.getId());

			// Se actualiza el movimiento proveedor con la conciliacion destino 
			this.mergeReporteHandler.getMovimientoProveedorRepository().save(movProveedor);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ex.getMessage(), CodigoError.NMP_PMIMONTE_BUSINESS_PROCESAR_CONCILIACION);
		}
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
