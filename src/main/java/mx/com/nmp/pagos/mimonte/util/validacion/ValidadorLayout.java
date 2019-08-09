/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util.validacion;

import java.math.BigDecimal;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutLineaDTO;
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
	public static boolean validateConsultaUnLayout(Long folio, TipoLayoutEnum tipoLayout) {
			return validateLong(folio) &&  
					null != tipoLayout &&
					tipoLayout == TipoLayoutEnum.PAGOS || tipoLayout == TipoLayoutEnum.COMISIONES_MOV
					|| tipoLayout == TipoLayoutEnum.COMISIONES_GENERALES || tipoLayout == TipoLayoutEnum.DEVOLUCIONES;
		
	}
	
	/**
	 * Valida que LayoutDTO no sea vacío o nulo
	 * 
	 * @param layoutDTO
	 * @return
	 */
	public static boolean validateLayoutDTO(LayoutDTO layoutDTO) {
		return null != layoutDTO && layoutDTO.toString().length()!=0;
	}
	
	/**
	 * Valida que el folio sea mayor a cero
	 * 
	 * @param folio
	 * @return
	 */
	public static boolean validateLong(Long folio) {
		return null != folio && folio > 0L;
	}
	/**
	 * Valida que la lista de layoutDTOs sea mayor a cero
	 * 
	 * @param layoutDTOs
	 * @return
	 */
	public static boolean validateLayoutDTOs(List<LayoutDTO> layoutDTOs) {
		return null != layoutDTOs && layoutDTOs.size() > 0;
	}
	/**
	 * Valida parámetros requeridos del LayoutDTO
	 * 
	 * @param layoutDTO
	 * @return
	 */
	public static boolean validateSaveLayout(LayoutDTO layoutDTO) {
		return null != layoutDTO &&
				(null != layoutDTO.getFolio() && layoutDTO.getFolio()>0L) &&
				(null != layoutDTO.getTipoLayout() &&
				(layoutDTO.getTipoLayout() == TipoLayoutEnum.PAGOS) ||
				(layoutDTO.getTipoLayout() == TipoLayoutEnum.COMISIONES_MOV) ||
				(layoutDTO.getTipoLayout() == TipoLayoutEnum.COMISIONES_GENERALES)) ||
				(layoutDTO.getTipoLayout() == TipoLayoutEnum.DEVOLUCIONES) &&
				validaLineas(layoutDTO.getLineas());
				
	}
    /**
     *Valida que el folio e idLayout sea mayor a cero
     *  
     * @param folio
     * @param idLayout
     * @return
     */
	public static boolean validateDeleteLayout(Long folio, Long idLayout) {
		return validateLong(folio) && validateLong(idLayout);
    }
	
	/**
	 * Valida las líneas de un Layout
	 * 
	 * @param layoutLineaDTOs
	 * @return
	 */
	public static boolean validaLineas(List<LayoutLineaDTO> layoutLineaDTOs) {
		boolean valor = false;
		if(null != layoutLineaDTOs && layoutLineaDTOs.size()>0) {
				for(LayoutLineaDTO layoutLineaDTO : layoutLineaDTOs) {
					if(validar(layoutLineaDTO)) {
						valor = true;
					}
					else {
						valor = false;
						break;
					}
				}
		}
		return valor;
	}
	
	/**
	 * Valida los campos requeridos de LayoutLineaDTO
	 *  
	 * @param layoutLineaDTO
	 * @return
	 */
	public static boolean validar(LayoutLineaDTO layoutLineaDTO) {
		return null != layoutLineaDTO &&
			   (null != layoutLineaDTO.getId() && layoutLineaDTO.getId()>=0L) && 
			   (null != layoutLineaDTO.getLinea() && layoutLineaDTO.getLinea().length()!=0) && 
			   (null != layoutLineaDTO.getCuenta() && layoutLineaDTO.getCuenta().length()!=0) && 
			   (null != layoutLineaDTO.getMonto() && layoutLineaDTO.getMonto().compareTo(BigDecimal.ZERO) != 0);
	}
	
	
}


