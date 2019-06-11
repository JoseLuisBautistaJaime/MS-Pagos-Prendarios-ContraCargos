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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizaIdPsRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizaSubEstatusDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizaionConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarIdPSRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarSubEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTOList;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionResponseSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadesRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadesRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusMovTransitoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FolioRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LiquidacionMovimientosRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionesRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
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

//	/**
//	 * Se encarga de consultar los movimientos generados por procesos nocturnos (MIDAS) y por el proveedor transaccional.
//	 * 
//	 * 
//	 * @param consultaMidasProveedorRequestDTO
//	 * @param createdBy
//	 * @return
//	 */
//	@ResponseBody
//	@ResponseStatus(HttpStatus.OK)
//	@PostMapping(value = "/conciliacion/consulta/midasyproveedor", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(httpMethod = "POST", value = "Consulta los movimientos generados por procesos nocturnos.", tags = {
//			"Conciliación" })
//	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
//			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
//			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
//			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
//			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
//	public Response consultaMidasProveedor(
//			@RequestBody ConsultaMidasProveedorRequestDTO consultaMidasProveedorRequestDTO,
//			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
//
//		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta exitosa",
//				buildConsultaMidasProveedorDummy());
//	}
	
	
	/**
	 * Realiza la consulta de la conciliación desde la pantalla de consulta de conciliaciones.
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

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH, consultaFolio);
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
	 * Se encarga de guardar los cambios realizados en la conciliacion para las secciones de movimientos en transito "Solicitar Pago",  "Marcar como devolucion" y "Comisiones". 
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
	public Response actualizaConciliacion(@RequestBody ActualizaionConciliacionRequestDTO actualizaionConciliacionRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String LastModifiedBy) {
		
		  ActualizaionConciliacionRequestDTO actualizaConciliacion = conciliacionServiceImpl.actualizaConciliacion(actualizaionConciliacionRequestDTO);
		
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_UPDATE, null);
	}


	/**
	 * Servicio que permite generar la conciliación usando los movimientos de procesos nocturnos, del proveedor transaccional (open pay) y de estado de cuenta de acuerdo a su disponibilidad.
	 * Genera la sección global con el resumen de la conciliación, asi como la extracción de movimientos en tránsito y comisiones. 
	 * 
	 * @param ActualizaionConciliacionRequestDTO
	 * @param LastModifiedBy
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/conciliacion/generar/{folio}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Servicio que permite generar la conciliación usando los movimientos de procesos nocturnos, del proveedor transaccional (open pay) y de estado de cuenta de acuerdo a su disponibilidad.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Entidad encontrada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response generarConciliacion(@PathVariable(value = "folio", required = true) Integer folio,
										@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {

		conciliacionServiceImpl.generarConciliacion(folio, lastModifiedBy);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_GENERACION, null);
	}


//	
//	/**
//	 *Al confirmar que la información es correcta, el usuario solicitará el cierre de la conciliación, y tendrá la posibilidad de visualizar y editar los layout antes de enviarlos.
//	 * 
//	 * @param folio
//	 * @param createdBy
//	 * @return
//	 */
//	@ResponseBody
//	@ResponseStatus(HttpStatus.OK)
//	@PostMapping(value = "/conciliacion/enviar/{folio}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(httpMethod = "POST", value = "Al confirmar que la información es correcta, el usuario solicitará el cierre de la conciliación, y tendrá la posibilidad de visualizar y editar los layout antes de enviarlos.", tags = {
//			"Conciliación" })
//	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Conciliacion Enviada de forma Exitosa."),
//			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
//			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
//			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
//			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
//	public Response enviaConcicliacion(@PathVariable(value = "folio", required = true) Integer folio,
//			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
//
//		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Conciliacion Enviada de forma Exitosa.", null);
//	}
//	
//	/**
//	 * Realiza la consulta de los movimientos en transito de la conciliacion (con error).
//	 * 
//	 * @param folio
//	 * @param createdBy
//	 * @return
//	 */
//	@ResponseBody
//	@ResponseStatus(HttpStatus.OK)
//	@GetMapping(value = "/conciliacion/transito/consulta/{folio}", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(httpMethod = "GET", value = "Realiza la consulta de los movimientos en transito de la conciliacion (con error).", tags = {
//			"Conciliación" })
//	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta Folio"),
//			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
//			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
//			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
//			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
//	public Response consultaTransitoFolio(@PathVariable(value = "folio", required = true) Integer folio) {
//
//		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta exitosa",
//				buildConsultaTransitoFolioDummy());
//	}
//	
//	

	// TODO: Completed
	/**
	 *Al confirmar que la información es correcta, el usuario solicitará el cierre de la conciliación, y tendrá la posibilidad de visualizar y editar los layout antes de enviarlos.
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
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Conciliacion Enviada de forma Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response enviaConciliacion(@PathVariable(value = "folio", required = true) Integer folio,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
		if(!ValidadorConciliacion.validateInteger(folio))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		conciliacionServiceImpl.enviarConciliacion(folio, createdBy);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.CONCILIATION_SENT_SUCCESSFULLY, null);
	}

	// TODO: Completed
	/**
	 * Realiza la consulta de los movimientos en transito de la conciliacion (con error).
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
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH, response);
	}
	
	
	/**
	 *Permite realizar la solicitud de pagos no reflejados en Midas de los movimientos que se encuentran en tránsito.
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
		if(!ValidadorConciliacion.validateSolicitarPagosRequestDTO(solicitarPagosRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		solicitarPagosService.solicitarPagosService(solicitarPagosRequestDTO.getFolio(), solicitarPagosRequestDTO.getIdMovimientos(), createdBy);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Solicitud Pago Exitosa.", null);

	}

	
	/**
	 * Marca las transacciones seleccionadas de movimientos en tránsito a movimientos de devolución para cuando los pagos solicitados no fueron realizados.
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

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.MARK_AS_SUCCESSFUL_RETURN, response);
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

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_RETURNS_CONSULTATION, devoluciones);
	}
	
	
	/**
	 * Realiza la notificación de devolución de las transacciones marcadas para la devolución a las entidades bancarias.
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

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.REQUEST_SUCCESSFUL_RETURN, response);
	}
	
	/**
	 * Realiza la liquidación de los movimientos seleccionados; se debe especificar la fecha de liquidación para cada uno de los movimientos.
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
	public Response liquidacionMovimientos(@RequestBody LiquidacionMovimientosRequestDTO liquidacionMovimientosRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
		
		List<DevolucionEntidadDTO> response = devolucionesServiceImpl.liquidarDevoluciones(liquidacionMovimientosRequestDTO, createdBy);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_CLEARANCE_REQUEST, response 
				//buildLiquidacionMovimientosDummy()
				);
	}
	
	// TODO: Completed
	/**
	 * Servicio callback que será usado para actualizar el id del registro de las plantillas que será devuelto por PeopleSoft. 
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
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Identificador PS actualizado en la conciliación."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response actualizaIdPs(@RequestBody ActualizarIdPSRequest actualizarIdPSRequest/*ActualizaIdPsRequestDTO actualizaIdPsRequestDTO*/,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {
		if(!ValidadorConciliacion.validateActualizarIdPSRequest(actualizarIdPSRequest))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		// Comentado por ahora, hasta que la funcionalidad de la capa de servicio este completa
//		conciliacionServiceImpl.actualizaIdPs(actualizarIdPSRequest);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.IDENTIFIER_PS_UPDATED_IN_THE_CONCILIATION, null);
	}

	// TODO: Completed
	/**
	 * Servicio que permite generar la conciliación usando los movimientos de procesos nocturnos, del proveedor transaccional (open pay) y de estado de cuenta de acuerdo a su disponibilidad.
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
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String requestUser,
			@RequestHeader(CatalogConstants.REQUEST_HEADER_URL) String urlCallBack) {		
		if(!ValidadorConciliacion.validateInteger(folio))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		conciliacionServiceImpl.generarConciliacion(folio, urlCallBack);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.CONCILIATION_PROCESS_BEGINS, null);		
	}
	
	/**
	 * Servicio que será usado para actualizar el sub estatus del proceso de
	 * conciliación para conocer el estatus de la consulta de los reportes.
	 * 
	 * @param ActualizaSubEstatusDTO
	 * @param LastModifiedBy
	 * @return
	 */
//	@ResponseBody
//	@ResponseStatus(HttpStatus.OK)
//	@PutMapping(value = "/conciliacion/actualizarSubEstatus", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(httpMethod = "PUT", value = "Servicio que será usado para actualizar el sub estatus del proceso de conciliación para conocer el estatus de la consulta de los reportes.", tags = {
//			"Conciliación" })
//	@ApiResponses({
//			@ApiResponse(code = 200, response = Response.class, message = "Sub Estatus Conciliacion actualizado correctamente."),
//			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
//			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
//			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
//			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
//	public Response actualizaSubEstatus(@RequestBody ActualizaSubEstatusDTO actualizaSubEstatusDTO,
//			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String LastModifiedBy) {
//		
//		
//		conciliacionService.actualizaSubEstatusConciliacion(actualizaSubEstatusDTO, LastModifiedBy);
//
//		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUB_STATUS_RECONCILIATION_UPDATED_CORRECTLY, null);
//	}
	
	/**
	 * Servicio que consulta el resumen de conciliaciones realizadas.
	 * 
	 * @param folio
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
//	@ResponseBody
//	@ResponseStatus(HttpStatus.OK)
//	@PostMapping(value = "/conciliacion/resumen", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(httpMethod = "POST", value = "Servicio que consulta el resumen de conciliaciones realizadas.", tags = {
//			"Conciliación" })
//	@ApiResponses({
//			@ApiResponse(code = 200, response = Response.class, message = "Estatus Proceso actualizado correctamente."),
//			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
//			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
//			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
//			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
//	public Response resumenConciliaciones(@RequestBody ResumenConciliacionesRequest resumenConciliacionesRequest) {
//		
//		ResumenConciliacionDTO response = conciliacionService.resumenConciliaciones(resumenConciliacionesRequest);
//
//		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.STATUS_PROCESS_CORRECTLY_UPDATED, response);
//	}
	
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
		if(!ValidadorConciliacion.validateConsultaActividadesRequest(consultaActividadesRequest))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		List<ConsultaActividadDTO> response = conciliacionService.consultaActividades(consultaActividadesRequest);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), ConciliacionConstants.SUCCESSFUL_SEARCH, response);
	}

	// TODO: Completed
	/**
	 * Realiza la actualizacion del sub estatus de una conciliacion
	 * @param actualizarSubEstatusRequestDTO
	 * @param requestUser
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/conciliacion/actualizarSubEstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Servicio que permite actualizar el subestatus de una conciliacion.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Se inicia proceso de Actualizacion de sub estatus."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response actualizaSubEstatus(@RequestBody ActualizarSubEstatusRequestDTO actualizarSubEstatusRequestDTO,
			@RequestHeader(name = CatalogConstants.REQUEST_USER_HEADER, required = true) String requestUser) {		
		if(!ValidadorConciliacion.validateActualizarSubEstatusRequestDTO(actualizarSubEstatusRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		conciliacionServiceImpl.actualizaSubEstatusConciliacion(actualizarSubEstatusRequestDTO, requestUser);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Actualizacion de sub estatus correcta.",
				null);
	}

	
	// TODO: Comleted
	/**
	 * Obtiene el resumen de conciliaciones realizadas
	 * @param resumenConciliacionRequestDTO
	 * @param requestUser
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/resumen", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Servicio que consulta el resumen de conciliaciones realizadas.", tags = {
			"Conciliación" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Se inicia consulta de resumen de conciliaciones."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response resumenConciliaciones(@RequestBody ResumenConciliacionRequestDTO resumenConciliacionRequestDTO) {		
		if(!ValidadorConciliacion.validateResumenConciliacionRequestDTO(resumenConciliacionRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		conciliacionServiceImpl.resumenConciliaciones(resumenConciliacionRequestDTO);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Resumen de conciliaciones obtenido correctamente.",
				null);
	}
	
	
	// TODO: Completed
	/**
	 * Realiza la consulta del log de las últimas actividades realizadas en el sistema como son: Altas Reportes, Movimientos realizados dentro de la conciliación, Cambios de estatus.
	 * @param consultaActividadesRequestDTO
	 * @return
	 */
//	@ResponseBody
//	@ResponseStatus(HttpStatus.OK)
//	@PostMapping(value = "/conciliacion/actividades", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(httpMethod = "POST", value = "Realiza la consulta del log de las últimas actividades realizadas en el sistema como son: Altas Reportes, Movimientos realizados dentro de la conciliación, Cambios de estatus.", tags = {
//			"Conciliación" })
//	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Se inicia consulta de actividades."),
//			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
//			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
//			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
//			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
//	public Response consultaActividades(@RequestBody ConsultaActividadesRequestDTO consultaActividadesRequestDTO) {		
//		if(!ValidadorConciliacion.validateConsultaActividadesRequestDTO(consultaActividadesRequestDTO))
//			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
//		// Comentado hasta que la capa de servicios este lista
////		conciliacionServiceImpl.consultaActividades(consultaActividadesRequestDTO);
//		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta de actividades ejecutada recuperada correctamente.",
//				null);
//	}
	
//	public static ConciliacionDTO buildDummy() {
//		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);
//		EntidadDTO entidadDTO = null;
//		entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");
//		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);
//		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
//				new Date(), false);
//		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
//				new Date(), new Date(), false);
//		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);
//		
//		ConciliacionDTO conciliacionDTO = new ConciliacionDTO(0001, estatusConciliacionDTO, entidadDTO, cuentaDTO,
//				null, null, null, null, null, null,
//				null, new Date(), new Date(), "NMP", "NMP");
//		
////		ConciliacionDTO conciliacionDTO = new ConciliacionDTO(0001, estatusConciliacionDTO, entidadDTO, cuentaDTO,
////				reporteProcesosNocturnosDTO, reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, null, null, null,
////				null, new Date(), new Date(), "NMP", "NMP");
//		return conciliacionDTO;
//	}
//
//	public static ConciliacionDTOList buildConsultaMidasProveedorDummy() {
//
//		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);
//		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");
//		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);
//		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
//				new Date(), false);
//		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
//				new Date(), new Date(), false);
//		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);
//		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L,
//				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
//				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(150.00, MathContext.DECIMAL64),
//				new BigDecimal(0.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64));
//		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);
//		
//		List<DevolucionConDTO> devolucionDTOList = new ArrayList<>();
//		
//		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
//				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
//				"859363", 3);
//		
//	devolucionDTOList.add(devolucionDTO);
//		
//	List<MovTransitoDTO> movTransitoDTOlist = new ArrayList<>();
//		
//		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);
//		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
//				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
//				"Mariana Rodriguez Urbano");
//		
//		movTransitoDTOlist.add(movTransitoDTO);
//		
//	List<ComisionesDTO> comisionesDTOList = new ArrayList<>();
//		
//		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
//				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");
//		
//	comisionesDTOList.add(comisionesDTO);
//		
//		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(1L, estatusConciliacionDTO, entidadDTO, cuentaDTO,
//				reporteProcesosNocturnosDTO, reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO,
//				globalDTO, devolucionDTOList, movTransitoDTOlist, comisionesDTOList, new Date(), new Date(), "NMP", "NMP");
//		return conciliacionDTOList;
//	}
//	
//	public static ConciliacionDTOList buildConsultaFolioDummy() {
//		
//		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(1, "En proceso", true);
//		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");
//		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);
//		ReporteProcesosNocturnosDTO reporteProcesosNocturnosDTO = new ReporteProcesosNocturnosDTO(new Date(),
//				new Date(), false);
//		ReporteProveedorTransaccionalDTO reporteProveedorTransaccionalDTO = new ReporteProveedorTransaccionalDTO(
//				new Date(), new Date(), false);
//		ReporteEstadoCuentaDTO reporteEstadoCuentaDTO = new ReporteEstadoCuentaDTO(new Date(), new Date(), false);
//		GlobalDTO globalDTO = new GlobalDTO(1, new Date(), 300L, 250L,
//				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(404607.92, MathContext.DECIMAL64),
//				new BigDecimal(404607.92, MathContext.DECIMAL64), new BigDecimal(150.00, MathContext.DECIMAL64),
//				new BigDecimal(0.00, MathContext.DECIMAL64), new BigDecimal(0.00, MathContext.DECIMAL64));
//		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Pendiente", true);
//		
//		List<DevolucionConDTO> devolucionConDTOList = new ArrayList<>();
//		
//		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO,
//				new BigDecimal(150.00, MathContext.DECIMAL64), "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia",
//				"859363", 3);
//		
//		devolucionConDTOList.add(devolucionDTO);
//		
//		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en midas", true);
//		
//		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();
//		
//		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
//				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
//				"Mariana Rodriguez Urbano");
//		
//		movTransitoDTOList.add(movTransitoDTO);
//		
//		List<ComisionesDTO> comisionesDTOList = new ArrayList<>();
//		
//		ComisionesDTO comisionesDTO = new ComisionesDTO(1, new Date(), new Date(),
//				new BigDecimal(1500.00, MathContext.DECIMAL64), "Cargo diverso sucursal");
//		
//		comisionesDTOList.add(comisionesDTO);
//		
//		ConciliacionDTOList conciliacionDTOList = new ConciliacionDTOList(1L, estatusConciliacionDTO, entidadDTO, cuentaDTO,
//				reporteProcesosNocturnosDTO, reporteProveedorTransaccionalDTO, reporteEstadoCuentaDTO, globalDTO,
//				devolucionConDTOList, movTransitoDTOList, comisionesDTOList, new Date(), new Date(), "NMP", "NMP");
//		
//		
//		return conciliacionDTOList;
//		
//	}
//	
//	public static List<ConsultaConciliacionDTO> buildConsultaConciliacionDummy() {
//		
//		List<ConsultaConciliacionDTO> consultaConciliacionDTOList = new ArrayList<>();
//		EstatusConciliacionDTO estatusConciliacionDTO = new EstatusConciliacionDTO(2, "Finalizado", true);
//		EntidadDTO entidadDTO = new EntidadDTO(1, "Banco Banamex", true, "Banamex");
//		CuentaDTO cuentaDTO = new CuentaDTO(1, "1122131", true);
//		ConsultaConciliacionDTO consultaConciliacionDTO = new ConsultaConciliacionDTO(1, estatusConciliacionDTO, new Date(), "NMP",
//				new Date(), "NMP", entidadDTO, cuentaDTO, 658);
//		ConsultaConciliacionDTO consultaConciliacionDTO2 = new ConsultaConciliacionDTO(2, estatusConciliacionDTO, new Date(), "NMP",
//				new Date(), "NMP", entidadDTO, cuentaDTO, 372);
//		consultaConciliacionDTOList.add(consultaConciliacionDTO);
//		consultaConciliacionDTOList.add(consultaConciliacionDTO2);
//		return consultaConciliacionDTOList;
//	}
	
	public static List<MovTransitoDTO> buildConsultaTransitoFolioDummy() {
		
		List<MovTransitoDTO> movTransitoDTOList = new ArrayList<>();
		EstatusMovTransitoDTO estatusMovTransitoDTO = new EstatusMovTransitoDTO(1, "No identificada en Midas", true);
		MovTransitoDTO movTransitoDTO = new MovTransitoDTO(1, estatusMovTransitoDTO, 821547220, null, new Date(),
				"Pago", new BigDecimal(218.87, MathContext.DECIMAL64), "", "Mastercard", "557920xxxxxxxx8994",
				"Mariana Rodriguez Urbano");
		movTransitoDTOList.add(movTransitoDTO);
		return movTransitoDTOList;
		
	}
	
//	public static List<DevolucionConDTO> buildMarcarDevolucionesDummy(){
//		
//		List<DevolucionConDTO> devolucionDTOlist = new ArrayList<>();
//		EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(1, "Solicitada", true);
//		DevolucionConDTO devolucionDTO = new DevolucionConDTO(1, new Date(), estatusDevolucionDTO, new BigDecimal(150.00, MathContext.DECIMAL64), 
//				"Visa",	"4152xxxxxxxx9531", "Juana Garcia Garcia", "859363", 3);
//		devolucionDTOlist.add(devolucionDTO);
//		return devolucionDTOlist;
//	}
//	
//	public static List<DevolucionesMovimientosDTO> buildLiquidacionMovimientosDummy(){
//		
//		 List<DevolucionesMovimientosDTO> devolucionesMovimientosDTOList = new ArrayList<>();
//		 EstatusDevolucionDTO estatusDevolucionDTO = new EstatusDevolucionDTO(3, "Liquidada", true);
//		 DevolucionesMovimientosDTO devolucionesMovimientosDTO = new DevolucionesMovimientosDTO(1, new Date(), estatusDevolucionDTO, new BigDecimal(150.00, MathContext.DECIMAL64),
//				 "Visa", "4152xxxxxxxx9531", "Juana Garcia Garcia", "859363", 3, new Date());
//		 devolucionesMovimientosDTOList.add(devolucionesMovimientosDTO);
//		return devolucionesMovimientosDTOList;
//	}
}
