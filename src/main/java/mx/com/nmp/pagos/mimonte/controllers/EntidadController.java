/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.PatternSyntaxException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.nmp.pagos.mimonte.builder.EntidadBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.dto.EntidadBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.services.impl.EntidadServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorCatalogo;

/**
 * @name EntidadController
 * @description Clase que expone el servicio REST para las operaciones
 *              relacionadas con el catalogo de entidades
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 05/03/2019 13:09 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar operciones sobre el catalogo de entidades.", description = "REST API para realizar operaciones sobre el catalogo de entidades", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Entidad" })
public class EntidadController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(EntidadController.class);

	/**
	 * Service para catalogo de entidades
	 */
	@Autowired
	@Qualifier("entidadServiceImpl")
	private EntidadServiceImpl entidadServiceImpl;

	/**
	 * Guarda un nuevo catalogo Entidad
	 * 
	 * @param pagoRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/catalogos/entidades", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Crea un nuevo catalogo entidad.", tags = { "Entidad" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Entidad creada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response save(@RequestBody EntidadBaseDTO entidadDTOReq,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
		if (!ValidadorCatalogo.validateEntidadBaseDTOSave(entidadDTOReq))
			throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR);
		EntidadResponseDTO entidadResponseDTO = null;
		EntidadDTO entidadDTO = null;
		EntidadDTO entidadDTOResp = null;
		entidadDTO = EntidadBuilder.buildEntidadDTOFromEntidadBaseDTO(entidadDTOReq, new Date(), null);
		entidadDTOResp = entidadServiceImpl.save(entidadDTO, createdBy);
		entidadResponseDTO = EntidadBuilder.buildEntidadResponseDTOFromEntidadDTO(entidadDTOResp);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				entidadResponseDTO);
	}

	/**
	 * Actualiza un catalogo Entidad
	 * 
	 * @param pagoRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/catalogos/entidades", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Actualiza un catalogo entidad.", tags = { "Entidad" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Entidad actualizada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response update(@RequestBody EntidadBaseDTO entidadDTOReq,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {
		try {
			if (!ValidadorCatalogo.validateEntidadBaseDTOUpdt(entidadDTOReq))
				throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR);
		} catch (PatternSyntaxException pse) {
			throw pse;
		}
		EntidadDTO entidadDTO = null;
		EntidadResponseDTO entidadResponseDTO = null;
		EntidadDTO entidadDTOResp = null;
		entidadDTO = EntidadBuilder.buildEntidadDTOFromEntidadBaseDTO(entidadDTOReq, null, new Date());
		entidadDTOResp = entidadServiceImpl.update(entidadDTO, lastModifiedBy);
		entidadResponseDTO = EntidadBuilder.buildEntidadResponseDTOFromEntidadDTO(entidadDTOResp);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_UPDATE,
				entidadResponseDTO);
	}

	/**
	 * Obtiene un catalogo entidad por su id
	 * 
	 * @param idEntidad
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/entidades/{idEntidad}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa un objeto catalogo entidad en base a su id", tags = {
			"Entidad" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Entidad encontrada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findById(@PathVariable(value = "idEntidad", required = true) Long idEntidad) {
		EntidadResponseDTO entidadResponseDTO = null;
		EntidadDTO entidadDTO = null;
		try {
			entidadDTO = (EntidadDTO) entidadServiceImpl.findById(idEntidad);
			entidadResponseDTO = EntidadBuilder.buildEntidadResponseDTOFromEntidadDTO(entidadDTO);
		} catch (EmptyResultDataAccessException erdex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND);
		}
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				entidadResponseDTO);
	}

	/**
	 * Obtiene uno o mas catalogos de entidad por su nombre y estatus
	 * 
	 * @param nombre
	 * @param estatus
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/entidades/consultas", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa un objeto catalogo entidad en base a nombre y estatus", tags = {
			"Entidad" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Entidades encontradas"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByNombreAndEstatus(@RequestParam(name = "nombre", required = false) String nombre,
			@RequestParam(name = "estatus", required = false) Boolean estatus) {

		List<EntidadResponseDTO> entidadResponseDTOList = null;
		try {
			entidadResponseDTOList = entidadServiceImpl.findByNombreAndEstatus(nombre, estatus);
		} catch (EmptyResultDataAccessException erdex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND);
		}
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				null != entidadResponseDTOList ? entidadResponseDTOList : new ArrayList<>());
	}

	/**
	 * Realiza eliimado logico de una entidad
	 * 
	 * @param idEntidad
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/catalogos/entidades/{idEntidad}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Eliminacion logica de un objeto catalogo entidad en base a su id", tags = {
			"Entidad" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Entidad encontrada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response deleteById(@PathVariable(value = "idEntidad", required = true) Long idEntidad,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {
		try {
			entidadServiceImpl.updateEstatusById(false, idEntidad, lastModifiedBy, new Date());
		} catch (EmptyResultDataAccessException erdex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND);
		}
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_DELETE,
				null);
	}

}
