package mx.com.nmp.pagos.mimonte.exception;

/**
 * Nombre: CatalogoNotFoundException
 * Descripcion: Clase se excepcion personalizada que indica que no se encontraron resultados
 * de extrafilter durante la busqueda de los mismos
 *
 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */
public class CatalogoNotFoundException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CatalogoNotFoundException(String message) {
        super(message);
    }
}
