/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.CalendarioEjecucionProcesoBuilder;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.CalendarioEjecucionProcesoRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroCalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.scheduling.ConciliacionEstadoCuentaScheduler;
import mx.com.nmp.pagos.mimonte.scheduling.ConciliacionLayoutsScheduler;
import mx.com.nmp.pagos.mimonte.scheduling.ConciliacionMidasProveedorScheduler;
import mx.com.nmp.pagos.mimonte.scheduling.PreconciliacionScheduler;
import mx.com.nmp.pagos.mimonte.services.conciliacion.CalendarioEjecucionProcesoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * @name CalendarioEjecucionProcesoServiceImpl
 * @description Clase de capa de servicio para la calendarización de los procesos automatizados que sirve para
 *              realizar operaciones de lógica de negocios
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 30/11/2021 10:48 hrs.
 * @version 0.1
 */
@Service("calendarioEjecucionProcesoServiceImpl")
public class CalendarioEjecucionProcesoServiceImpl implements CalendarioEjecucionProcesoService {


	@Autowired
	private CalendarioEjecucionProcesoRepository calendarioEjecucionProcesoRepository;

	@Autowired
	private PreconciliacionScheduler preconciliacionScheduler;

	@Autowired
	private ConciliacionMidasProveedorScheduler conciliacionMidasProveedorScheduler;

	@Autowired
	private ConciliacionEstadoCuentaScheduler conciliacionEstadoCuentaScheduler;

	@Autowired
	private ConciliacionLayoutsScheduler conciliacionLayoutsScheduler;



	/**
	 * Metodo que realiza una busqueda a partir de un objeto de tipo
	 * FiltroCalendarioEjecucionProcesoDTO devolviendo como resultado una lista de tipo
	 * CalendarioEjecucionProcesoDTO.
	 */
	@Override
	public List<CalendarioEjecucionProcesoDTO> consultarByPropiedades(FiltroCalendarioEjecucionProcesoDTO filtroDTO) {

		// Declaracion de objetos necesarios
		List<CalendarioEjecucionProcesoDTO> result = null;

		// Se hace UPPERCASE de nombre corresponsal
		filtroDTO.setCorresponsal(null != filtroDTO.getCorresponsal() ? filtroDTO.getCorresponsal().toUpperCase() : null);

		result = calendarioEjecucionProcesoRepository.findByPropiedades(filtroDTO.getIdCalendario(), filtroDTO.getIdProceso(), filtroDTO.getActivo(), filtroDTO.getReintentos(), null !=  filtroDTO.getCorresponsal() ? CorresponsalEnum.getByNombre( filtroDTO.getCorresponsal()) : null );

		return result;
	}

	/**
	 * Metodo que guarda una configuración de ejecución.
	 *
	 * @param calendarioEjecucionProcesoDTO
	 * @param isNuevo
	 * @return
	 */
	@Override
	public CalendarioEjecucionProcesoDTO saveCalendarioEjecucionProceso(CalendarioEjecucionProcesoDTO calendarioEjecucionProcesoDTO, Boolean isNuevo){

		CalendarioEjecucionProcesoDTO resultado;

		CalendarioEjecucionProceso elementoNuevo= CalendarioEjecucionProcesoBuilder.buildCalendarioEjecucionProcesoFromCalendarioEjecucionProcesoDTO(calendarioEjecucionProcesoDTO);

		if(isNuevo){
			resultado = CalendarioEjecucionProcesoBuilder.buildCalendarioEjecucionProcesoDTOFromCalendarioEjecucionProceso(calendarioEjecucionProcesoRepository.save(elementoNuevo));
		}else {
			Optional<CalendarioEjecucionProceso> elementoGuardado = calendarioEjecucionProcesoRepository.findById(elementoNuevo.getId());
			CalendarioEjecucionProceso elementoEditado =  elementoGuardado.orElse(null);
			if(elementoEditado != null && elementoEditado.getId() != null && elementoEditado.getId() > 0 ) {
				elementoEditado.setProceso(elementoNuevo.getProceso());
				elementoEditado.setConfiguracion(elementoNuevo.getConfiguracion());
				elementoEditado.setReintentos(elementoNuevo.getReintentos());
				elementoEditado.setRangoDiasCoberturaMin(elementoNuevo.getRangoDiasCoberturaMin());
				elementoEditado.setRangoDiasCoberturaMax(elementoNuevo.getRangoDiasCoberturaMax());
				elementoEditado.setProveedor(elementoNuevo.getProveedor());
				elementoEditado.setActivo(elementoNuevo.getActivo());
				elementoEditado.setLastModifiedBy(elementoNuevo.getLastModifiedBy());
				elementoEditado.setLastModifiedDate(elementoNuevo.getLastModifiedDate());
				resultado = CalendarioEjecucionProcesoBuilder.buildCalendarioEjecucionProcesoDTOFromCalendarioEjecucionProceso(calendarioEjecucionProcesoRepository.save(elementoEditado));
			}else {
				resultado = CalendarioEjecucionProcesoBuilder.buildCalendarioEjecucionProcesoDTOFromCalendarioEjecucionProceso(calendarioEjecucionProcesoRepository.save(elementoNuevo));
			}
		}

		if(resultado != null && resultado.getId() != null && resultado.getId() > 0) {
			String cronExpressions = resultado.getConfiguracionAutomatizacion();
			if (!StringUtils.isEmpty(cronExpressions)) {
				ScheduledExecutorService localExecutorService= Executors.newSingleThreadScheduledExecutor();
				TaskScheduler scheduler = new ConcurrentTaskScheduler(localExecutorService);
				if (resultado.getProceso().getId().equals(ProcesoEnum.PRE_CONCILIACION.getIdProceso())) {
					scheduler.schedule( () -> preconciliacionScheduler.lanzarPreconciliacionAutomatizada(resultado), new CronTrigger(cronExpressions));
				} else if(resultado.getProceso().getId().equals(ProcesoEnum.CONCILIACION_ETAPA_1.getIdProceso())){
					scheduler.schedule( () -> conciliacionMidasProveedorScheduler.lanzarConciliacionEtapa1(resultado), new CronTrigger(cronExpressions));
				} else if(resultado.getProceso().getId().equals(ProcesoEnum.CONCILIACION_ETAPA_2.getIdProceso())){
					scheduler.schedule( () -> conciliacionEstadoCuentaScheduler.lanzarConciliacionEtapa2(resultado), new CronTrigger(cronExpressions));
				}else if(resultado.getProceso().getId().equals( ProcesoEnum.CONCILIACION_ETAPA_3.getIdProceso())){
					scheduler.schedule( () -> conciliacionLayoutsScheduler.lanzarConciliacionEtapa3(resultado), new CronTrigger(cronExpressions));
				}
			}
		}

		return resultado;
	}

}
