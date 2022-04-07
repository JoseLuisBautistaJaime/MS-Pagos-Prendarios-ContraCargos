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
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DiaInhabilDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroDiaInhabilDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.services.conciliacion.DiaInhabilService;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorConciliacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @name DiaInhabilController
 * @description Clase que expone el servicio REST para registar y consultar los elemntos del
 *  *              catálogo de días inhábiles.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 16/12/2021 11:48 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar operaciones sobre el catálogo de días inhábiles.", description = "REST API para realizar operaciones sobre el catálogo de días inhábiles", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {"DiaInhabil" })
public class DiaInhabilController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	private BeanFactory beanFactory;


	@Autowired
	private DiaInhabilService diaInhabilService;

	/**
	 * Permite ejecutar procesos de manera asincrona
	 */
	@Autowired
	AsyncLayer asyncLayer;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private final Logger logger = LoggerFactory.getLogger(DiaInhabilController.class);


	/**
	 * Realiza la consulta de los días inhábiles registrados.
	 *
	 * @param filtroDiaInhabilDTO Request con los criterios de búsqueda de los días inhabiles.
	 * @return El listado de días inhábiles que coincidieron con los criterios.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/diainhabil/consulta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Realiza la consulta de los días inhábiles registrados en el sistema.", tags = {"DiaInhabil" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultar(@RequestBody FiltroDiaInhabilDTO filtroDiaInhabilDTO) {

		logger.info(">>>URL: POST /diainhabil/consulta > REQUEST ENTRANTE: {}", filtroDiaInhabilDTO);

		// Validaciones generales del request
		if (!ValidadorConciliacion.validateFiltroDiaInhabilDTO(filtroDiaInhabilDTO)) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR, CodigoError.NMP_PMIMONTE_0006);
		}

		List<DiaInhabilDTO> consulta = diaInhabilService.consultarByPropiedades(filtroDiaInhabilDTO);

		if (null == consulta) {
			consulta = new ArrayList<>();
		}

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH, consulta);
	}


	/**
	 * Servicio que permite dar de alta un día inhábil
	 *
	 * @param diaInhabilDTO Request con la información para el alta del día inhábil.
	 * @return La información del del día inhábil registrado.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/diainhabil", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Servicio que permite dar de alta un día inhábil.", tags = {"DiaInhabil"})
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response saveDiaInhabil(@RequestBody DiaInhabilDTO diaInhabilDTO) {

		logger.info(">>>URL: POST /diainhabil > REQUEST ENTRANTE: {}", diaInhabilDTO);

		// Validacion general de objeto y atributos
		if (!ValidadorConciliacion.validateSaveDiaInhabil(diaInhabilDTO)) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR, CodigoError.NMP_PMIMONTE_0008);
		}

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SAVE_SUCCESSFUL,	diaInhabilService.saveDiaInhabil(diaInhabilDTO));
	}


}
