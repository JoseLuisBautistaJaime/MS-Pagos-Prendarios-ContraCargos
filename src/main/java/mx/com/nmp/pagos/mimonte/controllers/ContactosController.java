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
import mx.com.nmp.pagos.mimonte.builder.ContactosBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.ContactoBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoReqSearchDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoReqUpdateDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoRespDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.exception.CatalogoNotFoundException;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.services.impl.ContactoServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorCatalogo;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorGenerico;

/**
 * @name CatalogoController
 * @description Controllador que expone los servicios REST por medio de las
 *              cuales se realiza la administracion y consulta del catalogo de
 *              contactos.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @version 0.1
 */

@RestController
@RequestMapping("/mimonte")
@Api(value = "Servicio que permite obtener los registros de un contacto en especifico.", description = "REST API para Contactos", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Contactos" })
public class ContactosController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	BeanFactory beanFactory;

	/**
	 * Bean de la capa service para obtener los resultados
	 */
	@Autowired
	private ContactoServiceImpl contactoServiceImpl;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private final Logger log = LoggerFactory.getLogger(ContactosController.class);

	/**
	 * Realiza el alta de un contacto
	 * 
	 * @param contacto
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/catalogos/contactos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Registra la información de los contactos en la base de datos.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para el catalogo especificado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response saveContacto(@RequestBody ContactoRequestDTO contacto,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
		
		log.info(">>> POST /catalogos/contactos | REQUEST ENTRANTE: {}", null!= contacto ? contacto : "");
		
		if (!ValidadorCatalogo.validaContactoReqSaveDTO(contacto))
			throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR, CodigoError.NMP_PMIMONTE_0008);
		if (!ValidadorGenerico.validateEmail2(contacto.getEmail()))
			throw new CatalogoException(CatalogConstants.CATALOG_EMAIL_FORMAT_IS_NOT_CORRECT,
					CodigoError.NMP_PMIMONTE_BUSINESS_013);
		
		ContactoRespDTO response = ContactosBuilder.buildContactoRespDTOFromContactoBaseDTO(contactoServiceImpl.save(
				ContactosBuilder.buildContactoBaseDTOFromContactoRequestDTO(contacto, new Date(), null),
				createdBy));
		
		log.info(">>> POST /catalogos/contactos | RESPONSE: {}", null != response ? response: "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				response );
	}

	/**
	 * Realiza la actualizacion de un contacto
	 * 
	 * @param contacto
	 * @param lastModifiedBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/catalogos/contactos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Actualiza la información de los contactos registrada en la base de datos.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para el catalogo especificado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response updateContacto(@RequestBody ContactoReqUpdateDTO contacto,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {
		// Objectos necesarios
		ContactoBaseDTO contactoBaseDTO = null;

		log.info(">>> PUT /catalogos/contactos | REQUEST ENTRANTE: {}", null != contacto ? contacto : "");
		
		// Valida el objeto y atributos
		if (!ValidadorCatalogo.validaContactoReqUpdateDTO(contacto))
			throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR, CodigoError.NMP_PMIMONTE_0008);

		// Valida el patron de email de contacto
		if (!ValidadorGenerico.validateEmail2(contacto.getEmail()))
			throw new CatalogoException(CatalogConstants.CATALOG_EMAIL_FORMAT_IS_NOT_CORRECT,
					CodigoError.NMP_PMIMONTE_BUSINESS_013);

		// Actauliza el contacto
		contactoBaseDTO = contactoServiceImpl.update(
				ContactosBuilder.buildContactoRespDTOFromContactoReqUpdateDTO(contacto, new Date(), null),
				lastModifiedBy);

		// Mapea el objetos a un objeto de respuesta
		ContactoRespDTO contactoResp = ContactosBuilder.buildContactoRespDTOFromContactoBaseDTO(contactoBaseDTO);

		log.info(">>> PUT /catalogos/contactos | RESPONSE: {}", null != contactoResp ? contactoResp : "");
		
		// Regresa la respuesta
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_UPDATE,
				contactoResp);
	}

	/**
	 * Realiza la eliminacion de un contacto por id
	 * 
	 * @param idContacto
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/catalogos/contactos/{idContacto}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "Elimina un contacto.", tags = { "Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para el catalogo especificado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response deleteContacto(@PathVariable("idContacto") Long idContacto) {
		
		log.info(">>> DELETE /catalogos/contactos/{idContacto} | REQUEST ENTRANTE: {}", null != idContacto ? idContacto : "");
		
		try {
			contactoServiceImpl.deleteById(idContacto);
		} catch (EmptyResultDataAccessException eex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_001);
		}
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_DELETE,
				null);
	}

	/**
	 * Realiza la consulta de contactos por id
	 * 
	 * @param idContacto
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/contactos/{idContacto}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de los contactos registrados con respecto al parámetro idContacto.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response getIdContacto(@PathVariable(value = "idContacto", required = true) Long idContacto) {
		ContactoRespDTO contactoRespDTO = null;
		
		log.info(">>> GET /catalogos/contactos/{idContacto} | REQUEST ENTRANTE: {}", null != idContacto ? idContacto : "");
		
		try {
			contactoRespDTO = contactoServiceImpl.findById(idContacto);
		} catch (EmptyResultDataAccessException eex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_001);
		}
		if (contactoRespDTO == null)
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND, CodigoError.NMP_PMIMONTE_0005);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				contactoRespDTO);
	}

	/**
	 * Realiza la consulta de contactos por id de tipo de contacto
	 * 
	 * @param idTipoContacto
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/contactos/tipocontacto/{idTipoContacto}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de los tipos de contactos registrados con respecto al parámetro id.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response getIdTipoContacto(@PathVariable(value = "idTipoContacto", required = true) Long idTipoContacto) {
		List<ContactoRespDTO> lst = null;
		
		log.info(">>> GET /catalogos/contactos/tipocontacto/{idTipoContacto} | REQUEST ENTRANTE: {}", null != idTipoContacto ? idTipoContacto : "");
		
		try {
			lst = contactoServiceImpl.findByIdTipoContacto(idTipoContacto);
		} catch (EmptyResultDataAccessException eex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_001);
		}
		if (lst == null || lst.isEmpty())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND, CodigoError.NMP_PMIMONTE_0005);
		
		log.info(">>> GET /catalogos/contactos/tipocontacto/{idTipoContacto} | RESPONSE: {}, TOTAL: {}", lst, lst.size());
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS, lst);
	}

	/**
	 * Realiza la consulta de contactos por nombre, email e id de tipo de contacto
	 * (todos opcionales)
	 * 
	 * @param contactoReqSearchDTO DTO con la información de los criterios de búsqueda.
	 * @return La lista de resultados obtenidos con base en los criterios recibidos.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/catalogos/contactos/consulta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Regresa la información de los contactos registrados que coincidan con los criterios de búsqueda indicados.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response getNombreEmailContacto(@RequestBody ContactoReqSearchDTO contactoReqSearchDTO) {
		
		log.info(">>> POST /catalogos/contactos/consulta | REQUEST ENTRANTE: {}", null != contactoReqSearchDTO ? contactoReqSearchDTO : "");
		
		// Valida que el objeto principal no sea nulo
		if (null == contactoReqSearchDTO)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		log.info(">> getNombreEmailContacto( {} )", contactoReqSearchDTO);

		if (contactoReqSearchDTO.getIdTipoContacto() == null || contactoReqSearchDTO.getIdTipoContacto() <= 0) {
			contactoReqSearchDTO.setIdTipoContacto(CatalogConstants.TIPO_CONTACTO_MIDAS);
		}

		if (contactoReqSearchDTO.getEmail() != null && !ValidadorGenerico.validateEmail2(contactoReqSearchDTO.getEmail())) {
			throw new CatalogoException(CatalogConstants.CATALOG_EMAIL_FORMAT_IS_NOT_CORRECT,
					CodigoError.NMP_PMIMONTE_BUSINESS_013);
		}

		List<ContactoRespDTO> lst;
		try {
			lst = contactoServiceImpl.findByIdTipoContactoAndNombreAndEmail(
					contactoReqSearchDTO.getIdTipoContacto(), contactoReqSearchDTO.getNombre(), contactoReqSearchDTO.getEmail());
		} catch (EmptyResultDataAccessException eex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_AND_NAME_AND_EMAIL_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_BUSINESS_014);
		}

		if (lst == null || lst.isEmpty())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND, CodigoError.NMP_PMIMONTE_0005);

		log.info(">>> POST /catalogos/contactos/consulta | RESPONSE: {}, TOTAL: {}", null != lst ? lst : "", null != lst ? lst.size() : "0");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS, lst);
	}

	/**
	 * Realiza la consulta de todos los contactos
	 * 
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/contactos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de los contactos registrados.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response getAll() {
		@SuppressWarnings("unchecked")
		List<ContactoRespDTO> lst = ContactosBuilder
				.buildContactoBaseDTOListFromContactoRespDTOList((List<ContactoRespDTO>) contactoServiceImpl.findAll());
		
		log.info(">>> GET /catalogos/contactos | RESPONSE: {}, TOTAL: {}", null != lst ? lst : "", null != lst ? lst.size() : "0");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				null != lst ? lst : new ArrayList<>());
	}

}
