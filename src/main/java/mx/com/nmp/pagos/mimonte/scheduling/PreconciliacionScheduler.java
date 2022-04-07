/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;


import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ProcesoPreconciliacionService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * @name PreconciliacionScheduler
 * @description Componente para la calendarización de la ejecución automática del proceso de pre-conciliación.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 29/11/2021 10:48 hrs.
 * @version 0.1
 */

@Component
public class PreconciliacionScheduler {

	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private static final Logger logger = LoggerFactory.getLogger(PreconciliacionScheduler.class);

	/**
	 * Los métodos para ejecutar el proceso de pre-conciliación.
	 */
	@Autowired
	private ProcesoPreconciliacionService procesoPreconciliacionService;

	/**
	 * Programa la ejecución de las  tareas automatizadas.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		createSchedulers();
	}

	/**
	 * Ejecuta tareas de programadas.
	 */
	public void createSchedulers(){

		List<CalendarioEjecucionProcesoDTO> listaConfiguraciones = procesoPreconciliacionService.obtenerCalendarizacionPreconciliacion();

		for (CalendarioEjecucionProcesoDTO configuracion : listaConfiguraciones) {
			String cronExpressions = configuracion.getConfiguracionAutomatizacion();
			if (!StringUtils.isEmpty(cronExpressions)) {
				ScheduledExecutorService localExecutorService= Executors.newSingleThreadScheduledExecutor();
				TaskScheduler scheduler = new ConcurrentTaskScheduler(localExecutorService);
				scheduler.schedule( () -> lanzarPreconciliacionAutomatizada(configuracion), new CronTrigger(cronExpressions));
			}
		}

	}

	/**
	 * Método encargado de lanzar la ejecución del proceso de pre-conciliación.
	 *
	 */
	public void lanzarPreconciliacionAutomatizada(CalendarioEjecucionProcesoDTO calendarizacion) {
        EjecucionPreconciliacion ejecucionPreconciliacion  = procesoPreconciliacionService.crearEjecucionPreconciliacion(calendarizacion);
        if(procesoPreconciliacionService.validarFechaInhabil(ejecucionPreconciliacion.getFechaPeriodoInicio())) {
			if(procesoPreconciliacionService.validarDuplicidadProceso(ejecucionPreconciliacion)) {
				procesoPreconciliacionService.ejecutarProcesoPreconciliacion(calendarizacion, ejecucionPreconciliacion);
			} else{
				logger.info("Ejecución del proceso duplicado");
			}
		} else {
			logger.info("Ejecución del proceso en día inhábil");
		}
	}


}
