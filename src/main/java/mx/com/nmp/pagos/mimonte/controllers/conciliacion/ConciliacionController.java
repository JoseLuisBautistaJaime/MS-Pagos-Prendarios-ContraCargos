/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import mx.com.nmp.pagos.mimonte.controllers.EntidadController;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizaionConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMidasProveedorRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionesMovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusDevolucionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusMovTransitoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.GlobalDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LiquidacionMovimientosRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteProcesosNocturnosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteProveedorTransaccionalDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * @name ConciliacionController
 * @description Clase que expone el servicio REST para las operaciones
 *              relacionadas con comciliacion.
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 16:38 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar operciones sobre la conciliación.", description = "REST API para realizar operaciones sobre la conciliación", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Conciliación" })
public class ConciliacionController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(EntidadController.class);

	/**
	 * Guarda una nueva conciliacón.
	 * 
	 * @param conciliacionRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Crea una nueva conciliación.", tags = { "Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Conciliación creada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response save(@RequestBody ConciliacionRequestDTO conciliacionRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Alta exitosa", buildDummy());
	}

	/**
	 * Se encarga de consultar los movimientos generados por procesos nocturnos
	 * (MIDAS) y por el proveedor transaccional.
	 * 
	 * @param consultaMidasProveedorRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/consulta/midasyproveedor", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Consulta los movimientos generados por procesos nocturnos.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta MidasProveedor"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultaMidasProveedor(
			@RequestBody ConsultaMidasProveedorRequestDTO consultaMidasProveedorRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta exitosa",
				buildConsultaMidasProveedorDummy());
	}
	
	
	/**
	 * Realiza la consulta de la conciliación desde la pantalla de consulta de conciliaciones
	 * 
	 * @param folio
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/conciliacion/consulta/{folio}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Realiza la consulta de la conciliación desde la pantalla de consulta de conciliaciones.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Folio encontrado"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultaFolio(@PathVariable(value = "folio", required = true) Long folio) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta exitosa",
				buildConsultaFolioDummy());
	}
	
	
	/**
	 * Realiza la consulta de las conciliaciones dadas de alta en el sistema.
	 * 
	 * @param consultaMidasProveedorRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/consulta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Consulta de las conciliaciones dadas de alta en el sistema.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta Conciliacion"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consulta(
			@RequestBody ConsultaConciliacionRequestDTO consultaConciliacionRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta exitosa",
				buildConsultaConciliacionDummy());
	}
	
	
	/**
	 * Se encarga de guardar los cambios realizados en la conciliacion para las secciones de movimientos en transito 
	 * 
	 * @param idEntidad
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/conciliacion/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Eliminacion logica de un objeto catalogo entidad en base a su id", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Entidad encontrada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response actualizaConciliacion(@RequestBody ActualizaionConciliacionRequestDTO ActualizaionConciliacionRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String LastModifiedBy) {
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Actualizacion exitosa",
				null);
	}
	
	/**
	 * .
	 * 
	 * @param consultaMidasProveedorRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/enviar/{folio}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Envia conciliacion.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Envia Conciliacion"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response enviaConcicliacion(@PathVariable(value = "folio", required = true) Long folio,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Conciliacion Enviada de forma Exitosa.", null);
	}
	
	/**
	 * Realiza la consulta de los movimientos en transito de la conciliacion (con error).
	 * 
	 * @param folio
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/conciliacion/transito/consulta/{folio}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Realiza la consulta de los movimientos en transito de la conciliacion (con error).", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta Folio"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultaTransitoFolio(@PathVariable(value = "folio", required = true) Long folio) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta exitosa",
				buildConsultaTransitoFolioDummy());
	}
	
	
	/**
	 * .
	 * 
	 * @param consultaMidasProveedorRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/solicitarpagos/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Solicitar Pagos.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Solicita Pagos"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response solicitarPagos(@RequestBody SolicitarPagosRequestDTO solicitarPagosRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String LastModifiedBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Solicitud Pago Exitosa.", null);
	}
	
	/**
	 * Realiza la consulta de movimientos de devolución para la conciliacion.
	 * 
	 * @param consultaMidasProveedorRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/marcardevoluciones/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "consulta de movimientos de devolución para la conciliacion.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Movimientos Devolucion"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response marcarDevoluciones(@RequestBody SolicitarPagosRequestDTO solicitarPagosRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String LastModifiedBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Marcar como Devolucion Exitosa.", buildMarcarDevolucionesDummy());
	}
	
	/**
	 * Realiza la consulta de movimientos de devolución para la conciliacion
	 * 
	 * @param folio
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/conciliacion/devoluciones/consulta/{folio}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Consulta de movimientos de devolución para la conciliacion.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Movimientos devolucion."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultaMovimientosDevolucion(@PathVariable(value = "folio", required = true) Long folio) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta Devoluciones Exitosa.",
				buildMarcarDevolucionesDummy());
	}
	
	
	/**
	 * El estatus de la transacción de devolución cambiará de Pendiente a Solicitada.
	 * 
	 * @param SolicitarPagosRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/devoluciones/solicitar/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "El estatus de la transacción de devolución cambiará de Pendiente a Solicitada.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Estatus de la transacción de devolución"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response devoluciones(@RequestBody SolicitarPagosRequestDTO solicitarPagosRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String LastModifiedBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Solicitud Devolucion Exitosa.", buildMarcarDevolucionesDummy());
	}
	
	/**
	 * Realiza la liquidación de los movimientos seleccionados.
	 * 
	 * @param SolicitarPagosRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/devoluciones/liquidar/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Realiza la liquidación de los movimientos seleccionados.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Liquidacion de movimientos"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response liquidacionMovimientos(@RequestBody LiquidacionMovimientosRequestDTO liquidacionMovimientosRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String LastModifiedBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Solicitud Liquidacion Exitosa.", buildLiquidacionMovimientosDummy());
	}

	public static ConciliacionDTO buildDummy() {
		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1L, "En proceso", true);
		EntidadDTO entidadDTO = null;
		entidadDTO = new EntidadDTO(1L, "Banco Banamex", true, "Banamex");
		CuentaDTO cuentaDTO = new CuentaDTO(1L, "1122131", true);
		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);
		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);
		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);
		ConciliacionDTO conciliacionDTO = new ConciliacionDTO(0001L, estatusConciliacionDTO, entidadDTO, cuentaDTO,
				reporteProcesosNocturnosDTO, reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, null, null, null,
				null, new Date(), new Date(), "Sistema", null);
		return conciliacionDTO;
	}

	public static ConciliacionDTO buildConsultaMidasProveedorDummy() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1L, "En proceso", true);
		EntidadDTO entidadDTO = new EntidadDTO(1L, "Banco Banamex", true, "Banamex");
		CuentaDTO cuentaDTO = new CuentaDTO(1L, "1122131", true);
		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);
		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);
		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);
		GlobalDTO globalDTO = new GlobalDTO(1L, new Date(), 300L, 250L,
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(150.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64));
		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);
		
		List<DevolucionConDTO> devolucionDTOList = new ArrayList<>();
		
		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1L, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3L);
		
		devolucionDTOList.add(devolucionDTO);
		
		List<EstatusMovTransitoDTO> estatusMovTransitoDTOlist = new ArrayList<>();
		
		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1L, "No identificada en midas", true);
		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1L, estatusMovTransitoDTO, 82154722002L, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");
		
		estatusMovTransitoDTOlist.add(estatusMovTransitoDTO);
		
		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();
		
		ComisionesDTO comisionesDTO = new ComisionesDTO(1L, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");
		
		comisionesDTOList.add(comisionesDTO);
		
		ConciliacionDTO conciliacionDTO = new ConciliacionDTO(1L, estatusConciliacionDTO, entidadDTO, cuentaDTO,
				reporteProcesosNocturnosDTO, reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO,
				devolucionDTO, movTransitoDTO, comisionesDTO, new Date(), new Date(), "NMP", "NMP");
		return conciliacionDTO;
	}
	
	public static ConciliacionDTO buildConsultaFolioDummy() {
		
		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1L, "En proceso", true);
		EntidadDTO entidadDTO = new EntidadDTO(1L, "Banco Banamex", true, "Banamex");
		CuentaDTO cuentaDTO = new CuentaDTO(1L, "1122131", true);
		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);
		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);
		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);
		GlobalDTO globalDTO = new GlobalDTO(1L, new Date(), 300L, 250L,
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(150.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64));
		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);
		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1L, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3L);
		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1L, "No identificada en midas", true);
		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1L, estatusMovTransitoDTO, 82154722002L, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");
		ComisionesDTO comisionesDTO = new ComisionesDTO(1L, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");
		ConciliacionDTO conciliacionDTO = new ConciliacionDTO(1L, estatusConciliacionDTO, entidadDTO, cuentaDTO,
				reporteProcesosNocturnosDTO, reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO,
				devolucionDTO, movTransitoDTO, comisionesDTO, new Date(), new Date(), "NMP", "NMP");
		return conciliacionDTO;
		
	}
	
	public static List<ConsultaConciliacionDTO> buildConsultaConciliacionDummy() {
		
		List<ConsultaConciliacionDTO> consultaConciliacionDTOList = new ArrayList<>();
		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(2L, "Finalizado", true);
		EntidadDTO entidadDTO = new EntidadDTO(1L, "Banco Banamex", true, "Banamex");
		CuentaDTO cuentaDTO = new CuentaDTO(1L, "1122131", true);
		ConsultaConciliacionDTO consultaConciliacionDTO = new ConsultaConciliacionDTO(1L, estatusConciliacionDTO, new Date(), "NMP",
				new Date(), "NMP", entidadDTO, cuentaDTO, 658L);
		ConsultaConciliacionDTO consultaConciliacionDTO2 = new ConsultaConciliacionDTO(1L, estatusConciliacionDTO, new Date(), "NMP",
				new Date(), "NMP", entidadDTO, cuentaDTO, 658L);
		consultaConciliacionDTOList.add(consultaConciliacionDTO);
		consultaConciliacionDTOList.add(consultaConciliacionDTO2);
		return consultaConciliacionDTOList;
	}
	
	public static List<MovTransitoDTO> buildConsultaTransitoFolioDummy() {
		
		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();
		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1L, "No identificada en Midas", true);
		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1L, estatusMovTransitoDTO, 82154722002L, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");
		movTransitoDTOList.add(movTransitoDTO);
		return movTransitoDTOList;
		
	}
	
	public static List<DevolucionConDTO> buildMarcarDevolucionesDummy(){
		
		List<DevolucionConDTO> devolucionDTOlist = new ArrayList<>();
		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);
		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1L, new Date(), estatusDevolucionDTO, new BigDecimal(150.00, MathContext.DECIMAL64), 
				"Visa",	"4152xxxxxxxx9531", "Juana Garcia Garcia", "859363", 3L);
		devolucionDTOlist.add(devolucionDTO);
		return devolucionDTOlist;
	}
	
	public static List<DevolucionesMovimientosDTO> buildLiquidacionMovimientosDummy(){
		
		 List<DevolucionesMovimientosDTO> devolucionesMovimientosDTOList = new ArrayList<>();
		 EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(3, "Liquidada", true);
		 DevolucionesMovimientosDTO devolucionesMovimientosDTO = new DevolucionesMovimientosDTO(1L, new Date(), estatusDevolucionDTO, new BigDecimal(150.00, MathContext.DECIMAL64),
				 "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia", "859363", 3L, new Date());
		 devolucionesMovimientosDTOList.add(devolucionesMovimientosDTO);
		return devolucionesMovimientosDTOList;
	}
}
