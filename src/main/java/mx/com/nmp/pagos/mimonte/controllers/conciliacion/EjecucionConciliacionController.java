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
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaEjecucionConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaEjecucionConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.services.conciliacion.EjecucionConciliacionService;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.EjecucionConciliacionServiceImpl;
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
import java.util.List;

/**
 * @name EjecucionConciliacionController
 * @description Clase que expone el servicio REST para las consultas de información
 *              relacionadas con la ejecución del proceso de conciliación.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 04/11/2021 11:48 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar consultas de información referente a las ejecuciones de los procesos de conciliación.", description = "REST API para realizar consultas de informacion  sobre la ejecución de los procesos de conciliación", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {"EjecucionConciliacion" })
public class EjecucionConciliacionController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	private BeanFactory beanFactory;


	@Autowired
	private EjecucionConciliacionService ejecucionConciliacionService;

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
	private final Logger LOG = LoggerFactory.getLogger(EjecucionConciliacionController.class);


	/**
	 * Realiza la consulta de las ejecuciones del proceso de conciliación registradas en el sistema.
	 * 
	 * @param consultaEjecucionConciliacionRequestDTO Request con los criterios de búsqueda de las ejecuciones del proceso de conciliación.
	 * @return El listado de ejecuciones del proceso de conciliacion que coincidieron con los criterios.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/ejecucionconciliacion/consulta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Realiza la consulta de las ejecuciones del proceso de conciliación registradas en el sistema.", tags = {"EjecucionConciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consulta(@RequestBody ConsultaEjecucionConciliacionRequestDTO consultaEjecucionConciliacionRequestDTO) {
		
		LOG.info(">>>URL: POST /ejecucionconciliacion/consulta > REQUEST ENTRANTE: {}", consultaEjecucionConciliacionRequestDTO);
		
		ValidadorConciliacion.validateFechasPrimary(consultaEjecucionConciliacionRequestDTO.getFechaEjecucionDesde(), consultaEjecucionConciliacionRequestDTO.getFechaEjecucionHasta());
		List<ConsultaEjecucionConciliacionDTO> consulta = ejecucionConciliacionService.consultarByPropiedades(consultaEjecucionConciliacionRequestDTO);

		if (null == consulta) {
			consulta = new ArrayList<>();
		} 

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH, consulta);
	}

	/**
	* Realiza la consulta de la ejecución de la conciliación a partir del id de la ejecución.
	*
	* @param idEjecucion El identificador de la ejecución de la conciliación.
	* @return La información de la ejecución de la conciliación indicada.
	*/
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/ejecucionconciliacion/consulta/{idEjecucion}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Realiza la consulta de la ejecución de la conciliación a partir del id de la ejecución.", tags = {"EjecucionConciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultaByIdEjecucion(@PathVariable(value = "idEjecucion", required = true) Long idEjecucion) {

		LOG.info(">>>URL: GET /ejecucionconciliacion/consulta/{idEjecucion} > REQUEST ENTRANTE: {}", idEjecucion);

		LOG.info(">> consultaByIdEjecucion({})", idEjecucion);
		ConsultaEjecucionConciliacionDTO consultaEjecucion = ejecucionConciliacionService.consultarByIdEjecucion(idEjecucion);

		LOG.info(">>>URL: GET /ejecucionconciliacion/consulta/{idEjecucion} > RESPONSE: {}", consultaEjecucion);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH, consultaEjecucion);
	}

}
