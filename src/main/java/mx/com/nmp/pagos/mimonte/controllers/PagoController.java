package mx.com.nmp.pagos.mimonte.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.nmp.pagos.mimonte.dto.EstatusTarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.OperacionDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoTarjetaDTO;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * Nombre: PagoController
 * Descripcion: Clase que expone el servicio REST para la el pago con tarjeta de una o varias prendas
 *
 * @author Ismael Flores
 * @version 0.1
 */
@RestController
@RequestMapping("/mimonte")
@Api(value = "Servicio que permite registrar el pago de partidas / contratos.", description = "REST API para Registro de Pagos", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Pagos" })
public class PagoController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	BeanFactory beanFactory;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private final Logger log = LoggerFactory.getLogger(PagoController.class);

	/**
	 * Mensaje que sera enviado si se se relaliza la transaccion de pago
	 * correctamente
	 */
	private static final String MSG_SUCCESS = "Registro de pago realizado correctamente.";

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/v1/pago", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Registra el pago de una o mas partidas/contratos.", tags = { "Pagos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Pago registrado"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parámetros especificados son invalido."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fué encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response post(@RequestBody PagoDTO registroPago) {
		log.debug("Entrando a operacion de servicio RegistroPagoController.post()...");

		System.out.println("Received object: " + registroPago);
		log.debug("Received object: " + registroPago);
		// No validation in dummy data
//        log.debug("Validando parámetros...");
//        ValidadorCadena.notNullNorEmpty(registroPago);

		log.debug("Intentando registrar el pago de las partidas {}...", "dumie");
		// dummy data building begins
		PagoDTO registroPagoDTO;
		List<OperacionDTO> operaciones = new ArrayList<>();
		operaciones.add(new OperacionDTO(1, "Operacion_1", "C123", 3500D));
		operaciones.add(new OperacionDTO(2, "Operacion_2", "C456", 2500D));
		TipoTarjetaDTO tipoTarjetaDto = new TipoTarjetaDTO("Visa", "Tarjeta tipo Visa", "T Visa");
//<<<<<<< HEAD:src/main/java/mx/com/nmp/pagos/mimonte/controllers/RegistroPagoController.java
////		EstatusTarjetaDTO estatusTarjetaDto = new EstatusTarjetaDTO();
//		TarjetaDTO tarjetaDto = new TarjetaDTO(tipoTarjetaDto, null, "1", "T001002003004", "MyBSmart", 2345);
//		registroPagoDTO = new RegistroPagoDTO(operaciones, tarjetaDto, 6000, "Pago de multiples partidas", false);
//=======
		EstatusTarjetaDTO estatusTarjetaDto = new EstatusTarjetaDTO();
		TarjetaDTO tarjetaDto = new TarjetaDTO(tipoTarjetaDto, estatusTarjetaDto, "1", "T001002003004", "MyBSmart", 2345);
		registroPagoDTO = new PagoDTO(operaciones, tarjetaDto, 6000, "Pago de multiples partidas", false);
//>>>>>>> 815c3d173bc98b66d7493e675bbc71eab9535dda:src/main/java/mx/com/nmp/pagos/mimonte/controllers/PagoController.java
		// dummy data building ends

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", registroPagoDTO);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), MSG_SUCCESS, registroPagoDTO);
	}
}
