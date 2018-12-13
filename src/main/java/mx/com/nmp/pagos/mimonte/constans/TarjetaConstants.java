package mx.com.nmp.pagos.mimonte.constans;

public final class TarjetaConstants {

	private TarjetaConstants() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_SUCCESS = "Registros recuperados correctamente.";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_PARAMETERS_ALIAS_SHOULD_NOT_BE_NULL_OR_VOID = "El alias no puede ir vacio o nulo";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_PARAMETER = "El parametro esta vacio o nulo";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_PARAMETER_IDCLIENTE = "El parametro idCliente esta vacio o nulo";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_PARAMETERS = "Los parametros no pueden ser vacios o nulos";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_TOKEN_ALREADY_EXISTS = "El token ya existe";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_PARAMETERS_SHOULD_NOT_BE_NULL_OR_VOID = "Los parametros no deben de ser nulos o vacios";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_ID_CLIENT_SHOULD_NOT_BE_NULL_OR_VOID = "El id del Cliente no puede ir nulo o debe ser mayor a 0";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_STATUS_SHOULD_NOT_BE_NULL_OR_VOID = "El estatus no debe ir vacio o nulo.";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_ID_STATUS_SHOULD_NOT_BE_NULL_OR_VOID = "El id del estatus de la tarjeta no puede ir nulo o debe ser mayor a 0";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_TIPO_SHOULD_NOT_BE_NULL_OR_VOID = "El tipo no debe ir vacio o nulo.";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_ID_TIPO_SHOULD_NOT_BE_NULL_OR_VOID = "El id del tipo de tarjeta no puede ir nulo o debe ser mayor a 0";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_TOKEN = "El token no existe";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_TOKENS = "El token ya existe.";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_TOKEN_NULL_OR_VOID = "El token esta nulo o vacio";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_DATE_NULL_OR_VOID = "La fecha de alta no puede ir nula o vacia";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_CLIENT_NULL_OR_VOID = "El cliente no puede ir nulo o vacio";

	/**
	 * Mensaje que sera enviado si no se recuperaron los registros correctamente
	 */
	public static final String MSG_FAILURE = "Usted aún no tiene tarjetas registradas. con el idCliente: ";
	
	/**
	 * Mensaje que sera enviado si no se recuperaron los registros correctamente
	 */
	public static final String MSG_FAILURES = "No hay registros para esos parametros.";
	
	/**
	 * Mensaje que sera enviado si no se recuperaron los registros correctamente
	 */
	public static final String MSG_FAILURE_TOKEN_IDCLIENTE = "El token o el idCliente estan vacios o nulos ";
	
	/**
	 * Mensaje que sera enviado si no se recuperaron los registros correctamente
	 */
	public static final String MSG_FAILURE_ALIAS = "El alias esta nulo o vacio.";
	
	
	/**
	 * Mensaje que sera enviado si no se recuperaron los registros correctamente
	 */
	public static final String MSG_FAILURE_TOKEN = "El token es nulo o vacio";

	/**
	 * Mensaje que sera enviado si se guarda el registro correctamente
	 */
	public static final String MSG_SUCCESS_ADD = "Registro agregado correctamente.";

	/**
	 * Mensaje que sera enviado si no si hay tres tarjetas registradas
	 */
	public static final String MSG_NO_SUCCESS_ADD_MAX_CARDS = "Usted ha alcanzado la cantidad máxima de tarjetas registradas. Para poder registrar una nueva tarjeta deberá eliminar una tarjeta registrada.";

	/**
	 * Mensaje que sera enviado si se actualiza el registro correctamente
	 */
	public static final String MSG_SUCCESS_UPDATE = "Registro actualizado correctamente.";

	/**
	 * Mensaje que sera enviado si se actualiza el registro correctamente
	 */
	public static final String MSG_NO_SUCCESS_UPDATE = "No se encontro el registro a actualizar.";
	
	/**
	 * Mensaje que sera enviado si se actualiza el registro correctamente
	 */
	public static final String MSG_NO_SUCCESS_UPDATE_NULL = "No se encontraron registros con el token especificado";

	/**
	 * Mensaje que sera enviado si se borra el registro correctamente
	 */
	public static final String MSG_SUCCESS_DELETE = "Registro borrado correctamente.";

	/**
	 * Mensaje que sera enviado si no se borra el registro correctamente
	 */
	public static final String MSG_NO_SUCCESS_DELETE = "No se encontro el registro a eliminar.";

}
