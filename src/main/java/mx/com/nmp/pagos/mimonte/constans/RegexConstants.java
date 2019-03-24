/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.constans;

/**
 * @name RegexConstants
 * @description Clase que encapsula valores constantes que contienen expresiones
 *              regulares usadas en la aplicacion
 *
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 07/03/2019 16:46 hrs.
 * @version 0.1
 */
public abstract class RegexConstants {

	private RegexConstants() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * Regex para direcciones de correo electronico
	 */
	public static final String REGEX_EMAIL = "^(.+)@(.+)$";

	/**
	 * Regex para numeros telefonicos
	 */
	public static final String REGEX_PHONE_NUMBER = "^\\\\+(?:[0-9] ?){6,14}[0-9]$";

	/**
	 * Regex para excluir caracteres especiales
	 */
	public static final String REGEX_NO_SPECIAL_CHARACTERS = "[\\\\p{L}\\\\s]+";

}
