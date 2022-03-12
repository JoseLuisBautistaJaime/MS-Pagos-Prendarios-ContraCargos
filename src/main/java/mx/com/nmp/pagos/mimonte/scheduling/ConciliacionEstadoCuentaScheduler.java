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
import mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionEstadoCuentaService;
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
 * @name ConciliacionEstadoCuentaScheduler
 @description Componente para la calendarización de la ejecución automática del proceso de conciliación Etapa 2 (Carga de Movimientos Estado de Cuenta)
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 08/02/2021 10:48 hrs.
 * @version 0.1
 */

@Component
public class ConciliacionEstadoCuentaScheduler {

	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private final Logger LOG = LoggerFactory.getLogger(ConciliacionEstadoCuentaScheduler.class);

	/**
	 * Los métodos para consultar y cargar los movimientos del estado de cuenta.
	 */
	@Autowired
	private ConciliacionEstadoCuentaService conciliacionEstadoCuenta;

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

		List<CalendarioEjecucionProcesoDTO> listaConfiguraciones = obtenerCalendarizacionConciliacionEtapa2();

		for (CalendarioEjecucionProcesoDTO configuracion : listaConfiguraciones) {
			String cronExpressions = configuracion.getConfiguracionAutomatizacion();
			if (!StringUtils.isEmpty(cronExpressions)) {
				ScheduledExecutorService localExecutorService= Executors.newSingleThreadScheduledExecutor();
				TaskScheduler scheduler = new ConcurrentTaskScheduler(localExecutorService);
				scheduler.schedule( () -> lanzarConciliacionEtapa2(configuracion), new CronTrigger(cronExpressions));
			}
		}
	}

	/**
	 * Método encargado de lanzar la ejecución del proceso de conciliación etapa 2.
	 *
	 */
	public void lanzarConciliacionEtapa2(CalendarioEjecucionProcesoDTO calendarizacion) {
		Conciliacion conciliacionSEC  = conciliacionEstadoCuenta.buscarConciliacionSinEstadoCuenta(calendarizacion);
		if(conciliacionSEC != null && conciliacionSEC.getId() != 0) {
			EjecucionConciliacion ejecucionConciliacion = conciliacionEstadoCuenta.buscarEjecucionConciliacion(conciliacionSEC);
			if(ejecucionConciliacion != null && ejecucionConciliacion.getId() != 0 ) {
				conciliacionEstadoCuenta.ejecutarProcesoConciliacionE2(ejecucionConciliacion);
			}else{
				LOG.info("No se encontraron los datos de ejecución del proceso");
			}
		}else{
			LOG.info("No se encontraron procesos sin el estado de cuenta conciliado");
		}
	}

	/**
	 * Método encargado de consultar la configuracion de la calendarizacion del proceso de conciliación etapa 2.
	 * @return
	 */
	public List<CalendarioEjecucionProcesoDTO>  obtenerCalendarizacionConciliacionEtapa2() {
		return conciliacionEstadoCuenta.obtenerCalendarizacionConciliacion(ProcesoEnum.CONCILIACION_ETAPA_2.getIdProceso(), CorresponsalEnum.OPENPAY.getNombre());
	}


}
