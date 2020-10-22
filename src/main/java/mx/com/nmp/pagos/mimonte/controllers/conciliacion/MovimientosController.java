/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

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
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarSubEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestMultipleDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.services.conciliacion.MovimientosEstadoCuentaService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.MovimientosMidasService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.MovimientosProveedorService;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.ConciliacionServiceImpl;
import mx.com.nmp.pagos.mimonte.util.ConciliacionDataValidator;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorConciliacion;

/**
 * @name MovimientosController
 * @description Clase de tipo Controlador spring que expone los servicios
 *              relacionados con Movimientos de estados de cuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 09:06 hrs.
 * @version 0.1
 */
@SuppressWarnings("JavaDoc")
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
	 * Service de MovimientosEstadoCuentaService
	 */
	@Autowired
	@Qualifier("movimientosEstadoCuentaService")
	private MovimientosEstadoCuentaService movimientosEstadoCuentaService;

	/**
	 * Servicio de conciliacion
	 */
	@Autowired
	ConciliacionServiceImpl conciliacionServiceImpl;

	/**
	 * Repository de conciliacion
	 */
	@Autowired
	private ConciliacionRepository conciliacionRepository;

	/**
	 * Validador generico para datos de conciliacion
	 */
	@Autowired
	private ConciliacionDataValidator conciliacionDataValidator;
	
	// Temporal format para los LOGs de timers
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	// ////////////////////////////////////////////////////////////////////////
	// PROCESOS NOCTURNOS (MIDAS) /////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////

	/**
	 * Permite dar de alta movimientos resultado de los Procesos Nocturnos.
	 * 
	 * @param movimientos
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
	public Response saveMovimientosNocturnos(@RequestBody MovimientoProcesosNocturnosListResponseDTO movimientos,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		
		LOG.info(">>>URL: POST /movimientos/nocturnos > REQUEST ENTRANTE: {}", movimientos.toString());
		
		long start = 0;
		long finish = 0;
		
		start = System.currentTimeMillis();
		
		// Validaciones generales
		if(!ValidadorConciliacion.validateMovimientoProcesosNocturnosListResponseDTO(movimientos))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,CodigoError.NMP_PMIMONTE_0008);
		
		// Se aplica trim() a elementos del objeto
		applyTrimToMovimientoProcesosNocturnosListResponseDTO(movimientos);
		
		LOG.debug("T>>> INICIA VALIDACOIN INICIAL: {}", sdf.format(new Date(start)));
		ValidadorConciliacion.validateFechasPrimary(movimientos.getFechaDesde(), movimientos.getFechaHasta());
		finish = System.currentTimeMillis();
		LOG.debug("T>>> TERMINA VALIDACION INICIAL: {}, EN: {}", sdf.format(new Date(finish)), (finish-start) );
		
		try {
			movimientosMidasService.save(movimientos, userRequest);
		} catch (ConciliacionException cex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, cex);
			throw cex;
		} catch (Exception eex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, eex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_130.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_130);
		}

		try {
			// Se actualiza el sub estatus de la conciliacion a consulta MIDAS completada
			start = System.currentTimeMillis();
			LOG.debug("T>>> INICIA ACTUALIZACION DE SUBESTATUS: {}", sdf.format(new Date(start)));
			conciliacionServiceImpl.actualizaSubEstatusConciliacion(new ActualizarSubEstatusRequestDTO(
					movimientos.getFolio(), ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_MIDAS_COMPLETADA,
					null), userRequest);			
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_030.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_030);
		}
		finish = System.currentTimeMillis();
		LOG.debug("T>>> FINALIZA ACTUALIZACION DE SUBESTATUS: {}, EN: {}",sdf.format(new Date(finish)) , (finish - start) );
		
		// Regresa la respuesta exitosa
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				null);
		
	}

	/**
	 * Permite consultar los movimientos del resultado de los procesos nocturnos.
	 * 
	 * @param commonConciliacionRequestDTO
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
	public Response findMovimientosNocturnos(
			@RequestBody CommonConciliacionEstatusRequestDTO commonConciliacionRequestDTO) {
		
		LOG.info(">>>URL: POST /movimientos/nocturnos/consulta > REQUEST ENTRANTE: {}", commonConciliacionRequestDTO.toString());
		
		MovimientoProcesosNocturnosListDTO movimientoProcesosNocturnosListDTO = null;
		
		// Se realizan validaciones del objeto
		if (!ValidadorConciliacion.validateCommonConciliacionEstatusRequestDTO(commonConciliacionRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		
		Optional<Conciliacion> conciliacion = conciliacionRepository.findById(commonConciliacionRequestDTO.getFolio());
		if (!conciliacion.isPresent())
			throw new ConciliacionException(ConciliacionConstants.CONCILIACION_ID_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_BUSINESS_045);
		Long total = movimientosMidasService.countByConciliacionId(commonConciliacionRequestDTO.getFolio(),
				commonConciliacionRequestDTO.getEstatus());
		if (null != total) {
			movimientoProcesosNocturnosListDTO = new MovimientoProcesosNocturnosListDTO();
			movimientoProcesosNocturnosListDTO.setTotal(total);
			movimientoProcesosNocturnosListDTO
					.setMovimientos(movimientosMidasService.findByFolio(commonConciliacionRequestDTO));
		} 
		else
			{
			movimientoProcesosNocturnosListDTO = new MovimientoProcesosNocturnosListDTO();
			movimientoProcesosNocturnosListDTO.setTotal(0L);
			movimientoProcesosNocturnosListDTO.setMovimientos(new ArrayList<>());
			}
		if (null == movimientoProcesosNocturnosListDTO.getTotal()
				|| null == movimientoProcesosNocturnosListDTO.getMovimientos()
				|| movimientoProcesosNocturnosListDTO.getMovimientos().isEmpty()){
			movimientoProcesosNocturnosListDTO = new MovimientoProcesosNocturnosListDTO();
			movimientoProcesosNocturnosListDTO.setTotal(0L);
			movimientoProcesosNocturnosListDTO.setMovimientos(new ArrayList<>());
		}

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.MSG_SUCCESSFUL_MOVIMIENTOS_QUERY, movimientoProcesosNocturnosListDTO);
	}

	// ////////////////////////////////////////////////////////////////////////
	// PROVEEDOR TRANSACCIONAL ////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////

	/**
	 * Permite dar de alta movimientos que provienen del Proveedor Transaccional
	 * (Open Pay).
	 * 
	 * @param movimientos
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
	public Response saveMovimientosProvedor(@RequestBody MovimientoTransaccionalListRequestDTO movimientos,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		
		LOG.info(">>>URL: POST /movimientos/proveedor > REQUEST ENTRANTE: {}", movimientos.toString());
		
		long start = 0;
		long finish = 0;
		
		start = System.currentTimeMillis();;
		LOG.debug("T>>> INICIAN VALIDACIONES PRIMARIAS: {}", sdf.format(new Date(start)));
		if (!ValidadorConciliacion.validateMovimientoTransaccionalListRequestDTO(movimientos)) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		}
		finish = System.currentTimeMillis();
		LOG.debug("T>>> FINALIZAN VALIDACIONES PRIMARIAS: {}, EN: {}", sdf.format(new Date(finish)), (finish-start) );
		
		start = System.currentTimeMillis();
		LOG.debug("T>>> INICIA VALIDACION GENERICA DE FECHAS: {}", sdf.format(new Date(start)));
		ValidadorConciliacion.validateFechasPrimary(movimientos.getFechaDesde(), movimientos.getFechaHasta());
		finish = System.currentTimeMillis();
		LOG.debug("T>>> FINALIZA VALIDACION GENERICA DE FECHAS: {}, EN: {}", sdf.format(new Date(finish)), (finish-start) );

		try {
			movimientosProveedorService.save(movimientos, userRequest);
		} catch (ConciliacionException cex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, cex);
			throw cex;
		} catch (Exception eex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, eex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_131.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_131);
		}

		try {
			// Se actualiza el sub estatus de la conciliacion a consulta OpenPay completada
			start = System.currentTimeMillis();
			LOG.debug("T>>> INICIA ACTUALIZACION DE SUB ESTATUS DE CONCILIACION: {}", sdf.format(new Date(start)));
			conciliacionServiceImpl.actualizaSubEstatusConciliacion(new ActualizarSubEstatusRequestDTO(
					movimientos.getFolio(), ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_OPEN_PAY_COMPLETADA,
					null), userRequest);
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_030.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_030);
		}
		finish = System.currentTimeMillis();
		LOG.debug("T>>> FINALIZA ACTUALIZACION DE SUB ESTATUS DE CONCILIACION: {}, EN: {}", sdf.format(new Date(finish)), (finish-start) );

		// Regresa la respuesta exitosa
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				null);
		
	}

	/**
	 * Permite consultar los movimientos del proveedor transaccional. (Open Pay)
	 * 
	 * @param commonConciliacionRequestDTO
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
		
		LOG.info(">>>URL: POST /movimientos/proveedor/consulta > REQUEST ENTRANTE: {}", commonConciliacionRequestDTO.toString());
		
		MovimientoTransaccionalListDTO movimientoTransaccionalListDTO = null;
		if (!ValidadorConciliacion.validateCommonConciliacionRequestDTO(commonConciliacionRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		Optional<Conciliacion> conciliacion = conciliacionRepository.findById(commonConciliacionRequestDTO.getFolio());
		if (!conciliacion.isPresent())
			throw new ConciliacionException(ConciliacionConstants.CONCILIACION_ID_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_BUSINESS_045);
		Long total = movimientosProveedorService.countByConciliacionId(commonConciliacionRequestDTO.getFolio());
		if (null != total) {
			movimientoTransaccionalListDTO = new MovimientoTransaccionalListDTO();
			movimientoTransaccionalListDTO.setTotal(total);
			movimientoTransaccionalListDTO
					.setMovimientos(movimientosProveedorService.findByFolio(commonConciliacionRequestDTO));
		} 
		else {
			movimientoTransaccionalListDTO = new MovimientoTransaccionalListDTO();
			movimientoTransaccionalListDTO.setTotal(0L);
			movimientoTransaccionalListDTO.setMovimientos(new ArrayList<>());
		}
		if (null == movimientoTransaccionalListDTO.getTotal() || null == movimientoTransaccionalListDTO.getMovimientos()
				|| movimientoTransaccionalListDTO.getMovimientos().isEmpty()) {
			movimientoTransaccionalListDTO = new MovimientoTransaccionalListDTO();
			movimientoTransaccionalListDTO.setTotal(0L);
			movimientoTransaccionalListDTO.setMovimientos(new ArrayList<>());
		}

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.MSG_SUCCESSFUL_MOVIMIENTOS_QUERY, movimientoTransaccionalListDTO);
	}

	// ////////////////////////////////////////////////////////////////////////
	// ESTADO CUENTA //////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////

	/**
	 * Recibe la solicitud para la consulta del archivo y el alta de los movimientos
	 * del estado de cuenta.
	 * 
	 * @param saveEstadoCuentaRequestDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/movimientos/estadocuenta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Recibe la solicitud para la consulta del archivo y el alta de los movimientos del estado de cuenta.", tags = {
			"Movimientos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta movimientos exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response saveMovimientoEsadoCuenta(@RequestBody SaveEstadoCuentaRequestDTO saveEstadoCuentaRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		
		LOG.info(">>>URL: POST /movimientos/estadocuenta > REQUEST ENTRANTE: {}", saveEstadoCuentaRequestDTO.toString());
		
		// Objetos necesarios
		Boolean procesoCorrecto = null;
		String descripcionError = null;

		// Validacion general de objeto y atributos
		if (!ValidadorConciliacion.validateSaveEstadoCuentaRequestDTO(saveEstadoCuentaRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		// Valida que la conciliacion exista
		conciliacionDataValidator.validateFolioExists(saveEstadoCuentaRequestDTO.getFolio());
		
		// Validacion de fechas
		ValidadorConciliacion.validateFechasPrimary(saveEstadoCuentaRequestDTO.getFechaInicial(),
				saveEstadoCuentaRequestDTO.getFechaFinal());
		
		// Procesa la consulta del estado de cuenta, consulta los archivos y persiste
		// los movimientos del estado de cuenta
		try {
			movimientosEstadoCuentaService.procesarConsultaEstadoCuenta(saveEstadoCuentaRequestDTO, userRequest);
			procesoCorrecto = true;
		} catch (ConciliacionException cex) {
			procesoCorrecto = false;
			descripcionError = cex.getCodigoError().getDescripcion();
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, cex);
			throw cex;
		} catch (Exception eex) {
			procesoCorrecto = false;
			descripcionError = CodigoError.NMP_PMIMONTE_BUSINESS_046.getDescripcion();
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, eex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_046.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_046);
		} finally {
			try {
				// Se actualiza el sub estatus de la conciliacion en base al resultado
				conciliacionServiceImpl.actualizaSubEstatusConciliacion(new ActualizarSubEstatusRequestDTO(
						saveEstadoCuentaRequestDTO.getFolio(),
						procesoCorrecto
								? ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_ESTADO_DE_CUENTA_COMPLETADA
								: ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_ESTADO_DE_CUENTA_ERROR,
						descripcionError), userRequest);
			} catch (Exception ex) {
				LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_030.getDescripcion(),
						CodigoError.NMP_PMIMONTE_BUSINESS_030);
			}
		}
		// Regresa la respuesta exitosa
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.SUCCESSFUL_SAVE_ESTADO_CUENTA, null);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/movimientos/estadocuenta/multiple", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Recibe la solicitud para la consulta del archivo y el alta de los movimientos del estado de cuenta con multiples folios de conciliacion.", tags = {
			"Movimientos" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta movimientos exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response saveMovimientoEsadoCuentaMultiple(@RequestBody SaveEstadoCuentaRequestMultipleDTO saveEstadoCuentaRequestMultipleDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {		
		LOG.info(">>>URL: POST /movimientos/estadocuenta/multiple > REQUEST ENTRANTE: {}", saveEstadoCuentaRequestMultipleDTO.toString());
		// Objetos necesarios
		Boolean procesoCorrecto = null;
		String descripcionError = null;

		// Validacion general de objeto y atributos
		if (!ValidadorConciliacion.validateSaveEstadoCuentaRequestMultipleDTO(saveEstadoCuentaRequestMultipleDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		// Valida que los folios de conciliacion existan
		for(Long folio : saveEstadoCuentaRequestMultipleDTO.getFolios()) {
			conciliacionDataValidator.validateFolioExists(folio);
		}
		
		// Validacion de fechas
		ValidadorConciliacion.validateFechasPrimary(saveEstadoCuentaRequestMultipleDTO.getFechaInicial(),
				saveEstadoCuentaRequestMultipleDTO.getFechaFinal());
		
		// Procesa la consulta del estado de cuenta, consulta los archivos y persiste
		// los movimientos del estado de cuenta
		try {
			movimientosEstadoCuentaService.procesarConsultaEstadoCuentaConciliacionMultiple(saveEstadoCuentaRequestMultipleDTO, userRequest);
			procesoCorrecto = true;
		} catch (ConciliacionException cex) {
			procesoCorrecto = false;
			descripcionError = cex.getCodigoError().getDescripcion();
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, cex);
			throw cex;
		} catch (Exception eex) {
			procesoCorrecto = false;
			descripcionError = CodigoError.NMP_PMIMONTE_BUSINESS_046.getDescripcion();
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, eex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_046.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_046);
		} finally {
			try {
				// Se actualiza el sub estatus de la conciliacion en base al resultado
				for(Long folio :saveEstadoCuentaRequestMultipleDTO.getFolios()) {
					conciliacionServiceImpl.actualizaSubEstatusConciliacion(new ActualizarSubEstatusRequestDTO(folio,
							procesoCorrecto
									? ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_ESTADO_DE_CUENTA_COMPLETADA
									: ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_ESTADO_DE_CUENTA_ERROR,
							descripcionError), userRequest);	
				}
			} catch (Exception ex) {
				LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_030.getDescripcion(),
						CodigoError.NMP_PMIMONTE_BUSINESS_030);
			}
		}
		// Regresa la respuesta exitosa
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.SUCCESSFUL_SAVE_ESTADO_CUENTA, null);
	}
	
	/**
	 * Consulta movimientos estado de cuneta por filtros de objeto
	 * CommonConciliacionRequestDTO
	 * 
	 * @param commonConciliacionRequestDTO
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
	public Response findMovimientoEsadoCuenta(@RequestBody CommonConciliacionRequestDTO commonConciliacionRequestDTO) {
		
		LOG.info(">>>URL: POST /movimientos/estadocuenta/consulta > REQUEST ENTRANTE: {}", commonConciliacionRequestDTO);
		
		MovimientosEstadoCuentaDTO movimientosEstadoCuentaDTO = null;
		if (!ValidadorConciliacion.validateCommonConciliacionRequestDTO(commonConciliacionRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		// Se valida que la conciliacion exista
		Optional<Conciliacion> conciliacion = conciliacionRepository.findById(commonConciliacionRequestDTO.getFolio());
		if (!conciliacion.isPresent())
			throw new ConciliacionException(ConciliacionConstants.CONCILIACION_ID_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_BUSINESS_045);
		Long total = movimientosEstadoCuentaService.countByConciliacionId(commonConciliacionRequestDTO.getFolio());
		if (null != total) {
			movimientosEstadoCuentaDTO = new MovimientosEstadoCuentaDTO();
			movimientosEstadoCuentaDTO.setTotal(total);
			movimientosEstadoCuentaDTO.setMovimientos(
					movimientosEstadoCuentaService.findByFolioAndPagination(commonConciliacionRequestDTO));
		} 
		else {
			movimientosEstadoCuentaDTO = new MovimientosEstadoCuentaDTO();
			movimientosEstadoCuentaDTO.setTotal(0L);
			movimientosEstadoCuentaDTO.setMovimientos(new ArrayList<>());
		}
		if (null == movimientosEstadoCuentaDTO.getTotal() || null == movimientosEstadoCuentaDTO.getMovimientos()
				|| movimientosEstadoCuentaDTO.getMovimientos().isEmpty()){
			movimientosEstadoCuentaDTO = new MovimientosEstadoCuentaDTO();
			movimientosEstadoCuentaDTO.setTotal(0L);
			movimientosEstadoCuentaDTO.setMovimientos(new ArrayList<>());
		}

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.SUCCESSFUL_QUERY_ESTADO_CUENTA, movimientosEstadoCuentaDTO);
	}

	/**
	 * Aplica un trim() (elimina espacios antes y despues de una cadena de caracteres) a algunos de los elementos de un objeto de tipo MovimientoProcesosNocturnosListResponseDTO
	 * 
	 * @param movimientoProcesosNocturnosListResponseDTO
	 */
	private static void applyTrimToMovimientoProcesosNocturnosListResponseDTO(MovimientoProcesosNocturnosListResponseDTO movimientoProcesosNocturnosListResponseDTO) {
		if(null != movimientoProcesosNocturnosListResponseDTO) {
			if(null != movimientoProcesosNocturnosListResponseDTO.getMovimientos() && !movimientoProcesosNocturnosListResponseDTO.getMovimientos().isEmpty()) {
				for(MovimientoMidasRequestDTO movimientoMidasRequestDTO : movimientoProcesosNocturnosListResponseDTO.getMovimientos()) {
					movimientoMidasRequestDTO.setTipoContratoAbr(movimientoMidasRequestDTO.getTipoContratoAbr().trim());
				}
			}
		}
	}
	
}
