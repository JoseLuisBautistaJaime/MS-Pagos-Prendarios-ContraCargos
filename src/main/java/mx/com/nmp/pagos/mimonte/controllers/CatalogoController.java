/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.nmp.pagos.mimonte.dto.CatalogoDTO;
import mx.com.nmp.pagos.mimonte.services.CatalogoService;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorCadena;

/**
 * @name CatalogoController 
 * @description Clase que expone el servicio REST
 * para la obtencion de los registros del catalogo especificado
 *
 * @author Javier Hernandez
 * @version 0.1
 */
@RestController
@RequestMapping("/mimonte")
@Api(value = "Servicio que permite obtener los registros de un catalogo especifico.", description = "REST API para Catálogos", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Catalogos" })
public class CatalogoController {
	/**
	 * Bean de la capa service para obtener los resultados
	 */
	@Autowired
	private CatalogoService catalogoService;

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	BeanFactory beanFactory;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private final Logger log = LoggerFactory.getLogger(CatalogoController.class);

	/**
	 * Mensaje que sera enviado si se recuperaron los registros correctamente
	 */
	private static final String MSG_SUCCESS = "Registros recuperados correctamente.";

	/**
	 * Metodo que expone la operacion de servicio para recuperar los registros de un
	 * catalogo especificado.
	 * @param nombreCatalogo
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/v1/catalogo/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la informacion de un catalogo registrado en la base de datos.", tags = {
			"Catalogos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para el catalogo especificado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response get(@PathVariable(value = "nombre", required = true) String nombreCatalogo) {
		
		log.info(">>> GET /v1/catalogo/{nombre} REQUEST: {}", null != nombreCatalogo ? nombreCatalogo : "");
		
		log.debug("Entrando a operacion de servicio CatalogoController.get()...");

		log.debug("Validando parametro nombreCatalogo...");
		ValidadorCadena.notNullNorEmpty(nombreCatalogo);

		log.debug("Intentando obtener el listado de registros para el catalogo {}...", nombreCatalogo);
		CatalogoDTO catalogoDTO = catalogoService.getRegistrosCatalogo(nombreCatalogo);

		log.info(">>> GET /v1/catalogo/{nombre} RESPONSE: {}", null != catalogoDTO ? catalogoDTO : "");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), MSG_SUCCESS, catalogoDTO);
	}

	/**
	 * Metodo que expone la operacion de servicio para recuperar los extrafilter que
	 * se encuentran registrados en el sistema.
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/v1/catalogos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la lista de los nombres de extrafilter registrados en el sistema para su consulta.", tags = {
			"Catalogos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para el catalogo especificado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response get() {
				
		log.debug("Entrando a operacion de servicio CatalogoController.getCatalogos()...");

		log.debug("Intentando obtener el listado de extrafilter registrados en la base de datos...");
		List<String> catalogos = catalogoService.getCatalogosSistema();

		log.info(">>> GET /v1/catalogos RESPONSE: {}, TOTAL: {}", null != catalogos ? catalogos : "", null != catalogos ? catalogos.size() : "0");
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), MSG_SUCCESS, catalogos);
	}

}
