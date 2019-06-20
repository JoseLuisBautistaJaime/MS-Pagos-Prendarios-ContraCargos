/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.aspects;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.ActividadRepository;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Actividad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubTipoActividadEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoActividadEnum;

/**
 * @name EventAspect
 * @description Componente Aspect que realiza la logica de registro de
 *              actividades
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 14/06/2019 12:44 hrs.
 * @version 0.1
 */
@Aspect
@Component
public class EventAspect {

	/**
	 * Repository ActividadRepository
	 */
	@Autowired
	@Qualifier("actividadRepository")
	private ActividadRepository actividadRepository;

	/**
	 * Intercepta la llamada a cualquier metodo anotado con ApplicationEvents
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(mx.com.nmp.pagos.mimonte.annotations.ApplicationEvents)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		// Declaracion de objetos
		Actividad actividad = null;
		Integer folio = null;
		String descripcion = null;
		TipoActividadEnum tipo = null;
		SubTipoActividadEnum subTipo = null;
		// Se obtienen los argumentos y se setean a atributos
		Object[] arr = joinPoint.getArgs();
		if (arr.length >= 4) {
			folio = Integer.parseInt(arr[0].toString());
			descripcion = arr[1].toString();
			tipo = (TipoActividadEnum) arr[2];
			subTipo = (SubTipoActividadEnum) arr[3];
			// Se construye la entidad actividad
			actividad = buildActividad(folio, descripcion, tipo, subTipo);
			// Se guarda la entidad Actividad
			if (null != actividad)
				actividadRepository.save(actividad);
		}
		Object proceed = joinPoint.proceed();
		return proceed;
	}

	/**
	 * Construye el entity Actividad a guardar
	 * 
	 * @param folio
	 * @param descripcion
	 * @param tipo
	 * @param subTipo
	 * @param argArray
	 * @return
	 */
	private Actividad buildActividad(final Integer folio, final String descripcion, final TipoActividadEnum tipo,
			final SubTipoActividadEnum subTipo) {
		Actividad actividad = null;
		if (null != folio && null != descripcion && null != tipo && null != subTipo) {
			actividad = new Actividad();
			actividad.setFolio(folio);
			actividad.setDescripcion(descripcion);
			actividad.setFecha(new Date());
			actividad.setSubTipo(subTipo);
			actividad.setTipo(tipo);
		}
		return actividad;
	}

}