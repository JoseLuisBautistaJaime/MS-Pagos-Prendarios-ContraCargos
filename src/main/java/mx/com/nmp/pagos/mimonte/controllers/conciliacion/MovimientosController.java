/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.services.conciliacion.MovimientosEstadoCuentaService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.MovimientosMidasService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.MovimientosProveedorService;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.ConciliacionServiceImpl;
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

	// ////////////////////////////////////////////////////////////////////////
	// PROCESOS NOCTURNOS (MIDAS) /////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////

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
	public Response saveMovimientosNocturnos(@RequestBody MovimientoProcesosNocturnosListResponseDTO movimientos,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		ValidadorConciliacion.validateFechasPrimary(movimientos.getFechaDesde(), movimientos.getFechaHasta());
		movimientosMidasService.save(movimientos, userRequest);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				null);
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
	public Response findMovimientosNocturnos(
			@RequestBody CommonConciliacionEstatusRequestDTO commonConciliacionRequestDTO) {
		MovimientoProcesosNocturnosListDTO movimientoProcesosNocturnosListDTO = null;
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
		} else
			throw new InformationNotFoundException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND,
					CodigoError.NMP_PMIMONTE_0009);
		if (null == movimientoProcesosNocturnosListDTO.getTotal()
				|| null == movimientoProcesosNocturnosListDTO.getMovimientos()
				|| movimientoProcesosNocturnosListDTO.getMovimientos().isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND,
					CodigoError.NMP_PMIMONTE_0009);
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
	public Response saveMovimientosProvedor(@RequestBody MovimientoTransaccionalListRequestDTO movimientos,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		if (!ValidadorConciliacion.validateMovimientoTransaccionalListRequestDTO(movimientos)) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		}
		ValidadorConciliacion.validateFechasPrimary(movimientos.getFechaDesde(), movimientos.getFechaHasta());
		movimientosProveedorService.save(movimientos, userRequest);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				null);
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
		} else
			throw new InformationNotFoundException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND,
					CodigoError.NMP_PMIMONTE_0009);
		if (null == movimientoTransaccionalListDTO.getTotal() || null == movimientoTransaccionalListDTO.getMovimientos()
				|| movimientoTransaccionalListDTO.getMovimientos().isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND,
					CodigoError.NMP_PMIMONTE_0009);
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
		// Objetos necesarios
		Boolean procesoCorrecto = null;
		String descripcionError = null;

		// Validacion general de objeto y atributos
		if (!ValidadorConciliacion.validateSaveEstadoCuentaRequestDTO(saveEstadoCuentaRequestDTO))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

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
	public Response findMovimientoEsadoCuenta(@RequestBody CommonConciliacionRequestDTO commonConciliacionRequestDTO) {
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
		} else
			throw new InformationNotFoundException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND,
					CodigoError.NMP_PMIMONTE_0009);
		if (null == movimientosEstadoCuentaDTO.getTotal() || null == movimientosEstadoCuentaDTO.getMovimientos()
				|| movimientosEstadoCuentaDTO.getMovimientos().isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND,
					CodigoError.NMP_PMIMONTE_0009);

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				ConciliacionConstants.SUCCESSFUL_QUERY_ESTADO_CUENTA, movimientosEstadoCuentaDTO);
	}

}
