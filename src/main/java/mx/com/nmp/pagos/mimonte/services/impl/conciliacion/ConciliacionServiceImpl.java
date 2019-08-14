/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import mx.com.nmp.pagos.mimonte.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.icu.util.Calendar;

import mx.com.nmp.pagos.mimonte.ActividadGenericMethod;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.ComisionesBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.ConciliacionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.EstatusConciliacionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.GlobalBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientoComisionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientoDevolucionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosTransitoBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.SubEstatusConciliacionBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.CuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ActividadPaginRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ActividadRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstatusConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.GlobalRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoComisionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoDevolucionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoTransitoRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ReporteRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.SubEstatusConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizaionConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarIdPSRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarSubEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTOList;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionResponseSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadesRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Global;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubEstatusConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubTipoActividadEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoActividadEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoActualizacionTransito;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.LayoutsService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.SolicitarPagosService;
//import mx.com.nmp.pagos.mimonte.util.CollectionUtil;
import mx.com.nmp.pagos.mimonte.util.ConciliacionDataValidator;
import mx.com.nmp.pagos.mimonte.util.FechasUtil;
import mx.com.nmp.pagos.mimonte.util.MiniMaquinaEstadosConciliacion;
import mx.com.nmp.pagos.mimonte.util.StringUtil;

/**
 * @name ConciliacionServiceImpl
 * @description Clase de capa de servicio para la conciliacion que sirve para
 *              realizar operaciones de logica de negocios
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/05/2019 17:48 hrs.
 * @version 0.1
 */
@Service("conciliacionServiceImpl")
public class ConciliacionServiceImpl implements ConciliacionService {

	/**
	 * Logger para registro para registro de actividad.
	 */
	private final Logger log = LoggerFactory.getLogger(ConciliacionServiceImpl.class);

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private EntidadRepository entidadRepository;

	@Autowired
	private EstatusConciliacionRepository estatusConciliacionRepository;

	@Autowired
	private ConciliacionRepository conciliacionRepository;

	@Autowired
	private MovimientoComisionRepository movimientoComisionRepository;

	@Autowired
	private MovimientoTransitoRepository movimientoTransitoRepository;

	@Autowired
	private ReporteRepository reporteRepository;

	@Autowired
	private MovimientoDevolucionRepository movimientoDevolucionRepository;

	@Autowired
	private SubEstatusConciliacionRepository subEstatusConciliacionRepository;

	@Autowired
	private GlobalRepository globalRepository;

	@Autowired
	private ConciliacionHelper conciliacionHelper;

	@Autowired
	private LayoutsService layoutsService;

	@Autowired
	private ConciliacionDataValidator conciliacionDataValidator;

	/**
	 * Mini maquina de estados para actualizacion de subestatus de conciliacion
	 */
	@Autowired
	private MiniMaquinaEstadosConciliacion miniMaquinaEstadosConciliacion;

	/**
	 * Repository de Actividad
	 */
	@Autowired
	@Qualifier("actividadRepository")
	private ActividadRepository actividadRepository;

	/**
	 * Repository de Actividad con paginacion
	 */
	@Autowired
	@Qualifier("actividadPaginRepository")
	private ActividadPaginRepository actividadPaginRepository;

	/**
	 * Repository de devoluciones
	 */
	@Autowired
	private DevolucionesService devolucionesService;

	/**
	 * Repository para la insercion de movimientos pago
	 */
	@Autowired
	@Qualifier("solicitarPagosService")
	private SolicitarPagosService solicitarPagosService;

	/**
	 * Registro de actividades
	 */
	@Autowired
	private ActividadGenericMethod actividadGenericMethod;

	/**
	 * Valor maximo por default para resultados de actividades cuando no se
	 * especifica folio
	 */
	@Value("${mimonte.variables.actividades-max-default}")
	private Integer actividadesMaxDefaultValue;

	/**
	 * Metodo que da de alta una conciliación regresando un folio a partir de un
	 * objeto de tipo ConciliacionResponseSaveDTO
	 */
	@Override
	@Transactional
	public ConciliacionDTO saveConciliacion(ConciliacionResponseSaveDTO conciliacionRequestDTO, String createdBy) {

		// Validación del objeto ConciliacionRequestDTO
		if (conciliacionRequestDTO.getCuenta() == null || conciliacionRequestDTO.getCuenta().getId() == null
				|| conciliacionRequestDTO.getCuenta().getId() < 1) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		}

		if (conciliacionRequestDTO.getEntidad() == null || conciliacionRequestDTO.getEntidad().getId() == null
				|| conciliacionRequestDTO.getEntidad().getId() < 1) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		}

		// Validación del atributo createdBy
		if (StringUtils.isBlank(createdBy)) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		}

		// Búsqueda y validacion del idCuenta.
		Optional<Cuenta> cuenta = cuentaRepository.findById(conciliacionRequestDTO.getCuenta().getId());
		if (!cuenta.isPresent()) {
			throw new ConciliacionException("La cuenta especificada no existe", CodigoError.NMP_PMIMONTE_BUSINESS_021);
		}

		// Búsqueda y validación del idEntidad.
		Optional<Entidad> entidad = entidadRepository.findById(conciliacionRequestDTO.getEntidad().getId());
		if (!entidad.isPresent()) {
			throw new ConciliacionException("La entidad especificada no existe", CodigoError.NMP_PMIMONTE_BUSINESS_022);
		}

		// Valida si la entidad y la cuenta estan relacionadas
		boolean flag = ((BigInteger) conciliacionRepository.checkCuentaEntidadRelationship(
				conciliacionRequestDTO.getCuenta().getId(), conciliacionRequestDTO.getEntidad().getId()))
						.compareTo(BigInteger.ONE) == 0;
		if (!flag)
			throw new ConciliacionException(ConciliacionConstants.THERE_IS_NO_SUCH_CUENTA_ENTIDAD_RELATIONSHIP,
					CodigoError.NMP_PMIMONTE_BUSINESS_023);

		// Se obtiene el estatus y el sub estatus de la conciliacion
		Optional<EstatusConciliacion> estatusConciliacion = estatusConciliacionRepository
				.findById(ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);
		if (!estatusConciliacion.isPresent()) {
			throw new ConciliacionException("Estatus conciliacion en proceso no encontrado",
					CodigoError.NMP_PMIMONTE_BUSINESS_024);
		}
		Optional<SubEstatusConciliacion> subEstatusConciliacion = subEstatusConciliacionRepository
				.findById(ConciliacionConstants.SUBESTATUS_CONCILIACION_CREADA);
		if (!subEstatusConciliacion.isPresent()) {
			throw new ConciliacionException("Sub estatus conciliacion creada no encontrado",
					CodigoError.NMP_PMIMONTE_BUSINESS_025);
		}
		conciliacionRequestDTO.setEstatus(EstatusConciliacionBuilder
				.buildEstatusConciliacionDTOFromEstatusConciliacion(estatusConciliacion.get()));
		conciliacionRequestDTO.setSubEstatus(SubEstatusConciliacionBuilder
				.buildSubEstatusConciliacionDTOFromSubEstatusConciliacion(subEstatusConciliacion.get()));

		// Se valida que la conciliacion no exista
		Conciliacion conciliacionBD = conciliacionRepository
				.findByEntidadIdAndCuentaIdAndCreatedDate(entidad.get().getId(), cuenta.get().getId(), new Date());
		if (conciliacionBD != null) {
			throw new ConciliacionException("Ya existe una conciliacion para la entidad, cuenta para la fecha actual",
					CodigoError.NMP_PMIMONTE_BUSINESS_026);
		}

		log.debug("Creando conciliacion...");

		// Se construye la conciliacion y se guarda
		Conciliacion conciliacion = ConciliacionBuilder
				.buildConciliacionFromConciliacionResponseSaveDTO(conciliacionRequestDTO);
		conciliacion = conciliacionRepository.save(conciliacion);

		// Se crea el objeto global vacio
		Global global = GlobalBuilder.buildGlobalDTOFromConciliacion(conciliacion);
		globalRepository.save(global);

		// Registro de actividad
		actividadGenericMethod.registroActividad(conciliacion.getId(), "Se creo la conciliacion con el folio "
				+ conciliacion.getId() + " para la entidad "
				+ (entidad.isPresent() && null != entidad.get().getNombre() ? entidad.get().getNombre() : "")
				+ ", y la cuenta "
				+ (cuenta.isPresent() && null != cuenta.get().getNumeroCuenta() ? cuenta.get().getNumeroCuenta() : ""),
				TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.GENERACION_CONCILIACION);

		return ConciliacionBuilder.buildConciliacionDTOFromConciliacionCuentaAndEntidad(conciliacion, cuenta.get(),
				entidad.get());

	}

	/**
	 * Metodo que actualiza la informacion de los movimientos en transito y
	 * movimientos de la comisión.
	 */
	@Override
	@Transactional
	public ActualizaionConciliacionRequestDTO actualizaConciliacion(
			ActualizaionConciliacionRequestDTO actualizaionConciliacionRequestDTO, final String requestUser) {
		// OBJETOS NECESARIOS
		Map<Integer, ComisionesRequestDTO> mapC = null;
		List<Integer> idsMov = null;
		List<Integer> idsCom = null;
		List<Integer> idsPagos = null;
		List<Integer> idsDevoluciones = null;
		Conciliacion conciliacion = null;
		Boolean flag = null;

		// VALIDA QUE FOLIO DE CONCILIACION EXISTA
		conciliacionDataValidator.validateFolioExists(actualizaionConciliacionRequestDTO.getFolio());

		// Valida que los ids de movimientos transito existan (si es que hay
		// movimientos transito) y que sean de un tipo valido (nuevo = 1)
		try {
			if (null != actualizaionConciliacionRequestDTO.getMovimientosTransito()
					&& !actualizaionConciliacionRequestDTO.getMovimientosTransito().isEmpty()) {
				idsMov = MovimientosBuilder.buildIntegerListFromMovTransitoRequestDTOList(
						actualizaionConciliacionRequestDTO.getMovimientosTransito());
				flag = ((BigInteger) movimientoTransitoRepository.checkIfIdsExist(idsMov, idsMov.size()))
						.compareTo(BigInteger.ONE) == 0;
				if (!flag) {
					throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_100.getDescripcion(),
							CodigoError.NMP_PMIMONTE_BUSINESS_100);
				}
			}
		} catch (ConciliacionException ex) {
			throw ex;
		} catch (Exception ex) {
			log.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_038.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_038);
		}

		// Valida que haya relacion entre el folio de conciliacion y los movimientos
		// transito especificados
		if (null != actualizaionConciliacionRequestDTO.getMovimientosTransito()
				&& !actualizaionConciliacionRequestDTO.getMovimientosTransito().isEmpty()) {
			flag = null;
			try {
				flag = ((BigInteger) movimientoTransitoRepository.checkIdsAndFolioRelationship(
						actualizaionConciliacionRequestDTO.getFolio(), idsMov, idsMov.size()))
								.compareTo(BigInteger.ONE) == 0;
				if (!flag) {
					throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_111.getDescripcion(),
							CodigoError.NMP_PMIMONTE_BUSINESS_111);
				}
			} catch (ConciliacionException ex) {
				log.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
				throw ex;
			} catch (Exception ex) {
				log.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_038.getDescripcion(),
						CodigoError.NMP_PMIMONTE_BUSINESS_038);
			}
		}

		// Valida que los movimientos comision existan previamente (si es que se
		// recibieron como parametro)
		flag = null;
		try {
			if (null != actualizaionConciliacionRequestDTO.getComisiones()
					&& !actualizaionConciliacionRequestDTO.getComisiones().isEmpty()) {
				idsCom = MovimientosBuilder.buildIntegerListFromComisionesRequestDTONO0List(
						actualizaionConciliacionRequestDTO.getComisiones());
				if (null == idsCom || idsCom.size() == 0)
					flag = true;
				else
					flag = ((BigInteger) movimientoComisionRepository.checkIfIdsExist(idsCom, idsCom.size()))
							.compareTo(BigInteger.ONE) == 0;
				if (!flag) {
					throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_102.getDescripcion(),
							CodigoError.NMP_PMIMONTE_BUSINESS_102);
				}
			}
		} catch (ConciliacionException ex) {
			throw ex;
		} catch (Exception ex) {
			log.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_103.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_103);
		}

		// Valida que haya relacion entre el folio de conciliacion y los movimientos
		// comision especificados
		if (null != actualizaionConciliacionRequestDTO.getComisiones()
				&& !actualizaionConciliacionRequestDTO.getComisiones().isEmpty()) {
			flag = null;
			try {
				if (null == idsCom || idsCom.isEmpty())
					flag = true;
				else
					flag = ((BigInteger) movimientoComisionRepository.checkIdsAndFolioRelationship(
							actualizaionConciliacionRequestDTO.getFolio(), idsCom, idsCom.size()))
									.compareTo(BigInteger.ONE) == 0;
				if (!flag) {
					throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_109.getDescripcion(),
							CodigoError.NMP_PMIMONTE_BUSINESS_109);
				}
			} catch (ConciliacionException ex) {
				log.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
				throw ex;
			} catch (Exception ex) {
				log.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_103.getDescripcion(),
						CodigoError.NMP_PMIMONTE_BUSINESS_103);
			}
		}

		// Obtiene la conciliacion para registrar la actividad
		try {
			conciliacion = conciliacionHelper.getConciliacionByFolio(actualizaionConciliacionRequestDTO.getFolio(),
					ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);
		} catch (Exception ex) {
			log.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_094.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_094);
		}

		// ACTUALIZA LOS MOVIMIENTOS EN TRANSITO
		if (null != actualizaionConciliacionRequestDTO.getMovimientosTransito()
				&& !actualizaionConciliacionRequestDTO.getMovimientosTransito().isEmpty()) {
			idsPagos = new ArrayList<>();
			idsDevoluciones = new ArrayList<>();
			try {
				// Clasifica los movimientos en dos listas distintas dependiendo del tipo
				for (MovTransitoRequestDTO movimiento : actualizaionConciliacionRequestDTO.getMovimientosTransito()) {
					if (TipoMovimientoActualizacionTransito.PAGOS.getNombre().equals(movimiento.getTipo()))
						idsPagos.add(movimiento.getId());
					else if (TipoMovimientoActualizacionTransito.DEVOLUCIONES.getNombre().equals(movimiento.getTipo()))
						idsDevoluciones.add(movimiento.getId());
				}
				// ACTUALIZA LOS MOVIMIENTOS TRANSITO A SUS NUEVOS TIPOS
				// Actualiza a movimientos pagos
				if (!idsPagos.isEmpty()) {
					solicitarPagosService.solicitarPagos(
							new SolicitarPagosRequestDTO(actualizaionConciliacionRequestDTO.getFolio(), idsPagos),
							requestUser);
				movimientoComisionRepository.flush();	
				}
				// Actualiza a movimientos devolucion
				if (!idsDevoluciones.isEmpty()) {
					devolucionesService.marcarDevolucion(new SolicitarPagosRequestDTO(
							actualizaionConciliacionRequestDTO.getFolio(), idsDevoluciones), requestUser);
					movimientoComisionRepository.flush();
				}
			} catch (ConciliacionException ex) {
				log.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
				throw ex;
			} catch (Exception ex) {
				log.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_039.getDescripcion(),
						CodigoError.NMP_PMIMONTE_BUSINESS_039);
			}
		}

		// GUARDA O ACTUALIZA LAS COMISIONES
		if (null != actualizaionConciliacionRequestDTO.getComisiones()
				&& !actualizaionConciliacionRequestDTO.getComisiones().isEmpty()) {
			try {
				mapC = new HashMap<>();
				for (ComisionesRequestDTO movimientoComision : actualizaionConciliacionRequestDTO.getComisiones()) {
					mapC.put(movimientoComision.getId(), movimientoComision);
				}
				// Se obtienen los movimientos de comision a atualizar
				List<MovimientoComision> movimientosComision = null;
				if (null != idsCom && !idsCom.isEmpty())
					movimientosComision = movimientoComisionRepository.findByIds(idsCom);

				// Se iteran y se les asignan los atributos actualizados
				if (null != movimientosComision && !movimientosComision.isEmpty()) {
					for (MovimientoComision mC : movimientosComision) {
						mC.setDescripcion(mapC.get(mC.getId()).getDescripcion());
						mC.setEstatus(mapC.get(mC.getId()).getEstatus());
						mC.setFechaOperacion(mapC.get(mC.getId()).getFechaOperacion());
						mC.setFechaCargo(mapC.get(mC.getId()).getFechaCargo());
						mC.setMonto(mapC.get(mC.getId()).getMonto());
					}
				} else if (null == movimientosComision)
					movimientosComision = new ArrayList<>();
				// Se agregan a la lista los nuevos movimientos de comision
				for (Map.Entry<Integer, ComisionesRequestDTO> entry : mapC.entrySet()) {
					ComisionesRequestDTO comisionesRequestDTO = entry.getValue();
					if (comisionesRequestDTO.getId().compareTo(0) == 0) {
						movimientosComision.add(ComisionesBuilder.buildMovimientoComisionFromComisionesRequestDTO(
								comisionesRequestDTO, requestUser, actualizaionConciliacionRequestDTO.getFolio()));
					}
				}
				// Se aguardan todos los movimientos comision (nuevos y actualizados)
				movimientoComisionRepository.saveAll(movimientosComision);
				movimientoComisionRepository.flush();
			} catch (Exception ex) {
				log.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_101.getDescripcion(),
						CodigoError.NMP_PMIMONTE_BUSINESS_101);
			}
		}
		// REGISTRO DE ACTIVIDAD
		try {
			actividadGenericMethod.registroActividad(conciliacion.getId(),
					"Se actualiza la conciliacion con el folio " + conciliacion.getId() + " con "
							+ (null != actualizaionConciliacionRequestDTO.getMovimientosTransito()
									? actualizaionConciliacionRequestDTO.getMovimientosTransito().size()
									: 0)
							+ " movimientos transito y "
							+ (null != actualizaionConciliacionRequestDTO.getComisiones()
									? actualizaionConciliacionRequestDTO.getComisiones().size()
									: 0)
							+ " comisiones",
					TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.ACTUALIZAR_CONCILIACION);
		} catch (Exception ex) {
			log.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_0012.getDescripcion(),
					CodigoError.NMP_PMIMONTE_0012);
		}

		// RETORNO DE RESPUESTA
		return actualizaionConciliacionRequestDTO;
	}

	/**
	 * Metodo que realiza una busqueda a partir de un objeto de tipo
	 * ConsultaConciliacionRequestDTO devolviendo como resultado una lista de tipo
	 * ConsultaConciliacionDTO.
	 */
	@Override
	public List<ConsultaConciliacionDTO> consulta(ConsultaConciliacionRequestDTO consultaConciliacionRequestDTO) {

		// Declaracion de objetos necesarios
		List<ConsultaConciliacionDTO> result = null;
		Optional<Entidad> entidad = null;
		Optional<EstatusConciliacion> estatusConciliacion = null;

		// Ajuste de fechas para filtros
		if (null == consultaConciliacionRequestDTO.getFechaDesde()
				&& null != consultaConciliacionRequestDTO.getFechaHasta()) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 1975);
			cal.set(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			consultaConciliacionRequestDTO.setFechaDesde(cal.getTime());
		}
		if (null != consultaConciliacionRequestDTO.getFechaDesde()
				&& null == consultaConciliacionRequestDTO.getFechaHasta()) {
			Calendar cal = Calendar.getInstance();
			consultaConciliacionRequestDTO.setFechaHasta(cal.getTime());
		}
		if (null != consultaConciliacionRequestDTO.getFechaDesde()
				&& null != consultaConciliacionRequestDTO.getFechaHasta()) {
			Calendar ini = Calendar.getInstance();
			Calendar fin = Calendar.getInstance();
			ini.setTime(consultaConciliacionRequestDTO.getFechaDesde());
			fin.setTime(consultaConciliacionRequestDTO.getFechaHasta());
			ini.set(Calendar.HOUR_OF_DAY, 0);
			ini.set(Calendar.MINUTE, 0);
			ini.set(Calendar.SECOND, 0);
			ini.set(Calendar.MILLISECOND, 0);
			fin.set(Calendar.HOUR_OF_DAY, 23);
			fin.set(Calendar.MINUTE, 59);
			fin.set(Calendar.SECOND, 59);
			fin.set(Calendar.MILLISECOND, 59);
			consultaConciliacionRequestDTO.setFechaDesde(ini.getTime());
			consultaConciliacionRequestDTO.setFechaHasta(fin.getTime());
		}

		// Valida que el folio especificado exista
		if (null != consultaConciliacionRequestDTO && null != consultaConciliacionRequestDTO.getFolio())
			conciliacionDataValidator.validateFolioExists(consultaConciliacionRequestDTO.getFolio());

		// Valida que la entidad especificada exista
		if (null != consultaConciliacionRequestDTO && null != consultaConciliacionRequestDTO.getIdEntidad()) {
			entidad = entidadRepository.findById(consultaConciliacionRequestDTO.getIdEntidad());
			if (!entidad.isPresent())
				throw new ConciliacionException(CatalogConstants.NO_ENTIDAD_FOUND,
						CodigoError.NMP_PMIMONTE_BUSINESS_060);
		}

		// Valida que el id de estatus de conciliacion especificado exista
		if (null != consultaConciliacionRequestDTO && null != consultaConciliacionRequestDTO.getIdEstatus()) {
			estatusConciliacion = estatusConciliacionRepository.findById(consultaConciliacionRequestDTO.getIdEstatus());
			if (!estatusConciliacion.isPresent())
				throw new ConciliacionException(ConciliacionConstants.ESTATUS_CONCILIACION_DOESNT_EXISTS,
						CodigoError.NMP_PMIMONTE_BUSINESS_091);
		}

		// TODO: (iaguilar) Validar si quitar esta parte dado que estas fechas se
		// validan en la capa de controlador
		// Validación de la fecha final no sea menor que la fecha inicial.
//		if (consultaConciliacionRequestDTO.getFechaDesde() != null
//				&& consultaConciliacionRequestDTO.getFechaHasta() != null) {
//			if (consultaConciliacionRequestDTO.getFechaHasta().before(consultaConciliacionRequestDTO.getFechaDesde()))
//				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
//						CodigoError.NMP_PMIMONTE_0008);
//		}

		if (null != consultaConciliacionRequestDTO.getFechaDesde()
				&& null != consultaConciliacionRequestDTO.getFechaHasta()) {
			result = ConciliacionBuilder.buildConsultaConciliacionDTOListFromConciliacionList(
					conciliacionRepository.findByFolioAndIdEntidadAndIdEstatusAndFechas(
							consultaConciliacionRequestDTO.getFolio(), consultaConciliacionRequestDTO.getIdEntidad(),
							consultaConciliacionRequestDTO.getIdEstatus(),
							consultaConciliacionRequestDTO.getFechaDesde(),
							consultaConciliacionRequestDTO.getFechaHasta()));
		} else if (null != consultaConciliacionRequestDTO.getFechaDesde()
				&& null == consultaConciliacionRequestDTO.getFechaHasta()) {
			result = ConciliacionBuilder.buildConsultaConciliacionDTOListFromConciliacionList(
					conciliacionRepository.findByFolioAndIdEntidadAndIdEstatusAndFechaDesde(
							consultaConciliacionRequestDTO.getFolio(), consultaConciliacionRequestDTO.getIdEntidad(),
							consultaConciliacionRequestDTO.getIdEstatus(),
							consultaConciliacionRequestDTO.getFechaDesde()));
		} else if (null == consultaConciliacionRequestDTO.getFechaDesde()
				&& null != consultaConciliacionRequestDTO.getFechaHasta()) {
			result = ConciliacionBuilder.buildConsultaConciliacionDTOListFromConciliacionList(
					conciliacionRepository.findByFolioAndIdEntidadAndIdEstatusAndFechaHasta(
							consultaConciliacionRequestDTO.getFolio(), consultaConciliacionRequestDTO.getIdEntidad(),
							consultaConciliacionRequestDTO.getIdEstatus(),
							consultaConciliacionRequestDTO.getFechaHasta()));
		} else {
			result = ConciliacionBuilder.buildConsultaConciliacionDTOListFromConciliacionList(
					conciliacionRepository.findByFolioAndIdEntidadAndIdEstatus(
							consultaConciliacionRequestDTO.getFolio(), consultaConciliacionRequestDTO.getIdEntidad(),
							consultaConciliacionRequestDTO.getIdEstatus()));
		}

		return result;
	}

	/**
	 * Metodo que consulta a partir del folio la conciliacion con los siguientes
	 * objetos: el estatus de la conciliacion, el sub estatus de la conciliacion, la
	 * entidad, la cuenta, reportes (Midas, Proveedor y estado de cuenta), global,
	 * devoluciones, movimientos en transito y comisiones
	 */
	@Override
	public ConciliacionDTOList consultaFolio(Long folio) {

		// Validación del folio en el request.
		if (folio == null || folio < 1)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		// Búsqueda de la conciliación a partir del folio.
		Conciliacion conciliacion = conciliacionHelper.getConciliacionByFolio(folio, null);

		// Búsqueda de los movimientos en devolución a partir del folio
		List<MovimientoDevolucion> mD = movimientoDevolucionRepository.findByIdConciliacion(folio);

		// Búsqueda de los movimientos en transito a partir del folio.
		List<MovimientoTransito> mT = movimientoTransitoRepository.findByIdConciliacion(folio);

		// Búsqueda de los movimientos en comisión a partir del folio.
		List<MovimientoComision> mC = movimientoComisionRepository.findByIdConciliacion(folio);

		// Búsqueda de los reporte a partir del folio.
		List<Reporte> reportes = reporteRepository.findByIdConciliacion(folio);

		return ConciliacionBuilder.buildConciliacionDTOListFromConciliacion(conciliacion, reportes,
				MovimientoDevolucionBuilder.buildDevolucionConDTOListFromMovimientoDevolucionList(mD),
				MovimientosTransitoBuilder.buildMovTransitoDTOListFromMovimientoTransitoList(mT),
				MovimientoComisionBuilder.buildComisionesDTOListFromMovimientoComisionList(mC));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService#getById(
	 * java.lang.Long)
	 */
	public Conciliacion getById(Long idConciliacion) {
		return conciliacionHelper.getConciliacionByFolio(idConciliacion, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService#
	 * enviarConciliacion(java.lang.Long, java.lang.String)
	 */
	@Override
	@Transactional
	public void enviarConciliacion(Long idConciliacion, String usuario) {
		// Validar conciliacion
		Conciliacion conciliacion = conciliacionHelper.getConciliacionByFolio(idConciliacion,
				ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);

		// Se valida que exista la conciliacion con el folio proporcionado
		conciliacionDataValidator.validateFolioExists(idConciliacion);

		// Valida que la conciliacion tenga el estatus correcto para poder dar de alta
		// el estado cuenta
		conciliacionDataValidator.validateSubEstatusByFolioAndSubEstatus(idConciliacion,
				Arrays.asList(ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_ESTADO_DE_CUENTA_COMPLETADA));

		// Validar conciliacion y actualizar estatus
		try {

			// Se mueven los movimientos de transito a movimientos pago antes de generar los
			// layouts
			solicitarPagosService.insertaMovimientosPagoFinal(idConciliacion, usuario);

			// Se crean los layouts correspondientes
			layoutsService.enviarConciliacion(idConciliacion, usuario);

			// Se actualiza el sub estatus a enviada
			conciliacion
					.setSubEstatus(new SubEstatusConciliacion(ConciliacionConstants.SUBESTATUS_CONCILIACION_ENVIADA));
			conciliacion.setLastModifiedBy(usuario);
			conciliacion.setLastModifiedDate(new Date());
			conciliacionRepository.save(conciliacion);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al actualizar los ids de asiento contable",
					CodigoError.NMP_PMIMONTE_BUSINESS_031);
		}

	}

	/**
	 * Actualiza el sub estatus de una conciliacion por folio
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void actualizaSubEstatusConciliacion(ActualizarSubEstatusRequestDTO actualizarSubEstatusRequestDTO,
			String usuario) {
		Boolean subEstatusValido = null;
		// Se valida si la conciliacion existe
		Optional<Conciliacion> conciliaicion = conciliacionRepository
				.findById(actualizarSubEstatusRequestDTO.getFolio());
		if (!conciliaicion.isPresent())
			throw new ConciliacionException(ConciliacionConstants.CONCILIACION_ID_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_BUSINESS_045);
		// Se obtienen: El id del estatus conciliacion el orden del mismo y el roden del
		// subestatus de acuerdo al id de subestatus especificado como parametro
		// mediante un query nativo
		Map<String, Object> map = conciliacionRepository
				.findIdEstatusConciliacion(actualizarSubEstatusRequestDTO.getIdSubEstatus());
		if (null == map || null == map.get("estatus"))
			throw new ConciliacionException(ConciliacionConstants.NO_STATUS_FOR_SUCH_SUB_STATUS,
					CodigoError.NMP_PMIMONTE_BUSINESS_027);
		// Se obtienen: el id del estatus conciliacion, el orden del mismo y el orden
		// del subestatus por folio de conciliacion mediante un query nativo
		Map<String, Object> currenOrders = conciliacionRepository
				.findOrderSubstatusAndStatusByFolio(actualizarSubEstatusRequestDTO.getFolio());
		if (null == currenOrders || null == currenOrders.get("sub_estatus_order"))
			throw new ConciliacionException(ConciliacionConstants.ERROR_GETTING_CURRENT_SUB_STATUS,
					CodigoError.NMP_PMIMONTE_BUSINESS_028);
		// Se valida que el orden del estatus actual no sea mayor al orden dele status
		// que se va a actualizar
		if (Integer.parseInt(currenOrders.get("estatus_order").toString()) > Integer
				.parseInt(map.get("estatus_order").toString()))
			throw new ConciliacionException(ConciliacionConstants.WRONG_ORDER_SUB_STATUS,
					CodigoError.NMP_PMIMONTE_BUSINESS_029);

		// Se valida que el estatus al que se quiere actualizar sea correcto en base a
		// la maquina de estados de sub-estatus conciliacion
		subEstatusValido = miniMaquinaEstadosConciliacion.checkIfSubEstatusIsRightByFolioAnfIdSubEstatus(
				actualizarSubEstatusRequestDTO.getFolio(), actualizarSubEstatusRequestDTO.getIdSubEstatus());
		if (null != subEstatusValido && !subEstatusValido)
			throw new ConciliacionException(ConciliacionConstants.WRONG_ORDER_SUB_STATUS,
					CodigoError.NMP_PMIMONTE_BUSINESS_029);
		else if (null == subEstatusValido)
			throw new ConciliacionException(ConciliacionConstants.ERROR_WHILE_VALIDATING_SUB_ESTAUS,
					CodigoError.NMP_PMIMONTE_BUSINESS_083);

		// Se actualiza el sub estatus de la conciliacion al que se recibio como
		// parametro, adicionalmente se actualizan los campos createdBy y createdDate
		conciliacionRepository.actualizaSubEstatusConciliacion(actualizarSubEstatusRequestDTO.getFolio(),
				new SubEstatusConciliacion(actualizarSubEstatusRequestDTO.getIdSubEstatus()), usuario, new Date(),
				new EstatusConciliacion(Integer.parseInt(map.get("estatus").toString())),
				actualizarSubEstatusRequestDTO.getDescripcion());

		// Se obtienen los datos del subestatus para el registro de actividades
		Optional<SubEstatusConciliacion> subEstatus = subEstatusConciliacionRepository
				.findById(actualizarSubEstatusRequestDTO.getIdSubEstatus());

		// Registro de actividad
		actividadGenericMethod.registroActividad(actualizarSubEstatusRequestDTO.getFolio(),
				"Se actualizo el sub-estado de la conciliacion con el folio "
						+ actualizarSubEstatusRequestDTO.getFolio() + " a: "
						+ (subEstatus.isPresent() && null != subEstatus.get().getDescription()
								? subEstatus.get().getDescription()
								: actualizarSubEstatusRequestDTO.getIdSubEstatus()),
				TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.ACTUALIZACION_ESTATUS_CONCILIACION);
	}

	/**
	 * Regresa un objeto con el resumen de totoal de conciliaciones en proceso, el
	 * total de devoluciones liquidadas y el total de conciliaciones, puede recibir
	 * fechas inicial y final como parametros
	 */
	@Override
	public ResumenConciliacionDTO resumenConciliaciones(ResumenConciliacionRequestDTO resumenConciliacionRequestDTO) {
		Map<String, BigInteger> res = null;
		ResumenConciliacionDTO resumenConciliacionDTO = null;
		// Se compara si la fecha inicial y final son nulas para plicar una consulta sin
		// argumentos de rango de fechas o de lo contrario aplicar una consulta con
		// argumentos de rango de fechas
		if (null == resumenConciliacionRequestDTO.getFechaInicial()
				&& null != resumenConciliacionRequestDTO.getFechaFinal()) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 1975);
			cal.set(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			resumenConciliacionRequestDTO.setFechaInicial(cal.getTime());
		}
		if (null != resumenConciliacionRequestDTO.getFechaInicial()
				&& null == resumenConciliacionRequestDTO.getFechaFinal()) {
			Calendar cal = Calendar.getInstance();
			resumenConciliacionRequestDTO.setFechaFinal(cal.getTime());
		}
		if (null != resumenConciliacionRequestDTO.getFechaInicial()
				&& null != resumenConciliacionRequestDTO.getFechaFinal()) {
			Calendar ini = Calendar.getInstance();
			Calendar fin = Calendar.getInstance();
			ini.setTime(resumenConciliacionRequestDTO.getFechaInicial());
			fin.setTime(resumenConciliacionRequestDTO.getFechaFinal());
			ini.set(Calendar.HOUR_OF_DAY, 0);
			ini.set(Calendar.MINUTE, 0);
			ini.set(Calendar.SECOND, 0);
			ini.set(Calendar.MILLISECOND, 0);
			fin.set(Calendar.HOUR_OF_DAY, 23);
			fin.set(Calendar.MINUTE, 59);
			fin.set(Calendar.SECOND, 59);
			fin.set(Calendar.MILLISECOND, 59);
			resumenConciliacionRequestDTO.setFechaInicial(ini.getTime());
			resumenConciliacionRequestDTO.setFechaFinal(fin.getTime());
			res = conciliacionRepository.resumenConciliaciones(resumenConciliacionRequestDTO.getFechaInicial(),
					resumenConciliacionRequestDTO.getFechaFinal(),
					ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO,
					ConciliacionConstants.ESTATUS_DEVOLUCION_LIQUIDADA);
		} else {
			res = conciliacionRepository.resumenConciliaciones(ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO,
					ConciliacionConstants.ESTATUS_DEVOLUCION_LIQUIDADA);
		}
		if (null != res && !res.isEmpty())
			resumenConciliacionDTO = new ResumenConciliacionDTO(res.get("en_proceso").longValue(),
					res.get("dev_liquidadas").longValue(), res.get("conc_totales").longValue());
		if (null == resumenConciliacionDTO)
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0009);
		return resumenConciliacionDTO;
	}

	/**
	 * Consulta actividades por un rango de fechas y folio de conciliacion
	 * opcionales, de no tener folio de conciliacion ni fecha regresa los 10 ultimos
	 * registros
	 */
	@Override
	public List<ConsultaActividadDTO> consultaActividades(ConsultaActividadesRequest consultaActividadesRequest) {
		// Objetos necesarios
		Map<String, Date> datesMap = null;
		List<ConsultaActividadDTO> consultaActividadDTOList = null;
		Boolean flagNullParams = null;

		// Bandera para saber si todos los parametros son nulos y hay que regresar el
		// top X en la consulta
		flagNullParams = !(null != consultaActividadesRequest
				&& (null != consultaActividadesRequest.getFolio() || null != consultaActividadesRequest.getFechaDesde()
						|| null != consultaActividadesRequest.getFechaHasta()));
		try {

			// Valida que el folio exista si es que no es nulo
			if (null != consultaActividadesRequest && null != consultaActividadesRequest.getFolio())
				conciliacionDataValidator.validateFolioExists(consultaActividadesRequest.getFolio());

			// Ajuste de fechas
			datesMap = FechasUtil.adjustDates(consultaActividadesRequest.getFechaDesde(),
					consultaActividadesRequest.getFechaHasta());
			consultaActividadesRequest.setFechaDesde(datesMap.get("startDate"));
			consultaActividadesRequest.setFechaHasta(datesMap.get("endDate"));

			if (!flagNullParams) {
				// Ningun atributo es nulo
				if (null != consultaActividadesRequest.getFolio() && null != consultaActividadesRequest.getFechaDesde()
						&& null != consultaActividadesRequest.getFechaHasta()) {
					consultaActividadDTOList = actividadRepository.findByFolioFechaDesdeAndFechaHasta(
							consultaActividadesRequest.getFolio(), consultaActividadesRequest.getFechaDesde(),
							consultaActividadesRequest.getFechaHasta());
				}

				// El folio es nulo y las fechas no
				else if (null == consultaActividadesRequest.getFolio()
						&& null != consultaActividadesRequest.getFechaDesde()
						&& null != consultaActividadesRequest.getFechaHasta()) {
					consultaActividadDTOList = actividadRepository.findByFechaDesdeAndFechaHasta(
							consultaActividadesRequest.getFechaDesde(), consultaActividadesRequest.getFechaHasta());
				}
			} else {
				// Todos los atributos son nulos se consultan los ultimos 10 por default
				Pageable pageable = PageRequest.of(0,
						null != actividadesMaxDefaultValue ? actividadesMaxDefaultValue : 10);
				consultaActividadDTOList = actividadPaginRepository.nGetTopXActividades(pageable);
			}
		} catch (java.lang.IllegalArgumentException ex) {
			throw new ConciliacionException(ConciliacionConstants.ENUM_TYPE_OR_SUB_TYPE_INCONCISTENCY,
					CodigoError.NMP_PMIMONTE_BUSINESS_002);
		} catch (Exception ex) {
			throw ex;
		}
		return consultaActividadDTOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService#
	 * consultaMovimientosTransito(java.lang.Integer)
	 */
	@Override
	public List<MovTransitoDTO> consultaMovimientosTransito(Long folio) {

		Conciliacion conciliacion = conciliacionHelper.getConciliacionByFolio(folio, null);

		List<MovimientoTransito> movsTransito = null;
		try {
			movsTransito = this.movimientoTransitoRepository.findByIdConciliacion(conciliacion.getId());
			if (null == movsTransito || movsTransito.isEmpty())
				throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
						CodigoError.NMP_PMIMONTE_0009);
		} catch (Exception ex) {
			ex.printStackTrace();
			if (ex instanceof InformationNotFoundException)
				throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
						CodigoError.NMP_PMIMONTE_0009);
			else
				throw new ConciliacionException("Error al consultar los movimientos en transito",
						CodigoError.NMP_PMIMONTE_9999);
		}

		return MovimientosTransitoBuilder.buildMovTransitoDTOListFromMovimientoTransitoList(movsTransito);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService#
	 * actualizarPS(mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarIdPSRequest,
	 * java.lang.String)
	 */
	@Transactional
	public void actualizarPS(ActualizarIdPSRequest actualizarIdPSRequest, String usuario) {
		log.info(">> actualizarPS");

		// SE VALIDA EL FOLIO DE LA CONCILIACION
		conciliacionDataValidator.validateFolioExists(actualizarIdPSRequest.getFolio());

		// SE VALIDAN LOS ID'S DE ASIENTO CONTABLE Y POLIZA TESORERIA
		if (StringUtil.isNullOrEmpty(actualizarIdPSRequest.getIdAsientoContable())
				&& StringUtil.isNullOrEmpty(actualizarIdPSRequest.getIdPolizaTesoreria())) {
			log.error("Valores nulos o vacios. IdAsientoContable: [" + actualizarIdPSRequest.getIdAsientoContable()
					+ "], " + "IdPolizaTesoreria: [" + actualizarIdPSRequest.getIdPolizaTesoreria() + "]");
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_095.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_095);
		}

		Conciliacion conciliacion;
		try {
			conciliacion = conciliacionRepository.findByFolio(actualizarIdPSRequest.getFolio());
		} catch (Exception ex) {
			log.error("Error al obtener la conciliacion para el folio: [" + actualizarIdPSRequest.getFolio() + "]", ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_094.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_094);
		}

		// VALIDA QUE TENGA EL SUB-ESTATUS CORRECTO
		if (conciliacion.getSubEstatus() == null || conciliacion.getSubEstatus().getId() == null
				|| !ConciliacionConstants.CON_SUB_ESTATUS_ACTUALIZACION_PS
						.contains(conciliacion.getSubEstatus().getId())) {
			log.error("La conciliacion no tiene un sub-estatus valido. Sub-estatus: [" + conciliacion.getSubEstatus()
					+ "]");
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_030.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_030);
		}

		conciliacion.setLastModifiedBy(usuario);
		conciliacion.setLastModifiedDate(new Date());

		if (StringUtil.isNotNullNorEmpty(actualizarIdPSRequest.getIdAsientoContable())) {
			conciliacion.setIdAsientoContable(actualizarIdPSRequest.getIdAsientoContable());
		}

		if (StringUtil.isNotNullNorEmpty(actualizarIdPSRequest.getIdPolizaTesoreria())) {
			conciliacion.setIdPolizaTesoreria(actualizarIdPSRequest.getIdPolizaTesoreria());
		}

		boolean conciliacionFinalizada = false;
		if (StringUtil.isNotNullNorEmpty(conciliacion.getIdAsientoContable())
				&& StringUtil.isNotNullNorEmpty(conciliacion.getIdPolizaTesoreria())) {
			conciliacionFinalizada = true;
		}

		if (conciliacionFinalizada) {
			log.debug("Finalizando la conciliacion ...");
			conciliacion.setEstatus(new EstatusConciliacion(ConciliacionConstants.ESTATUS_CONCILIACION_FINALIZADA));
			conciliacion.setSubEstatus(
					new SubEstatusConciliacion(ConciliacionConstants.SUBESTATUS_CONCILIACION_FINALIZADA));
		}

		try {
			conciliacionRepository.save(conciliacion);
		} catch (Exception ex) {
			log.error("Error al actualizar la conciliacion.", ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_031.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_031);
		}

		// Registro de actividad
		actividadGenericMethod.registroActividad(actualizarIdPSRequest.getFolio(),
				"Se actualiza el id de people-soft para la conciliacion "
						.concat(String.valueOf(actualizarIdPSRequest.getFolio()))
						.concat(" con el id de asiento contable: ")
						.concat(String.valueOf(actualizarIdPSRequest.getIdAsientoContable()))
						.concat(" y el di de poliza de tesoreria: ")
						.concat(String.valueOf(actualizarIdPSRequest.getIdPolizaTesoreria())),
				TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.ACTUALIZACION_ID_PEOPLE_SOFT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService#
	 * generarConciliacion(java.lang.Long, java.lang.String)
	 */
	@Transactional
	public void generarConciliacion(Long idConciliacion, String lastModifiedBy) throws ConciliacionException {
		// Validación del request
		if (idConciliacion == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		if (lastModifiedBy == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		// Valida que el folio sea valido
		conciliacionDataValidator.validateFolioExists(idConciliacion);

		// Valida que la conciliacion tenga alguno de los estatus validos para realizar
		// esta operacion ([Edo. Cta. Comp=12 & Openpay Compl=6])
		conciliacionDataValidator.validateSubEstatusByFolioAndSubEstatus(idConciliacion, Arrays.asList(6L, 12L));

		// Obtiene todos los reportes de la bd generados hasta el momento
		List<Reporte> reportes = reporteRepository.findByIdConciliacion(idConciliacion);
		if (reportes == null || reportes.size() == 0) {
			throw new ConciliacionException("No se tiene disponible ningun reporte para generar la conciliacion",
					CodigoError.NMP_PMIMONTE_BUSINESS_032);
		}

		// Al menos 2 reportes
		if (reportes.size() == 1) {
			throw new ConciliacionException(
					"Se requiere al menos 2 reportes para generar la conciliacion. Reporte recibido: "
							+ reportes.get(0).getTipo(),
					CodigoError.NMP_PMIMONTE_BUSINESS_033);
		}

		// Notificar cambios o alta de reportes, si existen...
		this.conciliacionHelper.generarConciliacion(idConciliacion, reportes);

		// Registro de actividad
		actividadGenericMethod.registroActividad(idConciliacion,
				"Se genera la conciliacion con el folio " + idConciliacion, TipoActividadEnum.ACTIVIDAD,
				SubTipoActividadEnum.GENERACION_CONCILIACION);

	}

}
