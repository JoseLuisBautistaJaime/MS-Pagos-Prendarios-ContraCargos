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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.EjecucionPreconciliacionService;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.EjecucionPreconciliacionServiceImpl;
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
import java.util.*;

/**
 * @name EjecucionPreconciliacionController
 * @description Clase que expone el servicio REST para las consultas de información
 *  *              relacionadas con la ejecución del proceso de preconciliación.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 04/11/2021 11:48 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar consultas de información referente a las ejecuciones de los procesos de preconciliación.", description = "REST API para realizar consultas de información  sobre la ejecución de los procesos de preconciliación", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {"EjecucionPreconciliación" })
public class EjecucionPreconciliacionController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	private BeanFactory beanFactory;


	@Autowired
	private EjecucionPreconciliacionService ejecucionPreconciliacionService;

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
	private final Logger LOG = LoggerFactory.getLogger(EjecucionPreconciliacionController.class);


	/**
	 * Realiza la consulta de las ejecuciones del proceso de preconciliación registradas en el sistema.
	 *
	 * @param consultaEjecucionPreconciliacionRequestDTO Request con los criterios de búsqueda de las ejecuciones del proceso de preconciliación.
	 * @return El listado de ejecuciones del proceso de preconciliacion que coincidieron con los criterios.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/ejecucionpreconciliacion/consulta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Realiza la consulta de las ejecuciones del proceso de preconciliación registradas en el sistema.", tags = {"EjecucionPreconciliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consulta(@RequestBody ConsultaEjecucionPreconciliacionRequestDTO consultaEjecucionPreconciliacionRequestDTO) {

		LOG.info(">>>URL: POST /ejecucionpreconciliacion/consulta > REQUEST ENTRANTE: {}", consultaEjecucionPreconciliacionRequestDTO);

		ValidadorConciliacion.validateFechasPrimary(consultaEjecucionPreconciliacionRequestDTO.getFechaEjecucionDesde(), consultaEjecucionPreconciliacionRequestDTO.getFechaEjecucionHasta());
		List<ConsultaEjecucionPreconciliacionDTO> consulta = ejecucionPreconciliacionService.consultarByPropiedades(consultaEjecucionPreconciliacionRequestDTO);

		if (null == consulta) {
			consulta = new ArrayList<>();
		}

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH, consulta);
	}

}
