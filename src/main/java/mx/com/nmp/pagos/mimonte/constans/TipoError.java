/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.constans;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @name TipoError
 * @description Enumeración que enlista los tipo de errores
 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */

public enum TipoError {
    /**
     * Error por parte del cliente
     */
    CLIENTE("Error del Cliente"),

    /**
     * Error por parte del servidor
     */
    SERVIDOR("Error del Servidor");

    /**
     * Cadena que indica el tipo de error
     */
    private String tipo;

    /**
     * Constructor
     *
     * @param tipo Cadena que indica el tipo de error
     */
    TipoError(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Recupera el valor de {@code tipo}
     *
     * @return Valor de {@code tipo}
     */
    @JsonValue
    public String getTipo() {
        return tipo;
    }
}
