/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.icu.util.Calendar;

import mx.com.nmp.pagos.mimonte.aspects.ActividadGenericMethod;
import mx.com.nmp.pagos.mimonte.aspects.ObjectsInSession;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.ReporteBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ReporteRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.MovimientoJdbcRepository;
import mx.com.nmp.pagos.mimonte.dto.EstadoCuentaWraper;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTOList;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMovEstadoCuentaRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaFileLayout;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaImplementacionEnum;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaBatchDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDBDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestMultipleDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ConciliacionEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubEstatusConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubTipoActividadEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoActividadEnum;
import mx.com.nmp.pagos.mimonte.services.EstadoCuentaParserService;
import mx.com.nmp.pagos.mimonte.services.EstadoCuentaReaderService;
import mx.com.nmp.pagos.mimonte.util.ConciliacionDataValidator;
import mx.com.nmp.pagos.mimonte.util.DateUtil;
import mx.com.nmp.pagos.mimonte.util.FechasUtil;

/**
 * @name MovimientosEstadoCuentaService
 * 
 * @description Realiza operaciones de logica de negocio sobre objetos
 *              relacionados con movimientos de estado de cuenta
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:40 PM
 */
@Service("movimientosEstadoCuentaService")
public class MovimientosEstadoCuentaService {

	/**
	 * Repository de MovimientoEstadoCuentaRepository
	 */
	@Autowired
	@Qualifier("movimientoEstadoCuentaRepository")
	private MovimientoEstadoCuentaRepository movimientoEstadoCuentaRepository;

	/**
	 * Repository de Reporte
	 */
	@Autowired
	@Qualifier("reporteRepository")
	private ReporteRepository reporteRepository;

	/**
	 * Conciliacion helper
	 */
	@Autowired
	private ConciliacionHelper conciliacionHelper;

	/**
	 * Repository de Estado Cuenta
	 */
	@Autowired
	@Qualifier("estadoCuentaRepository")
	private EstadoCuentaRepository estadoCuentaRepository;

	/**
	 * Estado de cuenta Reader
	 */
	@Autowired
	@Qualifier("estadoCuentaReaderC43Service")
	private EstadoCuentaReaderService estadoCuentaReaderService;

	/**
	 * Estado de cuenta Parser
	 */
	@Autowired
	@Qualifier("estadoCuentaParserC43Service")
	private EstadoCuentaParserService estadoCuentaParserService;

	/**
	 * Repository de conciliacion
	 */
	@Autowired
	@Qualifier("conciliacionRepository")
	private ConciliacionRepository conciliacionRepository;

	/**
	 * Service de conciliacion
	 */
	@Autowired
	private ConciliacionService conciliacionService;
	
	@Autowired
	@Qualifier("conciliacionEstadoCuentaRepository")
	ConciliacionEstadoCuentaRepository conciliacionEstadoCuentaRepository;
	
	
	/**
	 * Validador generico para datos de conciliacion
	 */
	@Autowired
	private ConciliacionDataValidator conciliacionDataValidator;

	/**
	 * Registro de actividades
	 */
	@Autowired
	private ActividadGenericMethod actividadGenericMethod;

	/**
	 * Registro de movimientos usando Jdbc
	 */
	@Autowired
	private MovimientoJdbcRepository movimientoJdbcRepository;

	@Inject
	private ObjectsInSession objectsInSession;
	
	/**
	 * Log de la clase
	 */
	private static final Logger LOG = LoggerFactory.getLogger(MovimientosEstadoCuentaService.class);

	public MovimientosEstadoCuentaService() {
		super();
	}

	/**
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 */
	public List<MovimientoEstadoCuentaDTO> listByDates(Date fechaDesde, Date fechaHasta) {
		return null;
	}

	/**
	 * 
	 * @param idConciliacion
	 */
	public Integer countByConciliacion(Long idConciliacion) {
		return 0;
	}

	/**
	 * 
	 * @param idConciliacion
	 * @param page
	 * @param pageSize
	 */
	public List<MovimientoEstadoCuentaDTO> listByConciliacion(Long idConciliacion, Integer page, Integer pageSize) {
		return null;
	}

	/**
	 * 
	 * @param criteria
	 * @param page
	 * @param pageSize
	 */
	public List<MovimientoEstadoCuentaDTO> search(ConsultaMovEstadoCuentaRequestDTO criteria, Integer page,
			Integer pageSize) {
		return null;
	}

	/**
	 * 
	 * @param criterios
	 */
	public Integer count(ConsultaMovEstadoCuentaRequestDTO criterios) {
		return 0;
	}

	/**
	 * 
	 * @param movimientos
	 */
	public void saveBatch(List<MovimientoEstadoCuentaBatchDTO> movimientos) {

	}

	/**
	 * Regresa el total de registros de movimientos estado de cuenta relacionados
	 * con el id de conciliacion especificado como parametro
	 * 
	 * @param idConciliacion
	 * @return
	 */
	public Long countByConciliacionId(final Long idConciliacion) {
		return movimientoEstadoCuentaRepository.countMovimientos(idConciliacion);
	}

	/**
	 * Encuentra la lista de conciliaciones por folio de conciliacion
	 * 
	 * @param commonConciliacionRequestDTO
	 * @return
	 */
	public List<MovimientoEstadoCuentaDTO> findByFolioAndPagination(
			final CommonConciliacionRequestDTO commonConciliacionRequestDTO) {
		List<MovimientoEstadoCuentaDBDTO> movimientoEstadoCuentaDBDTOLst = null;
		List<MovimientoEstadoCuentaDTO> movimientoEstadoCuentaDTOList = null;
//		Pageable pageable = PageRequest.of(commonConciliacionRequestDTO.getPagina(),
//				commonConciliacionRequestDTO.getResultados());
		movimientoEstadoCuentaDBDTOLst = movimientoEstadoCuentaRepository
				.listMovimientos(commonConciliacionRequestDTO.getFolio()/* , pageable */);
		movimientoEstadoCuentaDTOList = MovimientosBuilder
				.buildMovimientoEstadoCuentaDTOListFromMovimientoEstadoCuentaDBDTOList(movimientoEstadoCuentaDBDTOLst);
		return movimientoEstadoCuentaDTOList;
	}

	/**
	 * Guarda un reporte relacionado con un movimiento de estado de cuenta
	 * 
	 * @param request
	 * @param userRequest
	 */
	@Transactional
	public Reporte save(final SaveEstadoCuentaRequestDTO request, final String userRequest) {

		Conciliacion conciliacion = null;
		if (request.getFolio() != null) { // OXXO multi conciliacion
			conciliacion = conciliacionHelper.getConciliacionByFolio(request.getFolio(),
					ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);
		}

		// Insertar el nuevo reporte (este reporte sera el nuevo reporte a considerar)
		Reporte reporte = null;
		try {
			reporte = ReporteBuilder.buildReporteFromSaveEstadoCuentaRequestDTO(request, conciliacion, userRequest);
			reporte = reporteRepository.save(reporte);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al guardar la solicitud de consulta de estado de cuenta",
					CodigoError.NMP_PMIMONTE_BUSINESS_046);
		}

		return reporte;
	}

	/**
	 * Consulta estado de cuenta en base a las fechas indicadas
	 * 
	 * @param saveEstadoCuentaRequestDTO
	 * @param userRequest
	 */
	@Transactional
	public void procesarConsultaEstadoCuenta(SaveEstadoCuentaRequestDTO request, final String userRequest)
			throws ConciliacionException {
		// Objetos necesarios
		Map<String, Date> datesMap = null;
		List<Long> possibleSubEstatus = null;
		EstatusConciliacion estatusConciliacion = null;

		// Ajuste de fechas
		try {
			datesMap = FechasUtil.adjustDates(request.getFechaInicial(), request.getFechaFinal());
			if (null != datesMap) {
				request.setFechaInicial(
						null != datesMap.get("startDate ") ? datesMap.get("startDate ") : request.getFechaInicial());
				request.setFechaFinal(
						null != datesMap.get("endDate ") ? datesMap.get("endDate ") : request.getFechaFinal());
			}
		} catch (ConciliacionException ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_0013.getDescripcion(),
					CodigoError.NMP_PMIMONTE_0013);
		}

		// Se obtien el estatus de la conciliacion
		try {
			estatusConciliacion = conciliacionRepository.findEstatusByConciliacionId(request.getFolio());
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_125.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_125);
		}

		// Valida que tenga un sub.estatus valido
		try {
			possibleSubEstatus = MovimientosBuilder
					.buildLongListFromObjectList(conciliacionRepository.getPossibleSubestatusList(
							ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_ESTADO_DE_CUENTA, request.getFolio()));
			conciliacionDataValidator.validateSubEstatusByFolioAndSubEstatus(request.getFolio(), possibleSubEstatus);
		} catch (ConciliacionException ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_030.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_030);
		}

		// Actualiza el sub-estatus para realizar el registro del estado de cuenta (invoca un metodo de actualizacion con una nueva transaccion)
		if (null != estatusConciliacion) {
			conciliacionService.actualizaSubEstatusConciliacionNT(request.getFolio(),
					new SubEstatusConciliacion(ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_ESTADO_DE_CUENTA),
					userRequest, new Date(), estatusConciliacion, null);
		} else {
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_125.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_125);
		}

		// Valida que la conciliacion tenga el estatus correcto para poder dar de alta
		// el estado cuenta

		// Obtiene el estatus de la conciliacion
		estatusConciliacion = conciliacionRepository.findEstatusByConciliacionId(request.getFolio());

		// Guarda un nuevo reporte
		Reporte reporte = save(request, userRequest);

		// Consulta los diferentes estados de cuenta por cada fecha
		Date fechaEstadoCuenta = reporte.getFechaDesde();
		Long idConciliacion = reporte.getConciliacion().getId().longValue();
		long idReporte = reporte.getId();

		while (!fechaEstadoCuenta.after(reporte.getFechaHasta())) {

			// Lee el archivo usando la implementacion cuaderno 43
			EstadoCuentaFileLayout estadoCuentaFileLayout = estadoCuentaReaderService.read(fechaEstadoCuenta,
					idConciliacion, EstadoCuentaImplementacionEnum.CUADERNO_43);
			if (estadoCuentaFileLayout == null) {
				throw new ConciliacionException(
						"Error al leer el archivo de estado de cuenta para la fecha " + fechaEstadoCuenta + "",
						CodigoError.NMP_PMIMONTE_BUSINESS_047);
			}

			// Parsea el archivo
			EstadoCuentaWraper estadoCuentaWraper = estadoCuentaParserService.extract(estadoCuentaFileLayout);
			if (estadoCuentaWraper == null) {
				throw new ConciliacionException(
						"Error al parsear el archivo de estado de cuenta para la fecha " + fechaEstadoCuenta + "",
						CodigoError.NMP_PMIMONTE_BUSINESS_048);
			}

			saveEstadoCuentaMovimientos(idReporte, fechaEstadoCuenta, estadoCuentaWraper);

			// Se mueve al siguiente dia para consultar el estado de cuenta
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaEstadoCuenta);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			fechaEstadoCuenta = cal.getTime();
		}

		// Registro de actividad
		actividadGenericMethod.registroActividadV2(objectsInSession.getFolioByIdConciliacion(idConciliacion),
				"Se proceso la consulta del estado de cuenta para la conciliacion con el folio " + idConciliacion,
				TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.MOVIMIENTOS);
	}

	public void procesarConsultaEstadoCuentaConciliacionMultiple(SaveEstadoCuentaRequestMultipleDTO request, final String userRequest)
			throws ConciliacionException {
		// Objetos necesarios
		Map<String, Date> datesMap = null;
		List<Long> possibleSubEstatus = null;
		EstatusConciliacion estatusConciliacion = null;
		Map<Long, EstatusConciliacion> mapSubEstatusConciliacion = null;

		// Ajuste de fechas
		try {
			datesMap = FechasUtil.adjustDates(request.getFechaInicial(), request.getFechaFinal());
			if (null != datesMap) {
				request.setFechaInicial(
						null != datesMap.get("startDate ") ? datesMap.get("startDate ") : request.getFechaInicial());
				request.setFechaFinal(
						null != datesMap.get("endDate ") ? datesMap.get("endDate ") : request.getFechaFinal());
			}
		} catch (ConciliacionException ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_0013.getDescripcion(),
					CodigoError.NMP_PMIMONTE_0013);
		}

		// Valida que los folios pertenezcan al proveedor OXXO
		boolean oxxoFlag = ( (BigInteger) conciliacionRepository.validateCorresponsalOxxoAndFolios(request.getFolios(), request.getIdCorresponsal()) ).compareTo(BigInteger.ONE) == 0 ? true : false;
		if(!oxxoFlag) {
			LOG.error(ConciliacionConstants.Validation.NO_OXXO_AND_CONCILIACION_RELATIONSHIP);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_CORRESPONSAL_CONCILIACION.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_030);
		}
		
		// Se obtien el estatus de la conciliacion
		mapSubEstatusConciliacion = new HashMap<>();
		try {
			for(Long folio : request.getFolios()) {
				estatusConciliacion = conciliacionRepository.findEstatusByConciliacionId(folio);
				mapSubEstatusConciliacion.put(folio, estatusConciliacion);
			}
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_125.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_125);
		}

		// Valida que tenga un sub.estatus valido
		try {
			for(Long folio : request.getFolios()) {
				possibleSubEstatus = MovimientosBuilder
						.buildLongListFromObjectList(conciliacionRepository.getPossibleSubestatusList(
								ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_ESTADO_DE_CUENTA, folio));
				conciliacionDataValidator.validateSubEstatusByFolioAndSubEstatus(folio, possibleSubEstatus);
			}
			
		} catch (ConciliacionException ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_030.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_030);
		}
		
		// Actualiza el sub-estatus para realizar el registro del estado de cuenta (invoca un metodo de actualizacion con una nueva transaccion)
		if (null != estatusConciliacion) {
			conciliacionService.actualizaSubEstatusConciliacionMultipleNT(request.getFolios(),
					new SubEstatusConciliacion(ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_ESTADO_DE_CUENTA),
					userRequest, new Date(), estatusConciliacion, null);
		} else {
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_125.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_125);
		}

		// Valida que las conciliaciones tengan el estatus correcto para poder dar de alta
		// el estado cuenta

		// Obtiene el estatus de las conciliaciones
		for(Long folio : request.getFolios()) {
			estatusConciliacion = conciliacionRepository.findEstatusByConciliacionId(folio);
			mapSubEstatusConciliacion.put(folio, estatusConciliacion);
		}

		// Guarda un nuevo reporte con id de conciliacion 0
		SaveEstadoCuentaRequestDTO saveEstadoCuentaRequestDTO = new SaveEstadoCuentaRequestDTO();
		saveEstadoCuentaRequestDTO.setFechaFinal(request.getFechaFinal());
		saveEstadoCuentaRequestDTO.setFechaInicial(request.getFechaInicial());
		saveEstadoCuentaRequestDTO.setFolio(null != request.getFolios() && !request.getFolios().isEmpty() ? request.getFolios().get(0): 0L);
		Reporte reporte = save(saveEstadoCuentaRequestDTO, userRequest);

		// Se guarda la asociacion entre conciliaciones y el estado cuenta
		for(Long folio : request.getFolios()) {
			conciliacionEstadoCuentaRepository.save(new ConciliacionEstadoCuenta(folio, reporte.getId().longValue()));
		}
		
		// Consulta los diferentes estados de cuenta por cada fecha
		Date fechaEstadoCuenta = reporte.getFechaDesde();
		Long idConciliacion = request.getFolios().get(0); // TODO: primer conciliacion del set (solo se requiere para obtener la cuenta asignada)
		Integer idReporte = reporte.getId();

		while (!fechaEstadoCuenta.after(reporte.getFechaHasta())) {

			// Lee el archivo usando la implementacion cuaderno 43
			EstadoCuentaFileLayout estadoCuentaFileLayout = estadoCuentaReaderService.read(fechaEstadoCuenta,
					idConciliacion, EstadoCuentaImplementacionEnum.CUADERNO_43);
			if (estadoCuentaFileLayout == null) {
				throw new ConciliacionException(
						"Error al leer el archivo de estado de cuenta para la fecha " + fechaEstadoCuenta + "",
						CodigoError.NMP_PMIMONTE_BUSINESS_047);
			}

			// Parsea el archivo
			EstadoCuentaWraper estadoCuentaWraper = estadoCuentaParserService.extract(estadoCuentaFileLayout);
			if (estadoCuentaWraper == null) {
				throw new ConciliacionException(
						"Error al parsear el archivo de estado de cuenta para la fecha " + fechaEstadoCuenta + "",
						CodigoError.NMP_PMIMONTE_BUSINESS_048);
			}

			saveEstadoCuentaMovimientos(idReporte, fechaEstadoCuenta, estadoCuentaWraper);

			// Se mueve al siguiente dia para consultar el estado de cuenta
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaEstadoCuenta);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			fechaEstadoCuenta = cal.getTime();
		}

		// Registro de actividad
		/*actividadGenericMethod.registroActividad(idConciliacion,
				"Se proceso la consulta del estado de cuenta para la conciliacion con el folio " + idConciliacion,
				TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.MOVIMIENTOS);
		*/

		// Se distribuye el estado de cuenta entre las conciliaciones
		distribuirEstadoCuenta(reporte, request.getFolios(), userRequest);

	}

	/**
	 * Guarda el archivo de estado de cuenta y los movimientos
	 * 
	 * @param idReporte
	 * @param fechaEstadoCuenta
	 * @param estadoCuentaWraper
	 */
	private void saveEstadoCuentaMovimientos(long idReporte, Date fechaEstadoCuenta,
			EstadoCuentaWraper estadoCuentaWraper) {
		try {

			// Se crea el nuevo estado de cuenta
			EstadoCuenta estadoCuenta = this.estadoCuentaRepository.findOneByIdReporteAndFechaCarga(idReporte,
					fechaEstadoCuenta);
			if (estadoCuenta == null) {
				estadoCuenta = new EstadoCuenta();
				estadoCuenta.setIdReporte(idReporte);
			}

			int totalMovs = (estadoCuenta.getTotalMovimientos() != null ? estadoCuenta.getTotalMovimientos() : 0);
			totalMovs += (estadoCuentaWraper.movimientos != null ? estadoCuentaWraper.movimientos.size() : 0);
			estadoCuenta.setTotalMovimientos(totalMovs);

			// Habilitar soporte para multiples cabecera y totales adicionales
			estadoCuenta.setCabecera(estadoCuentaWraper.cabecera);
			estadoCuenta.setTotales(estadoCuentaWraper.totales);
			estadoCuenta.setTotalesAdicional(estadoCuentaWraper.totalesAdicional);
			estadoCuenta.setFechaCarga(fechaEstadoCuenta);
			estadoCuentaRepository.save(estadoCuenta);

			// Se persisten los movimientos
			if (estadoCuentaWraper.movimientos != null) {
				List<MovimientoEstadoCuenta> movimientos = estadoCuentaWraper.movimientos;
				// Se setea el id del estado de cuenta
				for (MovimientoEstadoCuenta movimiento : movimientos) {
					movimiento.setIdEstadoCuenta(estadoCuenta.getId());
				}

				LOG.info(">> Insertando movimientos Estado de cuenta...");
				//movimientoEstadoCuentaRepository.saveAll(movimientos);
				movimientoJdbcRepository.insertarLista(movimientos);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al persistir los movimientos del archivo del estado de cuenta",
					CodigoError.NMP_PMIMONTE_BUSINESS_049);
		}
	}


	/**
	 * Distribuye los movimientos de estado de cuenta entre las conciliaciones pendientes
	 * @param reporte
	 * @param folios
	 * @param userRequest
	 */
	private void distribuirEstadoCuenta(Reporte reporte, List<Long> folios, String userRequest) {

		// Obtiene los movimientos de estado de cuenta para la fecha indicada
		List<MovimientoEstadoCuenta> movsEdoCuenta = this.movimientoEstadoCuentaRepository.findByReporte(reporte.getId());

		// Por cada conciliacion se asignan los movimientos correspondientes a la fecha
		for (Long idConciliacion : folios) {

			// Obtiene la fecha de la conciliacion
			Conciliacion conciliacion = this.conciliacionHelper.getConciliacionByFolio(idConciliacion, null);
			Date fechaConciliacion = conciliacion.getCreatedDate();

			// Se obtienen los movs de estado de cuenta para la conciliacion basadonos en la fecha
			List<MovimientoEstadoCuenta> movsConciliacion = movsEdoCuenta.stream()
					.filter(mov -> DateUtil.isSameDay(mov.getFechaOperacion(), fechaConciliacion))
					.collect(Collectors.toList());

			if (movsConciliacion != null && movsConciliacion.size() > 0) {
				// Se crea el reporte para el estado de cuenta y se asocia a la conciliacion
				Reporte reporteConciliacion = crearReporteConciliacion(conciliacion, userRequest);
	
				// Si existen movimientos del estado de cuenta para la fecha se persisten
				crearEdoCuentaConciliacion(reporteConciliacion, movsConciliacion);

				// TODO: Se ejecuta el merge y se actualiza el subestatus de la conciliacion

				// Registro de actividad
				actividadGenericMethod.registroActividadV2(objectsInSession.getFolioByIdConciliacion(idConciliacion),
						"Se distribuyo el estado de cuenta para la conciliacion con el folio " + idConciliacion,
						TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.MOVIMIENTOS);
			}
		}
	}


	/**
	 * Crea un reporte para el estado de cuenta para la conciliacion 
	 * @param conciliacion
	 * @param userRequest
	 * @return
	 */
	private Reporte crearReporteConciliacion(Conciliacion conciliacion, String userRequest) throws ConciliacionException {
		Reporte reporte = null;
		try {
			SaveEstadoCuentaRequestDTO saveEstadoCuentaRequestDTO = new SaveEstadoCuentaRequestDTO();
			saveEstadoCuentaRequestDTO.setFechaFinal(conciliacion.getCreatedDate());
			saveEstadoCuentaRequestDTO.setFechaInicial(conciliacion.getCreatedDate());
			saveEstadoCuentaRequestDTO.setFolio(conciliacion.getId());
			reporte = save(saveEstadoCuentaRequestDTO, userRequest);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al guardar el reporte asociativo para la conciliacion " + conciliacion.getId());
		}
		return reporte;
	}


	/**
	 * Crea el estado de cuenta y asocia los movimientos correspondientes a la conciliacion
	 * @param reporteEdoCuenta
	 * @param movsEdoCuentaOrigen 
	 */
	private void crearEdoCuentaConciliacion(Reporte reporteEdoCuenta, List<MovimientoEstadoCuenta> movsEdoCuentaOrigen) throws ConciliacionException {

		// Crea el estado cuenta
		EstadoCuenta estadoCuenta = new EstadoCuenta();
		try {
			estadoCuenta.setIdReporte(Long.valueOf(reporteEdoCuenta.getId()));
			estadoCuenta.setTotalMovimientos(movsEdoCuentaOrigen.size());
			estadoCuenta.setCabecera(null);
			estadoCuenta.setTotales(null);
			estadoCuenta.setTotalesAdicional(null);
			estadoCuenta.setFechaCarga(reporteEdoCuenta.getCreatedDate());
			estadoCuenta = estadoCuentaRepository.save(estadoCuenta);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al guardar el estado de cuenta asociativo para la conciliacion " + reporteEdoCuenta.getId());
		}

		// Se crean los movs estado de cuenta y
		// Se asocia el id del estado de cuenta
		try {
			List<MovimientoEstadoCuenta> movsEdoCuenta = MovimientosBuilder.clonarMovsEdoCuenta(movsEdoCuentaOrigen, estadoCuenta.getId());
			LOG.info(">> Insertando movimientos Estado de cuenta...");
			movimientoJdbcRepository.insertarLista(movsEdoCuenta);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al persistir los movimientos del estado de cuenta asociativo para la conciliacion " + reporteEdoCuenta.getId());
		}
	}

}