/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.Set;

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
	
	public static GlobalDTO buildGlobalDTOFromGlobal(Global global) {
		GlobalDTO globalDTO = null;
		if(global != null) {
			globalDTO = new GlobalDTO();
			globalDTO.setId(global.getId());
			globalDTO.setFechaOperacion(global.getFecha());
			globalDTO.setTotalMovimientos(null);// se cualcula en tiempo de ejecucion
			globalDTO.setTotalPartidas(null); //se cualcula en tiempo de ejecucion
			globalDTO.setImporteMidas(global.getImporteMidas());
			globalDTO.setImporteProveedor(global.getImporteProveedor());
			globalDTO.setImporteBanco(global.getImporteBanco());
			globalDTO.setImporteDevoluciones(global.getImporteDevoluciones());
			globalDTO.setDiferenciasProveedorVsMidas(global.getDiferenciaProveedorMidas());
			globalDTO.setDiferenciasProveedorVsBanco(global.getDiferenciaProveedorBanco());
		}
		return globalDTO;
		
	}
	
	public static Global buildGlobalFromGlobalDTO(GlobalDTO globalDTO) {
		Global global = null;
		if(globalDTO != null) {
			global = new Global();
			global.setId(globalDTO.getId());
			global.setFecha(globalDTO.getFechaOperacion());
//			global Se calcula en tiempo de ejecucion
//			global Se calcula en tiempo de ejecucion
			global.setImporteMidas(globalDTO.getImporteMidas());
			global.setImporteProveedor(globalDTO.getImporteProveedor());
			global.setImporteBanco(globalDTO.getImporteBanco());
			global.setImporteDevoluciones(globalDTO.getImporteDevoluciones());
			global.setDiferenciaProveedorMidas(globalDTO.getDiferenciasProveedorVsMidas());
			global.setDiferenciaProveedorBanco(globalDTO.getDiferenciasProveedorVsBanco());
		}
		return global;
		
	}
	
	
	/**
	 * Construye un set de objetos de tipo Set<GlobalDTO> a partir de un set de tipo Set<Global>.
	 * @param globalSet
	 * @return
	 */
	public static GlobalDTO buildGlobalDTOSetFromGlobalSet(Set<Global> globalSet){
		GlobalDTO globalDTOSet = null;
		if(globalSet != null && !globalSet.isEmpty()) {
			globalDTOSet = new GlobalDTO();
			for(Global gS: globalSet) {
				globalDTOSet.setId(gS.getId());
				globalDTOSet.setFechaOperacion(gS.getFecha());
				globalDTOSet.setTotalMovimientos(null);
				globalDTOSet.setTotalPartidas(null);
				globalDTOSet.setImporteMidas(gS.getImporteMidas());
				globalDTOSet.setImporteProveedor(gS.getImporteProveedor());
				globalDTOSet.setImporteBanco(gS.getImporteBanco());
				globalDTOSet.setImporteDevoluciones(null);
				globalDTOSet.setDiferenciasProveedorVsMidas(gS.getDiferenciaProveedorMidas());
				globalDTOSet.setDiferenciasProveedorVsBanco(gS.getDiferenciaProveedorBanco());
				break;
			}
		}
		return globalDTOSet;
	}

}
