/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.ComisionSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionDeleteDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionSaveResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransaccionesRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ComisionesService;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorConciliacion;

/**
 * @name ComisionesController
 * @description Clase de tipo Controlador spring que expone los servicios
 *              relacionados con Comisiones.
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
	@Autowired
	@Qualifier("comisionesService")
	private ComisionesService comisionesService;

	/**
	 * Permite agregar y modificar comisiones al listado de comisiones del estado de
	 * cuenta
	 * 
	 * @param comisionSaveDTO
	 * @param userRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/comisiones", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite agregar y modificar comisiones al listado de comisiones del estado de cuenta.", tags = {
			"Comisiones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response save(@RequestBody ComisionSaveDTO comisionSaveDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		// Declaracion de objetos
		Map<String, Object> result = null;
		ComisionSaveResponseDTO comisionSaveResponseDTO = null;
		String msgResponse = null;
		// Se realiza validacion de request
		if (!ValidadorConciliacion.validateComisionSaveDTO(comisionSaveDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		// Se guarda la comision
		result = comisionesService.save(comisionSaveDTO, userRequest);
		// Se valida que el resultado del guardado no sea nulo
		if (null == result)
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND);
		// Se asigna la bandera de registro uevo y el objeto de resultado a un mapa para
		// saber que mensaje mostrar en el response
		comisionSaveResponseDTO = (ComisionSaveResponseDTO) result.get("result");
		msgResponse = null != result.get("flag") && ((Boolean) result.get("flag")) ? "Alta exitosa."
				: null != result.get("flag") && !((Boolean) result.get("flag")) ? "Actualizacion exitosa."
						: "Operacion exitosa.";
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), msgResponse, comisionSaveResponseDTO);
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
	@DeleteMapping(value = "/comisiones", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "Permite eliminar comisiones al listado de comisiones del estado de cuenta. Sólo se pueden eliminar comisiones que han sido agregadas.", tags = {
			"Comisiones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Eliminacion correcta"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response delete(@RequestBody ComisionDeleteDTO comisionDeleteDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		if (!ValidadorConciliacion.validateComisionDeleteDTO(comisionDeleteDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		try {
			comisionesService.delete(comisionDeleteDTO, userRequest);
		} catch (ConciliacionException cex) {
			throw cex;
		}
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_DELETE,
				null);
	}

	/**
	 * Realiza la consulta de transacciones realizadas en un periodo de tiempo
	 * marcado.
	 * 
	 * @param SolicitarPagosRequestDTO
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/comisiones/consulta/transacciones", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Realiza la consulta de transacciones realizadas en un periodo de tiempo marcado.", tags = {
			"Comisiones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultaComisionesTransacciones(
			@RequestBody ComisionesTransaccionesRequestDTO comisionesTransaccionesRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		if (!ValidadorConciliacion.validateComisionesTransaccionesRequestDTO(comisionesTransaccionesRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta Exitosa.",
				comisionesService.findByFechasAndComision(comisionesTransaccionesRequestDTO, userRequest));
	}

	/**
	 * Realiza la consulta de comisiones para la conciliacion.
	 * 
	 * @param folio
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/comisiones/consulta/{folio}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Realiza la consulta de comisiones para la conciliacion.", tags = {
			"Comisiones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultaComisiones(@PathVariable(value = "folio", required = true) Integer folio) {
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta exitosa",
				comisionesService.findByFolio(folio));
	}

}
