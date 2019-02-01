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
	public static final String MSG_FAIL_TOKEN_ALREADY_EXISTS = "El id_openpay ya existe";
	
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
	public static final String MSG_FAIL_CAN_NOT_FIND_THE_CARD_TYPE_RECORD= "No se encontro registro para el tipo de tarjeta.";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_CAN_NOT_FIND_THE_CARD_STATUS_RECORD = "No se encontro el registro para el estatus de tarjeta.";
	
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
	public static final String MSG_FAIL_TOKEN = "El id_openpay no existe";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_TOKENS = "El id_openpay ya existe.";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_TOKEN_NULL_OR_VOID = "El id_openpay esta nulo o vacio";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_DATE_NULL_OR_VOID = "La fecha de alta no puede ir nula o vacia";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_CLIENT_NULL_OR_VOID = "El cliente no puede ir nulo o vacio";
	
	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	public static final String MSG_FAIL_DIGITS_NULL_OR_VOID = "El campo digitos debe contener 4 carácteres.";

	/**
	 * Mensaje que sera enviado si no se recuperaron los registros correctamente
	 */
	public static final String MSG_FAILURE = " Usted aún no tiene tarjetas registradas. ";
	
	/**
	 * Mensaje que sera enviado si no se recuperaron los registros correctamente
	 */
	public static final String MSG_FAILURES = "No hay registros para esos parametros.";
	
	/**
	 * Mensaje que sera enviado si no se recuperaron los registros correctamente
	 */
	public static final String MSG_FAILURE_TOKEN_IDCLIENTE = "El id_openpay o el idCliente estan vacios o nulos ";
	
	/**
	 * Mensaje que sera enviado si no se recuperaron los registros correctamente
	 */
	public static final String MSG_FAILURE_ALIAS = "El alias esta nulo o vacio.";
	
	/**
	 * Mensaje que sera enviado si no se recuperaron los registros correctamente
	 */
	public static final String MSG_FAILURE_UPTOKEN = "El token esta nulo o vacio.";
	
	/**
	 * Mensaje que sera enviado si no se recuperaron los registros correctamente
	 */
	public static final String MSG_FAILURE_TOKEN = "El id_openpay es nulo o vacio";

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
	public static final String MSG_NO_SUCCESS_UPDATE_NULL = "No se encontraron registros con el id_openpay especificado";
	
	/**
	 * Mensaje que sera enviado si se actualiza el registro correctamente
	 */
	public static final String MSG_THE_ALIAS_IS_ALREADY_ASSIGNED_TO_A_CARD = "El alias ya existe para una de las tarjetas de este cliente.";

	/**
	 * Mensaje que sera enviado si se borra el registro correctamente
	 */
	public static final String MSG_SUCCESS_DELETE = "Registro borrado correctamente.";

	/**
	 * Mensaje que sera enviado si no se borra el registro correctamente
	 */
	public static final String MSG_NO_SUCCESS_DELETE = "No se encontro el registro a eliminar.";

	/**
	 * Mensaje que es mostrado cuando se intenta agrefar una tarjeta con digitos que contienen caracteres que no son numeros
	 */
	public static final String MSG_ONLY_NUMBERS = "Los digitos de la tarjeta deben contener solo numeros";
	
	/**
	 * Mensaje que es mostrado cuando el nombre del titular esta formado por uno o mas caracteres diferentes a letras
	 */
	public static final String MSG_ONLY_LETTERS = "El nombre del titular solo puede contener letras y espacios";
	
	/**
	 * Valores a evaluar para solo letras
	 */
	public static final String LETTER_VALUES = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚÜ ";
	
	/**
	 * Mensaje que aparece cuando se intenta agregar un alias de tarjeta que ya existe para el cliente en cuestion
	 */
	public static final String ALIAS_ALREADY_EXIST_FOR_CURRENT_CLIENT = "El alias ya existe para una de las tarjetas de este cliente";
	
	/**
	 * Mensaje que es mostrado en el servicio de pago cuando se intenta guardar una tarjeta que ya existe
	 */
	public static final String MSG_TARJETAS_ERROR = "La tarjeta no se guardo ya que el id_openpay de tarjetas ya existe";
}
