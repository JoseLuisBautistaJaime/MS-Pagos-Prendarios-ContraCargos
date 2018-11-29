package mx.com.nmp.pagos.mimonte.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import mx.com.nmp.pagos.mimonte.constans.PagoConstants;
import mx.com.nmp.pagos.mimonte.dto.EstatusPagoResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoResponseDTO;
import mx.com.nmp.pagos.mimonte.services.PagoService;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * Nombre: PagoController
 * Descripcion: Clase que expone el servicio REST para el
 * pago con tarjeta de una o varias partidas
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 19/11/2018 12:00 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping("/mimonte")
@Api(value = "Servicio que permite realizar el pago de partidas / contratos.", description = "REST API para Registro de Pagos", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Pagos" })
public class PagoController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Service que realiza el pago de partidas / contratos
	 */
	@Autowired
	@Qualifier("pagoServiceImpl")
	private PagoService pagoService;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private final Logger log = LoggerFactory.getLogger(PagoController.class);

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/v1/pago", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Registra el pago de una o mas partidas/contratos.", tags = { "Pagos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Pago registrado"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parámetros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public ResponseEntity<Response> post(@RequestBody PagoRequestDTO pagoRequestDTO) {
		log.debug("Entrando a operacion de servicio RegistroPagoController.post()...");
		log.debug("Received object: " + pagoRequestDTO);
		log.debug("Intentando registrar el pago de las partidas {}...", "dumie");

		// --------------------- Dummy data building begins
		EstatusPagoResponseDTO estatusPagoResponseDTO = new EstatusPagoResponseDTO(1, "C12");
		EstatusPagoResponseDTO estatusPagoResponseDTO2 = new EstatusPagoResponseDTO(1, "C34");
		List<EstatusPagoResponseDTO> estatusPagos = new ArrayList<>();
		estatusPagos.add(estatusPagoResponseDTO);
		estatusPagos.add(estatusPagoResponseDTO2);
		// Ahora se obtiene un numero aleatorio, pero ese valor entre 1 y 3 debe estar
		// en funcion de unas reglas de negocio
		// en el modulo de toma de deciciones
		PagoResponseDTO pagoResponseDTO = new PagoResponseDTO(estatusPagos, true, getRandomNumber());
		// --------------------- Dummy data building ends

		// real code begins
		// ---------------- CODE HERE ------------------
//		try {
//			pagoResponseDTO = pagoService.savePago(pagoRequestDTO);
//		} catch (ClienteException | PagoException gex) {
//			log.error(gex.getMessage());
//		}
		// real code ends

		log.debug("Regresando instancia Response con la respuesta obtenida: {}...", pagoResponseDTO);
		return new ResponseEntity<>(beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				PagoConstants.MSG_SUCCESS, pagoResponseDTO), HttpStatus.OK);
	}

	/**
	 * Metodo que regresa un numero aleatorio entre 1 y 3 simulando la tomade
	 * decicion para un numero de afiliacion
	 * 
	 * @return int value
	 */
	private static int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(3 - 1 + 1) + 1;
	}
}
