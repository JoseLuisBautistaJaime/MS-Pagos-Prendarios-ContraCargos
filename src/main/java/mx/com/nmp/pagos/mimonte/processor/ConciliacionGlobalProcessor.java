/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import java.util.List;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.GlobalBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.ComisionProveedor;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Global;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;
import mx.com.nmp.pagos.mimonte.observer.MergeReporteHandler;
import mx.com.nmp.pagos.mimonte.util.CodigosEdoCuentaMap;

/**
 * Nombre: ConciliacionGlobalProcessor
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

		// Se actualiza la seccion global para la conciliacion actual
		actualizarSeccionGlobal(reportesWrapper.getIdConciliacion(), reportesWrapper.getCorresponsal());

		// Se actualiza la seccion global para conciliacion anteriores que sufrieron afectaciones en los movimientos transito
		if (reportesWrapper.getIdsConciliacionesActualizar() != null && reportesWrapper.getIdsConciliacionesActualizar().size() > 0) {
			for (Long idConciliacionAnterior : reportesWrapper.getIdsConciliacionesActualizar()) {
				actualizarSeccionGlobal(idConciliacionAnterior, reportesWrapper.getCorresponsal());
			}
		}

		// Siguiente procesador
		processNext(reportesWrapper);
	}


	/**
	 * Actualiza la seccion global
	 * Para el caso de OXXO se considera el % de comision
	 * @param idConciliacion
	 * @param corresponsal
	 */
	private void actualizarSeccionGlobal(Long idConciliacion, CorresponsalEnum corresponsal) throws ConciliacionException {
		// Obtener seccion global
		Global global = this.mergeReporteHandler.getGlobalRepository().findByIdConciliacion(idConciliacion);
		if (global == null) {
			global = new Global();
			global.setConciliacion(new Conciliacion(idConciliacion));
		}

		// Se obtiene la comision e iva por operaciones proveedor para oxxo
		ComisionProveedor comisionProveedor = getComisionProveedor(corresponsal);

		// Obtiene los movimientos de los reportes en base a la conciliacion
		List<MovimientoMidas> movsMidas = getMovimientosMidasByConciliacion(idConciliacion);
		List<MovimientoProveedor> movsProveedor = getMovimientosProveedorByConciliacion(idConciliacion);
		List<MovimientoEstadoCuenta> movsEstadoCuenta = getMovimientosEstadoCuentaByConciliacion(idConciliacion);
		//List<MovimientoBonificacion> movsBonificacion
		
		Entidad entidadConciliacion = getEntidadByConciliacion(idConciliacion);
		Reporte reporteProveedor = getReporteProveedor(idConciliacion);

		CodigosEdoCuentaMap codigosEdoCuenta = this.mergeReporteHandler.getEstadoCuentaHelper().getCodigosEdoCuentaMap(entidadConciliacion.getId());

		// Actualizar seccion global
		global = GlobalBuilder.updateGlobal(global, movsMidas, movsProveedor, movsEstadoCuenta, codigosEdoCuenta, comisionProveedor);

		// Reporte de proveedor transaccional / Consulta reporte de procesos nocturnos - formato: DD/MM/AA)
		if (reporteProveedor != null) {
			global.setFecha(reporteProveedor.getCreatedDate());
		}

		// Guardar global en la bd
		this.mergeReporteHandler.getGlobalRepository().saveAndFlush(global);
	}


	/**
	 * Consulta la entidad relacionada a la conciliacion
	 * @param idConciliacion
	 * @return
	 */
	private Entidad getEntidadByConciliacion(Long idConciliacion) {
		Entidad entidad = null;
		try {
			entidad = this.mergeReporteHandler.getConciliacionRepository()
					.findEntidadByConciliacion(idConciliacion);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al consultar la entidad relacionada a la conciliacion",
					CodigoError.NMP_PMIMONTE_0005);
		}
		return entidad;
	}


	/**
	 * Obtiene el reporte a partir de la conciliacion
	 * @param idConciliacion
	 * @return
	 */
	private Reporte getReporteProveedor(Long idConciliacion) {
		Reporte reporte = null;
		try {
			reporte = this.mergeReporteHandler.getReporteRepository()
					.findLastByIdConciliacionAndTipo(idConciliacion, TipoReporteEnum.PROVEEDOR);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al consultar el reporte proveedor para la conciliacion",
					CodigoError.NMP_PMIMONTE_0005);
		}
		return reporte;
	}

}
