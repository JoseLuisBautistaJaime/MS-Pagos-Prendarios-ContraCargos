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
	public static final String MSG_FAIL_PARAMETER = "El parametro esta vacio o nulo";
	
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
	public static final String MSG_FAIL_TOKEN = "El token no existe";

	/**
	 * Mensaje que sera enviado si no se recuperaron los registros correctamente
	 */
	public static final String MSG_FAILURE = "Usted aún no tiene tarjetas registradas. con el idCliente: ";

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
	public static final String MSG_NO_SUCCESS_UPDATE_NULL = "El tipo de tarjeta es nulo o vacio y/o el estatus de tarjeta es nulo o vacio.";

	/**
	 * Mensaje que sera enviado si se borra el registro correctamente
	 */
	public static final String MSG_SUCCESS_DELETE = "Registro borrado correctamente.";

	/**
	 * Mensaje que sera enviado si no se borra el registro correctamente
	 */
	public static final String MSG_NO_SUCCESS_DELETE = "No se encontro el registro a eliminar.";

}
