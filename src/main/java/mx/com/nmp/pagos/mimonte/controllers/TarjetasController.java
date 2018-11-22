package mx.com.nmp.pagos.mimonte.controllers;

import java.util.ArrayList;
import java.util.Date;
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
import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.dto.EstatusTarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoTarjetaDTO;
import mx.com.nmp.pagos.mimonte.services.TarjetasService;
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
	@GetMapping(value = "/v1/tarjetas/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa la información de las tarjetas registradas en la base de datos.", tags = {
			"Tarjetas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response get(@PathVariable(value = "idCliente", required = true) Integer idCliente) {

		log.debug("Entrando a operacion de servicio RegistroTarjetasController.get()...");

//		log.debug("Validando parametro idTarjeta...");
//		ValidadorCadena.notNullNorEmpty(idCliente);

		log.debug("Intentando obtener el listado de registros para las tarjetas {}...", idCliente);
		List<TarjetaDTO> tarjetaDTO = tarjetasService.getTarjetas(idCliente);

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", tarjetaDTO);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), Constants.MSG_SUCCESS, tarjetaDTO);

	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/v1/tarjetas", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod ="GET", value = "Regresa la información de las tarjetas registradas en la base de datos.", tags = { "Tarjetas" })
    @ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
                    @ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
                    @ApiResponse(code = 404, response = Response.class, message = "No existen registros para la tarjeta especifica."),
                    @ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
    public Response get() {
    	
//    	 log.debug("Entrando a operacion de servicio RegistroTarjetasController.get()...");
//
//         log.debug("Validando parametro idTarjeta...");
//         ValidadorCadena.notNullNorEmpty(idTarjeta);
//
//         log.debug("Intentando obtener el listado de registros para las tarjetas {}...", idTarjeta);
//         List<TarjetaDTO> tarjetaDTO = tarjetasService.getTarjetas(idTarjeta);
    	
    	
    	List<TipoTarjetaDTO> tipoTarjeta = new ArrayList<TipoTarjetaDTO>();
		List<EstatusTarjetaDTO> estatusTarjeta = new ArrayList<EstatusTarjetaDTO>();
		List<TarjetaDTO> tarjetaDTO = new ArrayList<TarjetaDTO>();
		

		TipoTarjetaDTO tipoTarjetaDto = new TipoTarjetaDTO(1, "Tarjeta tipo Visa", "T Visa");
		tipoTarjeta.add(tipoTarjetaDto);

		EstatusTarjetaDTO estatusTarjetaDto = new EstatusTarjetaDTO();
		estatusTarjetaDto.setId(1);
		estatusTarjetaDto.setDescripcionCorta("Activa");
		estatusTarjetaDto.setDescripcion("Activa");
		estatusTarjeta.add(estatusTarjetaDto);
		
		//objects
		ClienteDTO clienteDTO = new ClienteDTO(0,"Juan",new Date());

		TarjetaDTO tarjetaDto = new TarjetaDTO("FS3444T53GT5","2345","myBsmart",new Date(),new Date(),clienteDTO,tipoTarjeta.get(0),estatusTarjeta.get(0));
		tarjetaDTO.add(tarjetaDto);
		tarjetaDTO.add(tarjetaDto);
		tarjetaDTO.add(tarjetaDto);

         log.debug("Regresando instancia Response con la respuesta obtenida: {}...", tarjetaDTO);
         return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), Constants.MSG_SUCCESS, tarjetaDTO);
    	
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

		log.debug("Entrando a operacion de servicio RegistroTarjetasController.post()...");

		log.debug("Intentando obtener el listado de registros para el catalogo {}...", tarjeta);
		List<TarjetaDTO> tarjetaDTO = tarjetasService.addTarjetas(tarjeta);

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", tarjetaDTO);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), Constants.MSG_SUCCESS_ADD, tarjetaDTO);

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
	public Response update(@PathVariable(value = "token", required = true) String token,
			@PathVariable(value = "alias", required = true) String alias) {

		// log.debug("Entrando a operacion de servicio
		// RegistroTarjetasController.update()...");
		//
		// log.debug("Validando parametro idTarjeta...");
		// ValidadorCadena.notNullNorEmpty(idTarjeta);
		//
		// log.debug("Validando parametro alias...");
		// ValidadorCadena.notNullNorEmpty(alias);

		log.debug("Intentando actualizar el registro de la tarjeta {}...", token);
		List<TarjetaDTO> tarjetaDTO = tarjetasService.updateTarjetas(token);

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", tarjetaDTO);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), Constants.MSG_SUCCESS_UPDATE, tarjetaDTO);

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/v1/tarjeta/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "Borra la información de la tarjeta registrada en la base de datos.", tags = {
			"Tarjetas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Registros obtenidos"),
			@ApiResponse(code = 400, response = Response.class, message = "El parámetro especificado es invalido."),
			@ApiResponse(code = 404, response = Response.class, message = "No existen registros para el catalogo especificado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response delete(@PathVariable(value = "token", required = true) String token) {

		// log.debug("Entrando a operacion de servicio
		// RegistroTarjetasController.delete()...");
		//
		// log.debug("Validando parametro idTarjeta...");
		// ValidadorCadena.notNullNorEmpty(idTarjeta);

		log.debug("Intentando borrar el registro de la tarjeta {}...", token);
		TarjetaDTO tarjetaDTO = tarjetasService.deleteTarjetas(token);

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", tarjetaDTO);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), Constants.MSG_SUCCESS_DELETE, tarjetaDTO);

	}

}
