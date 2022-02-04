/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EjecucionConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ProcesoEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;
import java.util.List;


/**
 * @name ConciliacionLayoutsScheduler
 * @description Componente para la calendarización de la ejecución automática del proceso de conciliación Etapa 3 (Envío de Layouts).
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 31/01/2022 10:48 hrs.
 * @version 0.1
 */

@Configuration
@EnableScheduling
public class ConciliacionLayoutsScheduler implements SchedulingConfigurer {


	/**
	 * Los métodos para generar y enviar los layouts de pagos, comisiones y devoluciones.
	 */
	@Autowired
	private ConciliacionLayouts conciliacionLayouts;


	/**
	 Ejecuta tareas de programadas.
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

		Runnable runnableTask = () ->  lanzarConciliacionEtapa3DN();

		Trigger trigger = new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				String cronExpressions = obtenerCalendarizacionConciliacionEtapa3DN().getConfiguracionAutomatizacion();
				if (StringUtils.isEmpty(cronExpressions)) {
					return null;
				}
				CronTrigger crontrigger = new CronTrigger(cronExpressions);
				return crontrigger.nextExecutionTime(triggerContext);
			}
		};

		taskRegistrar.addTriggerTask(runnableTask , trigger);

	}

	/**
	 * Método encargado de lanzar la ejecución del proceso de conciliación etapa 3.
	 *
	 */
	public void lanzarConciliacionEtapa3DN() {
        CalendarioEjecucionProcesoDTO calendarizacion = this.obtenerCalendarizacionConciliacionEtapa3DN();
		List<Conciliacion> listaConciliaciones  = conciliacionLayouts.buscarConciliacionSinLayouts(calendarizacion);
		for (Conciliacion conciliacionLayout : listaConciliaciones) {
			EjecucionConciliacion ejecucionConciliacion = conciliacionLayouts.buscarEjecucionConciliacion(conciliacionLayout);
			if(ejecucionConciliacion != null && ejecucionConciliacion.getId() != 0 ) {
				conciliacionLayouts.ejecutarProcesoConciliacionE3(ejecucionConciliacion);
			}
		}

	}

	/**
	 * Método encargado de consultar la configuracion de la calendarizacion del proceso de conciliación etapa 3.
	 *
	 * @return
	 */
	public CalendarioEjecucionProcesoDTO obtenerCalendarizacionConciliacionEtapa3DN() {
		return conciliacionLayouts.obtenerCalendarizacionConciliacion(ProcesoEnum.CONCILIACION_ETAPA_3_DN.getIdProceso(), CorresponsalEnum.OPENPAY.getNombre());
	}

}
