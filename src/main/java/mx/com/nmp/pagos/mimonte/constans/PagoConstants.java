package mx.com.nmp.pagos.mimonte.constans;

/**
 * Nombre: PagoConstants
 * Descripcion: Clase que almacena las constantes usadas para operaciones relacionadas con el pago de partidas / contratos.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 20/11/2018 10:00 hrs.
 * @version 0.1
 */
public final class PagoConstants {
    	
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
    	 * Mensaje que sera mostrado cuando se desee guardar una tarjeta pero el cliente en cuestion
    	 * ya haya alcanzado la cantidad maxima posible de tarjetas guardadas
    	 */
    	public static final String MAXIMUM_AMOUNT_OF_CARDS_ACHIEVED = "Ya se ha alcanzado la cantidad máxima de tarjetas por cliente";
    
    	/**
    	 * Cantidad maxima de tarjetas que un cliente puede tener registradas
    	 */
    	public static final int MAXIMUM_AMOUNT_OF_CARDS = 3;
	
}
