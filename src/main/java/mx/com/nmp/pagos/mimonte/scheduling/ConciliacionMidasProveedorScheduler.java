/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EjecucionConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ProcesoEnum;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionMidasProveedorService;
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
 * @name ConciliacionMidasProveedorScheduler
 * @description Componente para la calendarización de la ejecución automática del proceso de conciliación etapa 1 (carga de movimientos nocturnos de MIDAS / carga de movimientos del proveedor).
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 04/02/2021 10:48 hrs.
 * @version 0.1
 */

@Component
public class ConciliacionMidasProveedorScheduler {

	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private final Logger logger = LoggerFactory.getLogger(ConciliacionMidasProveedorScheduler.class);

	/**
	 * Los métodos para consultar y cargar los movimientos del estado de cuenta.
	 */
	@Autowired
	private ConciliacionMidasProveedorService conciliacionMidasProveedor;

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

		List<CalendarioEjecucionProcesoDTO> listaConfiguraciones = obtenerCalendarizacionConciliacionEtapa1();

		for (CalendarioEjecucionProcesoDTO configuracion : listaConfiguraciones) {
			String cronExpressions = configuracion.getConfiguracionAutomatizacion();
			if (!StringUtils.isEmpty(cronExpressions)) {
				ScheduledExecutorService localExecutorService= Executors.newSingleThreadScheduledExecutor();
				TaskScheduler scheduler = new ConcurrentTaskScheduler(localExecutorService);
				scheduler.schedule( () -> lanzarConciliacionEtapa1(configuracion), new CronTrigger(cronExpressions));
			}
		}

	}


	/**
	 * Método encargado de lanzar la ejecución del proceso de conciliación etapa 1.
	 *
	 */
	public void lanzarConciliacionEtapa1(CalendarioEjecucionProcesoDTO calendarizacion) {
        EjecucionConciliacion ejecucionConciliacion  = conciliacionMidasProveedor.crearEjecucionConciliacion(calendarizacion);
        if(conciliacionMidasProveedor.validarDuplicidadEjecucion(ejecucionConciliacion)) {
            ejecucionConciliacion.setConciliacion(conciliacionMidasProveedor.crearConciliacion(ejecucionConciliacion));
			if(conciliacionMidasProveedor.validarDuplicidadProceso(ejecucionConciliacion)) {
				conciliacionMidasProveedor.ejecutarProcesoConciliacionE1(ejecucionConciliacion);
			} else{
				logger.info("Proceso de conciliación duplicado");
			}
		}else{
			logger.info("Ejecución del proceso duplicado");
		}

	}

	/**
	 * Método encargado de consultar la configuracion de la calendarizacion del proceso de conciliación etapa 1.
	 * @return
	 */
	public List<CalendarioEjecucionProcesoDTO> obtenerCalendarizacionConciliacionEtapa1() {
		return conciliacionMidasProveedor.obtenerCalendarizacionConciliacion(ProcesoEnum.CONCILIACION_ETAPA_1.getIdProceso(), CorresponsalEnum.OPENPAY.getNombre());
	}

}
