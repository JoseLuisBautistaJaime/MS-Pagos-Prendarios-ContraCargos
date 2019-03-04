package mx.com.nmp.pagos.mimonte.exception;

/**
 * Nombre: CatalogoException Descripcion: Clase de excepcion personalizada que
 * indica que hubo un problema con una operaicon relacionada con un catalogo
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 04/03/2019 10:10 hrs.
 * @version: 0.1
 */
public class CatalogoException extends RuntimeException {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 5467450105229263241L;

	public CatalogoException(String message) {
		super(message);
	}

}
