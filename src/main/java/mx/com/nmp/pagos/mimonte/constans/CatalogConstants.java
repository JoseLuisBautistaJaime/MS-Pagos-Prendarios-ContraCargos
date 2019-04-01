/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.constans;

/**
 * Nombre: CatalogConstants
 * 
 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */

public final class CatalogConstants {

	/**
	 * Descripcion corta del catalogo ET; Estatus de Tarjeta
	 */
	public static final String ESTATUS_TARJETA = "ET";

	/**
	 * Descripcion corta del catalogo ET; Estatus de Pago;
	 */
	public static final String ESTATUS_PAGO = "EP";

	/**
	 * Identificador del catalogo TT; Tipo de Tarjeta
	 */
	public static final String TIPO_TARJETA = "TT";

	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String CONT_MSG_SUCCESS = "Consulta Exitosa.";

	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String CONT_MSG_SUCCESS_SAVE = "Alta Exitosa.";

	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String CONT_MSG_SUCCESS_UPDATE = "Actualización Exitosa.";

	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String CONT_MSG_SUCCESS_DELETE = "Baja Exitosa.";

	/**
	 * El nombre del header donde se especificara el nombre de usuario qque realiza
	 * la operacion
	 */
	public static final String REQUEST_USER_HEADER = "requestUser";

	/**
	 * Mensaje que es mostrado en cuando se lanza un error de validacion
	 */
	public static final String CATALOG_VALIDATION_ERROR = "Ocurrio un error de validacion";

	/**
	 * Mensaje que es mostrado cuando un id especificado como parametro ya sea para
	 * una peticion GET o DELETE no es encontrado
	 */
	public static final String CATALOG_ID_NOT_FOUND = "El id ingresado no fue encontrado";

	/**
	 * Mensaje que es mostrado cuando un nombre especificado como parametro ya sea para
	 * una peticion no es encontrado
	 */
	public static final String CATALOG_NOMBRE_NOT_FOUND = "El id ingresado no fue encontrado";
	
	/**
	 * Mensaje que es lanzado cuando se intenta dar de alta un codigo de estado de
	 * cuenta con una entidad y categoria repetida
	 */
	public static final String CODIGO_E_C_ALREADY_EXISTS = "Ya existe un codigo con esa entidad y esa categoria";

	/**
	 * Mensaje que es mostrado cuando se intenta dar de alta una afiliacion con un
	 * numero que ya existe
	 */
	public static final String NUMERO_AFILIACION_ALREADY_EXISTS = "Ya existe una afiliacion con ese numero";

	/**
	 * Mensaje que es mostrado cuando se genera un error inesperado durante una
	 * operacion con un objeto catalogo
	 */
	public static final String UNKNOWN_ERROR = "Ocurrio un error inesperado";

	/**
	 * Mensaje que es mostrado cuando se intenta ingresr un campo que contiene
	 * caracteres epeciales
	 */
	public static final String SPECIAL_CHARACTERS_WERE_FOUND = "El campo no puede contener caracteres especiales";

	/**
	 * Mensaje que es mostrado cuando no se encuentran reglas para el filtro de
	 * busqueda especificado
	 */
	public static final String NO_FILTER_DEFINITION_WAS_FOUND = "No existe una definicion para el filtro especificado";

	/**
	 * Mensaje que es mostrado cuando se intenta insertar una entidad con un nombre
	 * que ya existe
	 */
	public static final String ENTIDAD_NOMBRE_ALREADY_EXISTS = "Ya existe una entidad con ese nombre";

	/**
	 * Mensaje que es mostrado cuando uno o mas ids de catalogo de cuentas que se intentan ingresar no existen
	 */
	public static final String ID_CUENTA_DOES_NOT_EXISTS = "Uno o mas ids de cuenta no existen";
	
	/**
	 * Mensaje que es mostrado cuando uno o mas ids de catalogo de contactos que se intentan ingresar no existen
	 */
	public static final String ID_CONTACTO_DOES_NOT_EXISTS = "Uno o mas ids de cuenta no existen";
	
	/**
	 * Constructor privado, ya que será una clase para constantes por lo tanto no
	 * debe ser instanciada.
	 */
	private CatalogConstants() {
		super();
	}
}
