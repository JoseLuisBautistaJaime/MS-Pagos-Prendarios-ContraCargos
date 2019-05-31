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

	/**
	 * Valor que se setea por default para el alta de la conciliacion
	 */
	public static final String EN_PROCESO = "En Proceso";

	/**
	 * Valor que se setea por default para el alta de la subConciliacion;
	 */
	public static final String CREADA = "Creada";

	/**
	 * Mensaje mostrado para un alta exitosa.
	 */
	public static final String SAVE_SUCCESSFUL = "Alta exitosa.";

	/**
	 * Mensaje mostrado para una consulta exitosa.
	 */
	public static final String SUCCESSFUL_SEARCH = "Consulta exitosa.";

	/**
	 * Mensaje mostrado para una consulta exitosa.
	 */
	public static final String SUCCESSFUL_UPDATE = "Actualización exitosa.";

	/**
	 * Mensaje que es mostrado cuando se intenta buscar o asociar un id de conciliacion que no existe
	 */
	public static final String CONCILIACION_ID_NOT_FOUND = "No se encontro el id de conciliacion ingresado";
	
	/**
	 * Se regresa cuando no se encuentra informacion en una busqueda relacionada con los procesos de conciliacion en general
	 */
	public static final String INFORMATION_NOT_FOUND = "No se encontro informacion relacionada con los parametros de busqueda";
	
	/**
	 * Mensaje que es mostrado cuando no es posible obtener una conciliacion de acuerdo a los parametros recibidos
	 */
	public static final String CONCILIACION_NOT_FOUND_FOR_SUCH_PARAMS = "No se encontro una conciliacion para los parametros especificados";
	
	/**
	 * Mensaje que es mostrado cuando no es posible obtener una comision de acuerdo a los parametros recibidos
	 */
	public static final String COMISION_NOT_FOUND_FOR_SUCH_PARAMS = "No se encontro una conciliacion para los parametros especificados";
	
	/**
	 * Mensaje que es mostrado cuando se intenta eliminar una comision y esta no puede ser eliminada por regla de negocio
	 */
	public static final String COMISION_CANT_BE_DELETED = "La comision no puede ser eliminada ya que fue no fue dada de alta desde la aplicacion.";
	
	/**
	 * Subclase de cosntantes que se mapearan de propiedades del archivo de
	 * configuracion de spring
	 * 
	 * @author user
	 *
	 */
	public static final class ConstantProperties {
		private ConstantProperties() {
			super();
		}

		/**
		 * Valor del iva (IMPUESTO AL VALOR AGREGADO)
		 */
		public static final String IVA = "${mimonte.variables.iva}";
	}

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

		/**
		 * Mensaje que es mostrado cuando se intenta ingresar una fecha inicial
		 * posterior a la fecha final
		 */
		public static final String INITIAL_DATE_AFTER_FINAL_DATE = "La fecah inicial es posterior a la fecha final";

		/**
		 * Mensaje que es mostrado cuando no se encuentra una abreviatura y/o
		 * coincidencia con un id de catalogo
		 */
		public static final String NO_CATALOG_INFORMATION = "No se encontro informacion de uno o mas catalogos";

	}

}
