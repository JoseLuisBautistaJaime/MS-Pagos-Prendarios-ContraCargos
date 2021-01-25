/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.nmp.pagos.mimonte.builder.CorresponsalBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.dto.CorresponsalDTO;
import mx.com.nmp.pagos.mimonte.dto.CorresponsalesRespPostDTO;
import mx.com.nmp.pagos.mimonte.services.conciliacion.CorresponsalesService;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * @name CorresponsalesController
 * @description Clase de tipo Controlador spring que expone los servicios
 *              relacionados con el catalogo de proveedores.
 *
 * @author Daniel Hernandez danhernandez@quarksoft.net
 * @creationDate 06/11/2020
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar operaciones relacionadas con los Corresponsales registrados.", description = "REST API para Corresponsales", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Corresponsales" })
public class CorresponsalesController {

	/**
	 * Fabrica de beans
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Imprime logs de la aplicacion
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(CorresponsalesController.class);

	/**
	 * Service para Corresponsales
	 */
	@Autowired
	@Qualifier("corresponsalesService")
	private CorresponsalesService corresponsalesService;

	/**
	 * Realiza la consulta de comisiones para la conciliacion.
	 * 
	 * @param folio
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/corresponsales", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa todos los objetos del catalogo de Corresponsales.", tags = {
			"Corresponsales" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Corresponsales Encontradas"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findAll() {
		
		LOG.info(">>> URL: GET /catalogos/corresponsales > ");
		
		// Se realiza la consulta
		List<CorresponsalesRespPostDTO> corresponsalDTOList = CorresponsalBuilder
				.buildCorresponsalRespPostDTOListfromCorresponsalDTOList(
						(List<CorresponsalDTO>) corresponsalesService.findAll());
		
		LOG.info(">>> GET /catalogos/corresponsales RESPONSE: {}", null != corresponsalDTOList ? corresponsalDTOList : "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				null != corresponsalDTOList ? corresponsalDTOList : new ArrayList<>());
	}

}
