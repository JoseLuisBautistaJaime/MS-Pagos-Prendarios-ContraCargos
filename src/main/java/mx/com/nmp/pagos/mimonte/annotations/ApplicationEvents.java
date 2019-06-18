/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @name ApplicationEvents
 * @description Anotacion de AspectJ para la intercepcion de eventos en
 *              determinados metodos
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 14/06/2019 12:43 hrs.
 * @version 0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationEvents {
}
