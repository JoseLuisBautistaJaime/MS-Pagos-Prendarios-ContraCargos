/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util.validacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;

/**
 * @name ValidadorConciliacion
 * @description Clase que contiene validaciones para los objetos de conciliacion
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 29/04/2019 12:37 hrs.
 * @version 0.1
 */
public interface ValidadorConciliacion {

	/**
	 * Valida que los parametros de entrada de un objeto
	 * CommonConciliacionRequestDTO sean correctos
	 * 
	 * @param commonConciliacionRequestDTO
	 * @return
	 */
	public static boolean validateCommonConciliacionRequestDTO(
			final CommonConciliacionRequestDTO commonConciliacionRequestDTO) {
		return (null != commonConciliacionRequestDTO && null != commonConciliacionRequestDTO.getFolio()
				&& null != commonConciliacionRequestDTO.getPagina()
				&& null != commonConciliacionRequestDTO.getResultados() && commonConciliacionRequestDTO.getFolio() > 0
				&& commonConciliacionRequestDTO.getResultados() > 0 && commonConciliacionRequestDTO.getPagina() >= 0);
	}

}
