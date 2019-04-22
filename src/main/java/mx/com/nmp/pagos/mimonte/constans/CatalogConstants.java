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
	 * Mensaje que es mostrado cuando un nombre especificado como parametro ya sea
	 * para una peticion no es encontrado
	 */
	public static final String CATALOG_NOMBRE_NOT_FOUND = "El id ingresado no fue encontrado";

	/**
	 * Mensaje que es lanzado cuando se intenta dar de alta un codigo de estado de
	 * cuenta con una entidad y categoria repetida
	 */
	public static final String CODIGO_E_C_ALREADY_EXISTS = "Ya existe un codigo con esa entidad y esa categoria";
	
	/**
	 * Mensaje que es lanzado cuando se intenta dar de alta un codigo de estado de
	 * cuenta con una entidad, categoria y codigo repetido
	 */
	public static final String CODIGO_ALREADY_EXISTS = "Ya existe un codigo con esas caracteristicas";

	/**
	 * Mensaje que es lanzado cuando se intenta actualizar un codigo de estado de
	 * cuenta que no existe
	 */
	public static final String CODIGO_E_C_DOESNT_EXISTS = "El codigo que desea actualizar no existe";

	/**
	 * Mensaje que es mostrado cuando se intenta dar de alta una afiliacion con un
	 * numero que ya existe
	 */
	public static final String NUMERO_AFILIACION_ALREADY_EXISTS = "Ya existe una afiliacion con ese numero";

	/**
	 * Mensaje que es mostrado cuando una o mas afiliaciones que se desean ingresar
	 * no existen
	 */
	public static final String NUMERO_AFILIACION_DOESNT_EXISTS = "Una o mas afiliacion que desea ingresar no existen";

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
	 * Mensaje que es mostrado cuando parametro ya sea para una peticion GET o
	 * DELETE no es encontrado
	 */
	public static final String CATALOG_ID_AND_NAME_AND_EMAIL_NOT_FOUND = "No hay registros para los parametros ingresados.";

	/**
	 * Mensaje que es mostrado cuando parametro ya sea para una peticion GET o
	 * DELETE no es encontrado
	 */
	public static final String CATALOG_EMAIL_FORMAT_IS_NOT_CORRECT = "No es el formato correcto para el correo electronico.";

	/**
	 * Mensaje que es mostrado si el correo electronico existe.
	 */
	public static final String CATALOG_THE_EMAIL_THAT_WANTS_TO_ADD_ALREADY_EXISTS = "El correo electronico que desea guardar ya existe.";

	/**
	 * Mensaje que es mostrado si el correo electronico a actualizar ya existe.
	 */
	public static final String CATALOG_THE_EMAIL_THAT_WANTS_TO_ADD_ALREADY_NOT_EXISTS = "El correo electronico que desea actualizar ya existe.";

	/**
	 * Mensaje que es mostrado si el id a actualizar no existe.
	 */
	public static final String CATALOG_THE_ID_TO_UPDATE_DOES_NOT_EXIST = "El id a actualizar no existe.";

	/**
	 * Mensaje que es mostrado cuando se intenta insertar una entidad con un nombre
	 * y description que ya existe
	 */
	public static final String ENTIDAD_ALREADY_EXISTS = "La entidad que desea crear ya se encuentra registrada";

	/**
	 * Mensaje que es mostrado cuando uno o mas ids de catalogo de cuentas que se
	 * intentan ingresar no existen
	 */
	public static final String ID_CUENTA_DOES_NOT_EXISTS = "Uno o mas ids de cuenta no existen";

	/**
	 * Mensaje que es mostrado cuando uno o mas ids de catalogo de contactos que se
	 * intentan ingresar no existen
	 */
	public static final String ID_CONTACTO_DOES_NOT_EXISTS = "Uno o mas ids de contacto no existen";

	/**
	 * Mensaje que es mostrado si el idTipoContacto no existe.
	 */
	public static final String CATALOG_THE_CONTACT_TYPE_ID_DOES_NOT_EXIST = "El id de tipo contacto no existe.";

	/**
	 * Mensaje que es mostrado cuando se desea crear una cuenta con un numero que ya
	 * existe
	 */
	public static final String NUMERO_CUENTA_ALREADY_EXISTS = "Ya existe una cuenta con ese numero";

	/**
	 * Mensaje que es mostrado cuando se genera un error con un patron REGEX
	 */
	public static final String ERROR_WITH_REGEX_PATTERN = "Se genero un error con un patron regex";

	/**
	 * Mensaje que es mostrado cuando se genera un error de constraints
	 */
	public static final String CONSTRAINT_ERROR_MESSAGE = "Uno o mas ids especificados no existen";

	/**
	 * Mensaje que es mostrado cuando se desea modificar una entidad con un id que
	 * no existe
	 */
	public static final String NO_ENTIDAD_FOUND = "No existe la entidad especificada";

	/**
	 * Mensaje que es mostrado cuando se desea modificar una categoria con un id que
	 * no existe
	 */
	public static final String NO_CATEGORIA_FOUND = "No existe la categoria especificada";

	/**
	 * Mensaje que es mostrado cuando no se encuentra el catalogo solicitado
	 */
	public static final String CATALOG_NOT_FOUND = "El o los catalogo(s) solicitado(s) no fueron encontrado(s)";

	/**
	 * Mensaje que es mostrado cuando se intenta eliminar una afiliacion con una o
	 * mas cuentas asociadas u otras claves foraneas que dependen de esta
	 */
	public static final String AFILIACION_HAS_CUENTAS_ASSOCIATES = "La afiliacion tiene una o mas cuentas asocidas";

	/**
	 * Constante con el nombre del tipo de contacto de entidad
	 */
	public static final String ENTIDAD_TIPO_CONTACTO_NAME = "Contacto Entidad";

	/**
	 * Mensaje que es mostrado cuando se desea ingresar una relacion
	 * cuenta-afiliacion que no existe en el catalogo
	 */
	public static final String CUENTA_AFILIACION_RELATIONSHIP_DOESNT_EXISTS = "No existe una o mas asocianes cuenta-afiliacion";

	/**
	 * Mensaje que es mostrado cuando se desea eliminar un contacto que esta
	 * asociado a una entidad
	 */
	public static final String CONTCATO_HAS_ENTIDADES_ASSOCIATED = "El contacto esta asociado a una entidad";

	/**
	 * Mensaje que es mostrado cuando se genera un error de tipo no resultado unico
	 * para conulta
	 */
	public static final String DB_INCONSISTENCY_EXCEPTION = "Ha ocurrido un error de inconsistencia de datos";

	/**
	 * Mensaje que es mostrado cuando no se encuentran cuentas en el catalogo
	 */
	public static final String THERE_ARE_NO_ACCOUNTS = "No existen cuentas en el catalogo";

	/**
	 * Constructor privado, ya que será una clase para constantes por lo tanto no
	 * debe ser instanciada.
	 */
	private CatalogConstants() {
		super();
	}
}
