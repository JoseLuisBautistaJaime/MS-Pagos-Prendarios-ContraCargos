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
import mx.com.nmp.pagos.mimonte.dto.AfiliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionReqDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionRespPostDTO;
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
	 * Guarda un nuevo catalogo Afiliacion
	 * 
	 * @param pagoRequestDTO
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
	public Response save(@RequestBody AfiliacionReqDTO afiliacionDTOReq,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
		
//		AfiliacionDTO AfiliacionDTO = afiliacionServiceImpl.save(afiliacionDTOReq, createdBy);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Afiliacion guardada correctamente",
				buildDummyPost());
	}

	/**
	 * Actualiza un catalogo Afiliacion
	 * 
	 * @param AfiliacionDTO
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

//		AfiliacionDTO AfiliacionDTO = afiliacionServiceImpl.update(afiliacionDTOReq, createdBy);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Afiliacion actualizada correctamente",
				buildDummyPost());
	}

	/**
	 * Obtiene un catalogo afiliacion por su id
	 * 
	 * @param idAfiliacion
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
	public Response findById(@PathVariable(value = "numeroAfiliacion", required = true) Long numeroAfiliacion) {

		AfiliacionDTO AfiliacionDTO = afiliacionServiceImpl.findById(numeroAfiliacion);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Afiliacion recuperada correctamente",
				buildDummyPost());
	}

	/**
	 * Obtiene uno o mas catalogos de afiliacion por su nombre y estatus
	 * 
	 * @param nombre
	 * @param estatus
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

		AfiliacionDTO afiliacionDTO = afiliacionServiceImpl.findByCuentasId(idCuenta);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Afiliacion recuperada correctamente",
				buildDummyList());
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/catalogos/afiliaciones/{idAfiliacion}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Eliminacion logica del registro en base a su id", tags = {
			"Afiliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Afiliacion eliminada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response deleteByidAfiliacion(@PathVariable(value = "idAfiliacion", required = true) Long idAfiliacion,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {

		
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Afiliacion eliminada correctamente",
				null);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/afiliaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa todos los objeto catalogo afiliacion", tags = {
			"Afiliacion" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Afiliaciones encontradas"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findAll() {

//		List<AfiliacionDTO> AfiliacionDTOList = afiliacionServiceImpl.findAll();

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Afiliaciones recuperadas correctamente",
				buildDummyLst());
	}
	
	/**
	 * Crea un objeto de respuesta dummy
	 * 
	 * @return
	 */
	public static AfiliacionRespPostDTO buildDummyPost() {
		AfiliacionRespPostDTO afiliacionDto = new AfiliacionRespPostDTO();
		afiliacionDto.setEstatus(true);
		afiliacionDto.setId(234L);
		afiliacionDto.setNumero(12345678L);
		return afiliacionDto;
	}
	
	public static List<AfiliacionRespPostDTO> buildDummyLst() {
		List<AfiliacionRespPostDTO> lst = new ArrayList<>();
		AfiliacionRespPostDTO afiliacionDto = new AfiliacionRespPostDTO();
		afiliacionDto.setEstatus(true);
		afiliacionDto.setId(9987L);
		afiliacionDto.setNumero(990088L);
		AfiliacionRespPostDTO afiliacionDto2 = new AfiliacionRespPostDTO();
		afiliacionDto2.setEstatus(true);
		afiliacionDto2.setId(234L);
		afiliacionDto2.setNumero(12345678L);
		lst.add(afiliacionDto);
		lst.add(afiliacionDto2);
		return lst;
	}

	/**
	 * Crea un objeto de respuesta dummy
	 * 
	 * @return
	 */
	public static AfiliacionDTO buildDummy() {

		AfiliacionDTO afiliacionDto = new AfiliacionDTO();
		TipoAutorizacionDTO tipo = new TipoAutorizacionDTO();
		tipo.setDescripcion("3d secure ");
		tipo.setId(2);
		afiliacionDto.setCreatedBy("Victor Moran");
		afiliacionDto.setCreatedDate(new Date());
		afiliacionDto.setEstatus(true);
		afiliacionDto.setId(234L);
		afiliacionDto.setNumero(12345678L);
		afiliacionDto.setTipo(tipo);

		return afiliacionDto;
	}

	public static AfiliacionDTO buildDummyUP() {

		AfiliacionDTO afiliacionDto = new AfiliacionDTO();
		TipoAutorizacionDTO tipo = new TipoAutorizacionDTO();
		tipo.setDescripcion("3d secure ");
		tipo.setId(2);
		afiliacionDto.setCreatedBy("Victor Moran");
		afiliacionDto.setCreatedDate(new Date());
		afiliacionDto.setEstatus(true);
		afiliacionDto.setId(234L);
		afiliacionDto.setLastModifiedBy("Viktor Reznov");
		afiliacionDto.setLastModifiedDate(new Date());
		afiliacionDto.setNumero(12345678L);
		afiliacionDto.setTipo(tipo);

		return afiliacionDto;
	}

	public static List<AfiliacionRespPostDTO> buildDummyList() {

		List<AfiliacionRespPostDTO> afiliaciones = new ArrayList<>();
		AfiliacionRespPostDTO afiliacionDto = new AfiliacionRespPostDTO();
		AfiliacionRespPostDTO afiliacionDto2 = new AfiliacionRespPostDTO();
		afiliacionDto.setEstatus(true);
		afiliacionDto.setId(234L);
		afiliacionDto.setNumero(12345678L);
		afiliaciones.add(afiliacionDto);
		afiliacionDto2.setId(6789L);
		afiliacionDto2.setNumero(987654L);
		afiliacionDto2.setEstatus(true);
		afiliaciones.add(afiliacionDto2);
		return afiliaciones;
	}

}