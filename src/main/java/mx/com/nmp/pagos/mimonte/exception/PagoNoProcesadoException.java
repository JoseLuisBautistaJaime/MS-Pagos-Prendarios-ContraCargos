package mx.com.nmp.pagos.mimonte.exception;

/**
 * Nombre: PagoNoProcesadoException
 * Descripcion: Clase de excepcion personalizada que indica que no se ha podido realizar el pago de las partidas solicitadas
 *
 * @author: Ismael Flores iaguilar@quarksoft.net
 * @creationDate 26/11/2018 11:28 hrs.
 * @version: 0.1
 */
public class PagoNoProcesadoException extends Exception {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 650149673842235667L;

	/**
	 * Constructs a new exception with the specified detail message.
	 * 
	 * @param message the detail message.
	 */
	public PagoNoProcesadoException(String message) {
		super(message);
	}

}
