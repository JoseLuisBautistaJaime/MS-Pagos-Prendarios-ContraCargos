package mx.com.nmp.pagos.mimonte.exception;

/**
 * Nombre: DSSException
 * Descripcion: Clase de excepcion personalizada que indica
 * que hubo un problema con en el modulo DSS (Decision Support System)
 *
 * @author: Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 16:31 hrs.
 * @version: 0.1
 */
public class DSSException extends RuntimeException {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 7703864992904059356L;

	/**
	 * Constructs a new exception with the specified detail message.
	 * 
	 * @param message the detail message.
	 */
	public DSSException(String message) {
		super(message);
	}

}
