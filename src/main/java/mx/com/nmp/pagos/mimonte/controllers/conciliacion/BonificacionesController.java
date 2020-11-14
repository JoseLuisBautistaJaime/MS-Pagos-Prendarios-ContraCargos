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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.BonificacionDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.services.conciliacion.BonificacionesService;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * @name BonificacionesController
 * @description Clase de tipo Controlador spring que expone los servicios
 *              relacionados con Bonificaciones
 *
 * @author Jorge Galvez jgalvez@quarksoft.net
 * @creationDate 11/11/2020 11:21 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar operaciones relacionadas con Bonificaciones.", description = "REST API para Bonificaciones", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Bonificaciones" })
public class BonificacionesController {

	/**
	 * Fabrica de beans
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Imprime logs de la aplicacion
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BonificacionesController.class);

	/**
	 * Service para Devoluciones
	 */
	@Autowired
	private BonificacionesService bonificacionesService;


	/**
	 * Permite consultar bonificaciones para la conciliacion
	 * @param folio
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/bonificaciones/consulta/{folio}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Consulta las bonificaciones para la conciliacion.", tags = {
			"Bonificaciones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta Bonificaciones Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultar(@PathVariable(value = "folio", required = true) Long folio) {

		LOG.info(">>>URL: GET /bonificaciones/consulta > REQUEST ENTRANTE: {}", folio);

		List<BonificacionDTO> respuesta = bonificacionesService.consulta(folio);

		if (null == respuesta)
			respuesta = new ArrayList<BonificacionDTO>();
			
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta bonificaciones exitosa", respuesta);
	}


	/**
	 * Realiza la administración de devoluciones a nivel entidad - Actualización de
	 * la fecha y liquidación para las devoluciones.
	 * 
	 * @param devolucionUpdtDTOList
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/bonificaciones", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite agregar una nueva bonificacion.", tags = {
			"Bonificaciones" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Registro de Bonificacion Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response agregar(@RequestBody BonificacionDTO bonificacion,
			@RequestHeader(required = true, value = CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		
		LOG.info(">>>URL: PUT /bonificaciones > REQUEST ENTRANTE: {}", bonificacion);
		
		// Validacion de objeto y atributos
		if (bonificacion == null || userRequest == null || "".equals(userRequest))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		// Registrar
		BonificacionDTO result = bonificacionesService.save(bonificacion, userRequest);

		// Respuesta
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Registro bonificacion exitosa.",
				result);
	}

	/**
	 * Realiza la administración de devoluciones a nivel entidad - Enviar solicitud
	 * de devoluciones.
	 * 
	 * @param devolucionesIdsMovimientosDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/bonificaciones/{idBonificacion}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "Elimina una bonificacion de la conciliacion.", tags = {
			"Bonificaciones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Eliminacion bonificacion exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response solicitar(@PathVariable(value = "idBonificacion", required = true) Long idBonificacion,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		
		LOG.info(">>>URL: DELETE /bonificaciones/{idBonificacion} > REQUEST ENTRANTE: {}", idBonificacion);
		
		if (idBonificacion == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		try {
			bonificacionesService.delete(idBonificacion, userRequest);

		} catch (ConciliacionException ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_104.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_104);
		}
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Eliminacion bonificacion exitosa", null);
	}

}
