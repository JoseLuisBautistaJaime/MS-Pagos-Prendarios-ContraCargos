
package mx.com.nmp.pagos.mimonte.exception;


import javax.persistence.EntityNotFoundException;


/**
 * Nombre: SesionNotFoundException
 * Descripcion: Clase se excepcion personalizada que indica que no se encontraron resultados
 * de extrafilter durante la busqueda de los mismos
 *
 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */

public class SesionNotFoundException extends EntityNotFoundException {
    /**
     * Constructor
     *
     * @param message Mensjae especificando el motivo del error.
     */
    public SesionNotFoundException(String message) {
        super(message);
    }
}
