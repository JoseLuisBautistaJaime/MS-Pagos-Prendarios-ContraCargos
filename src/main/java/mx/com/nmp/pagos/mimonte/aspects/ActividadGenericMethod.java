/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.aspects;

import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.annotations.ApplicationEvents;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubTipoActividadEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoActividadEnum;

/**
 * @name ActividadGenericMethod
 * @description Clase contiene el metodo para estandarizar el registro de
 *              actividades en base de datos por medio de argumentos estandar
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 14/06/2019 12:43 hrs.
 * @version 0.1
 */
@Component
public class ActividadGenericMethod {

	/**
	 * Metodo generico que recibe argumentos para la construccion de la Actividad a
	 * persistir y cuya llamada es interceptada por ApplicationEvents
	 * 
	 * @param obj
	 * @param descripcion
	 * @param tipo
	 * @param subTipo
	 */
	@ApplicationEvents
	public void registroActividad(Object obj, String descripcion, TipoActividadEnum tipo,
			SubTipoActividadEnum subTipo) {
		/**
		 * No hay cuerpo para este metodo los argumentos son tomados por el interceptor
		 */
	}
}
