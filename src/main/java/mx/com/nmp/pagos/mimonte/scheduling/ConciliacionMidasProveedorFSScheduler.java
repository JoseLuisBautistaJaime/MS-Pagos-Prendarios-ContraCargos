/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO;
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


/**
 * @name ConciliacionMidasProveedorFSScheduler
 * @description Componente para la calendarización de la ejecución automática del proceso de conciliación etapa 1 (carga de movimientos nocturnos de MIDAS / carga de movimientos del proveedor)(configuración fines de semana).
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 21/12/2021 10:48 hrs.
 * @version 0.1
 */

@Configuration
@EnableScheduling
public class ConciliacionMidasProveedorFSScheduler implements SchedulingConfigurer {

	/**
	 * Los métodos comunes en la ejecución del proceso de conciliación
	 */
	@Autowired
	private ConciliacionCommon conciliacionCommon;

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

		Runnable runnableTask = () ->  lanzarConciliacionEtapa1FS();

		Trigger trigger = new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				String cronExpressions = obtenerCalendarizacionConciliacionEtapa1FS().getConfiguracionAutomatizacion();
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
	public void lanzarConciliacionEtapa1FS() {
        CalendarioEjecucionProcesoDTO calendarizacion = this.obtenerCalendarizacionConciliacionEtapa1FS();
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
	public CalendarioEjecucionProcesoDTO obtenerCalendarizacionConciliacionEtapa1FS() {
		return conciliacionCommon.obtenerCalendarizacionConciliacion(ProcesoEnum.CONCILIACION_ETAPA_1_FS.getIdProceso(), CorresponsalEnum.OPENPAY.getNombre());
	}

}
