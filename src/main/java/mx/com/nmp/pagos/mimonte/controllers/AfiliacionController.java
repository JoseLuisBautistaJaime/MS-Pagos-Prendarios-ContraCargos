package mx.com.nmp.pagos.mimonte.controllers;

import java.util.Date;

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
import mx.com.nmp.pagos.mimonte.dto.AfiliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoAutorizacionDTO;
import mx.com.nmp.pagos.mimonte.services.impl.AfiliacionServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * Nombre: AfiliacionController Descripcion: Clase que expone el servicio REST
 * para las operaciones relacionadas con el catalogo de afiliacion
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
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(AfiliacionController.class);

	/**
	 * Service de Afiliacion
	 */
	@Qualifier("afiliacionServiceImpl")
	private AfiliacionServiceImpl afiliacionServiceImpl;

	/**
	 * Guarda un nuevo catalogo Entidad
	 * 
	 * @param pagoRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/catalogos/afiliacion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Crea un nuevo catalogo entidad.", tags = { "Afiliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Entidad creada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response save(@RequestBody AfiliacionDTO afiliacionDTOReq) {

//		AfiliacionDTO AfiliacionDTO = afiliacionServiceImpl.save(afiliacionDTOReq);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Entidad guardada correctamente",
				buildDummy());
	}

	/**
	 * Actualiza un catalogo Entidad
	 * 
	 * @param AfiliacionDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/catalogos/afiliacion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Actualiza un catalogo afiliacion.", tags = { "Afiliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Afiliacion actualizada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response update(@RequestBody AfiliacionDTO afiliacionDTOReq) {

//		AfiliacionDTO AfiliacionDTO = afiliacionServiceImpl.update(afiliacionDTOReq);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Entidad actualizada correctamente",
				buildDummy());
	}

	/**
	 * Obtiene un catalogo entidad por su id
	 * 
	 * @param idAfiliacion
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/afiliacion/{idAfiliacion}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa un objeto catalogo afiliacion en base a su id", tags = {
			"Afiliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Afiliacion encontrada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findById(@PathVariable(value = "idAfiliacion", required = true) Long idAfiliacion) {

//		AfiliacionDTO AfiliacionDTO = afiliacionServiceImpl.findById(idAfiliacion);

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
	@GetMapping(value = "/catalogos/afiliacion/cuenta/{idCuenta}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa un objeto catalogo entidad en base a su id", tags = {
			"Afiliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Afiliacion encontradas"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByCuenta(@PathVariable(value = "idCuenta", required = true) Long idCuenta) {

//		AfiliacionDTO afiliacionDTO = afiliacionServiceImpl.findByCuentasId(idCuenta);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Afiliacion recuperada correctamente",
				buildDummy());
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/catalogos/afiliacion/{idAfiliacion}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "Elimina el registro en base a su id", tags = { "Afiliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Afiliacion encontradas"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response deleteByidAfiliacion(@PathVariable(value = "nombre", required = true) String nombre,
			@PathVariable(value = "estatus", required = true) Boolean estatus) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Afiliacion eliminada correctamente");
	}

	/**
	 * Crea un objeto de respuesta dummy
	 * 
	 * @return
	 */
	public static AfiliacionDTO buildDummy() {

		AfiliacionDTO afiliacionDto = new AfiliacionDTO();
		afiliacionDto.setCreatedBy("Victi Moran");
		afiliacionDto.setCreatedDate(new Date());
		afiliacionDto.setEstatus(true);
		afiliacionDto.setId(234L);
		afiliacionDto.setLastModifiedBy("Viktor Reznov");
		afiliacionDto.setLastModifiedDate(new Date());
		afiliacionDto.setNumero(12345678L);
		afiliacionDto.setTipo(new TipoAutorizacionDTO());

		return afiliacionDto;
	}

}