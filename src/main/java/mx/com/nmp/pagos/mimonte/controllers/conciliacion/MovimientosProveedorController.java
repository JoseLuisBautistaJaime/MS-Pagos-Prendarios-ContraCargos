/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoIDDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalDTO;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * @name MovimientosProveedorController
 * @description Clase de tipo Controlador spring que expone los servicios para
 *              realizar operaciones relacionadas con el proveedor transaccional
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 02/04/2019 15:05 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar operaciones relacionadas con proveedor transaccional.", description = "REST API para Proveedor transaccional", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Movimientos Proveedor" })
public class MovimientosProveedorController {

	/**
	 * Fabrica de beans
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Imprime logs de la aplicacion
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(MovimientosProveedorController.class);

	/**
	 * Service para catalogo de entidades
	 */
//	@Autowired
//	@Qualifier("movimientosProveedorService")
//	private MovimientosProveedorService movimientosProveedorService;

	/**
	 * Permite consultar los movimientos del proveedor transaccional. (Open Pay)
	 * 
	 * @param movimientosProveedorSaveDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/movimientos/proveedor/consulta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite consultar los movimientos del proveedor transaccional. (Open Pay)", tags = {
			"Movimientos Proveedor" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findMovimientosProvedor(@RequestBody CommonConciliacionRequestDTO commonConciliacionRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		MovimientoTransaccionalDTO movimientoTransaccionalDTO = null;
		movimientoTransaccionalDTO = buildDummy1();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				movimientoTransaccionalDTO);
	}

	/**
	 * Permite consultar los movimientos del resultado de los procesos nocturnos.
	 * 
	 * @param movimientosProveedorSaveDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/movimientos/nocturnos/consulta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite consultar los movimientos del resultado de los procesos nocturnos.", tags = {
			"Movimientos Proveedor" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findMovimientosNocturnos(@RequestBody CommonConciliacionRequestDTO commonConciliacionRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		MovimientoProcesosNocturnosDTO movimientoProcesosNocturnosDTO = null;
		movimientoProcesosNocturnosDTO = buildDummy2();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				movimientoProcesosNocturnosDTO);
	}

	/**
	 * Permite dar de alta movimientos que provienen del Proveedor Transaccional
	 * (Open Pay).
	 * 
	 * @param movimientoTransaccionalDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/movimientos/proveedor/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite dar de alta movimientos que provienen del Proveedor Transaccional (Open Pay).", tags = {
			"Movimientos Proveedor" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response saveMovimientosProvedor(@RequestBody MovimientoTransaccionalDTO movimientoTransaccionalDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		MovimientoIDDTO movimientoIDDTO = null;
		movimientoIDDTO = buildDummy3();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				movimientoIDDTO);
	}

	/**
	 * Permite dar de alta movimientos resultado de los Procesos Nocturnos.
	 * 
	 * @param movimientoProcesosNocturnosDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/movimientos/nocturnos/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite dar de alta movimientos resultado de los Procesos Nocturnos.", tags = {
			"Movimientos Proveedor" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response saveMovimientosNocturnos(@RequestBody MovimientoProcesosNocturnosDTO movimientoProcesosNocturnosDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		MovimientoIDDTO movimientoIDDTO = null;
		movimientoIDDTO = buildDummy3();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				movimientoIDDTO);
	}

	/**
	 * Construye una respuesta dummy
	 * 
	 * @return
	 */
	public static MovimientoTransaccionalDTO buildDummy1() {
		MovimientoTransaccionalDTO movimientoTransaccionalDTO = new MovimientoTransaccionalDTO();
		movimientoTransaccionalDTO.setCodigoAutorizacion("67032");
		movimientoTransaccionalDTO.setCodigoPuertaEnlace("Aprobado");
		movimientoTransaccionalDTO.setCodigoRespuesta("0");
		movimientoTransaccionalDTO.setEntidadGestora("EGLOBAL");
		movimientoTransaccionalDTO.setEsquemaTarjeta("Visa");
		movimientoTransaccionalDTO.setFecha(new Date());
		movimientoTransaccionalDTO.setId(1L);
		movimientoTransaccionalDTO.setIdComerciante("1063488");
		movimientoTransaccionalDTO.setIdentificadorBanco("");
		movimientoTransaccionalDTO.setIdentificadorCuenta("481515xxxxxx6567");
		movimientoTransaccionalDTO.setIdPedido("6ae26139-6b12-4050-8c2b-2de6319487b3");
		movimientoTransaccionalDTO.setIdTransaccion("1");
		movimientoTransaccionalDTO.setMetodoPago("Tarjeta");
		movimientoTransaccionalDTO.setMoneda("MXN");
		movimientoTransaccionalDTO.setMonto(406.45);
		movimientoTransaccionalDTO.setNumeroLotePago("20181001");
		movimientoTransaccionalDTO.setOrigenTransaccion("Internet");
		movimientoTransaccionalDTO.setReciboTransaccion("827412214425\"");
		movimientoTransaccionalDTO.setRecomendacionRiesgo("");
		movimientoTransaccionalDTO.setReferenciaPedido("148341390002");
		movimientoTransaccionalDTO.setReferenciaTransaccion("");
		movimientoTransaccionalDTO.setRespuesta3DS("");
		movimientoTransaccionalDTO.setRespuestaAVS("");
		movimientoTransaccionalDTO.setRespuestaCSC("");
		movimientoTransaccionalDTO.setResultado("Exito");
		movimientoTransaccionalDTO.setResultadoRevisionRiesgo("");
		movimientoTransaccionalDTO.setT3dsECI("");
		movimientoTransaccionalDTO.setTipoTransaccion("Pago");
		movimientoTransaccionalDTO.setTitularCuenta("Eduardo Lopez Lopez");
		return movimientoTransaccionalDTO;
	}

	/**
	 * Construye una respuesta dummy
	 * 
	 * @return
	 */
	public static MovimientoProcesosNocturnosDTO buildDummy2() {
		MovimientoProcesosNocturnosDTO movimientoProcesosNocturnosDTO = new MovimientoProcesosNocturnosDTO();
		movimientoProcesosNocturnosDTO.setCapital(400.12);
		movimientoProcesosNocturnosDTO.setComisiones(10.23);
		movimientoProcesosNocturnosDTO.setEstatus("Exitoso");
		movimientoProcesosNocturnosDTO.setFecha(new Date());
		movimientoProcesosNocturnosDTO.setFolio(12345);
		movimientoProcesosNocturnosDTO.setInteres(24.52);
		movimientoProcesosNocturnosDTO.setMontoOperacion(123.45);
		movimientoProcesosNocturnosDTO.setNumAutorizacion("12345");
		movimientoProcesosNocturnosDTO.setOperacionAbr("APL");
		movimientoProcesosNocturnosDTO.setOperacionDesc("Abonos Pagos-Libres");
		movimientoProcesosNocturnosDTO.setSucursal(12L);
		movimientoProcesosNocturnosDTO.setTipoContratoAbr("PL");
		movimientoProcesosNocturnosDTO.setTipoContratoDesc("Pagos Libres");
		return movimientoProcesosNocturnosDTO;
	}

	/**
	 * Construye una respuesta dummy
	 * 
	 * @return
	 */
	public static MovimientoIDDTO buildDummy3() {
		MovimientoIDDTO movimientoIDDTO = new MovimientoIDDTO();
		List<Long> lst = new ArrayList<>();
		lst.add(1L);
		movimientoIDDTO.setIdsMovimientos(lst);
		return movimientoIDDTO;
	}

}
