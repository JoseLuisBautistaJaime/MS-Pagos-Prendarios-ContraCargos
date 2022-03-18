/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.nmp.pagos.mimonte.async.AsyncLayer;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.CalendarioEjecucionProcesoBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.services.conciliacion.CalendarioEjecucionProcesoService;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorConciliacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @name CalendarioEjecucionProcesoController
 * @description Clase que expone el servicio REST para la gestión de las configuraciones de las ejecuciones de los procesos automatizados.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 17/03/2021 11:48 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite gestionar la información referente a las configuraciones de las ejecuciones de los procesos automatizados.", description = "REST API para gestionar las configuraciones de las ejecuciones de los procesos automatizados", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {"CalendarioEjecucionProceso" })
public class CalendarioEjecucionProcesoController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	private BeanFactory beanFactory;


	@Autowired
	private CalendarioEjecucionProcesoService calendarioEjecucionProcesoService;

	/**
	 * Permite ejecutar procesos de manera asincrona
	 */
	@Autowired
	AsyncLayer asyncLayer;
	
	/**
	 * Temporal format para los LOGs de timers
	 */
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private final Logger LOG = LoggerFactory.getLogger(CalendarioEjecucionProcesoController.class);


	/**
	 * Realiza la consulta de las configuraciones de las ejecuciones de los procesos automatizados.
	 * 
	 * @param filtroCalendarioEjecucionDTO Request con los criterios de búsqueda de las configuraciones de las ejecuciones de los procesos automatizados.
	 * @return El listado de configuraciones de las ejecuciones de los procesos automatizados que coincidieron con los criterios.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/calendarioejecucion/consulta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Realiza la consulta de las ejecuciones del proceso de conciliación registradas en el sistema.", tags = {"CalendarioEjecucionProceso" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultar(@RequestBody FiltroCalendarioEjecucionProcesoDTO filtroCalendarioEjecucionDTO) {
		
		LOG.info(">>>URL: POST /calendarioejecucion/consulta > REQUEST ENTRANTE: {}", filtroCalendarioEjecucionDTO);

		// Validaciones generales del request
		if (!ValidadorConciliacion.validateFiltroCalendarioEjecucionProcesoDTO(filtroCalendarioEjecucionDTO)) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR, CodigoError.NMP_PMIMONTE_0006);
		}

		List<CalendarioEjecucionProcesoDTO> consulta = calendarioEjecucionProcesoService.consultarByPropiedades(filtroCalendarioEjecucionDTO);

		if (null == consulta) {
			consulta = new ArrayList<>();
		} 

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH, consulta);
	}


	/**
	 * Servicio que permite dar de alta una nueva configuración de ejecución de un proceso y calendarizarlo.
	 *
	 * @param calendarioEjecucionRequestDTO Request con la información para el alta de la configuración de la ejecución de un proceso automatizado.
	 * @param createdBy              Usuario que crea la configuración.
	 * @return La información de la configuración registrada.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/calendarioejecucion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Servicio que permite dar de alta una nueva configuración para la ejecución de un proceso", tags = {"CalendarioEjecucionProceso" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response crearConfiguracion(@RequestBody CalendarioEjecucionProcesoRequestDTO calendarioEjecucionRequestDTO, @RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {

		LOG.info(">>>URL: POST /calendarioejecucion > REQUEST ENTRANTE: {}", calendarioEjecucionRequestDTO.toString());

		CalendarioEjecucionProcesoDTO calendarioEjecucionProcesoDTO = CalendarioEjecucionProcesoBuilder.buildCalendarioEjecucionProcesoDTOFromCalendarioEjecucionProcesoRequestDTO(calendarioEjecucionRequestDTO);

		// Validaciones generales del request
		if (!ValidadorConciliacion.validateSaveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO)) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR, CodigoError.NMP_PMIMONTE_0006);
		}


		Boolean isNuevo = true;

		if(calendarioEjecucionProcesoDTO.getId() != null) {
			calendarioEjecucionProcesoDTO.setLastModifiedDate(new Date());
			calendarioEjecucionProcesoDTO.setLastModifiedBy(createdBy);
			isNuevo = false;
		} else {
			calendarioEjecucionProcesoDTO.setCreatedDate(new Date());
			calendarioEjecucionProcesoDTO.setCreatedBy(createdBy);
			isNuevo = true;
		}

		CalendarioEjecucionProcesoDTO resultado = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO,isNuevo);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SAVE_SUCCESSFUL, resultado);

	}


}
