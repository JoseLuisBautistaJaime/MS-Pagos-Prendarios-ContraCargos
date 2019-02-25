package mx.com.nmp.pagos.mimonte.exception;

/**
 * Nombre: PagoException
 * Descripcion: Clase de excepcion personalizada que indica que hubo un problema con el pago
 *
 * @author: Ismael Flores iaguilar@quarksoft.net
 * @creationDate 26/11/2018 11:28 hrs.
 * @version: 0.1
 */
public class PagoException extends RuntimeException{

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -829933261837505581L;

	/**
	 * Constructs a new exception with the specified detail message.
	 * 
	 * @param message the detail message.
	 */
	public PagoException(String message) {
		super(message);
	}
	
}
