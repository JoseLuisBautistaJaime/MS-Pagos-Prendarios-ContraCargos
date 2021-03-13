/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.GlobalDTO;
import mx.com.nmp.pagos.mimonte.model.ComisionProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Global;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.util.CodigosEdoCuentaMap;
import mx.com.nmp.pagos.mimonte.util.ConciliacionMathUtil;

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
			globalDTO.setTotalMovimientos(global.getMovimientos() != null ? global.getMovimientos().longValue() : 0);
			globalDTO.setTotalPartidas(global.getPartidas() != null ? global.getPartidas().longValue() : 0);
			globalDTO.setImporteMidas(global.getImporteMidas());
			globalDTO.setImporteProveedor(global.getImporteProveedor());
			globalDTO.setImporteBanco(global.getImporteBanco());
			globalDTO.setImporteDevoluciones(global.getImporteDevoluciones());
			globalDTO.setDiferenciasProveedorVsMidas(global.getDiferenciaProveedorMidas());
			globalDTO.setDiferenciasProveedorVsBanco(global.getDiferenciaProveedorBanco());
			globalDTO.setImporteBonificaciones(global.getImporteBonificaciones());
		}
		return globalDTO;
	}


	public static Global updateGlobal(Global global, List<MovimientoMidas> movsMidas,
			List<MovimientoProveedor> movsProveedor, List<MovimientoEstadoCuenta> movsEstadoCuenta,
			CodigosEdoCuentaMap codigosEdoCuenta, ComisionProveedor comisionProveedor) {

		//(Total de movimientos reportados en el reporte del proveedor transaccional)
		global.setMovimientos(movsProveedor != null ? movsProveedor.size() : 0);
		
		// (Total de partidas obtenidas del reporte de procesos nocturnos)
		global.setPartidas(movsMidas != null ? movsMidas.size() : 0);

		int totalOperaciones = movsProveedor.size();
		
		global.setImporteMidas(ConciliacionMathUtil.getImporteMidas(movsMidas));
		global.setImporteProveedor(ConciliacionMathUtil.getImporteProveedor(movsProveedor, comisionProveedor));
		global.setImporteBanco(ConciliacionMathUtil.getImporteBanco(movsEstadoCuenta, codigosEdoCuenta, comisionProveedor, totalOperaciones));
		global.setImporteDevoluciones(ConciliacionMathUtil.getDevolucionesEstadoCuenta(movsEstadoCuenta, codigosEdoCuenta));
		global.setDiferenciaProveedorMidas(ConciliacionMathUtil.getDiferenciaProveedorMidas(movsProveedor, movsMidas, comisionProveedor));
		global.setDiferenciaProveedorBanco(ConciliacionMathUtil.getDiferenciaProveedorBanco(movsProveedor, movsEstadoCuenta, codigosEdoCuenta, comisionProveedor, totalOperaciones));
		
		return global;
	}

	public static Global buildGlobalDTOFromConciliacion(Conciliacion conciliacion) {
		Global global = new Global();
		global.setConciliacion(conciliacion);
		global.setFecha(new Date());
		global.setDevoluciones(0);
		global.setDiferenciaProveedorBanco(new BigDecimal(0));
		global.setDiferenciaProveedorMidas(new BigDecimal(0));
		global.setImporteBanco(new BigDecimal(0));
		global.setImporteDevoluciones(new BigDecimal(0));
		global.setImporteMidas(new BigDecimal(0));
		global.setImporteProveedor(new BigDecimal(0));
		global.setMonto(new BigDecimal(0));
		global.setMovimientos(0);
		global.setPartidas(0);
		return global;
	}

}
