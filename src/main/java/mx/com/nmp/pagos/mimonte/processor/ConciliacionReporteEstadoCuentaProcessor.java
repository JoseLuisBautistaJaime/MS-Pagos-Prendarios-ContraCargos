/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientoComisionBuilder;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;
import mx.com.nmp.pagos.mimonte.observer.MergeReporteHandler;

/**
 * Nombre: ConciliacionProcessor Descripcion: Clase que se encarga de procesar
 * el reporte Estado Cuenta y generar los movimientos correspondientes
 * devoluciones
 *
 * @author JGALVEZ Fecha: 04/06/2019 9:44 PM
 * @version 0.1
 */
@Component
@Service("conciliacionReporteEstadoCuentaProcessor")
public class ConciliacionReporteEstadoCuentaProcessor extends ConciliacionProcessorChain {

	public ConciliacionReporteEstadoCuentaProcessor(MergeReporteHandler mergeReporteHandler) {
		super(mergeReporteHandler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.nmp.pagos.mimonte.processor.ConciliacionProcessorChain#process(mx.com.
	 * nmp.pagos.mimonte.model.conciliacion.Reporte)
	 */
	public void process(ReportesWrapper reportesWrapper) throws ConciliacionException {

		if (reportesWrapper.contains(TipoReporteEnum.ESTADO_CUENTA)) {

			// TODO: Detectar movimientos de tipo devoluciones

			// Comisiones
			List<MovimientoComision> movsComisiones = extraerMovimientoComision(
					reportesWrapper.getReporteEstadoCuenta().getId().longValue());
			if (CollectionUtils.isNotEmpty(movsComisiones)) {
				for (MovimientoComision movComision : movsComisiones) {

					// Se verifica si ya existe
					MovimientoComision movComisionBD = this.mergeReporteHandler.getMovimientoComisionRepository()
							.findByIdMovimientoEstadoCuenta(movComision.getIdMovimientoEstadoCuenta());
					if (movComisionBD != null) {
						movComision.setId(movComisionBD.getId());
						movComision.setLastModifiedBy(ConciliacionConstants.USER_SYSTEM);
						movComision.setLastModifiedDate(new Date());
					} else {
						movComision.setCreatedBy(ConciliacionConstants.USER_SYSTEM);
						movComision.setCreatedDate(new Date());
						movComision.setIdConciliacion(reportesWrapper.getIdConciliacion());
					}
				}

				// Se guardan las comisiones
				this.mergeReporteHandler.getMovimientoComisionRepository().saveAll(movsComisiones);
			}
		}

		// Procesa siguiente reporte
		processNext(reportesWrapper);
	}

	/**
	 * Extrae los movimientos de tipo comision
	 * 
	 * @param idReporte
	 * @return
	 * @throws ConciliacionException
	 */
	private List<MovimientoComision> extraerMovimientoComision(Long idReporte) throws ConciliacionException {
		List<MovimientoComision> movsComision = new ArrayList<MovimientoComision>();

		List<MovimientoEstadoCuenta> movsEstadoCuenta = getMovimientosEstadoCuentaComisiones(idReporte);
		if (CollectionUtils.isNotEmpty(movsEstadoCuenta)) {
			for (MovimientoEstadoCuenta movEstadoCuenta : movsEstadoCuenta) {
				MovimientoComision movComision = MovimientoComisionBuilder
						.buildMovComisionFromMovEstadoCuenta(movEstadoCuenta);
				movsComision.add(movComision);
			}
		}

		return movsComision;
	}

	/**
	 * Obtiene los movimientos de estado de cuenta de comisiones
	 * 
	 * @param idReporteEstadoCuenta
	 * @return
	 */
	private List<MovimientoEstadoCuenta> getMovimientosEstadoCuentaComisiones(Long idReporte)
			throws ConciliacionException {

		// Codigos de Estado de Cuenta
		List<String> codigosEstadoCuenta = getCodigosEstadoCuenta(ConciliacionConstants.CATEGORIA_ESTADO_CUENTA_COMISIONES);

		// Obtiene los movimientos de estado de cuenta que contiene el codigo
		// correspondiente
		List<MovimientoEstadoCuenta> movimientosEstadoCuenta = this.mergeReporteHandler
				.getMovimientoEstadoCuentaRepository().findByReporteAndClaveLeyendaIn(idReporte, codigosEstadoCuenta);

		return movimientosEstadoCuenta;
	}

}
