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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizaIdPsRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizaSubEstatusDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizaionConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransProyeccionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransRealDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransaccionesDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransaccionesOperacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTOList;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadesRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionesMovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusDevolucionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusMovTransitoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FolioRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.GlobalDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LiquidacionMovimientosRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteProcesosNocturnosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteProveedorTransaccionalDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionesRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SubEstatusConciliacionDTO;
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
	private final Logger log = LoggerFactory.getLogger(ConciliacionController.class);

	/**
	 * Servicio que permite dar de alta una nueva conciliación para entidad y cuenta
	 * seleccionados.
	 * 
	 * @param conciliacionRequestDTO
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Servicio que permite dar de alta una nueva conciliación para entidad y cuenta seleccionados.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response saveConciliacion(@RequestBody ConciliacionRequestDTO conciliacionRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Alta exitosa", buildDummy());
	}

	// /**
	// * Se encarga de consultar los movimientos generados por procesos nocturnos
	// (MIDAS) y por el proveedor transaccional.
	// *
	// *
	// * @param consultaMidasProveedorRequestDTO
	// * @param createdBy
	// * @return
	// */
	// @ResponseBody
	// @ResponseStatus(HttpStatus.OK)
	// @PostMapping(value = "/conciliacion/consulta/midasyproveedor", produces =
	// MediaType.APPLICATION_JSON_VALUE, consumes =
	// MediaType.APPLICATION_JSON_VALUE)
	// @ApiOperation(httpMethod = "POST", value = "Consulta los movimientos
	// generados por procesos nocturnos.", tags = {
	// "Conciliación" })
	// @ApiResponses({ @ApiResponse(code = 200, response = Response.class, message =
	// "Consulta exitosa"),
	// @ApiResponse(code = 400, response = Response.class, message = "El o los
	// parametros especificados son invalidos."),
	// @ApiResponse(code = 403, response = Response.class, message = "No cuenta con
	// permisos para acceder a el recurso"),
	// @ApiResponse(code = 404, response = Response.class, message = "El recurso que
	// desea no fue encontrado"),
	// @ApiResponse(code = 500, response = Response.class, message = "Error no
	// esperado") })
	// public Response consultaMidasProveedor(
	// @RequestBody ConsultaMidasProveedorRequestDTO
	// consultaMidasProveedorRequestDTO,
	// @RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
	//
	// return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
	// "Consulta exitosa",
	// buildConsultaMidasProveedorDummy());
	// }

	/**
	 * Realiza la consulta de la conciliación desde la pantalla de consulta de
	 * conciliaciones.
	 * 
	 * @param folio
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/conciliacion/consulta/{folio}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Realiza la consulta de la conciliación desde la pantalla de consulta de conciliaciones.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultaFolio(@PathVariable(value = "folio", required = true) Integer folio) {

		ConciliacionDTOList dummy = null;

		if (folio < 1 || folio > 16)
			dummy = null;
		if (folio == 1)
			dummy = buildConsultaFolioDummy1();
		if (folio == 2)
			dummy = buildConsultaFolioDummy2();
		if (folio == 3)
			dummy = buildConsultaFolioDummy3();
		if (folio == 4)
			dummy = buildConsultaFolioDummy4();
		if (folio == 5)
			dummy = buildConsultaFolioDummy5();
		if (folio == 6)
			dummy = buildConsultaFolioDummy6();
		if (folio == 7)
			dummy = buildConsultaFolioDummy7();
		if (folio == 8)
			dummy = buildConsultaFolioDummy8();
		if (folio == 9)
			dummy = buildConsultaFolioDummy9();
		if (folio == 10)
			dummy = buildConsultaFolioDummy10();
		if (folio == 11)
			dummy = buildConsultaFolioDummy11();
		if (folio == 12)
			dummy = buildConsultaFolioDummy12();
		if (folio == 13)
			dummy = buildConsultaFolioDummy13();
		if (folio == 14)
			dummy = buildConsultaFolioDummy14();
		if (folio == 15)
			dummy = buildConsultaFolioDummy15();
		if (folio == 16)
			dummy = buildConsultaFolioDummy16();

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta exitosa", dummy);
	}

	/**
	 * Realiza la consulta de las conciliaciones dadas de alta en el sistema.
	 * 
	 * @param consultaMidasProveedorRequestDTO
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/consulta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Realiza la consulta de las conciliaciones dadas de alta en el sistema.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consulta(@RequestBody ConsultaConciliacionRequestDTO consultaConciliacionRequestDTO) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta exitosa",
				buildConsultaConciliacionDummy());
	}

	/**
	 * Se encarga de guardar los cambios realizados en la conciliacion para las
	 * secciones de movimientos en transito "Solicitar Pago", "Marcar como
	 * devolucion" y "Comisiones".
	 * 
	 * @param ActualizaionConciliacionRequestDTO
	 * @param LastModifiedBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/conciliacion", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Se encarga de guardar los cambios realizados en la conciliacion para las secciones de movimientos en transito.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Entidad encontrada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response actualizaConciliacion(
			@RequestBody ActualizaionConciliacionRequestDTO actualizaionConciliacionRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String LastModifiedBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Actualizacion exitosa", null);
	}

	/**
	 * Al confirmar que la información es correcta, el usuario solicitará el cierre
	 * de la conciliación, y tendrá la posibilidad de visualizar y editar los layout
	 * antes de enviarlos.
	 * 
	 * @param folio
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/enviar/{folio}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Al confirmar que la información es correcta, el usuario solicitará el cierre de la conciliación, y tendrá la posibilidad de visualizar y editar los layout antes de enviarlos.", tags = {
			"Conciliación" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Conciliacion Enviada de forma Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response enviaConcicliacion(@PathVariable(value = "folio", required = true) Integer folio,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Conciliacion Enviada de forma Exitosa.",
				null);
	}

	/**
	 * Realiza la consulta de los movimientos en transito de la conciliacion (con
	 * error).
	 * 
	 * @param folio
	 * @param createdBy
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
	public Response consultaTransitoFolio(@PathVariable(value = "folio", required = true) Integer folio) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta exitosa",
				buildConsultaTransitoFolioDummy());
	}

	/**
	 * Permite realizar la solicitud de pagos no reflejados en Midas de los
	 * movimientos que se encuentran en tránsito.
	 * 
	 * @param solicitarPagosRequestDTO
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/solicitarpagos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite realizar la solicitud de pagos no reflejados en Midas de los movimientos que se encuentran en tránsito.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Solicitud Pago Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response solicitarPagos(@RequestBody SolicitarPagosRequestDTO solicitarPagosRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Solicitud Pago Exitosa.", null);
	}

	/**
	 * Marca las transacciones seleccionadas de movimientos en tránsito a
	 * movimientos de devolución para cuando los pagos solicitados no fueron
	 * realizados.
	 * 
	 * @param solicitarPagosRequestDTO
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/marcardevoluciones", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Marca las transacciones seleccionadas de movimientos en tránsito a movimientos de devolución para cuando los pagos solicitados no fueron realizados.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Marcar como Devolucion Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response marcarDevoluciones(@RequestBody SolicitarPagosRequestDTO solicitarPagosRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Marcar como Devolucion Exitosa.",
				buildMarcarDevolucionesDummy());
	}

	/**
	 * Realiza la consulta de movimientos de devolución para la conciliacion.
	 * 
	 * @param folio
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/conciliacion/devoluciones/consulta/{folio}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Realiza la consulta de movimientos de devolución para la conciliacion.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta Devoluciones Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultaMovimientosDevolucion(@PathVariable(value = "folio", required = true) Integer folio) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta Devoluciones Exitosa.",
				buildMarcarDevolucionesDummyVarias());
	}

	/**
	 * Realiza la notificación de devolución de las transacciones marcadas para la
	 * devolución a las entidades bancarias.
	 * 
	 * @param solicitarPagosRequestDTO
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/devoluciones/solicitar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "El estatus de la transacción de devolución cambiará de Pendiente a Solicitada.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Solicitud Devolucion Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response devoluciones(@RequestBody FolioRequestDTO folioRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Solicitud Devolucion Exitosa.",
				buildMarcarDevolucionesDummy());
	}

	/**
	 * Realiza la liquidación de los movimientos seleccionados; se debe especificar
	 * la fecha de liquidación para cada uno de los movimientos.
	 * 
	 * @param SolicitarPagosRequestDTO
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/devoluciones/liquidar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Realiza la liquidación de los movimientos seleccionados; se debe especificar la fecha de liquidación para cada uno de los movimientos.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Solicitud Liquidacion Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response liquidacionMovimientos(
			@RequestBody LiquidacionMovimientosRequestDTO liquidacionMovimientosRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Solicitud Liquidacion Exitosa.",
				buildLiquidacionMovimientosDummy());
	}

	/**
	 * Servicio callback que será usado para actualizar el id del registro de las
	 * plantillas que será devuelto por PeopleSoft.
	 * 
	 * @param actualizaIdPsRequestDTO
	 * @param LastModifiedBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/conciliacion/actualizarPS", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Servicio callback que será usado para actualizar el id del registro de las plantillas que será devuelto por PeopleSoft.", tags = {
			"Conciliación" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Identificador PS actualizado en la conciliación."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response actualizaIdPs(@RequestBody ActualizaIdPsRequestDTO actualizaIdPsRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String LastModifiedBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				"Identificador PS actualizado en la conciliación.", null);
	}

	/**
	 * Servicio que permite generar la conciliación usando los movimientos de
	 * procesos nocturnos, del proveedor transaccional (open pay) y de estado de
	 * cuenta de acuerdo a su disponibilidad.
	 * 
	 * @param folio
	 * @param createdBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/generar/{folio}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Servicio que permite generar la conciliación usando los movimientos de procesos nocturnos, del proveedor transaccional (open pay) y de estado de cuenta de acuerdo a su disponibilidad.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Se inicia proceso de conciliacion."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response ConsultaGenerarFolio(@PathVariable(value = "folio", required = true) Integer folio,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String requestUser,
			@RequestHeader(CatalogConstants.REQUEST_HEADER_URL) String urlCallBack) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Se inicia proceso de conciliacion.",
				null);
	}

	/**
	 * Servicio que será usado para actualizar el sub estatus del proceso de
	 * conciliación para conocer el estatus de la consulta de los reportes.
	 * 
	 * @param ActualizaSubEstatusDTO
	 * @param LastModifiedBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/conciliacion/actualizarSubEstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Servicio que será usado para actualizar el sub estatus del proceso de conciliación para conocer el estatus de la consulta de los reportes.", tags = {
			"Conciliación" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Sub Estatus Conciliacion actualizado correctamente."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response actualizaSubEstatus(@RequestBody ActualizaSubEstatusDTO actualizaSubEstatusDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String LastModifiedBy) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				"Sub Estatus Conciliacion actualizado correctamente.", null);
	}

	/**
	 * Servicio que consulta el resumen de conciliaciones realizadas.
	 * 
	 * @param folio
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/resumen", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Servicio que consulta el resumen de conciliaciones realizadas.", tags = {
			"Conciliación" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Estatus Proceso actualizado correctamente."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response resumenConciliaciones(@RequestBody ResumenConciliacionesRequest resumenConciliacionesRequest) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				"Estatus Proceso actualizado correctamente.", buildResumenConciliacionesDummy());
	}

	/**
	 * Realiza la consulta del log de las últimas actividades realizadas en el
	 * sistema.
	 * 
	 * @param resumenConciliacionesRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/actividades", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Realiza la consulta del log de las últimas actividades realizadas en el sistema.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultaActividades(@RequestBody ConsultaActividadesRequest consultaActividadesRequest) {

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta exitosa.",
				buildConsultaActividadesDummy());
	}

	/**
	 * Construye un objeto dummy
	 * @return
	 */
	public static ConciliacionDTO buildDummy() {
		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);
		EntidadDTO entidadDTO = null;
		entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");
		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);
		ConciliacionDTO conciliacionDTO = new ConciliacionDTO(0001, estatusConciliacionDTO, entidadDTO, cuentaDTO, null, null, null, null, null, null, null, new Date(), new Date(), "NMP", "NMP");		
		// new attributes
		conciliacionDTO.setSubEstatus(new SubEstatusConciliacionDTO(1, "Creada"));
		conciliacionDTO.setSubEstatusDescripcion("");
		conciliacionDTO.setIdPolizaTesoreria(null);
		conciliacionDTO.setIdAsientoContable(null);
		return conciliacionDTO;
	}

//	public static ConciliacionDTOList buildConsultaMidasProveedorDummy() {
//
//		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);
//
//		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(1, "Creada");
//
//		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");
//
//		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);
//
//		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
//				new Date(), false);
//
//		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
//				new Date(), new Date(), false);
//
//		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);
//
//		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
//				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
//				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
//				new BigDecimal(0.00, MathContext.DECIMAL64));
//
//		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);
//
//		List<DevolucionConDTO> devolucionDTOList = new ArrayList<>();
//
//		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
//				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
//				"859363", 3);
//
//		devolucionDTOList.add(devolucionDTO);
//
//		List<MovTransitoDTO> movTransitoDTOlist = new ArrayList<>();
//
//		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);
//
//		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
//				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
//				"Mariana Rodriguez Urbano");
//
//		movTransitoDTOlist.add(movTransitoDTO);
//
//		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();
//
//		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
//				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");
//
//		comisionesDTOList.add(comisionesDTO);
//		
//		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
//		
//		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
//		
//		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
//		
//		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
//		
//		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
//		
//		ComisionesTransDTO comisionesTransDTO = new ComisionesTransDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);
//
//		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(1L, estatusConciliacionDTO, subEstatusConciliacionDTO, "", 1L, 1L, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO, reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionDTOList,
//				movTransitoDTOlist, comisionesDTOList, comisionesTransaccionesDTO ,  new Date(), new Date(), "NMP", "NMP");
//
//		return conciliacionDTOList;
//	}

	public static ConciliacionDTOList buildConsultaFolioDummy1() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(1, "Creada");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(1L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy2() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(2, "Consulta Midas");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(2L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy3() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(3,
				"Consulta Midas Completada");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(3L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy4() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(4, "Consulta Midas Error");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(4L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy5() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(5, "Consulta Open Pay");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(5L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy6() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(6,
				"Consulta Open Pay Completada");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(6L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy7() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(7,
				"Consulta Open Pay Error");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(7L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy8() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(8, "Conciliacion");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(8L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy9() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(9,
				"Conciliacion Completada");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(9L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO,new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy10() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(10, "Conciliacion Error");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(10L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy11() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(11,
				"Consulta Estado de Cuenta");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(11L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy12() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(12,
				"Consulta Estado de Cuenta Completada");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(12L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy13() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(13,
				"Consulta Estado de Cuenta Error");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(13L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy14() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(14, "Enviada");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(14L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy15() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(15, "Enviada Error");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(15L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static ConciliacionDTOList buildConsultaFolioDummy16() {

		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(2, "Finalizada", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(16, "Finalizada");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);

		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
				new Date(), false);

		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
				new Date(), new Date(), false);

		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);

		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L, new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
				new BigDecimal(150.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64),
				new BigDecimal(0.00, MathContext.DECIMAL64));

		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);

		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();

		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);

		devolucionConDTOList.add(devolucionDTO);

		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();

		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");

		movTransitoDTOList.add(movTransitoDTO);

		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();

		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");

		comisionesDTOList.add(comisionesDTO);
		
		List<ComisionesTransaccionesOperacionDTO> ComisionesTransaccionesOperacionDTOList = new ArrayList<>();
		
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO = new ComisionesTransaccionesOperacionDTO("Pagos", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		ComisionesTransaccionesOperacionDTO comisionesTransaccionesOperacionDTO2 = new ComisionesTransaccionesOperacionDTO("Devoluciones", 15L, new BigDecimal(15.00, MathContext.DECIMAL64),	new BigDecimal(4.00, MathContext.DECIMAL64),	new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO2);
		
		ComisionesTransaccionesOperacionDTOList.add(comisionesTransaccionesOperacionDTO);
		
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO(ComisionesTransaccionesOperacionDTOList,	new BigDecimal(33.00, MathContext.DECIMAL64));
		
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO(new BigDecimal(15.00, MathContext.DECIMAL64), new BigDecimal(4.00, MathContext.DECIMAL64), new BigDecimal(19.00, MathContext.DECIMAL64));
		
		ComisionesTransaccionesDTO comisionesTransaccionesDTO = new ComisionesTransaccionesDTO(new Date(), new Date(), new BigDecimal(4.5, MathContext.DECIMAL64), comisionesTransProyeccionDTO, comisionesTransRealDTO);

		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(16L, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", 1L, 1L, entidadDTO, cuentaDTO, reporteProcesosNocturnosDTO,
				reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO, devolucionConDTOList,
				movTransitoDTOList, comisionesDTOList, comisionesTransaccionesDTO, new Date(), new Date(), "NMP", "NMP");

		return conciliacionDTOList;

	}

	public static List<ConsultaConciliacionDTO> buildConsultaConciliacionDummy() {

		List<ConsultaConciliacionDTO> consultaConciliacionDTOList = new ArrayList<>();

		// Inicio Caso 1
		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO = new SubEstatusConciliacionDTO(1, "Creada");

		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);
		// Fin Caso 1

		// Inicio Caso 2
		EstatusConciliacionDTO estatusConciliacionDTO2 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO2 = new SubEstatusConciliacionDTO(2, "Consulta Midas");

		EntidadDTO entidadDTO2 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO2 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 2

		// Inicio Caso 3
		EstatusConciliacionDTO estatusConciliacionDTO3 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO3 = new SubEstatusConciliacionDTO(3,
				"Consulta Midas Completada");

		EntidadDTO entidadDTO3 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO3 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 3

		// Inicio Caso 4
		EstatusConciliacionDTO estatusConciliacionDTO4 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO4 = new SubEstatusConciliacionDTO(4, "Consulta Midas Error");

		EntidadDTO entidadDTO4 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO4 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 4

		// Inicio Caso 5
		EstatusConciliacionDTO estatusConciliacionDTO5 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO5 = new SubEstatusConciliacionDTO(5, "Consulta Open Pay");

		EntidadDTO entidadDTO5 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO5 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 5

		// Inicio Caso 6
		EstatusConciliacionDTO estatusConciliacionDTO6 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO6 = new SubEstatusConciliacionDTO(6,
				"Consulta Open Pay Completada");

		EntidadDTO entidadDTO6 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO6 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 6

		// Inicio Caso 7
		EstatusConciliacionDTO estatusConciliacionDTO7 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO7 = new SubEstatusConciliacionDTO(7,
				"Consulta Open Pay Error");

		EntidadDTO entidadDTO7 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO7 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 7

		// Inicio Caso 8
		EstatusConciliacionDTO estatusConciliacionDTO8 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO8 = new SubEstatusConciliacionDTO(8, "Conciliacion");

		EntidadDTO entidadDTO8 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO8 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 8

		// Inicio Caso 9
		EstatusConciliacionDTO estatusConciliacionDTO9 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO9 = new SubEstatusConciliacionDTO(9,
				"Conciliacion Completada");

		EntidadDTO entidadDTO9 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO9 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 9

		// Inicio Caso 10
		EstatusConciliacionDTO estatusConciliacionDTO10 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO10 = new SubEstatusConciliacionDTO(10, "Conciliacion Error");

		EntidadDTO entidadDTO10 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO10 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 10

		// Inicio Caso 11
		EstatusConciliacionDTO estatusConciliacionDTO11 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO11 = new SubEstatusConciliacionDTO(11,
				"Consulta Estado de Cuenta");

		EntidadDTO entidadDTO11 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO11 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 11

		// Inicio Caso 12
		EstatusConciliacionDTO estatusConciliacionDTO12 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO12 = new SubEstatusConciliacionDTO(12,
				"Consulta Estado de Cuenta Completada");

		EntidadDTO entidadDTO12 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO12 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 12

		// Inicio Caso 13
		EstatusConciliacionDTO estatusConciliacionDTO13 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO13 = new SubEstatusConciliacionDTO(13,
				"Consulta Estado de Cuenta Error");

		EntidadDTO entidadDTO13 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO13 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 13

		// Inicio Caso 14
		EstatusConciliacionDTO estatusConciliacionDTO14 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO14 = new SubEstatusConciliacionDTO(14, "Enviada");

		EntidadDTO entidadDTO14 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO14 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 14

		// Inicio Caso 15
		EstatusConciliacionDTO estatusConciliacionDTO15 = new EstatusConciliacionDTO(1, "En Proceso", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO15 = new SubEstatusConciliacionDTO(15, "Enviada Error");

		EntidadDTO entidadDTO15 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO15 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 15

		// Inicio Caso 16
		EstatusConciliacionDTO estatusConciliacionDTO16 = new EstatusConciliacionDTO(2, "Finalizada", true);

		SubEstatusConciliacionDTO subEstatusConciliacionDTO16 = new SubEstatusConciliacionDTO(16, "Finalizada");

		EntidadDTO entidadDTO16 = new EntidadDTO(1, "Banco Banamex", true, "Banamex");

		CuentaDTO cuentaDTO16 = new CuentaDTO(1, "1122131", true);
		// Fin Caso 16

		// Inicio 1
		ConsultaConciliacionDTO consultaConciliacionDTO1 = new ConsultaConciliacionDTO(1, estatusConciliacionDTO,
				subEstatusConciliacionDTO, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO, cuentaDTO,
				658);
		// Fin 1

		// Inicio 2
		ConsultaConciliacionDTO consultaConciliacionDTO2 = new ConsultaConciliacionDTO(2, estatusConciliacionDTO2,
				subEstatusConciliacionDTO2, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO2, cuentaDTO2,
				372);
		// Fin 2

		// Inicio 3
		ConsultaConciliacionDTO consultaConciliacionDTO3 = new ConsultaConciliacionDTO(3, estatusConciliacionDTO3,
				subEstatusConciliacionDTO3, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO3, cuentaDTO3,
				372);
		// Fin 3

		// Inicio 4
		ConsultaConciliacionDTO consultaConciliacionDTO4 = new ConsultaConciliacionDTO(4, estatusConciliacionDTO4,
				subEstatusConciliacionDTO4, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO4, cuentaDTO4,
				372);
		// Fin 4

		// Inicio 5
		ConsultaConciliacionDTO consultaConciliacionDTO5 = new ConsultaConciliacionDTO(5, estatusConciliacionDTO5,
				subEstatusConciliacionDTO5, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO5, cuentaDTO5,
				372);
		// Fin 5

		// Inicio 6
		ConsultaConciliacionDTO consultaConciliacionDTO6 = new ConsultaConciliacionDTO(6, estatusConciliacionDTO6,
				subEstatusConciliacionDTO6, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO6, cuentaDTO6,
				372);
		// Fin 6

		// Inicio 7
		ConsultaConciliacionDTO consultaConciliacionDTO7 = new ConsultaConciliacionDTO(7, estatusConciliacionDTO7,
				subEstatusConciliacionDTO7, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO7, cuentaDTO7,
				372);
		// Fin 7

		// Inicio 8
		ConsultaConciliacionDTO consultaConciliacionDTO8 = new ConsultaConciliacionDTO(8, estatusConciliacionDTO8,
				subEstatusConciliacionDTO8, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO8, cuentaDTO8,
				372);
		// Fin 8

		// Inicio 9
		ConsultaConciliacionDTO consultaConciliacionDTO9 = new ConsultaConciliacionDTO(9, estatusConciliacionDTO9,
				subEstatusConciliacionDTO9, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO9, cuentaDTO9,
				372);
		// Fin 9

		// Inicio 10
		ConsultaConciliacionDTO consultaConciliacionDTO10 = new ConsultaConciliacionDTO(10, estatusConciliacionDTO10,
				subEstatusConciliacionDTO10, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO10,
				cuentaDTO10, 372);
		// Fin 10

		// Inicio 11
		ConsultaConciliacionDTO consultaConciliacionDTO11 = new ConsultaConciliacionDTO(11, estatusConciliacionDTO11,
				subEstatusConciliacionDTO11, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO11,
				cuentaDTO11, 372);
		// Fin 11

		// Inicio 12
		ConsultaConciliacionDTO consultaConciliacionDTO12 = new ConsultaConciliacionDTO(12, estatusConciliacionDTO12,
				subEstatusConciliacionDTO12, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO12,
				cuentaDTO12, 372);
		// Fin 12

		// Inicio 13
		ConsultaConciliacionDTO consultaConciliacionDTO13 = new ConsultaConciliacionDTO(13, estatusConciliacionDTO13,
				subEstatusConciliacionDTO13, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO13,
				cuentaDTO13, 372);
		// Fin 13

		// Inicio 14
		ConsultaConciliacionDTO consultaConciliacionDTO14 = new ConsultaConciliacionDTO(14, estatusConciliacionDTO14,
				subEstatusConciliacionDTO14, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO14,
				cuentaDTO14, 372);
		// Fin 14

		// Inicio 15
		ConsultaConciliacionDTO consultaConciliacionDTO15 = new ConsultaConciliacionDTO(15, estatusConciliacionDTO15,
				subEstatusConciliacionDTO15, "", null, null, new Date(), "NMP", new Date(), "NMP", entidadDTO15,
				cuentaDTO15, 372);
		// Fin 15

		// Inicio 16
		ConsultaConciliacionDTO consultaConciliacionDTO16 = new ConsultaConciliacionDTO(16, estatusConciliacionDTO16,
				subEstatusConciliacionDTO16, "", 1L, 1L, new Date(), "NMP", new Date(), "NMP", entidadDTO16,
				cuentaDTO16, 372);
		// Fin 16

		consultaConciliacionDTOList.add(consultaConciliacionDTO1);
		consultaConciliacionDTOList.add(consultaConciliacionDTO2);
		consultaConciliacionDTOList.add(consultaConciliacionDTO3);
		consultaConciliacionDTOList.add(consultaConciliacionDTO4);
		consultaConciliacionDTOList.add(consultaConciliacionDTO5);
		consultaConciliacionDTOList.add(consultaConciliacionDTO6);
		consultaConciliacionDTOList.add(consultaConciliacionDTO7);
		consultaConciliacionDTOList.add(consultaConciliacionDTO8);
		consultaConciliacionDTOList.add(consultaConciliacionDTO9);
		consultaConciliacionDTOList.add(consultaConciliacionDTO10);
		consultaConciliacionDTOList.add(consultaConciliacionDTO11);
		consultaConciliacionDTOList.add(consultaConciliacionDTO12);
		consultaConciliacionDTOList.add(consultaConciliacionDTO13);
		consultaConciliacionDTOList.add(consultaConciliacionDTO14);
		consultaConciliacionDTOList.add(consultaConciliacionDTO15);
		consultaConciliacionDTOList.add(consultaConciliacionDTO16);

		return consultaConciliacionDTOList;
	}

	public static List<MovTransitoDTO> buildConsultaTransitoFolioDummy() {

		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();
		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en Midas", true);
		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");
		movTransitoDTOList.add(movTransitoDTO);
		return movTransitoDTOList;

	}

	public static List<DevolucionConDTO> buildMarcarDevolucionesDummy() {

		List<DevolucionConDTO> devolucionDTOlist = new ArrayList<>();
		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Solicitada", true);
		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
				"859363", 3);
		devolucionDTOlist.add(devolucionDTO);
		return devolucionDTOlist;
	}

	public static List<DevolucionConDTO> buildMarcarDevolucionesDummyVarias() {

		List<DevolucionConDTO> devolucionDTOlist = new ArrayList<>();
		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(2, "Solicitada", true);
		EstatusDevolucionDTO estatusDevolucionDTO2 = new EstatusDevolucionDTO(1, "Pendiente", true);
		EstatusDevolucionDTO estatusDevolucionDTO3 = new EstatusDevolucionDTO(3, "Liquidada", true);
		
		// Devolucion 1
		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
				new BigDecimal("150.00"), "Visa", "4152xxxxxxxx1478", "Juana Garcia Garcia",
				"859363", 3);
		// Devolucion 2
		DevolucionConDTO devolucionDTO2 = new DevolucionConDTO(2, new Date(), estatusDevolucionDTO2,
				new BigDecimal("170.00"), "Master Card", "4456xxxxxxxx9987", "Ettore Bugatti",
				"444558", 30);
		// Devolucion 3
		DevolucionConDTO devolucionDTO3 = new DevolucionConDTO(3, new Date(), estatusDevolucionDTO3,
				new BigDecimal("210.00"), "Visa", "6697xxxxxxxx1230", "ferruccio lamborghini",
				"120354", 10);
		// Devolucion 4
		DevolucionConDTO devolucionDTO4 = new DevolucionConDTO(4, new Date(), estatusDevolucionDTO,
				new BigDecimal("180.00"), "Master Card", "7894xxxxxxxx1478", "Horacio Paganni",
				"789665", 30);
		// Devolucion 5
		DevolucionConDTO devolucionDTO5 = new DevolucionConDTO(5, new Date(), estatusDevolucionDTO2,
				new BigDecimal("190.00"), "Visa", "1365xxxxxxxx7963", "C.E. Harald von Koenigsegg",
				"147741", 10);
		// Devolucion 6
		DevolucionConDTO devolucionDTO6 = new DevolucionConDTO(6, new Date(), estatusDevolucionDTO3,
				new BigDecimal("200.00"), "Master Card", "1248xxxxxxxx1002", "Bruce McLaren",
				"112336", 3);
		
		devolucionDTOlist.add(devolucionDTO);
		devolucionDTOlist.add(devolucionDTO2);
		devolucionDTOlist.add(devolucionDTO3);
		devolucionDTOlist.add(devolucionDTO4);
		devolucionDTOlist.add(devolucionDTO5);
		devolucionDTOlist.add(devolucionDTO6);
		return devolucionDTOlist;
	}
	
	public static List<DevolucionesMovimientosDTO> buildLiquidacionMovimientosDummy() {

		List<DevolucionesMovimientosDTO> devolucionesMovimientosDTOList = new ArrayList<>();
		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(3, "Liquidada", true);
		DevolucionesMovimientosDTO devolucionesMovimientosDTO = new DevolucionesMovimientosDTO(1, new Date(),
				estatusDevolucionDTO, new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531",
				"Juana Garcia Garcia", "859363", 3, new Date());
		devolucionesMovimientosDTOList.add(devolucionesMovimientosDTO);
		return devolucionesMovimientosDTOList;
	}

	public static ResumenConciliacionDTO buildResumenConciliacionesDummy() {
		ResumenConciliacionDTO resumenConciliacionDTO = new ResumenConciliacionDTO(10L, 120L, 150L);
		return resumenConciliacionDTO;
	}

	public static List<ConsultaActividadDTO> buildConsultaActividadesDummy() {

		List<ConsultaActividadDTO> consultaActividadDTOList = new ArrayList<>();

		ConsultaActividadDTO ConsultaActividadDTO = new ConsultaActividadDTO(1, new Date(),
				"Reporte estado de cuenta de 12702 cargado en el sistema");
		ConsultaActividadDTO ConsultaActividadDTO2 = new ConsultaActividadDTO(2, new Date(),
				"Devolucion 1234 cambio su estatus a liquidada");

		consultaActividadDTOList.add(ConsultaActividadDTO);
		consultaActividadDTOList.add(ConsultaActividadDTO2);

		return consultaActividadDTOList;
	}
}
