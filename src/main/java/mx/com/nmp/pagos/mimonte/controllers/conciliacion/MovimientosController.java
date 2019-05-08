/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.math.BigDecimal;
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
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MetodoPagoMovimientosProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoIDDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.TarjetaMovimientosProveedorDTO;
import mx.com.nmp.pagos.mimonte.services.conciliacion.MovimientosMidasService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.MovimientosProveedorService;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * @name MovimientosController
 * @description Clase de tipo Controlador spring que expone los servicios
 *              relacionados con Movimientos de estados de cuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 09:06 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "", description = "REST API para Movimientos", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Movimientos" })
public class MovimientosController {

	/**
	 * Fabrica de beans
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Instancia que imprime logs de los eventos
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(MovimientosController.class);

	/**
	 * Service de movimientos midas
	 */
	@Autowired
	@Qualifier("movimientosMidasService")
	private MovimientosMidasService movimientosMidasService;

	/**
	 * Service de movimientos proveedor
	 */
	@Autowired
	@Qualifier("movimientosProveedorService")
	private MovimientosProveedorService movimientosProveedorService;

	/**
	 * Consulta movimientos estado de cuneta por filtros de objeto
	 * CommonConciliacionRequestDTO
	 * 
	 * @param commonConciliacionRequestDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/movimientos/estadocuenta/consulta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite consultar los movimientos del estado de cuenta.", tags = {
			"Movimientos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta movimientos exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response save(@RequestBody CommonConciliacionRequestDTO commonConciliacionRequestDTO) {
		MovimientosEstadoCuentaDTO movimientosEstadoCuentaDTO = null;
		movimientosEstadoCuentaDTO = buildDummy1();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta movimientos exitosa.",
				movimientosEstadoCuentaDTO);
	}

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
			"Movimientos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta movimientos exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findMovimientosProvedor(@RequestBody CommonConciliacionRequestDTO commonConciliacionRequestDTO) {
		MovimientoTransaccionalListDTO movimientoTransaccionalListDTO = null;
		movimientoTransaccionalListDTO = buildDummyX1();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.MSG_SUCCESSFUL_MOVIMIENTOS_QUERY, movimientoTransaccionalListDTO);
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
			"Movimientos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findMovimientosNocturnos(@RequestBody CommonConciliacionEstatusRequestDTO commonConciliacionRequestDTO) {
		MovimientoProcesosNocturnosListDTO movimientoProcesosNocturnosListDTO = null;
		movimientoProcesosNocturnosListDTO = buildDummyX2();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.MSG_SUCCESSFUL_MOVIMIENTOS_QUERY, movimientoProcesosNocturnosListDTO);
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
	@PostMapping(value = "/movimientos/proveedor", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite dar de alta movimientos que provienen del Proveedor Transaccional (Open Pay).", tags = {
			"Movimientos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response saveMovimientosProvedor(
			@RequestBody MovimientoTransaccionalListRequestDTO movimientoTransaccionalDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
//		MovimientoIDDTO movimientoIDDTO = null;
//		movimientoIDDTO = buildDummyX3();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
//			movimientoIDDTO
				null);
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
	@PostMapping(value = "/movimientos/nocturnos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite dar de alta movimientos resultado de los Procesos Nocturnos.", tags = {
			"Movimientos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response saveMovimientosNocturnos(
			@RequestBody MovimientoProcesosNocturnosListResponseDTO movimientoProcesosNocturnosDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {

//		MovimientoIDDTO movimientoIDDTO = null;
//		movimientoIDDTO = buildDummyX3();

//		MovimientoIDDTO movimientoIDDTO = null;
//		movimientoIDDTO = buildDummyX3();

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				null);
	}

	/**
	 * Construye una respuesta dummy
	 * 
	 * @return
	 */
	public static MovimientoTransaccionalListDTO buildDummyX1() {
		MovimientoTransaccionalListDTO movimientoTransaccionalListDTO = new MovimientoTransaccionalListDTO();
		MovimientoProveedorDTO movimientoTransaccionalDTO = new MovimientoProveedorDTO();
		movimientoTransaccionalDTO.setPaymentMethod(new MetodoPagoMovimientosProveedorDTO());
		movimientoTransaccionalDTO.setCard(new TarjetaMovimientosProveedorDTO());
		movimientoTransaccionalDTO.setAmount(new BigDecimal("100.02"));
		movimientoTransaccionalDTO.setAuthorization("AUTH123");
		movimientoTransaccionalDTO.getCard().setAddress("ADDRESS123");
		movimientoTransaccionalDTO.getCard().setAllowsCharges(true);
		movimientoTransaccionalDTO.getCard().setAllowsPayouts(true);
		movimientoTransaccionalDTO.getCard().setBankCode("bxm123");
		movimientoTransaccionalDTO.getCard().setBankName("citi");
		movimientoTransaccionalDTO.getCard().setBrand("brand 123");
		movimientoTransaccionalDTO.getCard().setCardNumber("1234567891234567");
		movimientoTransaccionalDTO.getCard().setCreationDate(new Date());
		movimientoTransaccionalDTO.getCard().setCustomerId("123");
		movimientoTransaccionalDTO.getCard().setExpirationMonth("10");
		movimientoTransaccionalDTO.getCard().setExpirationYear("2024");
		movimientoTransaccionalDTO.getCard().setHolderName("Nathan Gould");
		movimientoTransaccionalDTO.getCard().setId("1");
		movimientoTransaccionalDTO.getCard().setType("visa");
		movimientoTransaccionalDTO.setConciliated(true);
		movimientoTransaccionalDTO.setCreationDate(new Date());
		movimientoTransaccionalDTO.setCurrency("mxn");
		movimientoTransaccionalDTO.setCustomerId("12554");
		movimientoTransaccionalDTO.setDescription("movimiento proveedor 1");
		movimientoTransaccionalDTO.setErrorCode("5003");
		movimientoTransaccionalDTO.setErrorMessage("ocurrio un error");
		movimientoTransaccionalDTO.setId(1L);
		movimientoTransaccionalDTO.setIdMovimiento("1");
		movimientoTransaccionalDTO.setMethod("method 1");
		movimientoTransaccionalDTO.setOperationDate(new Date());
		movimientoTransaccionalDTO.setOperationType("type 1");
		movimientoTransaccionalDTO.setOrderId("3344");
		movimientoTransaccionalDTO.setPaymentMethod(new MetodoPagoMovimientosProveedorDTO());
		movimientoTransaccionalDTO.getPaymentMethod().setType("card");
		movimientoTransaccionalDTO.getPaymentMethod().setUrl("http://payment.net/123");
		movimientoTransaccionalDTO.setStatus("active");
		movimientoTransaccionalDTO.setTransactionType("type tr 1");
		movimientoTransaccionalListDTO.setTotal(406L);
		List<MovimientoProveedorDTO> lst = new ArrayList<>();
		lst.add(movimientoTransaccionalDTO);
		movimientoTransaccionalListDTO.setMovimientos(lst);
		return movimientoTransaccionalListDTO;
	}

	/**
	 * Construye una respuesta dummy
	 * 
	 * @return
	 */
	public static MovimientoProcesosNocturnosListDTO buildDummyX2() {
		MovimientoProcesosNocturnosListDTO movimientoProcesosNocturnosListDTO = new MovimientoProcesosNocturnosListDTO();
		MovimientoMidasDTO movimientoProcesosNocturnosDTO = new MovimientoMidasDTO();
		movimientoProcesosNocturnosDTO.setId(1L);
		movimientoProcesosNocturnosDTO.setTransaccion(1L);
		movimientoProcesosNocturnosDTO.setCapitalActual(new BigDecimal("400.12"));
		movimientoProcesosNocturnosDTO.setComisiones(new BigDecimal("10.23"));
		movimientoProcesosNocturnosDTO.setEstatus(true);
		movimientoProcesosNocturnosDTO.setFecha(new Date());
		movimientoProcesosNocturnosDTO.setFolioPartida(12345L);
		movimientoProcesosNocturnosDTO.setInteres(new BigDecimal("24.52"));
		movimientoProcesosNocturnosDTO.setMontoOperacion(new BigDecimal("123.45"));
		movimientoProcesosNocturnosDTO.setNumAutorizacion("12345");
		movimientoProcesosNocturnosDTO.setOperacionAbr("APL");
		movimientoProcesosNocturnosDTO.setOperacionDesc("Abonos Pagos-Libres");
		movimientoProcesosNocturnosDTO.setSucursal(12L);
		movimientoProcesosNocturnosDTO.setTipoContratoAbr("PL");
		movimientoProcesosNocturnosDTO.setTipoContratoDesc("Pagos Libres");
		movimientoProcesosNocturnosDTO.setEstadoTransaccion("En proceso");
		movimientoProcesosNocturnosDTO.setImporteTransaccion(new BigDecimal("101.2"));
		movimientoProcesosNocturnosDTO.setConsumidor("123");
		movimientoProcesosNocturnosListDTO.setTotal(400);
		List<MovimientoMidasDTO> lst = new ArrayList<>();
		lst.add(movimientoProcesosNocturnosDTO);
		movimientoProcesosNocturnosListDTO.setMovimientos(lst);
		return movimientoProcesosNocturnosListDTO;
	}

	/**
	 * Construye una respuesta dummy
	 * 
	 * @return
	 */
	public static MovimientoIDDTO buildDummyX3() {
		MovimientoIDDTO movimientoIDDTO = new MovimientoIDDTO();
		List<Long> lst = new ArrayList<>();
		lst.add(1L);
		movimientoIDDTO.setIdsMovimientos(lst);
		return movimientoIDDTO;
	}

	/**
	 * Construye una respuesta dummy
	 * 
	 * @return
	 */
	public static MovimientosEstadoCuentaDTO buildDummy1() {
		MovimientosEstadoCuentaDTO movimientosEstadoCuentaDTO = new MovimientosEstadoCuentaDTO();
		List<MovimientoEstadoCuentaDTO> movimientoEstadoCuentaDTOList = new ArrayList<>();
		movimientosEstadoCuentaDTO.setTotal(100D);
		MovimientoEstadoCuentaDTO movimientoEstadoCuentaDTO1 = new MovimientoEstadoCuentaDTO();
		movimientoEstadoCuentaDTO1.setDepositos(0D);
		movimientoEstadoCuentaDTO1.setDescripcion("Ventas netas tarjeta …");
		movimientoEstadoCuentaDTO1.setFecha(new Date());
		movimientoEstadoCuentaDTO1.setRetiros(12882.62D);
		movimientoEstadoCuentaDTO1.setSaldo(0D);
		movimientoEstadoCuentaDTO1.setId(1L);
		MovimientoEstadoCuentaDTO movimientoEstadoCuentaDTO2 = new MovimientoEstadoCuentaDTO();
		movimientoEstadoCuentaDTO2.setDepositos(0D);
		movimientoEstadoCuentaDTO2.setDescripcion("Cargos…");
		movimientoEstadoCuentaDTO2.setFecha(new Date());
		movimientoEstadoCuentaDTO2.setRetiros(245.00D);
		movimientoEstadoCuentaDTO2.setSaldo(0D);
		movimientoEstadoCuentaDTO2.setId(2L);
		movimientoEstadoCuentaDTOList.add(movimientoEstadoCuentaDTO1);
		movimientoEstadoCuentaDTOList.add(movimientoEstadoCuentaDTO2);
		movimientosEstadoCuentaDTO.setMovimientos(movimientoEstadoCuentaDTOList);
		return movimientosEstadoCuentaDTO;
	}

}
