/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import java.util.*;



/**
 * @name ConciliacionMidasProveedorDNScheduler
 * @description Componente para la calendarización de la ejecución automática del proceso de conciliación etapa 1 (carga de movimientos nocturnos de MIDAS / carga de movimientos del proveedor)(configuración días naturales).
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 21/12/2021 10:48 hrs.
 * @version 0.1
 */

@Configuration
@EnableScheduling
public class ConciliacionMidasProveedorDNScheduler implements SchedulingConfigurer {


	/**
	 * Los métodos para consultar y cargar los movimientos del estado de cuenta.
	 */
	@Autowired
	private ConciliacionMidasProveedor conciliacionMidasProveedor;

	/**
	 Ejecuta tareas de programadas.
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

		Runnable runnableTask = () ->  lanzarConciliacionEtapa1DN();

		Trigger trigger = new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				String cronExpressions = obtenerCalendarizacionConciliacionEtapa1DN().getConfiguracionAutomatizacion();
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
	 * Método encargado de lanzar la ejecución del proceso de conciliación etapa 1.
	 *
	 */
	public void lanzarConciliacionEtapa1DN() {
        CalendarioEjecucionProcesoDTO calendarizacion = this.obtenerCalendarizacionConciliacionEtapa1DN();
        EjecucionConciliacion ejecucionConciliacion  = conciliacionMidasProveedor.crearEjecucionConciliacion(calendarizacion);
        if(conciliacionMidasProveedor.validarDuplicidadEjecucion(ejecucionConciliacion)) {
			ejecucionConciliacion.setConciliacion(conciliacionMidasProveedor.crearConciliacion(ejecucionConciliacion));
			if(conciliacionMidasProveedor.validarDuplicidadProceso(ejecucionConciliacion)) {
				conciliacionMidasProveedor.ejecutarProcesoConciliacionE1(ejecucionConciliacion);
			}
		}

	}

	/**
	 * Método encargado de consultar la configuracion de la calendarizacion del proceso de conciliación etapa 1.
	 * @return
	 */
	public CalendarioEjecucionProcesoDTO obtenerCalendarizacionConciliacionEtapa1DN() {
		return conciliacionMidasProveedor.obtenerCalendarizacionConciliacion(ProcesoEnum.CONCILIACION_ETAPA_1_DN.getIdProceso(), CorresponsalEnum.OPENPAY.getNombre());
	}

}
