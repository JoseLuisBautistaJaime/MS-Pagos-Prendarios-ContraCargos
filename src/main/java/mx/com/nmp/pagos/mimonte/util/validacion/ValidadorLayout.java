/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util.validacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutLineaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutRequestDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;
import mx.com.nmp.pagos.mimonte.util.StringUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * @name ValidadorLayout
 * @description Clase que contiene validaciones para los objetos relacionados
 *              con layouts
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
		return validateLong(folio) && null != tipoLayout && tipoLayout == TipoLayoutEnum.PAGOS
				|| tipoLayout == TipoLayoutEnum.COMISIONES_MOV || tipoLayout == TipoLayoutEnum.COMISIONES_GENERALES
				|| tipoLayout == TipoLayoutEnum.DEVOLUCIONES;

	}

	/**
	 * Valida que LayoutDTO no sea vacío o nulo
	 * 
	 * @param layoutDTO
	 * @return
	 */
	public static boolean validateLayoutDTO(LayoutDTO layoutDTO) {
		return null != layoutDTO && layoutDTO.toString().length() != 0;
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
	 * Valida un objeto de tipo LayoutRequestDTO para que no sea nulo y sus
	 * atributos no sean nulos y tengan valores validos
	 * 
	 * @param layoutRequestDTO
	 * @return
	 */
	public static boolean validateSaveLayout(LayoutRequestDTO layoutRequestDTO) {
		return layoutRequestDTO != null
				&& (layoutRequestDTO.getFolio() != null && layoutRequestDTO.getFolio() > 0L)
				&& (layoutRequestDTO.getTipoLayout() != null
					&& (layoutRequestDTO.getTipoLayout() == TipoLayoutEnum.PAGOS ||
						layoutRequestDTO.getTipoLayout() == TipoLayoutEnum.COMISIONES_MOV ||
						layoutRequestDTO.getTipoLayout() == TipoLayoutEnum.COMISIONES_GENERALES ||
						layoutRequestDTO.getTipoLayout() == TipoLayoutEnum.DEVOLUCIONES))
				&& validaLineas(layoutRequestDTO.getLineas());
	}

	/**
	 * Valida que el folio e idLayout sea mayor a cero
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
		if (null != layoutLineaDTOs && layoutLineaDTOs.size() > 0) {
			for (LayoutLineaDTO layoutLineaDTO : layoutLineaDTOs) {
				if (validar(layoutLineaDTO)) {
					valor = true;
				} else {
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
		return layoutLineaDTO != null
				&& (layoutLineaDTO.getId() != null && layoutLineaDTO.getId() >= 0L)
				&& (StringUtil.isNotNullNorEmpty(layoutLineaDTO.getCuenta()))
				&& (StringUtil.isNotNullNorEmpty(layoutLineaDTO.getDepId()))
				&& (StringUtil.isNotNullNorEmpty(layoutLineaDTO.getLinea()))
				&& (StringUtil.isNotNullNorEmpty(layoutLineaDTO.getNegocio()))
				&& (StringUtil.isNotNullNorEmpty(layoutLineaDTO.getProyectoNMP()))
				&& (StringUtil.isNotNullNorEmpty(layoutLineaDTO.getUnidadOperativa()))
				&& (layoutLineaDTO.getMonto() != null && layoutLineaDTO.getMonto().compareTo(BigDecimal.ZERO) != 0);
	}

}
