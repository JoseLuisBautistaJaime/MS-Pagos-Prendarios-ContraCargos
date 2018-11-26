package mx.com.nmp.pagos.mimonte.exception;

/**
 * Nombre: DatosIncompletosException
 * Descripcion: Clase de excepcion personalizada que indica que los datos del pago no han podido ser procesados
 *
 * @author: Ismael Flores iaguilar@quarksoft.net
 * @creationDate 26/11/2018 11:28 hrs.
 * @version: 0.1
 */
public class DatosIncompletosException extends PagoException{

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -1607724154712233272L;

	/**
	 * Constructs a new exception with the specified detail message.
	 * 
	 * @param message the detail message.
	 */
	public DatosIncompletosException(String message) {
		super(message);
	}
	
}
