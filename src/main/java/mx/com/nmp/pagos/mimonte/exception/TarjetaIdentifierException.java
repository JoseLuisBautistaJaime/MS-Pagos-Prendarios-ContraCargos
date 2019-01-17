package mx.com.nmp.pagos.mimonte.exception;

/**
 * Nombre: PagoException Descripcion: Clase de excepcion personalizada que
 * indica que el identificador de una tarjeta ya existe
 *
 * @author: Ismael Flores Aguilar iaguilar@quarksoft.net
 * @creationDate 17/01/2019 13:59 hrs.
 * @version: 0.1
 */
public class TarjetaIdentifierException extends TarjetaException {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1693974470593172119L;

	/**
	 * Constructs a new exception with the specified detail message.
	 * 
	 * @param message the detail message.
	 */
	public TarjetaIdentifierException(String message) {
		super(message);
	}

}
