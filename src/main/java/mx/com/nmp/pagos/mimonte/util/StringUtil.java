/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util;

/**
 * @name CollectionUtil
 * @description Utilería con métodos auxiliares utilizados en la validación de elementos de tipo String.
 *
 * @author ngonzalez
 * @creationDate 30/07/2019 18:01 hrs.
 * @version 0.1
 */
@SuppressWarnings({"JavaDoc"})
public class StringUtil {

    /**
     * Valida que el argumento recibido no sea nulo ni vacío.
     *
     * @param value El valor que se requiere validar.
     * @return TRUE si no es nulo ni vacío.
     */
    public static boolean isNotNullNorEmpty(String value) {
        return value != null && !value.equals("");
    }

    /**
     * Valida que el argumento recibido sea nulo o vacío.
     *
     * @param value El valor que se requiere validar.
     * @return TRUE si es nulo o vacío.
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.equals("");
    }

}
