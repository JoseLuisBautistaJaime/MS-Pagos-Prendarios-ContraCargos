/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MontoLayoutConciliacionDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EjecucionConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ProcesoEnum;
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

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * @name ConciliacionLayoutsScheduler
 * @description Componente para la calendarización de la ejecución automática del proceso de conciliación Etapa 3 (Envío de Layouts).
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 31/01/2022 10:48 hrs.
 * @version 0.1
 */

@Component
public class ConciliacionLayoutsScheduler {

	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private final Logger LOG = LoggerFactory.getLogger(ConciliacionLayoutsScheduler.class);

	/**
	 * Los métodos para generar y enviar los layouts de pagos, comisiones y devoluciones.
	 */
	@Autowired
	private ConciliacionLayouts conciliacionLayouts;

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
	private void createSchedulers(){

		List<CalendarioEjecucionProcesoDTO> listaConfiguraciones = obtenerCalendarizacionConciliacionEtapa3();

		for (CalendarioEjecucionProcesoDTO configuracion : listaConfiguraciones) {
			String cronExpressions = configuracion.getConfiguracionAutomatizacion();
			if (!StringUtils.isEmpty(cronExpressions)) {
				ScheduledExecutorService localExecutorService= Executors.newSingleThreadScheduledExecutor();
				TaskScheduler scheduler = new ConcurrentTaskScheduler(localExecutorService);
				scheduler.schedule( () -> lanzarConciliacionEtapa3(configuracion), new CronTrigger(cronExpressions));
			}
		}

	}

	/**
	 * Método encargado de lanzar la ejecución del proceso de conciliación etapa 3.
	 *
	 */
	public void lanzarConciliacionEtapa3(CalendarioEjecucionProcesoDTO calendarizacion) {
		List<Conciliacion> listaConciliaciones  = conciliacionLayouts.buscarConciliacionSinLayouts(calendarizacion);
		if(!listaConciliaciones.isEmpty()) {
			for (Conciliacion conciliacionLayout : listaConciliaciones) {
				EjecucionConciliacion ejecucionConciliacion = conciliacionLayouts.buscarEjecucionConciliacion(conciliacionLayout);
				if (ejecucionConciliacion != null && ejecucionConciliacion.getId() != 0) {
					conciliacionLayouts.ejecutarProcesoConciliacionE3(ejecucionConciliacion);
				}else{
					LOG.info("No se encontraron los datos de ejecución del proceso");
				}
			}
		} else{
			LOG.info("No se encontraron procesos sin sus layouts generados y enviados");
		}

	}

	/**
	 * Método encargado de consultar la configuracion de la calendarizacion del proceso de conciliación etapa 3.
	 *
	 * @return
	 */
	public List<CalendarioEjecucionProcesoDTO> obtenerCalendarizacionConciliacionEtapa3() {
		return  conciliacionLayouts.obtenerCalendarizacionConciliacion(ProcesoEnum.CONCILIACION_ETAPA_3.getIdProceso(), CorresponsalEnum.OPENPAY.getNombre());
	}

}
