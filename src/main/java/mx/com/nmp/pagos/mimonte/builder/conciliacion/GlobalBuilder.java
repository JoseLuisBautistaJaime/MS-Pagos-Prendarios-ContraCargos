/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.math.BigDecimal;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.GlobalDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Global;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;
import mx.com.nmp.pagos.mimonte.util.MergeMathUtil;

/**
 * @name GlobalBuilder
 * @description Clase que construye objetos en base a otros relacionados con
 *              global
 * @author Jose Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 29/04/2019 19:47 hrs.
 * @version 0.1
 */
public abstract class GlobalBuilder {

	public GlobalBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye un set de objetos de tipo Set<GlobalDTO> a partir de un set de tipo
	 * Set<Global>.
	 * @param globalSet
	 * @return
	 */
	public static GlobalDTO buildGlobalDTOFromGlobal(Global global) {
		GlobalDTO globalDTO = null;
		if (global != null) {
			globalDTO = new GlobalDTO();
			globalDTO.setId(global.getId());
			globalDTO.setFechaOperacion(global.getFecha());
			globalDTO.setTotalMovimientos(null);
			globalDTO.setTotalPartidas(null);
			globalDTO.setImporteMidas(global.getImporteMidas());
			globalDTO.setImporteProveedor(global.getImporteProveedor());
			globalDTO.setImporteBanco(global.getImporteBanco());
			globalDTO.setImporteDevoluciones(global.getImporteDevoluciones());
			globalDTO.setDiferenciasProveedorVsMidas(global.getDiferenciaProveedorMidas());
			globalDTO.setDiferenciasProveedorVsBanco(global.getDiferenciaProveedorBanco());
		}
		return globalDTO;
	}


	public static Global updateGlobal(Global global, ReportesWrapper reportesWrapper) {
		if (global == null) {
			global = new Global();
			global.setConciliacion(new Conciliacion(reportesWrapper.getIdConciliacion()));
		}

		// Reporte
		Reporte reporteMidas = reportesWrapper.getReporteMidas();
		Reporte reporteProveedor = reportesWrapper.getReporteProveedor();
		Reporte reporteEstadoCuenta = reportesWrapper.getReporteEstadoCuenta();
		
		List<MovimientoMidas> movimientosMidas = reportesWrapper.getByTipoReporte(TipoReporteEnum.MIDAS);
		List<MovimientoProveedor> movimientosProveedor = reportesWrapper.getByTipoReporte(TipoReporteEnum.PROVEEDOR);
		List<MovimientoEstadoCuenta> movimientosEstadoCuenta = reportesWrapper.getByTipoReporte(TipoReporteEnum.ESTADO_CUENTA);

		// Actualiza seccion global
		if (reporteProveedor != null) {
			// Reporte de proveedor transaccional / Consulta reporte de procesos nocturnos - formato: DD/MM/AA)
			global.setFecha(reporteProveedor.getCreatedDate());
			//(Total de movimientos reportados en el reporte del proveedor transaccional)
			global.setMovmientos(movimientosProveedor.size());
		}
		
		if (reporteMidas != null) {
			// (Total de partidas obtenidas del reporte de procesos nocturnos)
			global.setPartidas(movimientosMidas.size());
		}

		global.setImporteMidas(MergeMathUtil.getImporteMidas(movimientosMidas));
		global.setImporteProveedor(MergeMathUtil.getImporteProveedor(movimientosProveedor));
		global.setImporteBanco(MergeMathUtil.getImporteBanco(movimientosEstadoCuenta));
		global.setDevoluciones(MergeMathUtil.getDevolucionesEstadoCuenta(movimientosEstadoCuenta));
		global.setDiferenciaProveedorMidas(MergeMathUtil.getDiferenciaProveedorMidas(movimientosProveedor, movimientosMidas));
		global.setDiferenciaProveedorBanco(MergeMathUtil.getDiferenciaProveedorBanco(movimientosProveedor, movimientosEstadoCuenta));
		
		return global;
	}

}
