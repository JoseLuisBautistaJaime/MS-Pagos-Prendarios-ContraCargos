/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.dto.ComisionSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionDeleteDTO;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * @name ComisionesController
 * @description Clase de tipo Controlador spring que expone los servicios
 *              relacionados con Comisiones
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 02/04/2019 14:56 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar operaciones relacionadas con Comisiones.", description = "REST API para Comisiones", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Comisiones" })
public class ComisionesController {

	/**
	 * Fabrica de beans
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Imprime logs de la aplicacion
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(ComisionesController.class);

	/**
	 * Service para Comisiones
	 */
//	@Autowired
//	@Qualifier("comisionesService")
//	private ComisionesService comisionesService;

	/**
	 * Permite agregar y modificar comisiones al listado de comisiones del estado de
	 * cuenta
	 * 
	 * @param comisionSaveDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/comisiones/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite agregar y modificar comisiones al listado de comisiones del estado de cuenta.", tags = {
			"Comisiones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response save(@RequestBody ComisionSaveDTO comisionSaveDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		// There is no dummy response, just empty response
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				new String(""));
	}

	/**
	 * Permite eliminar comisiones al listado de comisiones del estado de cuenta.
	 * Sólo se pueden eliminar comisiones que han sido agregadas
	 * 
	 * @param comisionDeleteDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/conciliacion/comisiones/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "Permite eliminar comisiones al listado de comisiones del estado de cuenta. Sólo se pueden eliminar comisiones que han sido agregadas.", tags = {
			"Comisiones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Eliminacion correcta"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response delete(@RequestBody ComisionDeleteDTO comisionDeleteDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		// There is no dummy response, just empty response
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				new String(""));
	}
}
