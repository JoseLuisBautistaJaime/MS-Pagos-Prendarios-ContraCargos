
package mx.com.nmp.pagos.mimonte.exception;


/**
 * Nombre: PartidaNotFoundException
 * Descripcion: Clase se excepcion personalizada que indica que no se encontraron resultados
 * de extrafilter durante la busqueda de los mismos
 *
 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */
public class PartidaNotFoundException extends RuntimeException {
    /**
     * Constructor
     *
     * @param message Mensjae especificando el motivo del error.
     */
    public PartidaNotFoundException(String message) {
        super(message);
    }
}