/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @name CollectionUtil
 * @description Utilería con métodos auxiliares utilizados en la validación de elementos de tipo Collection.
 *
 * @author ngonzalez
 * @creationDate 30/07/2019 18:01 hrs.
 * @version 0.1
 */
@SuppressWarnings({"JavaDoc", "unchecked"})
public class CollectionUtil {

    /**
     * Crea una lista a partir de los elementos recibidos.
     *
     * @param items La lista de elementos.
     * @param <T> El tipo de elementos.
     * @return La lista creada.
     */
    public static <T> List<T> asList(T... items) {
        List<T> list = new ArrayList<>();

        if (isNotNullNorEmpty(items))
            list.addAll(Arrays.asList(items));

        return list;
    }

    /**
     * Valida que los argumentos recibidos no sean nulos ni vacíos.
     *
     * @param args La lista de argumentos.
     * @param <T> El tipo de argumentos.
     * @return TRUE si los argumentos no son nulos ni vacíos.
     */
    private static <T> boolean isNotNullNorEmpty(T... args) {
        return args != null && args.length > 0;
    }

    /**
     * Valida si los argumentos recibidos son nulos o vacíos.
     *
     * @param args La lista de argumentos.
     * @param <T> El tipo de argumentos.
     * @return TRUE si los argumentos son nulos o vacíos.
     */
    public static <T> boolean isNullOrEmpty(T... args) {
        return args == null || args.length == 0;
    }

}
