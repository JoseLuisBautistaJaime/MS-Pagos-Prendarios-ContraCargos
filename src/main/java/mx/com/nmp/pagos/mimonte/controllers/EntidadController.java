package mx.com.nmp.pagos.mimonte.controllers;

import java.util.HashSet;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionEntDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoEntDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaEntDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadResponseDTO;
import mx.com.nmp.pagos.mimonte.services.impl.EntidadServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * Nombre: EntidadController Descripcion: Clase que expone el servicio REST para
 * las operaciones relacionadas con el catalogo de entidades
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
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
	@SuppressWarnings("unused")
//	@Autowired
//	@Qualifier("entidadRepository")
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
	public Response save(@RequestBody EntidadDTO entidadDTOReq) {
//		entidadServiceImpl.save(entidadDTOReq);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Entidad guardada correctamente",
				buildDummy());
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
	public Response update(@RequestBody EntidadDTO entidadDTOReq) {
//		entidadServiceImpl.save(entidadDTOReq);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Entidad actualizada correctamente",
				buildDummy());
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
//		EntidadResponseDTO entidadResponseDTO = EntidadBuilder
//				.buildEntidadResponseDTOFromEntidadDTO((EntidadDTO) entidadServiceImpl.findById(idEntidad));
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Entidad recuperada correctamente",
				buildDummy());
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
	@GetMapping(value = "/catalogos/entidades/consulta/{nombre}/{estatus}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa un objeto catalogo entidad en base a su id", tags = {
			"Entidad" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Entidades encontradas"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByNombreAndEstatus(@PathVariable(value = "nombre", required = true) String nombre,
			@PathVariable(value = "estatus", required = true) Boolean estatus) {
//		EntidadResponseDTO entidadResponseDTO = entidadServiceImpl.findByNombreAndEstatus(nombre, estatus);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Entidad recuperada correctamente",
				buildDummy());
	}

	/**
	 * Crea un objeto de respuesta dummy
	 * 
	 * @return
	 */
	public static EntidadResponseDTO buildDummy() {
		Set<AfiliacionEntDTO> afiliaciones = new HashSet<>();
		afiliaciones.add(new AfiliacionEntDTO(1L, 12345678L, true));
		afiliaciones.add(new AfiliacionEntDTO(2L, 44423699L, true));
		Set<AfiliacionEntDTO> afiliaciones2 = new HashSet<>();
		afiliaciones2.add(new AfiliacionEntDTO(3L, 88345670L, true));
		afiliaciones2.add(new AfiliacionEntDTO(4L, 33423699L, true));
		Set<CuentaEntDTO> set = new HashSet<>();
		set.add(new CuentaEntDTO(1L, 123456789L, true, afiliaciones));
		set.add(new CuentaEntDTO(2L, 999456770L, true, afiliaciones2));
		Set<ContactoEntDTO> set2 = new HashSet<>();
		set2.add(new ContactoEntDTO(1L, "Juan Bautista", "josua@gmail.com", true));
		set2.add(new ContactoEntDTO(2L, "Maria DB", "mari_DB@gmail.com", true));
		EntidadResponseDTO entidadResoponseDTO = new EntidadResponseDTO(1L, "Banamex", "Banco banamex", true, set,
				set2);
		return entidadResoponseDTO;
	}

}
