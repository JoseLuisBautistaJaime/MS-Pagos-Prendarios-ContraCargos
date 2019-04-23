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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.nmp.pagos.mimonte.builder.ContactosBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.dto.ContactoReqUpdateDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoRespDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.exception.CatalogoNotFoundException;
import mx.com.nmp.pagos.mimonte.services.impl.ContactoServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorCatalogo;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorGenerico;

/**
 * Nombre: CatalogoController Descripcion: Controllador que expone los servicios
 * REST por medio de las cuales se realiza la administración y consulta del
 * catálogo de contactos.
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
	@SuppressWarnings("unused")
	@Autowired
	private ContactoServiceImpl contactoServiceImpl;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(ContactosController.class);

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
		if(!ValidadorCatalogo.validaContactoReqSaveDTO(contacto))
			throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR);
		if(!ValidadorGenerico.validateEmail2(contacto.getEmail()))
			throw new CatalogoException(CatalogConstants.CATALOG_EMAIL_FORMAT_IS_NOT_CORRECT);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				(ContactoRespDTO) ContactosBuilder
				.buildContactoRespDTOFromContactoBaseDTO(contactoServiceImpl.save(
						ContactosBuilder.buildContactoBaseDTOFromContactoRequestDTO(contacto, new Date(), null),
						createdBy)));
		
		
	}

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
		if(!ValidadorCatalogo.validaContactoReqUpdateDTO(contacto))
			throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR);
		if(!ValidadorGenerico.validateEmail2(contacto.getEmail()))
			throw new CatalogoException(CatalogConstants.CATALOG_EMAIL_FORMAT_IS_NOT_CORRECT);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_UPDATE,
				(ContactoRespDTO) ContactosBuilder
				.buildContactoRespDTOFromContactoBaseDTO(contactoServiceImpl.update(
						ContactosBuilder.buildContactoRespDTOFromContactoReqUpdateDTO(contacto, new Date(), null),
						lastModifiedBy)));
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/catalogos/contactos/{idContacto}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "Elimina un contacto.", tags = { "Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para el catalogo especificado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response DeleteContacto(@PathVariable("idContacto") Long idContacto) {
		try {
			contactoServiceImpl.deleteById(idContacto);
		} catch (EmptyResultDataAccessException eex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND);
		}
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_DELETE,
				null);
	}

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
		try {
			contactoRespDTO = contactoServiceImpl.findById(idContacto);
		} catch (EmptyResultDataAccessException eex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND);
		}
		if(contactoRespDTO == null)
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				contactoRespDTO);
	}

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
		try {
			lst = contactoServiceImpl.findByIdTipoContacto(idTipoContacto);
		} catch (EmptyResultDataAccessException eex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND);
		}
		if(lst == null || lst.isEmpty())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				null != lst ? lst : new ArrayList<>());
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/contactos/consultas/{idTipoContacto}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de los contactos registrados con respecto al parámetro del nombre y del email.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response getNombreEmailContacto(@RequestParam(name = "nombre", required = false) String nombre,
			@RequestParam(name = "email", required = false) String email,
			@PathVariable(name = "idTipoContacto", required = true) Long idTipoContacto) {
		if(email != null) {
			if(!ValidadorGenerico.validateEmail2(email))
				throw new CatalogoException(CatalogConstants.CATALOG_EMAIL_FORMAT_IS_NOT_CORRECT);
		}
		List<ContactoRespDTO> lst = null;
		try {
			lst = contactoServiceImpl.findByIdTipoContactoAndNombreAndEmail(idTipoContacto, nombre, email);
		} catch (EmptyResultDataAccessException eex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_AND_NAME_AND_EMAIL_NOT_FOUND);
		}
		if(lst == null|| lst.isEmpty())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				null != lst ? lst : new ArrayList<>());
	}

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

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				null != lst ? lst : new ArrayList<>());
	}

}
