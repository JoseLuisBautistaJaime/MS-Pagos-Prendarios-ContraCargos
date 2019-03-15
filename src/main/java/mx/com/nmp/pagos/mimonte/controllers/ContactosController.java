package mx.com.nmp.pagos.mimonte.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.dto.ContactoReqUpdateDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoReqUpdateEstatusDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoRespDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoContactoRespDTO;
import mx.com.nmp.pagos.mimonte.dto.getContactosDTO;
import mx.com.nmp.pagos.mimonte.services.impl.ContactoServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;

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
	@PostMapping(value = "/catalogos/contacto", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Registra la información de los contactos en la base de datos.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para el catalogo especificado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response add(@RequestBody ContactoRequestDTO contacto,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {

		// ContactoRespDTO tipoContactoRespDTOReal =
		// (ContactoRespDTO)ContactosBuilder.buildContactoRespDTOFromContactoBaseDTO(
		// contactoServiceImpl.save(ContactosBuilder.buildContactoBaseDTOFromContactoRequestDTO(contacto),
		// createdBy));

		// Dummy

		TipoContactoRespDTO tipoContactoRespDTO = new TipoContactoRespDTO();
		tipoContactoRespDTO.setDescripcion("contacto");
		tipoContactoRespDTO.setId(1L);

		ContactoRespDTO contactoResponseDTO = new ContactoRespDTO();
		contactoResponseDTO.setEmail("miemail@email.com");
		contactoResponseDTO.setDescripcion("contacto");
		contactoResponseDTO.setEstatus(true);
		contactoResponseDTO.setId(1234L);
		contactoResponseDTO.setNombre("micontacto");
		contactoResponseDTO.setTipoContactoResDTO(tipoContactoRespDTO);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				contactoResponseDTO);

		// End Dummy

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
		// Dummy

		TipoContactoRespDTO tipoContactoRespDTO = new TipoContactoRespDTO();
		tipoContactoRespDTO.setDescripcion("contacto");
		tipoContactoRespDTO.setId(1L);

		ContactoRespDTO contactoResponseDTO = new ContactoRespDTO();
		contactoResponseDTO.setEmail("miemail@email.com");
		contactoResponseDTO.setDescripcion("contacto");
		contactoResponseDTO.setEstatus(true);
		contactoResponseDTO.setId(1234L);
		contactoResponseDTO.setNombre("micontacto");
		contactoResponseDTO.setTipoContactoResDTO(tipoContactoRespDTO);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_UPDATE,
				contactoResponseDTO);

		// End Dummy

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/catalogos/estatus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Actualiza la información del estatus del contacto registrada en la base de datos.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para el catalogo especificado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response updateEstatus(@RequestBody ContactoReqUpdateEstatusDTO contacto,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {
		// Dummy

		TipoContactoRespDTO tipoContactoRespDTO = new TipoContactoRespDTO();
		tipoContactoRespDTO.setDescripcion("contacto");
		tipoContactoRespDTO.setId(1L);

		ContactoRespDTO contactoResponseDTO = new ContactoRespDTO();
		contactoResponseDTO.setEmail("miemail@email.com");
		contactoResponseDTO.setDescripcion("contacto");
		contactoResponseDTO.setEstatus(true);
		contactoResponseDTO.setId(1234L);
		contactoResponseDTO.setNombre("micontacto");
		contactoResponseDTO.setTipoContactoResDTO(tipoContactoRespDTO);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_UPDATE,
				contactoResponseDTO);

		// End Dummy

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/{idContacto}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de los contactos registrados con respecto al parámetro idContacto.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response getIdContacto(@PathVariable(value = "idContacto", required = true) Long idContacto) {

		// Dummy

		TipoContactoRespDTO tipoContactoRespDTO = new TipoContactoRespDTO();
		tipoContactoRespDTO.setDescripcion("contacto");
		tipoContactoRespDTO.setId(1L);

		ContactoRespDTO contactoResponseDTO = new ContactoRespDTO();
		contactoResponseDTO.setEmail("miemail@email.com");
		contactoResponseDTO.setDescripcion("contacto");
		contactoResponseDTO.setEstatus(true);
		contactoResponseDTO.setId(1234L);
		contactoResponseDTO.setNombre("micontacto");
		contactoResponseDTO.setTipoContactoResDTO(tipoContactoRespDTO);

		// End Dummy

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				contactoResponseDTO);

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogo/contactos/{idTipoContacto}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de los tipos de contactos registrados con respecto al parámetro id.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response getIdTipoContacto(@PathVariable(value = "idTipoContacto", required = true) Long idTipoContacto) {

		// Dummy

		List<ContactoRespDTO> contactosRespList = new ArrayList<ContactoRespDTO>();

		TipoContactoRespDTO tipoContactoRespDTO = new TipoContactoRespDTO();
		tipoContactoRespDTO.setDescripcion("contacto");
		tipoContactoRespDTO.setId(1L);

		ContactoRespDTO contactoResponseDTO = new ContactoRespDTO();
		contactoResponseDTO.setEmail("miemail@email.com");
		contactoResponseDTO.setDescripcion("contacto");
		contactoResponseDTO.setEstatus(true);
		contactoResponseDTO.setId(1L);
		contactoResponseDTO.setNombre("micontacto");
		contactoResponseDTO.setTipoContactoResDTO(tipoContactoRespDTO);

		ContactoRespDTO contactoResponseDTO2 = new ContactoRespDTO();
		contactoResponseDTO2.setEmail("miemail@email.com");
		contactoResponseDTO2.setDescripcion("contacto");
		contactoResponseDTO2.setEstatus(true);
		contactoResponseDTO2.setId(2L);
		contactoResponseDTO2.setNombre("micontacto");
		contactoResponseDTO2.setTipoContactoResDTO(tipoContactoRespDTO);

		ContactoRespDTO contactoResponseDTO3 = new ContactoRespDTO();
		contactoResponseDTO3.setEmail("miemail@email.com");
		contactoResponseDTO3.setDescripcion("contacto");
		contactoResponseDTO3.setEstatus(true);
		contactoResponseDTO3.setId(3L);
		contactoResponseDTO3.setNombre("micontacto");
		contactoResponseDTO3.setTipoContactoResDTO(tipoContactoRespDTO);

		contactosRespList.add(contactoResponseDTO);
		contactosRespList.add(contactoResponseDTO2);
		contactosRespList.add(contactoResponseDTO3);

		// End Dummy

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				contactosRespList);

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogo/{Nombres}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de los contactos registrados con respecto al parámetro del nombre.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response getNombreContacto(@PathVariable(value = "Nombres", required = true) String nombres) {

		// Dummy

		List<ContactoRespDTO> contactosRespList = new ArrayList<ContactoRespDTO>();

		TipoContactoRespDTO tipoContactoRespDTO = new TipoContactoRespDTO();
		tipoContactoRespDTO.setDescripcion("contacto");
		tipoContactoRespDTO.setId(1L);

		ContactoRespDTO contactoResponseDTO = new ContactoRespDTO();
		contactoResponseDTO.setEmail("miemail@email.com");
		contactoResponseDTO.setDescripcion("contacto");
		contactoResponseDTO.setEstatus(true);
		contactoResponseDTO.setId(1L);
		contactoResponseDTO.setNombre("micontacto");
		contactoResponseDTO.setTipoContactoResDTO(tipoContactoRespDTO);

		ContactoRespDTO contactoResponseDTO2 = new ContactoRespDTO();
		contactoResponseDTO2.setEmail("miemail@email.com");
		contactoResponseDTO2.setDescripcion("contacto");
		contactoResponseDTO2.setEstatus(true);
		contactoResponseDTO2.setId(2L);
		contactoResponseDTO2.setNombre("micontacto");
		contactoResponseDTO2.setTipoContactoResDTO(tipoContactoRespDTO);

		ContactoRespDTO contactoResponseDTO3 = new ContactoRespDTO();
		contactoResponseDTO3.setEmail("miemail@email.com");
		contactoResponseDTO3.setDescripcion("contacto");
		contactoResponseDTO3.setEstatus(true);
		contactoResponseDTO3.setId(3L);
		contactoResponseDTO3.setNombre("micontacto");
		contactoResponseDTO3.setTipoContactoResDTO(tipoContactoRespDTO);

		contactosRespList.add(contactoResponseDTO);
		contactosRespList.add(contactoResponseDTO2);
		contactosRespList.add(contactoResponseDTO3);

		// End Dummy

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				contactosRespList);

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogo/contacto/{emails}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de los contactos registrados con respecto al parámetro del email.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response getEmailContacto(@PathVariable(value = "emails", required = true) String email) {

		// Dummy

		TipoContactoRespDTO tipoContactoRespDTO = new TipoContactoRespDTO();
		tipoContactoRespDTO.setDescripcion("contacto");
		tipoContactoRespDTO.setId(1L);

		ContactoRespDTO contactoResponseDTO = new ContactoRespDTO();
		contactoResponseDTO.setEmail("miemail@email.com");
		contactoResponseDTO.setDescripcion("contacto");
		contactoResponseDTO.setEstatus(true);
		contactoResponseDTO.setId(1L);
		contactoResponseDTO.setNombre("micontacto");
		contactoResponseDTO.setTipoContactoResDTO(tipoContactoRespDTO);

		// End Dummy

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				contactoResponseDTO);

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogo/{nombre}/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de los contactos registrados con respecto al parámetro del nombre y del email.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response getNombreEmailContacto(@PathVariable(value = "nombre", required = true) String nombre,
			@PathVariable(value = "email", required = true) String email) {

		// Dummy

		TipoContactoRespDTO tipoContactoRespDTO = new TipoContactoRespDTO();
		tipoContactoRespDTO.setDescripcion("contacto");
		tipoContactoRespDTO.setId(1L);

		ContactoRespDTO contactoResponseDTO = new ContactoRespDTO();
		contactoResponseDTO.setEmail("miemail@email.com");
		contactoResponseDTO.setDescripcion("contacto");
		contactoResponseDTO.setEstatus(true);
		contactoResponseDTO.setId(1L);
		contactoResponseDTO.setNombre("micontacto");
		contactoResponseDTO.setTipoContactoResDTO(tipoContactoRespDTO);

		// End Dummy

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				contactoResponseDTO);

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogo/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de los contactos registrados.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response getAll() {

		// Dummy

		List<ContactoRespDTO> contactosRespList = new ArrayList<ContactoRespDTO>();

		TipoContactoRespDTO tipoContactoRespDTO = new TipoContactoRespDTO();
		tipoContactoRespDTO.setDescripcion("contacto");
		tipoContactoRespDTO.setId(1L);

		ContactoRespDTO contactoResponseDTO = new ContactoRespDTO();
		contactoResponseDTO.setEmail("miemail@email.com");
		contactoResponseDTO.setDescripcion("contacto");
		contactoResponseDTO.setEstatus(true);
		contactoResponseDTO.setId(1L);
		contactoResponseDTO.setNombre("micontacto");
		contactoResponseDTO.setTipoContactoResDTO(tipoContactoRespDTO);

		ContactoRespDTO contactoResponseDTO2 = new ContactoRespDTO();
		contactoResponseDTO2.setEmail("miemail@email.com");
		contactoResponseDTO2.setDescripcion("contacto");
		contactoResponseDTO2.setEstatus(true);
		contactoResponseDTO2.setId(2L);
		contactoResponseDTO2.setNombre("micontacto");
		contactoResponseDTO2.setTipoContactoResDTO(tipoContactoRespDTO);

		ContactoRespDTO contactoResponseDTO3 = new ContactoRespDTO();
		contactoResponseDTO3.setEmail("miemail@email.com");
		contactoResponseDTO3.setDescripcion("contacto");
		contactoResponseDTO3.setEstatus(true);
		contactoResponseDTO3.setId(3L);
		contactoResponseDTO3.setNombre("micontacto");
		contactoResponseDTO3.setTipoContactoResDTO(tipoContactoRespDTO);

		contactosRespList.add(contactoResponseDTO);
		contactosRespList.add(contactoResponseDTO2);
		contactosRespList.add(contactoResponseDTO3);

		// End Dummy

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				contactosRespList);

	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogo/getContacto", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de los contactos registrados.", tags = {
			"Contactos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response getAlls(@RequestBody getContactosDTO contacto) {

		// Dummy

		List<ContactoRespDTO> contactosRespList = new ArrayList<ContactoRespDTO>();

		TipoContactoRespDTO tipoContactoRespDTO = new TipoContactoRespDTO();
		tipoContactoRespDTO.setDescripcion("contacto");
		tipoContactoRespDTO.setId(1L);

		ContactoRespDTO contactoResponseDTO = new ContactoRespDTO();
		contactoResponseDTO.setEmail("miemail@email.com");
		contactoResponseDTO.setDescripcion("contacto");
		contactoResponseDTO.setEstatus(true);
		contactoResponseDTO.setId(1L);
		contactoResponseDTO.setNombre("micontacto");
		contactoResponseDTO.setTipoContactoResDTO(tipoContactoRespDTO);

		ContactoRespDTO contactoResponseDTO2 = new ContactoRespDTO();
		contactoResponseDTO2.setEmail("miemail@email.com");
		contactoResponseDTO2.setDescripcion("contacto");
		contactoResponseDTO2.setEstatus(true);
		contactoResponseDTO2.setId(2L);
		contactoResponseDTO2.setNombre("micontacto");
		contactoResponseDTO2.setTipoContactoResDTO(tipoContactoRespDTO);

		ContactoRespDTO contactoResponseDTO3 = new ContactoRespDTO();
		contactoResponseDTO3.setEmail("miemail@email.com");
		contactoResponseDTO3.setDescripcion("contacto");
		contactoResponseDTO3.setEstatus(true);
		contactoResponseDTO3.setId(3L);
		contactoResponseDTO3.setNombre("micontacto");
		contactoResponseDTO3.setTipoContactoResDTO(tipoContactoRespDTO);

		contactosRespList.add(contactoResponseDTO);
		contactosRespList.add(contactoResponseDTO2);
		contactosRespList.add(contactoResponseDTO3);

		// End Dummy

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				contactosRespList);

	}

}
