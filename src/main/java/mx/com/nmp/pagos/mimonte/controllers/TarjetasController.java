package mx.com.nmp.pagos.mimonte.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import mx.com.nmp.pagos.mimonte.config.Constants;
import mx.com.nmp.pagos.mimonte.dto.TarjeDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.services.TarjetasService;
import mx.com.nmp.pagos.mimonte.util.Response;

@RestController
@RequestMapping("/mimonte")
@Api(value = "Servicio que permite registrar tarjetas de crédito y/o débito.", description = "REST API para registro de Tarjetas", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Tarjetas" })
public class TarjetasController {

	/**
	 * Bean de la capa service para obtener los resultados
	 */
	@Autowired
	private TarjetasService tarjetasService;

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	BeanFactory beanFactory;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private final Logger log = LoggerFactory.getLogger(TarjetasController.class);

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/v1/tarjetas/cliente/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de las tarjetas registradas con respecto al parámetro del id del cliente.", tags = { "Tarjetas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response get(@PathVariable(value = "idCliente", required = true) Integer idCliente) {

		Response response = new Response();

		log.info("Intentando obtener el listado de registros para las tarjetas {}...", idCliente);

		Response tarjeta = tarjetasService.getTarjetasIdCliente(idCliente);

		response = tarjeta;

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", tarjeta);

		return response;

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/v1/tarjetas/{token}/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de las tarjetas registradas con respecto a los parámetros token y id del cliente.", tags = { "Tarjetas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response get(@PathVariable(value = "idCliente", required = true) Integer idcliente,
			@PathVariable(value = "token", required = true) String token) {

		Response response = new Response();

		log.info("Intentando obtener el listado de registros para las tarjetas {}...", idcliente);

		Response tarjeta = tarjetasService.getTarjetasTokenIdCliente(idcliente, token);

		response = tarjeta;

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", tarjeta);

		return response;

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/v1/tarjetas/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de las tarjetas registradas con respecto al parámetro del token.", tags = { "Tarjetas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response get(@PathVariable(value = "token", required = true) String token) {

		Response response = new Response();

		log.info("Intentando obtener el listado de registros para las tarjetas {}...", token);

		Response tarjeta = tarjetasService.getTarjetasToken(token);

		response = tarjeta;

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", tarjeta);

		return response;

	}
	

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/v1/tarjeta", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Registra la información de la tarjeta en la base de datos.", tags = {
			"Tarjetas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para el catalogo especificado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response add(@RequestBody TarjetaDTO tarjeta) {

		Response response = new Response();

		log.info("Intentando obtener el listado de registros para las tarjetas {}...", tarjeta);

		Response guardado = tarjetasService.addTarjetas(tarjeta);

		response = guardado;

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", guardado);

		return response;

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/v1/tarjeta/{token}/{alias}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Actualiza la información de la tarjeta registrada en la base de datos.", tags = {
			"Tarjetas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para el catalogo especificado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response update(@PathVariable(value = "token", required = true) String token, @PathVariable(value = "alias", required = true) String alias) {

		log.debug("Intentando actualizar el registro de la tarjeta {}...", token);
		Response reponse = tarjetasService.updateTarjeta(token, alias);

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", reponse);
		return reponse;

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/v1/tarjeta/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "Borra la información de la tarjeta registrada en la base de datos.", tags = {	"Tarjetas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para el catalogo especificado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response delete(@PathVariable(value = "token", required = true) String token) {

		log.debug("Intentando borrar el registro de la tarjeta {}...", token);
		Response deleteTarjeta = tarjetasService.deleteTarjeta(token);
		
		Response response = deleteTarjeta;

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", response);
		
		return response;

	}

}
