

package mx.com.nmp.pagos.mimonte.util.validacion;


import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


/**
 * Nombre: ValidadorObjeto
 * Descripcion: Clase de utilería para validar objeto, mediante las anotaciones de validacion
 * @see javax.validation.constraints
 *
 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */

@Component
public class ValidadorObjeto {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidadorObjeto.class);

    /**
     * Objeto que se encarga de leer las anotaciones de validación
     */
    @Autowired
    private static Validator validator;

    /**
     * Constructor de la clase.
     */
    public ValidadorObjeto() {
        super();
    }
    
    static {
    	Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
        factory.close(); 
        } 
    

    /**
     * Método que valida si un objeto es nulo o no
     *
     * @param objeto Objeto que se sometera a validación
     * @param <T> Especifica el tipo del parametro
     *
     * @throws IllegalArgumentException Cuando el valor del parametro se {@literal null}
     * o alguna de las propiedades no cumpla con las validaciones
     */
    public <T> void noNulo(T objeto) {
        Assert.notNull(objeto, "El valor no debe ser nulo");

        Set<ConstraintViolation<T>> errores = validator.validate(objeto);

        if (!errores.isEmpty()) {
            StringBuilder msj = new StringBuilder();
            errores.forEach(
                    cv -> msj.append(cv.getPropertyPath())
                            .append(" ")
                            .append(cv.getMessage())
                            .append(String.format("%n"))
            );
            LOGGER.warn("Errores al validar las propiedades del objeto: {}", msj);
            throw new IllegalArgumentException(msj.toString());
        }
    }
}
