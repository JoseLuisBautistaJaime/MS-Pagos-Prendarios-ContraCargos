/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.constans;

/**
 * @name ConciliacionConstants
 * @description Clase con valores con valores constantes utiliazados en los
 *              procesos relacionados a Conciliacion
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @createdDate 26/04/2019 15:33 hrs.
 */
public final class ConciliacionConstants {

	private ConciliacionConstants() {
		super();
	}

	/**
	 * Valor estatico para un tamanio de 10 caracteres
	 */
	public static final int ENTITY_VALIDATION_SIZE_VALUE_10 = 10;

	/**
	 * Valor estatico para un tamanio de 45 caracteres
	 */
	public static final int ENTITY_VALIDATION_SIZE_VALUE_45 = 45;

	/**
	 * Mensaje que es mostrado cuando s intenta ingresar una cadena de caracteres
	 * mayor a 10
	 */
	public static final String ENTITY_VALIDATION_SIZE_MESSAGE_10 = "Debe ingresar maximo 10 caracteres";

	/**
	 * Mensaje que es mostrado cuando s intenta ingresar una cadena de caracteres
	 * mayor a 45
	 */
	public static final String ENTITY_VALIDATION_SIZE_MESSAGE_45 = "Debe ingresar maximo 45 caracteres";

	/**
	 * Mensaje que es mostrado si se genera un error en la creacion del objeto
	 * reporte en BD
	 */
	public static final String REPORT_GENERATION_ERROR_MESSAGE = "Se ha generado un error durante la generacion del reporte";

	/**
	 * Mensaje que es mostrado cuando se realiza una consulta de manera exitosa
	 */
	public static final String MSG_SUCCESSFUL_MOVIMIENTOS_QUERY = "Consulta movimientos exitosa.";

	public static final class Validation {

		private Validation() {
			super();
		}

		/**
		 * Mensaje que es mostrado cuando no se encuentra informacion para los
		 * parametros de busqueda ingresados
		 */
		public static final String NO_INFORMATION_FOUND = "No se encontro informacion para los parametros ingresados";

		/**
		 * Mensaje que es mostrado cuando se genera un error de validacion de parametros
		 * de entrada
		 */
		public static final String VALIDATION_PARAM_ERROR = "Se genero un error de validacion";

	}

}
