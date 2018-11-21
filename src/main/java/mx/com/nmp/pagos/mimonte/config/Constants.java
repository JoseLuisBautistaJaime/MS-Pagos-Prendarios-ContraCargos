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
}
