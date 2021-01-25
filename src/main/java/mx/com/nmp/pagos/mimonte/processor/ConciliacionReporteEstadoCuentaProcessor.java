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
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoComisionEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;
import mx.com.nmp.pagos.mimonte.observer.MergeReporteHandler;

/**
 * Nombre: ConciliacionProcessor Descripcion: Clase que se encarga de procesar
 * el reporte Estado Cuenta y generar los movimientos correspondientes
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

			// Comisiones
			List<MovimientoComision> movsComisiones = extraerMovimientoComision(reportesWrapper.getIdConciliacion(), reportesWrapper.getIdEntidad());
			if (CollectionUtils.isNotEmpty(movsComisiones)) {

				List<MovimientoComision> movimientosComisionSave = new ArrayList<MovimientoComision>();

				for (MovimientoComision movComision : movsComisiones) {
					movComision.setCreatedBy(ConciliacionConstants.USER_SYSTEM);
					movComision.setCreatedDate(new Date());
					movComision.setIdConciliacion(reportesWrapper.getIdConciliacion());
					movimientosComisionSave.add(movComision);
				}

				// Se guardan las comisiones
				//this.mergeReporteHandler.getMovimientoComisionRepository().saveAll(movimientosComisionSave);
				try {
					this.mergeReporteHandler.getMovimientoJdbcRepository().insertarLista(
							movimientosComisionSave,
						ConciliacionConstants.StoreProcedureNames.SAVE_MOV_COMISION_FUNCTION_NAME
					);
				} catch (Exception ex) {
					ex.printStackTrace();
					throw new ConciliacionException(ex.getMessage(), CodigoError.NMP_PMIMONTE_0011);
				}
			}
		}

		// Procesa siguiente reporte
		processNext(reportesWrapper);
	}


	/**
	 * Extrae los movimientos de tipo comision
	 * 
	 * @param idConciliacion
	 * @param idEntidad
	 * @return
	 * @throws ConciliacionException
	 */
	private List<MovimientoComision> extraerMovimientoComision(Long idConciliacion, Long idEntidad) throws ConciliacionException {
		List<MovimientoComision> movsComision = new ArrayList<MovimientoComision>();

		// Comisiones
		List<MovimientoEstadoCuenta> movsEstadoCuenta = this.mergeReporteHandler.getEstadoCuentaHelper()
				.getMovimientosEstadoCuentaByCategoria(idConciliacion, ConciliacionConstants.CATEGORIA_ESTADO_CUENTA_COMISIONES, idEntidad);
		if (CollectionUtils.isNotEmpty(movsEstadoCuenta)) {
			for (MovimientoEstadoCuenta movEstadoCuenta : movsEstadoCuenta) {
				MovimientoComision movComision = MovimientoComisionBuilder
						.buildMovComisionFromMovEstadoCuenta(movEstadoCuenta, idConciliacion, TipoMovimientoComisionEnum.COMISION);
				movsComision.add(movComision);
			}
		}

		// Iva
		movsEstadoCuenta = this.mergeReporteHandler.getEstadoCuentaHelper()
				.getMovimientosEstadoCuentaByCategoria(idConciliacion, ConciliacionConstants.CATEGORIA_ESTADO_CUENTA_IVA, idEntidad);
		if (CollectionUtils.isNotEmpty(movsEstadoCuenta)) {
			for (MovimientoEstadoCuenta movEstadoCuenta : movsEstadoCuenta) {
				MovimientoComision movComision = MovimientoComisionBuilder
						.buildMovComisionFromMovEstadoCuenta(movEstadoCuenta, idConciliacion, TipoMovimientoComisionEnum.IVA_COMISION);
				movsComision.add(movComision);
			}
		}

		return movsComision;
	}

}
