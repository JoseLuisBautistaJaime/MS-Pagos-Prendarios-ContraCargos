/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import mx.com.nmp.pagos.mimonte.builder.CodigoEstadoCuentaBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaReqSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaReqUpdtDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaUpdtDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.exception.CatalogoNotFoundException;
import mx.com.nmp.pagos.mimonte.services.impl.CodigoEstadoCuentaServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorCatalogo;

/**
 * @name CodigoEstadoCuentaController
 * @description Clase que expone el servicio REST para las operaciones
 *              relacionadas con el catalogo de codigos de estado de cuenta
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 05/03/2019 13:09 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar operaciones sobre el catalogo de codigos de estado de cuenta.", description = "REST API para realizar operaciones sobre el catalogo de codigos de estado de cuenta", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"CodigoEstadoCuenta" })
public class CodigoEstadoCuentaController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(CodigoEstadoCuentaController.class);

	/**
	 * Service para el catalogo de codigos de estados de cuenta
	 */
	@Autowired
	@Qualifier("codigoEstadoCuentaServiceImpl")
	private CodigoEstadoCuentaServiceImpl codigoEstadoCuentaServiceImpl;

	/**
	 * Guarda un nuevo catalogo Codigo Estado de Cuenta
	 * 
	 * @param codigoEstadoCuentaReqSaveDTO
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/catalogos/codigos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Crea un nuevo catalogo de codigos de estado de cuenta.", tags = {
			"CodigoEstadoCuenta" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Codigo de estado de cuenta creado"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response save(@RequestBody CodigoEstadoCuentaReqSaveDTO codigoEstadoCuentaReqSaveDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
		
		log.info(">>> POST /catalogos/codigos REQUEST: {}", null != codigoEstadoCuentaReqSaveDTO ? codigoEstadoCuentaReqSaveDTO : "");
		
		// Valida que el objeto de request y sus atributos sean correctos
		if (!ValidadorCatalogo.validateCodigoEstadoCuentaSave(codigoEstadoCuentaReqSaveDTO))
			throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR, CodigoError.NMP_PMIMONTE_0008);
		CodigoEstadoCuentaUpdtDTO codigo = null;
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTOSave = null;
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTOResp = null;
		try {
			// Construye un DTO estandar
			codigoEstadoCuentaDTOSave = CodigoEstadoCuentaBuilder
					.buildCodigoEstadoCuentaDTOFromCodigoEstadoCuentaReqSaveDTO(codigoEstadoCuentaReqSaveDTO,
							new Date(), null);
			// Guarda el objeto
			codigoEstadoCuentaDTOResp = codigoEstadoCuentaServiceImpl.save(codigoEstadoCuentaDTOSave, createdBy);
			// Construye el objeto de respuesta
			codigo = CodigoEstadoCuentaBuilder
					.buildCodigoEstadoCuentaUpdtDTOFromCodigoEstadoCuentaDTO(codigoEstadoCuentaDTOResp);
		} catch (EmptyResultDataAccessException ex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_001);
		} catch (RuntimeException rex) {
			throw rex;
		}
		
		log.info(">>> POST /catalogos/codigos RESPONSE: {}", null != codigo ? codigo : "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				codigo);
	}

	/**
	 * Actualiza un Codigo Estado de Cuenta
	 * 
	 * @param codigoEstadoCuentaDTOReq
	 * @param lastModifiedBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/catalogos/codigos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Actualiza un catalogo codigo de estado de cuenta.", tags = {
			"CodigoEstadoCuenta" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Codigo de estado de cuenta actualizado"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response update(@RequestBody CodigoEstadoCuentaReqUpdtDTO codigoEstadoCuentaDTOReq,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {
		
		log.info(">>> PUT /catalogos/codigos REQUEST: {}", null != codigoEstadoCuentaDTOReq ? codigoEstadoCuentaDTOReq : "");
		
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTOResp = null;
		// Valida que el objeto de request y sus atributos sean correctos
		if (!ValidadorCatalogo.validateCodigoEstadoCuentaUpdate(codigoEstadoCuentaDTOReq))
			throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR, CodigoError.NMP_PMIMONTE_0008);
		// Construye un DTO estandar
		codigoEstadoCuentaDTO = CodigoEstadoCuentaBuilder
				.buildCodigoEstadoCuentaDTOFromCodigoEstadoCuentaReqUpdtDTO(codigoEstadoCuentaDTOReq, null, new Date());
		// Actualiza el codigo estado de cuenta
		codigoEstadoCuentaDTOResp = codigoEstadoCuentaServiceImpl.update(codigoEstadoCuentaDTO, lastModifiedBy);
		// Construye el objeto de respuesta
		CodigoEstadoCuentaUpdtDTO codigo = CodigoEstadoCuentaBuilder
				.buildCodigoEstadoCuentaUpdtDTOFromCodigoEstadoCuentaDTO(codigoEstadoCuentaDTOResp);
		
		log.info(">>> PUT /catalogos/codigos RESPONSE: {}", null != codigo ? codigo : "");
		
		// Regresa el resultado
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_UPDATE,
				codigo);
	}

	/**
	 * Regresa un Codigo de estado de Cuenta por id
	 * 
	 * @param idCodigo
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/codigos/{idCodigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa un objeto de catalogo codigo de estado de cuenta en base a su id", tags = {
			"CodigoEstadoCuenta" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Codigo de estado de cuenta encontrado"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findById(@PathVariable(value = "idCodigo", required = true) Long idCodigo) {
		
		log.info(">>> GET /catalogos/codigos/{idCodigo} REQUEST: {}", null != idCodigo ? idCodigo : "");
		
		CodigoEstadoCuentaUpdtDTO codigo = null;
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		try {
			// Encuentra un codigo estado cuente
			codigoEstadoCuentaDTO = codigoEstadoCuentaServiceImpl.findById(idCodigo);
			// Construye el DTO de respuesta
			codigo = CodigoEstadoCuentaBuilder
					.buildCodigoEstadoCuentaUpdtDTOFromCodigoEstadoCuentaDTO(codigoEstadoCuentaDTO);
		} catch (EmptyResultDataAccessException eex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_001);
		}
		
		log.info(">>> GET /catalogos/codigos/{idCodigo} RESPONSE: {}", null != codigo ? codigo : "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS, codigo);
	}

	/**
	 * Regresa uno o mas objetos CodigoEstadoCuenta por id de entidad relacionada
	 * 
	 * @param idEntidad
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/codigos/entidad/{idEntidad}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa uno o mas objetos de catalogo codigo de estado de cuenta en base a un id de su entidad asociada a este", tags = {
			"CodigoEstadoCuenta" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Codigos de estado de cuenta encontrados"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByEntidadId(@PathVariable(value = "idEntidad", required = true) Long idEntidad) {
		
		log.info(">>> GET /catalogos/codigos/entidad/{idEntidad} REQUEST: {}", null != idEntidad ? idEntidad : "");
		
		List<CodigoEstadoCuentaUpdtDTO> lst = null;
		// Encuentra un codigo estado cuenta por id de entidad
		try {
			lst = codigoEstadoCuentaServiceImpl.findByEntidadId(idEntidad);
		} catch (EmptyResultDataAccessException eex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_001);
		}
		
		log.info(">>> GET /catalogos/codigos/entidad/{idEntidad} RESPONSE: {}, TOTAL: {}", null != lst ? lst : "", null != lst ? lst.size() : "0");
		
		// Regresa el resultado
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				null != lst ? lst : new ArrayList<>());
	}

	/**
	 * Hace una eliminacion de un codigo de estaod de cuenta
	 * 
	 * @param idCodigo
	 * @param lastModifiedBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/catalogos/codigos/{idCodigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "Elimina un catalogo codigo de estado de cuenta.", tags = {
			"CodigoEstadoCuenta" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Codigo de estado de cuenta eliminado"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response deleteById(@PathVariable(value = "idCodigo", required = true) Long idCodigo,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {
		
		log.info(">>> DELETE /catalogos/codigos/{idCodigo} REQUEST: {}", null != idCodigo ? idCodigo : "");
		
		// Elimina un codigo estado cuenta por id
		try {
			codigoEstadoCuentaServiceImpl.deleteById(idCodigo);
		} catch (EmptyResultDataAccessException eex) {
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND);
		}
		
		log.info(">>> DELETE /catalogos/codigos/{idCodigo} RESPONSE: {}", "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_DELETE,
				null);
	}

	/**
	 * Regresa todos los catalogos de codigo estado de cuenta
	 * 
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/codigos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa todos los catalogo de codigo estado de cuenta", tags = {
			"CodigoEstadoCuenta" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Codigos de estado de cuenta encontrados"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findAll() {
		List<CodigoEstadoCuentaUpdtDTO> lst = null;
		// Se encuentran todos los codigos de estado cuenta
		@SuppressWarnings("unchecked")
		List<CodigoEstadoCuentaDTO> lstResult = (List<CodigoEstadoCuentaDTO>) codigoEstadoCuentaServiceImpl.findAll();
		// Se construye una lista de respuetsa de codigos de estado de cuenta
		lst = CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaUpdtDTOListFromCodigoEstadoCuentaDTOList(lstResult);
		
		log.info(">>> GET /catalogos/codigos RESPONSE: {}, TOTAL: {}", null != lst ? lst : "", null != lst ? lst.size() : "0");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				null != lst ? lst : new ArrayList<>());
	}

}
