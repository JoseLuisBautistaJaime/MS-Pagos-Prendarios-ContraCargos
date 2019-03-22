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
	 * Constructor privado, ya que será una clase para constantes por lo tanto no
	 * debe ser instanciada.
	 */
	private CatalogConstants() {
		super();
	}
}
