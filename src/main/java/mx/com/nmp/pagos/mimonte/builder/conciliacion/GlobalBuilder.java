/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.GlobalDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Global;

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

}
