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
import mx.com.nmp.pagos.mimonte.builder.CuentaBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dto.CuentaBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaEntDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaSaveDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.exception.CatalogoNotFoundException;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.services.impl.CuentaServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.UtilValidation;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorCatalogo;

/**
 * @name cuentasController
 * @description Clase que expone el servicio REST para las operaciones
 *              relacionadas con el catalogo de cuentas
 *
 * @author Victor Manuel Moran Hernandez
 * @creationDate 12/03/2019 12:22 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar operciones sobre el catalogo de cuentas.", description = "REST API para realizar operaciones sobre el catalogo de cuentas", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Cuentas" })
public class CuentasController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private final Logger log = LoggerFactory.getLogger(CuentasController.class);

	/**
	 * Service de entiudad Cuenta
	 */
	@Autowired
	@Qualifier("cuentaServiceImpl")
	private CuentaServiceImpl cuentaServiceImpl;

	/**
	 * Guarda un nuevo catalogo Cuenta
	 * 
	 * @param cuentaSaveDTO
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/catalogos/cuentas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Crea un nuevo catalogo Cuenta.", tags = { "Cuentas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Cuenta creada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response save(@RequestBody CuentaSaveDTO cuentaSaveDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
		
		log.info(">>> POST /catalogos/cuentas REQUEST: {}", null != cuentaSaveDTO ? cuentaSaveDTO : "");
		
		// Valida que el objeto de request y sus atributos sean validos
		if (!ValidadorCatalogo.validateCuentaSave(cuentaSaveDTO))
			throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR, CodigoError.NMP_PMIMONTE_0008);
		CuentaEntDTO cuentaEntDTO = null;
		
		// TODO: PENDIENTE
		// Valida que el numero de afiliacion sea un valor alfanumerico
//				if (!UtilValidation.validaCadenaAlfanumerica(cuentaSaveDTO.getNumero()))
//					throw new ConciliacionException(CodigoError.NMP_PMIMONTE_0015.getDescripcion(),
//							CodigoError.NMP_PMIMONTE_0015);
				
		// Guarda una cuenta
		cuentaEntDTO = CuentaBuilder.buildCuentaEntDTOFromCuentaBaseDTO(cuentaServiceImpl
				.save(CuentaBuilder.buildCuentaBaseDTOFromCuentaSaveDTO(cuentaSaveDTO, new Date(), null), createdBy));
		
		log.info(">>> POST /catalogos/cuentas RESPONSE: {}", null != cuentaEntDTO ? cuentaEntDTO : "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				cuentaEntDTO);
	}

	/**
	 * Actualiza un catalogo Cuenta
	 * 
	 * @param cuentaDTOReq
	 * @param lastModifiedBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/catalogos/cuentas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Actualiza un catalogo cuentas.", tags = { "Cuentas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "cuentas actualizada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response update(@RequestBody CuentaDTO cuentaDTOReq,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {
		CuentaEntDTO cuentaEntDTO = null;
		
		log.info(">>> PUT /catalogos/cuentas REQUEST: {}", null != cuentaDTOReq ? cuentaDTOReq : "");
		
		// Valida que el objeto de request y sus atributos sean validos
		if (!ValidadorCatalogo.validateCuentaUpdate(cuentaDTOReq))
			throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR, CodigoError.NMP_PMIMONTE_0008);
		
		// TODO: PENDIENTE
		// Valida que el numero de afiliacion sea un valor alfanumerico
//		if (!UtilValidation.validaCadenaAlfanumerica(cuentaDTOReq.getNumero()))
//			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_0015.getDescripcion(),
//					CodigoError.NMP_PMIMONTE_0015);
		
		// Actualiza una cuenta
		cuentaEntDTO = CuentaBuilder.buildCuentaEntDTOFromCuentaBaseDTO(cuentaServiceImpl
				.update(CuentaBuilder.buildCuentaBaseDTOFromCuentaDTO(cuentaDTOReq, null, new Date()), lastModifiedBy));
		
		log.info(">>> PUT /catalogos/cuentas RESPONSE: {}", null != cuentaEntDTO ? cuentaEntDTO : "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_UPDATE,
				cuentaEntDTO);
	}

	/**
	 * Obtiene un catalogo Cuenta por su numero de cuenta
	 * 
	 * @param numeroCuenta
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/cuentas/{numeroCuenta}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa un objeto catalogo cuentas en base a su id", tags = {
			"Cuentas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "cuentas encontrada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByNumeroCuenta(@PathVariable String numeroCuenta) {
		
		log.info(">>> GET /catalogos/cuentas/{numeroCuenta} REQUEST: {}", null != numeroCuenta ? numeroCuenta : "");
		
		CuentaEntDTO cuentaEntDTO = null;
		// Encuentra una cuenta por su numero
		try {
			if (null != numeroCuenta && !numeroCuenta.equals(""))
				cuentaEntDTO = cuentaServiceImpl.findByNumeroCuenta(numeroCuenta);
			else
				throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND, CodigoError.NMP_PMIMONTE_0005);
		} catch (EmptyResultDataAccessException erdaex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_001);
		} catch (javax.persistence.NonUniqueResultException nuex) {
			throw new CatalogoException(CatalogConstants.DB_INCONSISTENCY_EXCEPTION, CodigoError.NMP_PMIMONTE_BUSINESS_002);
		}
		
		log.info(">>> GET /catalogos/cuentas/{numeroCuenta} RESPONSE: {}", null != cuentaEntDTO ? cuentaEntDTO : "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				cuentaEntDTO);
	}

	/**
	 * Obtiene uno o mas catalogos de Cuenta por su nombre y estatus
	 * 
	 * @param idEntidad
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/cuentas/entidad/{idEntidad}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa un objeto catalogo Cuenta en base a su id", tags = { "Cuentas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "cuentas encontradas"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByCuenta(@PathVariable(value = "idEntidad", required = true) Long idEntidad) {
		
		log.info(">>> GET /catalogos/cuentas/entidad/{idEntidad} REQUEST: {}", null != idEntidad ? idEntidad : "");
		
		List<CuentaEntDTO> cuentaEntDTOList = null;
		// Encuentra una lista de cuentas por su id de entidad asociada
		try {
			cuentaEntDTOList = cuentaServiceImpl.findByEntidadId(idEntidad);
		} catch (EmptyResultDataAccessException erdaex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_001);
		}
		
		log.info(">>> GET /catalogos/cuentas/entidad/{idEntidad} RESPONSE: {}", null != cuentaEntDTOList ? cuentaEntDTOList : "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				cuentaEntDTOList);
	}

	/**
	 * Elimina logicamente una cuenta por su id
	 * 
	 * @param idCuenta
	 * @param lastModifiedBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/catalogos/cuentas/{idCuenta}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Elimina el registro en base a su id", tags = { "Cuentas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Cuenta Eliminada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response deleteByidcuenta(@PathVariable(value = "idCuenta", required = true) Long idCuenta,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {
		
		log.info(">>> PUT /catalogos/cuentas/{idCuenta} REQUEST: {}", null != idCuenta ? idCuenta : "");
		
		// Elimina de manera logica una cuenta por su id
		try {
			cuentaServiceImpl.updateEstatusById(false, idCuenta, lastModifiedBy, new Date());
		} catch (EmptyResultDataAccessException erdaex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_001);
		}
		
		log.info(">>> PUT /catalogos/cuentas/{idCuenta} RESPONSE: {}", "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_DELETE,
				null);
	}

	/**
	 * Regresa una lista con todas las cuentas
	 * 
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/cuentas", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa todos los catalogo Cuenta", tags = { "Cuentas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "cuentas encontradas"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findAll() {
		// Encuentra todas las cuentas
		@SuppressWarnings("unchecked")
		List<CuentaEntDTO> cuentaEntDTOList = CuentaBuilder
				.buildCuentaEntDTOListFromCuentaBaseDTOList((List<CuentaBaseDTO>) cuentaServiceImpl.findAll());
		
		log.info(">>> GET /catalogos/cuentas RESPONSE: {}, TOTAL: {}", null != cuentaEntDTOList ? cuentaEntDTOList : "", null != cuentaEntDTOList ? cuentaEntDTOList.size() : "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				null != cuentaEntDTOList ? cuentaEntDTOList : new ArrayList<>());
	}

}