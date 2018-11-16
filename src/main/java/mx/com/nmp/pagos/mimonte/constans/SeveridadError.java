

package mx.com.nmp.pagos.mimonte.constans;


import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Nombre: SeveridadError
 * Descripcion: Mapea la severidad en estructura ENUM
 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */

public enum SeveridadError {
    /**
     * Severidad de error Alta
     */
    ALTA(1),

    /**
     * Severidad de error Media
     */
    MEDIA(2),

    /**
     * Severidad de error Baja
     */
    BAJA(3);

    /**
     * Numero que indica la severidad del error.
     */
    private int severidad;

    /**
     * Constructor de la enumeracion.
     *
     * @param severidad Numero que indica la severidad del error.
     */
    SeveridadError(int severidad) {
        this.severidad = severidad;
    }

    /**
     * Recupera el valor de {@code severidad}
     *
     * @return Valor de {@code severidad}
     */
    @JsonValue
    public int getSeveridad() {
        return severidad;
    }
}
