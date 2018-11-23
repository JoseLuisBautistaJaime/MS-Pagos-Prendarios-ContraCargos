package mx.com.nmp.pagos.mimonte.constans;

public final class PagoConstants {
    	
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
