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
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientoDevolucionBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuentaCabecera;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoComisionEnum;
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
			
			// Devoluciones
			List<MovimientoDevolucion> movsDevoluciones = extraerMovimientoDevolucion(reportesWrapper.getIdConciliacion());
			if (CollectionUtils.isNotEmpty(movsDevoluciones)) {

				List<MovimientoDevolucion> movimientosDevolucionSave = new ArrayList<MovimientoDevolucion>();

				for (MovimientoDevolucion movDevolucion : movsDevoluciones) {

					// TODO: Se verifica si ya existe
					movDevolucion.setCreatedBy(ConciliacionConstants.USER_SYSTEM);
					movDevolucion.setCreatedDate(new Date());
					movDevolucion.setIdConciliacion(reportesWrapper.getIdConciliacion());
					
					movimientosDevolucionSave.add(movDevolucion);
				}

				// Se guardan las devoluciones
				//this.mergeReporteHandler.getMovimientoDevolucionRepository().saveAll(movimientosDevolucionSave);
				try {
					this.mergeReporteHandler.getMovimientoJdbcRepository().insertarLista(
						movimientosDevolucionSave,
						ConciliacionConstants.StoreProcedureNames.SAVE_MOV_DEVOLUCION_FUNCTION_NAME
					);
				} catch (Exception ex) {
					ex.printStackTrace();
					throw new ConciliacionException(ex.getMessage(), CodigoError.NMP_PMIMONTE_0011);
				}
			}


			// Comisiones
			List<MovimientoComision> movsComisiones = extraerMovimientoComision(reportesWrapper.getIdConciliacion());
			if (CollectionUtils.isNotEmpty(movsComisiones)) {

				List<MovimientoComision> movimientosComisionSave = new ArrayList<MovimientoComision>();

				for (MovimientoComision movComision : movsComisiones) {

					// Se verifica si ya existe
					MovimientoComision movComisionBD = this.mergeReporteHandler.getMovimientoComisionRepository()
							.findByIdMovimientoEstadoCuenta(movComision.getIdMovimientoEstadoCuenta());
					if (movComisionBD != null) { // Update last modified
						movComision = movComisionBD;
						movComision.setLastModifiedBy(ConciliacionConstants.USER_SYSTEM);
						movComision.setLastModifiedDate(new Date());
					} else { // Crear nueva
						movComision.setCreatedBy(ConciliacionConstants.USER_SYSTEM);
						movComision.setCreatedDate(new Date());
						movComision.setIdConciliacion(reportesWrapper.getIdConciliacion());
					}
					
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
	 * @return
	 * @throws ConciliacionException
	 */
	private List<MovimientoComision> extraerMovimientoComision(Long idConciliacion) throws ConciliacionException {
		List<MovimientoComision> movsComision = new ArrayList<MovimientoComision>();

		// Comisiones
		List<MovimientoEstadoCuenta> movsEstadoCuenta = getMovimientosEstadoCuentaByCategoria(idConciliacion, ConciliacionConstants.CATEGORIA_ESTADO_CUENTA_COMISIONES);
		if (CollectionUtils.isNotEmpty(movsEstadoCuenta)) {
			for (MovimientoEstadoCuenta movEstadoCuenta : movsEstadoCuenta) {
				MovimientoComision movComision = MovimientoComisionBuilder
						.buildMovComisionFromMovEstadoCuenta(movEstadoCuenta, TipoMovimientoComisionEnum.COMISION);
				movsComision.add(movComision);
			}
		}

		// Iva
		movsEstadoCuenta = getMovimientosEstadoCuentaByCategoria(idConciliacion, ConciliacionConstants.CATEGORIA_ESTADO_CUENTA_IVA);
		if (CollectionUtils.isNotEmpty(movsEstadoCuenta)) {
			for (MovimientoEstadoCuenta movEstadoCuenta : movsEstadoCuenta) {
				MovimientoComision movComision = MovimientoComisionBuilder
						.buildMovComisionFromMovEstadoCuenta(movEstadoCuenta, TipoMovimientoComisionEnum.IVA_COMISION);
				movsComision.add(movComision);
			}
		}

		return movsComision;
	}


	/**
	 * Extrae los movimientos de tipo comision
	 * 
	 * @param idConciliacion
	 * @return
	 * @throws ConciliacionException
	 */
	private List<MovimientoDevolucion> extraerMovimientoDevolucion(Long idConciliacion) throws ConciliacionException {
		
		List<MovimientoEstadoCuenta> movsEstadoCuenta = getMovimientosEstadoCuentaByCategoria(idConciliacion, ConciliacionConstants.CATEGORIA_ESTADO_CUENTA_DEVOLUCIONES);
		
		List<MovimientoDevolucion> movsDevolucion = new ArrayList<MovimientoDevolucion>();
		if (CollectionUtils.isNotEmpty(movsEstadoCuenta)) {
			for (MovimientoEstadoCuenta movEstadoCuenta : movsEstadoCuenta) {
				EstadoCuentaCabecera cabecera = getEstadoCuentaCabecera(movEstadoCuenta.getIdEstadoCuenta());
				MovimientoDevolucion movComision = MovimientoDevolucionBuilder
						.buildMovimientoFromMovEstadoCuenta(movEstadoCuenta, idConciliacion, null, cabecera);
				movsDevolucion.add(movComision);
			}
		}

		return movsDevolucion;
	}


	/**
	 * Obtiene la cabecera a partir del estado de cuenta
	 * @param idEstadoCuenta
	 * @return
	 */
	private EstadoCuentaCabecera getEstadoCuentaCabecera(Long idEstadoCuenta) {
		EstadoCuentaCabecera cabecera = null;
		try {
			cabecera = this.mergeReporteHandler
				.getEstadoCuentaCabeceraRepository().findCabeceraByEstadoCuenta(idEstadoCuenta);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al consultar la cabecera del estado de cuenta", CodigoError.NMP_PMIMONTE_9999);
		}

		return cabecera;
	}


	/**
	 * Obtiene los movimientos de estado de cuenta por categoria
	 * 
	 * @param idConciliacion
	 * @param idCategoria
	 * @return
	 */
	private List<MovimientoEstadoCuenta> getMovimientosEstadoCuentaByCategoria(Long idConciliacion, Long idCategoria) throws ConciliacionException {

		// Codigos de Estado de Cuenta
		List<String> codigosEstadoCuenta = this.mergeReporteHandler.getEstadoCuentaHelper().getCodigosEstadoCuenta(idCategoria);

		// Obtiene los movimientos de estado de cuenta que contiene el codigo
		// correspondiente
		List<MovimientoEstadoCuenta> movimientosEstadoCuenta = this.mergeReporteHandler
				.getMovimientoEstadoCuentaRepository().findByConciliacionAndClaveLeyendaIn(idConciliacion, codigosEstadoCuenta);

		return movimientosEstadoCuenta;
	}

}
