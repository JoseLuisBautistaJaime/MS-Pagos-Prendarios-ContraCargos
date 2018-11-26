package mx.com.nmp.pagos.mimonte.exception;

/**
 * Nombre: CantidadMaximaTarjetasAlcanzadaException
 * Descripcion: Clase de excepcion personalizada que indica que el numero maximo de posibles tarjetas guardadas
 * por cliente se ha alcanzado ya
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 26/11/2018 11:28 hrs. 
 * @version 0.1
 */
public class CantidadMaximaTarjetasAlcanzadaException extends PagoException {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -1608147941428344616L;

	/**
	 * Constructs a new exception with the specified detail message.
	 * 
	 * @param message the detail message.
	 */
	public CantidadMaximaTarjetasAlcanzadaException(String message) {
		super(message);
	}
	
}
