/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
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
import mx.com.nmp.pagos.mimonte.builder.AfiliacionBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionReqDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionReqSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionRespPostDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.exception.CatalogoNotFoundException;
import mx.com.nmp.pagos.mimonte.services.impl.AfiliacionServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorCatalogo;

/**
 * @name AfiliacionController
 * @description Clase que expone el servicio REST para las operaciones
 *              relacionadas con el catalogo de afiliacion
 *
 * @author Victor Manuel Moran Hernandez
 * @creationDate 06/03/2019 13:12 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar operciones sobre el catalogo de afiliacion.", description = "REST API para realizar operaciones sobre el catalogo de afiliacion", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Afiliacion" })
public class AfiliacionController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private final Logger log = LoggerFactory.getLogger(AfiliacionController.class);

	/**
	 * Service de Afiliacion
	 */
	@Autowired
	@Qualifier("afiliacionServiceImpl")
	private AfiliacionServiceImpl afiliacionServiceImpl;

	/**
	 * Guarda un nuevo catalogo Afiliacion
	 * 
	 * @param afiliacionReqSaveDTO
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/catalogos/afiliaciones", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Crea un nuevo catalogo Afiliacion.", tags = { "Afiliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Afiliacion creada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response save(@RequestBody AfiliacionReqSaveDTO afiliacionReqSaveDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
		
		log.info(">>> POST /catalogos/afiliaciones REQUEST ENTRANTE: {}", null != afiliacionReqSaveDTO ? afiliacionReqSaveDTO: "");
		
		// Valida que el objeto y sus atributos sean correctos
		if (!ValidadorCatalogo.validateAfilacionSave(afiliacionReqSaveDTO))
			throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR, CodigoError.NMP_PMIMONTE_0008);
		// Realiza el alta
		AfiliacionRespPostDTO afiliacionDTO = AfiliacionBuilder.buildAfiliacionRespPostDTOfromAfiliacionDTO(
				(AfiliacionDTO) afiliacionServiceImpl.save(AfiliacionBuilder.buildAfiliacionDTOFromAfiliacionSaveReqDTO(
						afiliacionReqSaveDTO, new Date(), null), createdBy));
		
		log.info(">>> POST /catalogos/afiliaciones RESPONSE: {}", null != afiliacionDTO ? afiliacionDTO : "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				afiliacionDTO);
	}

	/**
	 * Actualiza un catalogo Afiliacion
	 * 
	 * @param afiliacionDTOReq
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/catalogos/afiliaciones", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Actualiza un catalogo afiliacion.", tags = { "Afiliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Afiliacion actualizada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response update(@RequestBody AfiliacionReqDTO afiliacionDTOReq,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
		
		log.info(">>> PUT /catalogos/afiliaciones REQUEST ENTRANTE: {}", null != afiliacionDTOReq ? afiliacionDTOReq : "");
		
		// Valida que el objeto y sus atributos
		if (!ValidadorCatalogo.validateAfilacionUpdt(afiliacionDTOReq))
			throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR, CodigoError.NMP_PMIMONTE_0008);
		// Realiza la actualizacion
		AfiliacionRespPostDTO afiliacionDTO = AfiliacionBuilder
				.buildAfiliacionRespPostDTOfromAfiliacionDTO((AfiliacionDTO) afiliacionServiceImpl.update(
						AfiliacionBuilder.buildAfiliacionDTOFromAfiliacionReqDTO(afiliacionDTOReq, null, new Date()),
						createdBy));
		
		log.info(">>> PUT /catalogos/afiliaciones RESPONSE: {}", null != afiliacionDTO ? afiliacionDTO : "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_UPDATE,
				afiliacionDTO);
	}

	/**
	 * Obtiene un catalogo afiliacion por su numero
	 * 
	 * @param numeroAfiliacion
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/afiliaciones/{numeroAfiliacion}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa un objeto catalogo afiliacion en base a su numero", tags = {
			"Afiliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Afiliacion encontrada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findById(@PathVariable(value = "numeroAfiliacion", required = true) String numeroAfiliacion) {
		
		log.info(">>> GET /catalogos/afiliaciones/{numeroAfiliacion} REQUEST: {}", (null != numeroAfiliacion) ? numeroAfiliacion : "");
		
		AfiliacionRespPostDTO afiliacionDTO = null;
		// Realiza la consulta
		try {
			afiliacionDTO = AfiliacionBuilder.buildAfiliacionRespPostDTOfromAfiliacionDTO(
					(AfiliacionDTO) afiliacionServiceImpl.findByNumero(numeroAfiliacion));
		} catch (EmptyResultDataAccessException eex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_001);
		} catch (javax.persistence.NonUniqueResultException nuex) {
			throw new CatalogoException(CatalogConstants.DB_INCONSISTENCY_EXCEPTION, CodigoError.NMP_PMIMONTE_BUSINESS_002);
		}
		if (null == afiliacionDTO)
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND, CodigoError.NMP_PMIMONTE_0005);
		
		log.info(">>> GET /catalogos/afiliaciones/{numeroAfiliacion} RESPONSE: {}", afiliacionDTO);
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				afiliacionDTO);
	}

	/**
	 * Obtiene uno o mas catalogos de afiliacion por su numero de cuenta asociada
	 * 
	 * @param idCuenta
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/afiliaciones/cuenta/{idCuenta}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa un objeto catalogo afiliacion en base a el id de cuenta con la que mantiene relacion", tags = {
			"Afiliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Afiliacion encontradas"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByCuenta(@PathVariable(value = "idCuenta", required = true) Long idCuenta) {
		
		log.info(">>> GET /catalogos/afiliaciones/cuenta/{idCuenta} REQUEST: {}", null != idCuenta ? idCuenta : "");
		
		Set<AfiliacionRespPostDTO> afiliacionDTOSet = null;
		// Realiza la consulta
		try {
			afiliacionDTOSet = AfiliacionBuilder.buildAfiliacionRespPostDTOSetfromAfiliacionDTOSet(
					(Set<AfiliacionDTO>) afiliacionServiceImpl.findByCuentasId(idCuenta));
		} catch (EmptyResultDataAccessException eex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_001);
		}
		if (null == afiliacionDTOSet || afiliacionDTOSet.isEmpty())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND, CodigoError.NMP_PMIMONTE_0005);
		
		log.info(">>> GET /catalogos/afiliaciones/cuenta/{idCuenta} RESPONSE: {}", afiliacionDTOSet);
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				afiliacionDTOSet);
	}

	/**
	 * Elimina fisicamente una afiliacion por id
	 * 
	 * @param idAfiliacion
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/catalogos/afiliaciones/{idAfiliacion}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "Eliminacion logica del registro en base a su id", tags = {
			"Afiliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Afiliacion eliminada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response deleteByidAfiliacion(@PathVariable(value = "idAfiliacion", required = true) Long idAfiliacion,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
		
		log.info(">>> DELETE /catalogos/afiliaciones/{idAfiliacion} REQUEST: {}", null != idAfiliacion ? idAfiliacion : "");
		
		// Realiza la eliminacion
		try {
			afiliacionServiceImpl.deleteById(idAfiliacion);
		} catch (EmptyResultDataAccessException ex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_001);
		} catch (DataIntegrityViolationException ex) {
			throw new CatalogoException(CatalogConstants.AFILIACION_HAS_CUENTAS_ASSOCIATES, CodigoError.NMP_PMIMONTE_BUSINESS_003);
		} catch (Exception ex) {
			throw ex;
		}
		
		log.info(">>> DELETE /catalogos/afiliaciones/{idAfiliacion} REQUEST: {}", "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_DELETE,
				null);
	}

	/**
	 * Regresa todas las afiliaciones
	 * 
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/afiliaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa todos los objeto catalogo afiliacion", tags = { "Afiliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Afiliaciones encontradas"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findAll() {
		// Realiza la consulta
		@SuppressWarnings("unchecked")
		List<AfiliacionRespPostDTO> afiliacionDTOList = AfiliacionBuilder
				.buildAfiliacionRespPostDTOListfromAfiliacionDTOList(
						(List<AfiliacionDTO>) afiliacionServiceImpl.findAll());
		
		log.info(">>> GET /catalogos/afiliaciones RESPONSE: {}", null != afiliacionDTOList ? afiliacionDTOList : "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				null != afiliacionDTOList ? afiliacionDTOList : new ArrayList<>());
	}

}