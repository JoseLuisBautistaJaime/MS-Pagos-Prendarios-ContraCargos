package mx.com.nmp.pagos.mimonte.exception;

/**
 * Nombre: ClienteException
 * Descripcion: Clase de excepcion personalizada que
 * indica que hubo un problema con una operaicon relacionada con el cliente
 *
 * @author: Ismael Flores iaguilar@quarksoft.net
 * @creationDate 29/11/2018 11:16 hrs.
 * @version: 0.1
 */
public class ClienteException extends RuntimeException {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = -4376327909008889142L;

	/**
	 * Constructs a new exception with the specified detail message.
	 * 
	 * @param message the detail message.
	 */
	public ClienteException(String message) {
		super(message);
	}

}
