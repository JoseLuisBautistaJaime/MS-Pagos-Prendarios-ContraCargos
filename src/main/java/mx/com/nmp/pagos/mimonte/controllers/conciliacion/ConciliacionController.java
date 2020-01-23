/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.text.SimpleDateFormat;
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
import mx.com.nmp.pagos.mimonte.async.AsyncLayer;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.ConciliacionBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizaionConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarIdPSRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarSubEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesRequestDTO;
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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubEstatusConciliacion;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.SolicitarPagosService;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.ConciliacionServiceImpl;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.DevolucionesServiceImpl;
import mx.com.nmp.pagos.mimonte.util.ConciliacionDataValidator;
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
@SuppressWarnings({ "JavaDoc", "SpringAutowiredFieldsWarningInspection", "DefaultAnnotationParam" })
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
	private ConciliacionService conciliacionService;

	@Autowired
	private DevolucionesServiceImpl devolucionesServiceImpl;

	/**
	 * Permite ejecutar procesos de manera asincrona
	 */
	@Autowired
	AsyncLayer asyncLayer;

	/**
	 * Repository de SolicitarPagosService
	 */
	@Autowired
	@Qualifier("solicitarPagosService")
	private SolicitarPagosService solicitarPagosService;

	/**
	 * Validador generico de conciliacion
	 */
	@Autowired
	private ConciliacionDataValidator conciliacionDataValidator;
	
	/**
	 * Temporal format para los LOGs de timers
	 */
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private final Logger LOG = LoggerFactory.getLogger(ConciliacionController.class);

	/**
	 * Servicio que permite dar de alta una nueva conciliación para entidad y cuenta
	 * seleccionados.
	 * 
	 * @param conciliacionRequestDTO Request con la información para el alta de la
	 *                               conciliación.
	 * @param createdBy              Usuario que crea la conciliación.
	 * @return La información de la conciliación registrada.
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

		// TODO: Log de request entrante
		LOG.info(">>>URL: POST /conciliacion > REQUEST ENTRANTE: {}", conciliacionRequestDTO.toString());
		
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
	 * @param folio El identificador de la conciliación.
	 * @return La información de la conciliación indicada.
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
	public Response consultaFolio(@PathVariable(value = "folio", required = true) Long folio) {
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: GET /conciliacion/consulta/{folio} > REQUEST ENTRANTE: {}", folio);
		
		LOG.info(">> consultaFolio(" + folio + ")");
		ConciliacionDTOList consultaFolio = conciliacionServiceImpl.consultaFolio(folio);
		
		// TODO: Log de request entrante
				LOG.info(">>>URL: GET /conciliacion/consulta/{folio} > RESPONSE: {}", consultaFolio);
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH,
				consultaFolio);
	}

	/**
	 * Realiza la consulta de las conciliaciones dadas de alta en el sistema.
	 * 
	 * @param consultaConciliacionRequestDTO Request con los criterios de búsqueda
	 *                                       de las conciliaciones.
	 * @return El listado de conciliaciones que coincidieron con los criterios.
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
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: POST /conciliacion/consulta > REQUEST ENTRANTE: {}", consultaConciliacionRequestDTO.toString());
		
		ValidadorConciliacion.validateFechasPrimary(consultaConciliacionRequestDTO.getFechaDesde(),
				consultaConciliacionRequestDTO.getFechaHasta());
		List<ConsultaConciliacionDTO> consulta = conciliacionServiceImpl.consulta(consultaConciliacionRequestDTO);

		if (consulta != null && !consulta.isEmpty()) {
			for (ConsultaConciliacionDTO con : consulta) {
				con.setNumeroMovimientos(consulta.size());
			}
		} else
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0009);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH,
				consulta);
	}

	/**
	 * Se encarga de guardar los cambios realizados en la conciliacion para las
	 * secciones de movimientos en transito "Solicitar Pago", "Marcar como
	 * devolucion" y "Comisiones".
	 * 
	 * @param actualizaionConciliacionRequestDTO
	 * @param lastModifiedBy
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
			@RequestHeader(required = true, value = CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: PUT /conciliacion > REQUEST ENTRANTE: {}", actualizaionConciliacionRequestDTO.toString());
		
		// Validaciones generales del request
		if (!ValidadorConciliacion.validateActualizaionConciliacionRequestDTO(actualizaionConciliacionRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		// Validacion de fechas de las comisiones
		if (null != actualizaionConciliacionRequestDTO.getComisiones()
				&& !actualizaionConciliacionRequestDTO.getComisiones().isEmpty()) {
			for (ComisionesRequestDTO comision : actualizaionConciliacionRequestDTO.getComisiones()) {
				if (!ValidadorConciliacion.validateFecha(comision.getFechaCargo()))
					throw new ConciliacionException(ConciliacionConstants.FECHA_IS_WRONG,
							CodigoError.NMP_PMIMONTE_BUSINESS_088);
				if (!ValidadorConciliacion.validateFecha(comision.getFechaOperacion()))
					throw new ConciliacionException(ConciliacionConstants.FECHA_IS_WRONG,
							CodigoError.NMP_PMIMONTE_BUSINESS_088);
			}
		}
		conciliacionServiceImpl.actualizaConciliacion(actualizaionConciliacionRequestDTO, lastModifiedBy);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_UPDATE,
				null);
	}

	/**
	 * Al confirmar que la información es correcta, el usuario solicitará el cierre
	 * de la conciliación, y tendrá la posibilidad de visualizar y editar los layout
	 * antes de enviarlos.
	 * 
	 * @param folio     El identificador de la conciliación.
	 * @param createdBy Usuario que realiza la solicitud.
	 * @return Mensaje de solicitud de envío.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/generarlayouts/{folio}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Al confirmar que la información es correcta, el usuario solicitará el cierre de la conciliación, y tendrá la posibilidad de visualizar y editar los layout antes de enviarlos.", tags = {
			"Conciliación" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Conciliacion Enviada de forma Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response enviaConciliacion(@PathVariable(value = "folio", required = true) Long folio,
			@RequestHeader(required = true, value = CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: POST /conciliacion/generarlayouts/{folio} > REQUEST ENTRANTE: {}", folio);
		
		Boolean flag = null;
		String statusError = null;
		boolean status = true;		
		long start = 0;
		long finish = 0;

		LOG.info(">> enviaConciliacion(" + folio + ", " + createdBy + ")");

		// Valida el atributo
		LOG.info(">>> INICIA VALIDACION DE FOLIO");
		if (!ValidadorConciliacion.validateLong(folio))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		LOG.info(">>> FINALIZA VALIDACION DE FOLIO");

		// Se valida que exista la conciliacion con el folio proporcionado
		conciliacionDataValidator.validateFolioExists(folio);
		
		// VALIDACION DE MERGE PREVIO
		flag = conciliacionServiceImpl.validateConciliacionMerge(folio);
		if(!flag)
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_135.getDescripcion(), CodigoError.NMP_PMIMONTE_BUSINESS_135);
		
		// Realiza el envio de la conciliacion
		try {
			conciliacionServiceImpl.enviarConciliacion(folio, createdBy);
		} catch (ConciliacionException ex) {
			status = false;
			statusError = ex.getMessage();
			LOG.error(">>> ERROR DURANTE ENVIO DE CONCILIACION: {}", ex);
			throw ex;
		} catch (Exception ex) {
			status = false;
			statusError = ex.getMessage();
			LOG.error(">>> ERROR DURANTE ENVIO DE CONCILIACION: {}", ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_9999.getDescripcion(),
					CodigoError.NMP_PMIMONTE_9999);
		} finally {
			if (!status) {
				try {
					// Se actualiza el sub estatus a layouts generados con error
					start = System.currentTimeMillis();
					LOG.info("T >>> INICIA ACTUALIZACION DE SUB-ESTATUS CON ERROR: {}", sdf.format(new Date(start)));
					conciliacionService.actualizaSubEstatusConciliacion(new ActualizarSubEstatusRequestDTO(folio,
							ConciliacionConstants.SUBESTATUS_GENERACION_LAYOUTS_ERROR, statusError), createdBy);
					finish = System.currentTimeMillis();
					LOG.info("T >>> FINALIZA ACTUALIZACION DE SUB-ESTATUS CON ERROR: {}, EN: {}",
							sdf.format(new Date(finish)), (finish - start));
				} catch (Exception ex) {
					ex.printStackTrace();
					LOG.error(">>>ERROR: {}", ex);
				}
			}
		}

		// Regresa la respuesta
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.CONCILIATION_LAYOUTS_SUCCESSFULLY_CREATED, null);
	}

	/**
	 * Realiza la consulta de los movimientos en tránsito de la conciliación (con
	 * error).
	 * 
	 * @param folio El identificador de la conciliación.
	 * @return La lista de movimientos en tránsito asociados a la conciliación
	 *         indicada.
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
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: GET /conciliacion/transito/consulta/{folio} > REQUEST ENTRANTE: {}", folio);
		
		LOG.info(">> consultaTransitoFolio(" + folio + ")");

		List<MovTransitoDTO> response = conciliacionService.consultaMovimientosTransito(folio);
		if (null == response || response.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0009);
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: GET /conciliacion/transito/consulta/{folio} > RESPONSE: {}", response.toString());
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH,
				response);
	}

	/**
	 * Permite realizar la solicitud de pagos no reflejados en Midas de los
	 * movimientos que se encuentran en tránsito.
	 * 
	 * @param solicitarPagosRequestDTO Request con la información de los movimientos
	 *                                 para los que se realiza la solicitud.
	 * @param createdBy                Usuario que realiza la solicitud.
	 * @return Mensaje de solicitud de pagos.
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
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: POST /conciliacion/solicitarpagos > REQUEST ENTRANTE: {}", solicitarPagosRequestDTO.toString());
		
		if (!ValidadorConciliacion.validateSolicitarPagosRequestDTO(solicitarPagosRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		solicitarPagosService.solicitarPagos(solicitarPagosRequestDTO, createdBy);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Solicitud de pago exitosa.", null);

	}

	/**
	 * Marca las transacciones seleccionadas de movimientos en tránsito a
	 * movimientos de devolución para cuando los pagos solicitados no fueron
	 * realizados.
	 * 
	 * @param solicitarPagosRequestDTO Request con la información de los movimientos
	 *                                 que se requieren marcar para devolución.
	 * @param createdBy                Usuario que realiza la solicitud.
	 * @return Mensaje de solicitud de marcado de devoluciones.
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
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: POST /conciliacion/marcardevoluciones > REQUEST ENTRANTE: {}", solicitarPagosRequestDTO.toString());
		
		if (!ValidadorConciliacion.validateSolicitarPagosRequestDTO(solicitarPagosRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		List<DevolucionConDTO> response = devolucionesServiceImpl.marcarDevolucion(solicitarPagosRequestDTO, createdBy);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.MARK_AS_SUCCESSFUL_RETURN, response);
	}

	/**
	 * Realiza la consulta de movimientos de devolución para la conciliación.
	 * 
	 * @param folio El identificador de la conciliación.
	 * @return El listado de movimientos devolución asociados a la conciliación
	 *         indicada.
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
	public Response consultaMovimientosDevolucion(@PathVariable(value = "folio", required = true) Long folio) {
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: GET /conciliacion/devoluciones/consulta/{folio} > REQUEST ENTRANTE: {}", folio);
		
		LOG.info(">> consultaMovimientosDevolucion(" + folio + ")");

		List<DevolucionConDTO> devoluciones = devolucionesServiceImpl.consultaDevolucion(folio);
		if (null == devoluciones || devoluciones.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0009);
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: GET /conciliacion/transito/consulta/{folio} > RESPONSE: {}", devoluciones.toString());
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.SUCCESSFUL_RETURNS_CONSULTATION, devoluciones);
	}

	/**
	 * Realiza la notificación de devolución de las transacciones marcadas para la
	 * devolución a las entidades bancarias.
	 * 
	 * @param folioRequestDTO Request con la información de la devolución que se
	 *                        quiere solicitar.
	 * @param createdBy       Usuario que solicita la devolución.
	 * @return Mensaje de solicitud de devolución.
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
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: POST /conciliacion/devoluciones/solicitar > REQUEST ENTRANTE: {}", folioRequestDTO.toString());
		
		if (!ValidadorConciliacion.validateFolioRequestDTO(folioRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		List<DevolucionEntidadDTO> response = devolucionesServiceImpl.solicitarDevoluciones(folioRequestDTO, createdBy);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.REQUEST_SUCCESSFUL_RETURN, response);
	}

	/**
	 * Realiza la liquidación de los movimientos seleccionados; se debe especificar
	 * la fecha de liquidación para cada uno de los movimientos.
	 * 
	 * @param liquidacionMovimientosRequestDTO Request con la información de la
	 *                                         devoluciones que se quieren liquidar.
	 * @param createdBy                        Usuario que solicita la liquidación.
	 * @return Mensaje de solicitud de liquidación.
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
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: POST /conciliacion/devoluciones/liquidar > REQUEST ENTRANTE: {}", liquidacionMovimientosRequestDTO.toString());
		
		// Validacion de objeto y atributos
		if (!ValidadorConciliacion.validateLiquidacionMovimientosRequestDTO(liquidacionMovimientosRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		// Valida las fechas del objeto
		for (MovimientosDTO mov : liquidacionMovimientosRequestDTO.getMovimientos()) {
			if (!ValidadorConciliacion.validateFecha(mov.getFecha())) {
				throw new ConciliacionException(ConciliacionConstants.FECHA_IS_WRONG,
						CodigoError.NMP_PMIMONTE_BUSINESS_088);
			}
		}
		// Liquidar devoluciones
		List<DevolucionEntidadDTO> response = devolucionesServiceImpl
				.liquidarDevoluciones(liquidacionMovimientosRequestDTO, createdBy);
		// Regresa respuesta
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.SUCCESSFUL_CLEARANCE_REQUEST, response);
	}

	/**
	 * Servicio callback que será usado para actualizar el id del registro de las
	 * plantillas que será devuelto por PeopleSoft.
	 * 
	 * @param actualizarIdPSRequest Request con la información para realizar la
	 *                              acción.
	 * @param lastModifiedBy        Usuario que realiza la solicitud.
	 * @return Mensaje de actualización del registro.
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
	public Response actualizaIdPs(@RequestBody ActualizarIdPSRequest actualizarIdPSRequest,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: PUT /conciliacion/actualizarPS > REQUEST ENTRANTE: {}", actualizarIdPSRequest.toString());
		
		if (!ValidadorConciliacion.validateActualizarIdPSRequest(actualizarIdPSRequest))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		conciliacionServiceImpl.actualizarPS(actualizarIdPSRequest, lastModifiedBy);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.IDENTIFIER_PS_UPDATED_IN_THE_CONCILIATION, null);
	}

	/**
	 * Servicio que permite generar la conciliación usando los movimientos de
	 * procesos nocturnos, del proveedor transaccional (open pay) y de estado de
	 * cuenta de acuerdo a su disponibilidad.
	 * 
	 * @param folio       El identificador de la conciliación.
	 * @param requestUser Usuario que realiza la solicitud de la generación del
	 *                    merge de los movimientos.
	 * @return Mensaje de solicitud de merge/conciliación.
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
	public Response consultaGenerarFolio(@PathVariable(value = "folio", required = true) Long folio,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String requestUser) {
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: POST /conciliacion/generar/{folio} > REQUEST ENTRANTE: {}", folio);
		
		LOG.info(">> consultaGenerarFolio(" + folio + ", " + requestUser + ")");

		// VALIDACIONES INICIAN
		conciliacionServiceImpl.validacionesIniciales(folio, requestUser);
		// VALIDACIONES FINALIZAN

		try {
			conciliacionServiceImpl.actualizaSubEstatusConciliacion(new ActualizarSubEstatusRequestDTO(folio,
					ConciliacionConstants.SUBESTATUS_CONCILIACION_CONCILIACION, null), requestUser);
		} catch (ConciliacionException ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_9999.getDescripcion(),
					CodigoError.NMP_PMIMONTE_9999);
		}

		// SE GENERA LA CONCILIACION DE MANERA ASICRONA
		asyncLayer.generarConciliacion(folio, requestUser);
		// FINALIZA GENERACION DE LA CONCILIACION DE MANERA ASINCRONA

		// Regresa la respuesta exitosa
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.CONCILIATION_PROCESS_BEGINS, null);
	}

	/**
	 * Realiza la consulta de la bitácora de las últimas actividades realizadas en
	 * el sistema.
	 * 
	 * @param consultaActividadesRequest Request con los criterios de consulta de la
	 *                                   bitácora.
	 * @return El listado de la bitácora que corresponde a los criterios indicados.
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
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: POST /conciliacion/actividades > REQUEST ENTRANTE: {}", consultaActividadesRequest.toString());
		
		ValidadorConciliacion.validateFechasPrimary(consultaActividadesRequest.getFechaDesde(),
				consultaActividadesRequest.getFechaHasta());
		List<ConsultaActividadDTO> response = conciliacionService.consultaActividades(consultaActividadesRequest);
		if (null == response)
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0005);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH,
				response);
	}

	/**
	 * Realiza la actualización del sub-estatus de una conciliación.
	 * 
	 * @param actualizarSubEstatusRequestDTO Request con la información para
	 *                                       realizar la acción.
	 * @param requestUser                    Usuario que realiza la solicitud.
	 * @return Mensaje de actualización de sub-estatus.
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
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: PUT /conciliacion/actualizarSubEstatus > REQUEST ENTRANTE: {}", actualizarSubEstatusRequestDTO.toString());
		
		if (!ValidadorConciliacion.validateActualizarSubEstatusRequestDTO(actualizarSubEstatusRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		conciliacionServiceImpl.actualizaSubEstatusConciliacion(actualizarSubEstatusRequestDTO, requestUser);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.SUB_ESTATUS_UPDATED_OK, null);
	}

	/**
	 * Obtiene el resumen de conciliaciones realizadas.
	 * 
	 * @param resumenConciliacionRequestDTO Request con los criterios de búsqueda
	 *                                      del resumen.
	 * @return La información del resumen obtenido.
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
		
		// TODO: Log de request entrante
		LOG.info(">>>URL: POST /conciliacion/resumen > REQUEST ENTRANTE: {}", resumenConciliacionRequestDTO.toString());
		
		ValidadorConciliacion.validateFechasPrimary(resumenConciliacionRequestDTO.getFechaInicial(),
				resumenConciliacionRequestDTO.getFechaFinal());
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH,
				conciliacionServiceImpl.resumenConciliaciones(resumenConciliacionRequestDTO));
	}

}
