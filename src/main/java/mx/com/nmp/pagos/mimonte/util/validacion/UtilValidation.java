/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util.validacion;

import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;

/**
 * @name UtilValidation
 * @description Interface que tiene metodos de validacion genericos sobre
 *              patrones de cadenas etc.
 * @author: Ismael Flores iaguilar@quarksoft.net
 * @version: 0.1
 */
public interface UtilValidation {

	/**
	 * Valida que una cadena de caracteres contenga caracteres alfanumericos
	 * 
	 * @param cadena
	 * @return
	 */
	public static boolean validaCadenaAlfanumerica(String cadena) {
		return cadena.matches(ConciliacionConstants.ALPHANUMERIC_PATTERN);
	}

}
