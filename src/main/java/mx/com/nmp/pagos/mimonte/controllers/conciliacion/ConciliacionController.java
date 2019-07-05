/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import mx.com.nmp.pagos.mimonte.builder.conciliacion.ConciliacionBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizaionConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarIdPSRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarSubEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTOList;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionResponseSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadesRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FolioRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LiquidacionMovimientosRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.SolicitarPagosService;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.ConciliacionServiceImpl;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.DevolucionesServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorConciliacion;

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

	@Autowired
	private ConciliacionServiceImpl conciliacionServiceImpl;

	@Autowired
//	@Qualifier("conciliacionService")
	private ConciliacionService conciliacionService;

	@Autowired
	private DevolucionesServiceImpl devolucionesServiceImpl;

	/**
	 * Repository de SolicitarPagosService
	 */
	@Autowired
	@Qualifier("solicitarPagosService")
	private SolicitarPagosService solicitarPagosService;

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
		
		ConciliacionResponseSaveDTO conciliacionResponseSaveDTO = ConciliacionBuilder
				.buildConciliacionResponseSaveDTOFromConciliacionRequestDTO(conciliacionRequestDTO, new Date(), null,
						createdBy);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SAVE_SUCCESSFUL,
				ConciliacionBuilder.buildConciliacionResponseSaveDTOFromConciliacionDTO(
						conciliacionServiceImpl.saveConciliacion(conciliacionResponseSaveDTO, createdBy)));
	}

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

		ConciliacionDTOList consultaFolio = conciliacionServiceImpl.consultaFolio(folio);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH,
				consultaFolio);
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

		List<ConsultaConciliacionDTO> consulta = conciliacionServiceImpl.consulta(consultaConciliacionRequestDTO);

		if (consulta != null && !consulta.isEmpty()) {
			for (ConsultaConciliacionDTO con : consulta) {
				con.setNumeroMovimientos(consulta.size());
			}
		}

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH,
				consulta);
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

		conciliacionServiceImpl.actualizaConciliacion(actualizaionConciliacionRequestDTO);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_UPDATE,
				null);
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
	public Response enviaConciliacion(@PathVariable(value = "folio", required = true) Integer folio,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
		if (!ValidadorConciliacion.validateInteger(folio))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		conciliacionServiceImpl.enviarConciliacion(folio, createdBy);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.CONCILIATION_SENT_SUCCESSFULLY, null);
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
		List<MovTransitoDTO> response = conciliacionService.consultaMovimientosTransito(folio);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH,
				response);
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
		if (!ValidadorConciliacion.validateSolicitarPagosRequestDTO(solicitarPagosRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		solicitarPagosService.solicitarPagosService(solicitarPagosRequestDTO.getFolio(),
				solicitarPagosRequestDTO.getIdMovimientos(), createdBy);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Solicitud de pago exitosa.", null);

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

		List<DevolucionConDTO> response = devolucionesServiceImpl.marcarDevolucion(solicitarPagosRequestDTO, createdBy);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.MARK_AS_SUCCESSFUL_RETURN, response);
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

		List<DevolucionConDTO> devoluciones = devolucionesServiceImpl.consultaDevolucion(folio);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.SUCCESSFUL_RETURNS_CONSULTATION, devoluciones);
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

		List<DevolucionEntidadDTO> response = devolucionesServiceImpl.solicitarDevoluciones(folioRequestDTO, createdBy);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.REQUEST_SUCCESSFUL_RETURN, response);
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

		List<DevolucionEntidadDTO> response = devolucionesServiceImpl
				.liquidarDevoluciones(liquidacionMovimientosRequestDTO, createdBy);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.SUCCESSFUL_CLEARANCE_REQUEST, response
		// buildLiquidacionMovimientosDummy()
		);
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
	public Response actualizaIdPs(
			@RequestBody ActualizarIdPSRequest actualizarIdPSRequest/*
																	 * ActualizaIdPsRequestDTO actualizaIdPsRequestDTO
																	 */,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {
		if (!ValidadorConciliacion.validateActualizarIdPSRequest(actualizarIdPSRequest))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		// Comentado por ahora, hasta que la funcionalidad de la capa de servicio este
		// completa
		conciliacionServiceImpl.actualizarPS(actualizarIdPSRequest, lastModifiedBy);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.IDENTIFIER_PS_UPDATED_IN_THE_CONCILIATION, null);
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
	public Response consultaGenerarFolio(@PathVariable(value = "folio", required = true) Integer folio,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String requestUser) {
		if (!ValidadorConciliacion.validateInteger(folio))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		conciliacionServiceImpl.generarConciliacion(folio, requestUser);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.CONCILIATION_PROCESS_BEGINS, null);
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
		if (!ValidadorConciliacion.validateConsultaActividadesRequest(consultaActividadesRequest))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		List<ConsultaActividadDTO> response = conciliacionService.consultaActividades(consultaActividadesRequest);
		if (null == response || response.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH,
				response);
	}

	/**
	 * Realiza la actualizacion del sub estatus de una conciliacion
	 * 
	 * @param actualizarSubEstatusRequestDTO
	 * @param requestUser
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/conciliacion/actualizarSubEstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Servicio que permite actualizar el subestatus de una conciliacion.", tags = {
			"Conciliación" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Se inicia proceso de Actualizacion de sub estatus."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response actualizaSubEstatus(@RequestBody ActualizarSubEstatusRequestDTO actualizarSubEstatusRequestDTO,
			@RequestHeader(name = CatalogConstants.REQUEST_USER_HEADER, required = true) String requestUser) {
		if (!ValidadorConciliacion.validateActualizarSubEstatusRequestDTO(actualizarSubEstatusRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		conciliacionServiceImpl.actualizaSubEstatusConciliacion(actualizarSubEstatusRequestDTO, requestUser);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Actualizacion de sub estatus correcta.",
				null);
	}

	/**
	 * Obtiene el resumen de conciliaciones realizadas
	 * 
	 * @param resumenConciliacionRequestDTO
	 * @param requestUser
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/resumen", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Servicio que consulta el resumen de conciliaciones realizadas.", tags = {
			"Conciliación" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Se inicia consulta de resumen de conciliaciones."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response resumenConciliaciones(@RequestBody ResumenConciliacionRequestDTO resumenConciliacionRequestDTO) {
		if (!ValidadorConciliacion.validateResumenConciliacionRequestDTO(resumenConciliacionRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH,
				conciliacionServiceImpl.resumenConciliaciones(resumenConciliacionRequestDTO));
	}

}
