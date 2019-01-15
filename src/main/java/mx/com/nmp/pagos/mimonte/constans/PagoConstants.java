package mx.com.nmp.pagos.mimonte.constans;

/**
 * Nombre: PagoConstants
 * Descripcion: Clase que almacena las constantes usadas para operaciones relacionadas con el pago de partidas / contratos.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 20/11/2018 10:00 hrs.
 * @version 0.1
 */
public final class PagoConstants {
    	
	private PagoConstants() {
		/**
		 * hidden constructor
		 */
	}
	
		/**
		 * Mensaje que sera enviado si se se relaliza el pago
		 * correctamente
		 */
		public static final String MSG_SUCCESS = "Registro de pago realizado correctamente.";
	
		/**
		 * Mensaje que será mostrado cuando no se pueda realizar el pago por un error desconocido
		 * 
		 */
		public static final String UNKNOWN_PAY_ERROR = "Error desconocido al intentar realizar pago";
	
    	/**
    	 * Mensaje que sera mostrado cuando se desee guardar una nueva tarjeta en un pago,
    	 * pero los datos de esta esten incompletos
    	 */
    	public static final String INCOMPLETE_CARD_DATA = "Falta uno o mas datos de la tarjeta";
    	
    	/**
    	 * Mensaje que sera mostrado cuando se desee guardar una nueva tarjeta en un pago,
    	 * pero los datos de esta esten incompletos
    	 */
    	public static final String NO_OPERATIONS_MESSAGE = "No existen operaciones";
    	
    	/**
    	 * Mensaje que sera mostrado cuando se desee guardar una tarjeta pero el cliente en cuestion
    	 * ya haya alcanzado la cantidad maxima posible de tarjetas guardadas
    	 */
    	public static final String MAXIMUM_AMOUNT_OF_CARDS_ACHIEVED = "Ya se ha alcanzado la cantidad máxima de tarjetas por cliente";
    	
    	/**
    	 * Nombre de la propiedad que en archivo de configuracion YML que contiene el dato de 
    	 * la cantidad maxima de tarjetas posibles por cliente
    	 */
    	public static final String MAXIMUM_AMOUNT_OF_CARDS_PROPERTY = "${mimonte.variables.cantidad-maxima-tarjetas}";

    	/**
    	 * Mensaje que es enviado cuando se intenta registrar el pago de un cliente que no existe
    	 */
    	public static final String CLIENTE_NOT_FOUND = "El cliente no existe";
    	
    	/**
    	 * Mensaje que es mostrado cuando el id del cliente es menor o igual a 0
    	 */
    	public static final String CLIENT_ID_LESS_OR_EQUAL_THAN_0 = "El id de cliente debe ser mayor a 0";
    	
    	/**
    	 * Mensaje que es enviado cuando la suma de el monto de las operaciones es diferente al monto total
    	 */
    	public static final String IRREGULAR_OPERATIONS_AMOUNT = "La suma del monto de las operaciones debe ser igual a el total de el pago";
    	
    	/**
    	 * Mensaje que es mostrado cuando el monto total del pago es menor o igual 0
    	 */
    	public static final String TOTAL_AMOUNT_LESS_OR_EQUAL_THAN_0 = "El monto total del pago es menor o igual a 0";
    	
    	/**
    	 * Mensaje que es enviado cuando el id de una operacion es menor o igual a 0
    	 */
    	public static final String OPERATION_ID_LESS_OR_EQUAL_THAN_0 = "El id de una o mas operaciones es menor o igual a 0";
    	
    	/**
    	 * Mensaje que es enviado cuando el monto de una operacion es menor o igual a 0
    	 */
    	public static final String OPERATION_AMOUNT_LESS_OR_EQUAL_THAN_0 = "El monto de una o mas operaciones es menor o igual a 0";
    	
    	/**
    	 * Mensaje que es enviado cuando el folio de la partida no es un valor numerico entero
    	 */
    	public static final String NUMBER_FORMAT_IN_FOLIO_CONTRATO = "El formato del folio de la partida o contrato debe ser numerico";
    	
    	/**
    	 * Mensaje que es enviado cuando el id de transaccion de midas no es un valor numerico entero
    	 */
    	public static final String NUMBER_FORMAT_IN_ID_TRANSACCION_MIDAS = "El formato del id de transaccion de midas debe ser numerico";
}
