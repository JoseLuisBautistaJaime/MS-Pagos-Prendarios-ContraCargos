package mx.com.nmp.pagos.mimonte.controllers;

import java.util.List;

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
import mx.com.nmp.pagos.mimonte.constans.TarjetaConstants;
import mx.com.nmp.pagos.mimonte.dto.AliasDTO;
import mx.com.nmp.pagos.mimonte.dto.CatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjeDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.model.Tarjetas;
import mx.com.nmp.pagos.mimonte.services.TarjetasService;
import mx.com.nmp.pagos.mimonte.services.impl.TarjetasServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorCadena;

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
		
		log.debug("Entrando a operacion de servicio TarjetasController.get()...");


		log.info("Intentando obtener el listado de registros para las tarjetas {}...", idCliente);

		List<TarjeDTO> tarjetasCliente = tarjetasService.getTarjetasIdCliente(idCliente);


		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", tarjetasCliente);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), TarjetaConstants.MSG_SUCCESS, tarjetasCliente);

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/v1/tarjetas/{token}/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de las tarjetas registradas con respecto a los parámetros token y id del cliente.", tags = { "Tarjetas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response get(@PathVariable(value = "idCliente", required = true) Integer idcliente, @PathVariable(value = "token", required = true) String token) {

		log.debug("Entrando a operacion de servicio TarjetasController.get()...");

		log.debug("Validando parametro token...");
		ValidadorCadena.notNullNorEmpty(token);

		TarjeDTO tarjetaIdClienteAndToken = tarjetasService.getTarjetasTokenIdCliente(idcliente, token);

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", tarjetaIdClienteAndToken);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), TarjetaConstants.MSG_SUCCESS, tarjetaIdClienteAndToken);

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

		log.debug("Entrando a operacion de servicio TarjetasController.get()...");

		log.debug("Validando parametro token...");
		ValidadorCadena.notNullNorEmpty(token);

		log.debug("Intentando obtener el listado de registros para el tarjetas {}...", token);

		TarjeDTO tarjetaToken = tarjetasService.getTarjetasToken(token);

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", tarjetaToken);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), TarjetaConstants.MSG_SUCCESS, tarjetaToken);

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

		
		log.info("Intentando obtener el listado de registros para las tarjetas {}...", tarjeta);

		Tarjetas guardaTarjeta = tarjetasService.addTarjetas(tarjeta);

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", guardaTarjeta);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), TarjetaConstants.MSG_SUCCESS_ADD, guardaTarjeta);

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/v1/tarjeta/update", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Actualiza la información de la tarjeta registrada en la base de datos.", tags = {
			"Tarjetas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para el catalogo especificado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response update(@RequestBody AliasDTO  alias) {
		
		log.debug("Entrando a operacion de servicio TarjetasController.update()...");

	    log.debug("Validando parametro token...");
	    ValidadorCadena.notNullNorEmpty(alias.getToken());
	    
	    log.debug("Validando parametro alias...");
	    ValidadorCadena.notNullNorEmpty(alias.getAlias());

	    log.debug("Intentando obtener el listado de registros para la tarjeta {}...", alias.getToken() + " " + alias);
	    Tarjetas updateTarjetas = tarjetasService.updateTarjeta(alias.getToken(), alias.getAlias());

	    log.debug("Regresando instancia Response con la respuesta obtenida: {}...", updateTarjetas);
	    return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), TarjetaConstants.MSG_SUCCESS_UPDATE, updateTarjetas);

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
		
		log.debug("Entrando a operacion de servicio TarjetasController.delete()...");
		
		log.debug("Validando parametro token...");
	    ValidadorCadena.notNullNorEmpty(token);

		log.debug("Intentando borrar el registro de la tarjeta {}...", token);
		 Tarjetas deletetarjeta = tarjetasService.deleteTarjeta(token);
		
		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", deletetarjeta);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), TarjetaConstants.MSG_SUCCESS_DELETE, deletetarjeta);

	}

}
