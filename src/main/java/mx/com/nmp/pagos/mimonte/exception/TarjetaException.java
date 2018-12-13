package mx.com.nmp.pagos.mimonte.exception;

/**
 * Nombre: PagoException Descripcion: Clase de excepcion personalizada que
 * indica que hubo un problema con las tarjetas.
 *
 * @author: José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 07/12/2018 12:46 hrs.
 * @version: 0.1
 */
public class TarjetaException extends RuntimeException {

	/**
	 * Serial Version.
	 */
	private static final long serialVersionUID = -4463904039590760366L;

	/**
	 * Constructs a new exception with the specified detail message.
	 * 
	 * @param message the detail message.
	 */
	public TarjetaException(String message) {
		super(message);
	}

}
