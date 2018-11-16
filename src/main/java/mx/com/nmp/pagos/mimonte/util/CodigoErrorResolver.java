
package mx.com.nmp.pagos.mimonte.util;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.exception.CatalogoNotFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Nombre: CodigoErrorResolver
 * Descripcion:  Se encarga de buscar el codigo de error que le corresponde a un excepción
 *
 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */
public class CodigoErrorResolver {

    /**
     * Mapa de estrategias para manejar los mensajes de error según la excepción
     */
    private static final Map<Class<?>, CodigoError> EXCEPCION_CODIGO_ERROR_ESTRATEGY;

    static {
        EXCEPCION_CODIGO_ERROR_ESTRATEGY = Stream.of(
                new AbstractMap.SimpleEntry<>(IllegalArgumentException.class, CodigoError.NMP_PMIMONTE_0001),
                new AbstractMap.SimpleEntry<>(MethodArgumentTypeMismatchException.class, CodigoError.NMP_PMIMONTE_0001),
                new AbstractMap.SimpleEntry<>(CatalogoNotFoundException.class, CodigoError.NMP_PMIMONTE_0005),
                new AbstractMap.SimpleEntry<>(HttpMessageNotReadableException.class, CodigoError.NMP_PMIMONTE_0006),
                new AbstractMap.SimpleEntry<>(MethodArgumentNotValidException.class, CodigoError.NMP_PMIMONTE_0007),
                new AbstractMap.SimpleEntry<>(UnsatisfiedServletRequestParameterException.class, CodigoError.NMP_PMIMONTE_0008)
        ).collect(Collectors.toConcurrentMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    /**
     * Constructor
     */
    private CodigoErrorResolver() {
        super();
    }

    /**
     * Regresa el codigo de error según la clase
     *
     * @param clase Clase de llave del codigo de error
     *
     * @return Codigo correpondiente o {@literal null} si no existe
     */
    public static CodigoError get(Class<?> clase) {
        return EXCEPCION_CODIGO_ERROR_ESTRATEGY.get(clase);
    }

}
