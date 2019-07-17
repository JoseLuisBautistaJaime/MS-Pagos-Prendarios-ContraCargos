/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util.validacion;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutDTO;

import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;

/**
 * @name ValidadorLayout
 * @description Clase que contiene validaciones para los objetos relacionados con layouts
 * @author Quarksoft
 * @creationDate 15/07/2019 15:37 hrs.
 * @version 0.1
 */
public interface ValidadorLayout {

	/**
	 * Valida que el folio sea mayor a cero y que el layout sea el adecuado. 
	 * 
	 * @param folio
	 * @param tipoLayout
	 * @return
	 */
	public static boolean validateConsultaUnLayout(Long folio, String tipoLayout) {
			return folio>0L && tipoLayout.equals(TipoLayoutEnum.PAGOS.toString()) || tipoLayout.equals(TipoLayoutEnum.COMISIONES_MOV.toString())
					|| tipoLayout.equals(TipoLayoutEnum.COMISIONES_GENERALES.toString()) || tipoLayout.equals(TipoLayoutEnum.DEVOLUCIONES.toString());
		
	}
	
	/**
	 * Valida que LayoutDTO no sea vacío o nulo
	 * 
	 * @param layoutDTO
	 * @return
	 */
	public static boolean validateLayoutDTO(LayoutDTO layoutDTO) {
		return layoutDTO != null && !layoutDTO.toString().equals("");
	}
	
	/**
	 * Valida que el folio sea mayor a cero
	 * 
	 * @param folio
	 * @return
	 */
	public static boolean validateLong(Long folio) {
		return folio > 0L;
	}
	/**
	 * Valida que la lista de layoutDTOs sea mayor a cero
	 * 
	 * @param layoutDTOs
	 * @return
	 */
	public static boolean validateLayoutDTOs(List<LayoutDTO> layoutDTOs) {
		return layoutDTOs.size() > 0;
	}
	/**
	 * Valida parámetros requeridos del LayoutDTO
	 * 
	 * @param layoutDTO
	 * @return
	 */
	public static boolean validateSaveLayout(LayoutDTO layoutDTO) {
		return layoutDTO.getFolio() > 0L && !layoutDTO.getTipoLayout().toString().equals("") && layoutDTO.getLineas().size() > 0;
	}
    /**
     *Valida que el folio e idLayout sea mayor a cero
     *  
     * @param folio
     * @param idLayout
     * @return
     */
	public static boolean validateDeleteLayout(Long folio, Long idLayout) {
		return folio > 0L && idLayout > 0L;
	}
	
}
