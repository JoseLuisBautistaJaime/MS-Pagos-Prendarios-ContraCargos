package mx.com.nmp.pagos.mimonte.config;

public final class Constants {

    public static final String SPRING_PROFILE_CLOUD = "cloud";
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_BMX = "bmx";
    public static final String SPRING_PROFILE_SWAGGER = "swagger";
    public static final String RESPONSE_CODE_SUCCESS = "S";
    public static final String RESPONSE_CODE_ERROR = "E";

    public static final int LONGITUD_DESCRIPCION_CORTA = 20;
    public static final int LONGITUD_DESCRIPCION = 50;
    
    /**
     * Mensaje que sera enviado si se recuperaron los registros correctamente
     */
    public static final String MSG_SUCCESS = "Registros recuperados correctamente.";
    
    /**
     * Mensaje que sera enviado si se guarda el registro correctamente
     */
    public static final String MSG_SUCCESS_ADD = "Registro agregado correctamente.";
    
    /**
     * Mensaje que sera enviado si se actualiza el registro correctamente
     */
    public static final String MSG_SUCCESS_UPDATE = "Registro actualizado correctamente.";
    
    /**
     * Mensaje que sera enviado si se borra el registro correctamente
     */
    public static final String MSG_SUCCESS_DELETE = "Registro borrado correctamente.";

    private Constants() {
    }
    
    public static final class PagoConstants{
    	
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
}
