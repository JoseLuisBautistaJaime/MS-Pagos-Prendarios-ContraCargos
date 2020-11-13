/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.aspects.ActividadGenericMethod;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.DevolucionesBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientoDevolucionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosTransitoBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.CuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstatusConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstatusDevolucionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstatusTransitoRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoDevolucionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoTransitoRepository;
import mx.com.nmp.pagos.mimonte.dto.BaseEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.BaseEntidadDTODev;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDTO2;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDetalleDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionUpdtDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionesIdsMovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FolioRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LiquidacionMovimientosRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.EstatusDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubTipoActividadEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoActividadEnum;
import mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.SolicitarDevolucionesService;
import mx.com.nmp.pagos.mimonte.util.ConciliacionDataValidator;
import mx.com.nmp.pagos.mimonte.util.FechasUtil;

/**
 * @name DevolucionesServiceImpl
 * @description Clase de capa de servicio para las devoluciones que sirve para
 *              realizar operaciones de logica de negocios
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/05/2019 17:48 hrs.
 * @version 0.1
 */
@Service("devolucionesServiceImpl")
public class DevolucionesServiceImpl implements DevolucionesService {

	@Autowired
	private MovimientoDevolucionRepository movimientoDevolucionRepository;

	@Autowired
	private ConciliacionRepository conciliacionRepository;

	@Autowired
	private EstatusDevolucionRepository estatusDevolucionRepository;

	@Autowired
	private MovimientoTransitoRepository movimientoTransitoRepository;

	@Autowired
	private EntidadRepository entidadRepository;
	
	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private SolicitarDevolucionesService solicitarDevolucionesService;

	@Autowired
	private ConciliacionHelper conciliacionHelper;

	@Autowired
	@Qualifier("estatusTransitoRepository")
	private EstatusTransitoRepository estatusTransitoRepository;

	@Autowired
	private EstatusConciliacionRepository estatusConciliacionRepository;

	@Autowired
	MovimientoConciliacionRepository movimientoConciliacionRepository;

	/**
	 * Registro de actividades
	 */
	@Autowired
	private ActividadGenericMethod actividadGenericMethod;

	/**
	 * Validador generico para datos relacionados conconciliacion
	 */
	@Autowired
	private ConciliacionDataValidator conciliacionDataValidator;

	/**
	 * Log de actividades en el servidor
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DevolucionesServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#
	 * consultaDevolucion(java.lang.Integer)
	 */
	@Override
	public List<DevolucionConDTO> consultaDevolucion(Long folio) {

		if (folio < 1)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		Conciliacion conciliacion = this.conciliacionHelper.getConciliacionByFolio(folio, null);

		List<MovimientoDevolucion> devoluciones = movimientoDevolucionRepository
				.findByIdConciliacion(conciliacion.getId());
		if (devoluciones == null || devoluciones.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0009);

		return DevolucionesBuilder.buildDevolucionConDTOListFromMovimientoDevolucionList(devoluciones);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#
	 * liquidarDevoluciones(mx.com.nmp.pagos.mimonte.dto.conciliacion.
	 * LiquidacionMovimientosRequestDTO, java.lang.String)
	 */
	@Override
	@Transactional
	public List<DevolucionEntidadDTO> liquidarDevoluciones(LiquidacionMovimientosRequestDTO liquidarDevoluciones,
			String usuario) {
		// Objetos necesarios
		List<DevolucionEntidadDTO> movimientosLiquidados = new ArrayList<>();
		List<Integer> list = null;
		Boolean flagExist = null;
		Boolean flagHaveRelationship = null;
		Boolean flagHaveRightEstatus = null;

		// Valida que el folio de conciliacion exista
		conciliacionDataValidator.validateFolioExists(liquidarDevoluciones.getFolio());

		// Extrae los ids y los pone en una lista
		list = MovimientosBuilder.buildIdsListFromMovimientosDTOList(liquidarDevoluciones.getMovimientos());
		try {
			// Consulta ids de movimientos devolucion
			if (null != list && !list.isEmpty())
				flagExist = ((BigInteger) movimientoDevolucionRepository.checkIfIdsExists(list,
						liquidarDevoluciones.getMovimientos().size())).compareTo(BigInteger.ONE) == 0;
			else
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_097.getDescripcion(),
						CodigoError.NMP_PMIMONTE_BUSINESS_097);
		} catch (ConciliacionException ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		} catch (Exception ex) {
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_0011.getDescripcion(),
					CodigoError.NMP_PMIMONTE_0011);
		}
		// Valida el resultado de la consulta para corroborar que los movimientos
		// existan
		if (!flagExist)
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_096.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_096);

		// Valida que el folio tiene relacion con todos los ids de movimientos
		// especificados
		try {
			flagHaveRelationship = ((BigInteger) movimientoDevolucionRepository
					.verifyIfFolioAndIdsRelationShipExists(liquidarDevoluciones.getFolio(), list, list.size()))
							.compareTo(BigInteger.ONE) == 0;
		} catch (Exception ex) {
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_0011.getDescripcion(),
					CodigoError.NMP_PMIMONTE_0011);
		}
		if (!flagHaveRelationship)
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_087.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_087);

		// Valida que los ids de movimientos especificados tengan un estatus corrrecto
		// (2)
		try {
			flagHaveRightEstatus = ((BigInteger) movimientoDevolucionRepository.verifyIfHaveRightEstatus(list,
					list.size())).compareTo(BigInteger.ONE) == 0;
		} catch (Exception ex) {
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_098.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_098);
		}
		if (!flagHaveRightEstatus)
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_098.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_098);

		try {
			// Se obtiene el estatus liquidada
			EstatusDevolucion edLiquidada = estatusDevolucionRepository
					.getOne(ConciliacionConstants.ESTATUS_DEVOLUCION_LIQUIDADA);

			// Por cada movimiento a liquidar:
			for (MovimientosDTO movimientos : liquidarDevoluciones.getMovimientos()) {

				// Se obtiene el movimiento a liquidar
				MovimientoDevolucion md = movimientoDevolucionRepository
						.findByIdConciliacionAndIdMovimiento(liquidarDevoluciones.getFolio(), movimientos.getId());
				if (md == null) {
					throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND,
							CodigoError.NMP_PMIMONTE_0009);
				}
				md.setEstatus(edLiquidada);
				md.setLastModifiedDate(new Date());
				md.setLastModifiedBy(usuario);
				md.setFechaLiquidacion(movimientos.getFecha());
				movimientoDevolucionRepository.save(md);

				Entidad entidad = entidadRepository.findByConciliacion(md.getIdConciliacion());
				DevolucionEntidadDTO devolucionEntidadDTO = DevolucionesBuilder
						.buildDevolucionEntidadDTOFromMovimientosDevolucion(md, entidad);
				movimientosLiquidados.add(devolucionEntidadDTO);
			}

			// Registro de actividad
			if (null != liquidarDevoluciones.getFolio()) {
				actividadGenericMethod
						.registroActividad(liquidarDevoluciones.getFolio(),
								"Se realizo la liquidacion de ".concat(String.valueOf(movimientosLiquidados.size()))
										.concat(" devolucion(es) de la conciliacion con el folio: ")
										.concat(String.valueOf(null != liquidarDevoluciones.getFolio()
												? liquidarDevoluciones.getFolio()
												: null))
										.concat(", por un total de: $ ")
										.concat(String
												.valueOf(getTotalFromDevolucionEntidadDTOList(movimientosLiquidados))),
								TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.MOVIMIENTOS);
			}

			movimientoDevolucionRepository.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS,
					CodigoError.NMP_PMIMONTE_BUSINESS_035);
		}

		return movimientosLiquidados;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#
	 * solicitarDevoluciones(mx.com.nmp.pagos.mimonte.dto.conciliacion.
	 * FolioRequestDTO, java.lang.String)
	 */
	@Override
	@Transactional
	public List<DevolucionEntidadDTO> solicitarDevoluciones(FolioRequestDTO folio, String modifiedBy)
			throws ConciliacionException {

		// Valida que el folio exista
		conciliacionDataValidator.validateFolioExists(folio.getFolio());

		List<DevolucionEntidadDTO> movimientosSolicitados = null;

		try {
			// Se obtienen los movimientos por folio
			List<Integer> idsEstatus = Arrays.asList(ConciliacionConstants.ESTATUS_DEVOLUCION_PENDIENTE);
			List<MovimientoDevolucion> movimientosDevolucion = movimientoDevolucionRepository
					.findByIdConciliacionAndEstatusIdIn(folio.getFolio(), idsEstatus);

			// Se validan que existan todos los movimientos
			if (movimientosDevolucion == null || movimientosDevolucion.size() <= 0) {
				throw new ConciliacionException(ConciliacionConstants.THERE_IS_NO_MOVIMIENTOS_DEVOLUCION_PENDIENTES,
						CodigoError.NMP_PMIMONTE_BUSINESS_092);
			}
			movimientosSolicitados = solicitarDevoluciones(movimientosDevolucion, modifiedBy, folio.getFolio(), false);

			// Registro de actividad
			actividadGenericMethod.registroActividad(folio.getFolio(), "Se realizo la solicitud de devolucion de "
					.concat(String.valueOf(null != movimientosSolicitados ? movimientosSolicitados.size() : null))
					.concat(" movimiento(s) de la conciliacion con el folio: ").concat(String.valueOf(folio.getFolio()))
					.concat(", por un total de: $ ")
					.concat(String.valueOf(getTotalFromDevolucionEntidadDTOList(movimientosSolicitados))),
					TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.MOVIMIENTOS);

		} catch (Exception ex) {
			ex.printStackTrace();
			if (ex instanceof ConciliacionException)
				throw ex;
			else
				throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS,
						CodigoError.NMP_PMIMONTE_BUSINESS_035);
		}

		return movimientosSolicitados;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#consulta(
	 * mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionRequestDTO)
	 */
	@Override
	public List<DevolucionEntidadDTO> consulta(DevolucionRequestDTO devoluciones) {
		// Objetos necesarios
		Optional<Entidad> entidad = null;
		Optional<EstatusDevolucion> estatusDevolcion= null;
		List<DevolucionEntidadDetalleDTO> result = null;
		Map<String, Date> datesMap = null;

		// Valida que el objeto principal no sea nulo
		if (null == devoluciones)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		// Valida que la entidad exista
		if (null != devoluciones.getIdEntidad()) {
			entidad = entidadRepository.findById(devoluciones.getIdEntidad());
			if (!entidad.isPresent())
				throw new ConciliacionException(CatalogConstants.NO_ENTIDAD_FOUND,
						CodigoError.NMP_PMIMONTE_BUSINESS_060);
		}
		
		if (null != devoluciones.getEstatus()) {
			estatusDevolcion = estatusDevolucionRepository.findById(devoluciones.getEstatus());
			if (!estatusDevolcion.isPresent())
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_133.getDescripcion(),
						CodigoError.NMP_PMIMONTE_BUSINESS_133);
		}
		// Realiza los ajustes de las fechas de filtrado (pone las horas en 0 a la
		// inicial y en 23 a la final)
		if (null != devoluciones.getFechaDesde() || null != devoluciones.getFechaHasta()) {
			datesMap = FechasUtil.adjustDates(devoluciones.getFechaDesde(), devoluciones.getFechaHasta());
			devoluciones.setFechaDesde(datesMap.get("startDate"));
			devoluciones.setFechaHasta(datesMap.get("endDate"));
		}

		// Realiza la consulta de la informacion

		// Ninguna fecha nula
		if (null != devoluciones.getFechaDesde() && null != devoluciones.getFechaHasta()) {
			result = conciliacionRepository.findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursal(
					devoluciones.getEstatus(), devoluciones.getIdEntidad(), devoluciones.getIdentificadorCuenta(),
					devoluciones.getSucursal(), devoluciones.getFechaDesde(), devoluciones.getFechaHasta(), CorresponsalEnum.getByNombre(devoluciones.getIdCorresponsal()) );
		}

		// La fecha final nula
		else if (null != devoluciones.getFechaDesde() && null == devoluciones.getFechaHasta()) {
			result = conciliacionRepository.findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursalWOFechaHasta(
					devoluciones.getEstatus(), devoluciones.getIdEntidad(), devoluciones.getIdentificadorCuenta(),
					devoluciones.getSucursal(), devoluciones.getFechaDesde(), CorresponsalEnum.getByNombre(devoluciones.getIdCorresponsal()) );
		}

		// La fecha inicial nula
		else if (null == devoluciones.getFechaDesde() && null != devoluciones.getFechaHasta()) {
			result = conciliacionRepository.findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursalWOFechaDesde(
					devoluciones.getEstatus(), devoluciones.getIdEntidad(), devoluciones.getIdentificadorCuenta(),
					devoluciones.getSucursal(), devoluciones.getFechaHasta(), CorresponsalEnum.getByNombre(devoluciones.getIdCorresponsal()) );
		}

		// Ambas fechas nulas
		else {
			result = conciliacionRepository.findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursalWOFechas(
					devoluciones.getEstatus(), devoluciones.getIdEntidad(), devoluciones.getIdentificadorCuenta(),
					devoluciones.getSucursal(), CorresponsalEnum.getByNombre(devoluciones.getIdCorresponsal()) );
		}

		// Crea el DTO de respuesta y lo regresa
		return DevolucionesBuilder.buildDevolucionEntidadDTOListFromDevolucionEntidadDetalleDTOList(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#
	 * solicitarDevoluciones(mx.com.nmp.pagos.mimonte.dto.conciliacion.
	 * DevolucionesIdsMovimientosDTO, java.lang.String)
	 */
	@Override
	@Transactional
	public List<DevolucionEntidadDTO> solicitarDevoluciones(DevolucionesIdsMovimientosDTO solicitar,
			String modifiedBy) {
		// Objetos necesarios
		List<MovimientoDevolucion> movimientosDevolucion = null;
		List<DevolucionEntidadDTO> movimientosSolicitados = null;
		Boolean flagMovs = null;

		// Valida que los ids de movimientos devolucion existan
		flagMovs = ((BigInteger) movimientoDevolucionRepository.checkIfIdsExists(solicitar.getIdsMovimientos(),
				solicitar.getIdsMovimientos().size())).compareTo(BigInteger.ONE) == 0;
		if (!flagMovs)
			throw new ConciliacionException(ConciliacionConstants.DEV_MOVS_DONT_EXIST,
					CodigoError.NMP_PMIMONTE_BUSINESS_096);

		// Valida que los ids de devoluciones tengan un estatus valido
		flagMovs = ((BigInteger) movimientoDevolucionRepository.checkIfIdsEstatus(solicitar.getIdsMovimientos()))
				.compareTo(BigInteger.ONE) == 0;
		if (!flagMovs)
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_093.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_093);

		try {
			// Se obtienen los movimientos de devolucion usando los ids recibidos
			movimientosDevolucion = movimientoDevolucionRepository.findAllById(solicitar.getIdsMovimientos());
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_105.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_105);
		}

		// Se validan que existan todos los movimientos
		if (movimientosDevolucion == null || movimientosDevolucion.size() <= 0
				|| movimientosDevolucion.size() != solicitar.getIdsMovimientos().size()) {
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_096.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_096);
		}

		try {
			movimientosSolicitados = solicitarDevoluciones(movimientosDevolucion, modifiedBy, null, true);
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		}

		return movimientosSolicitados;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#
	 * marcarDevolucion(mx.com.nmp.pagos.mimonte.dto.conciliacion.
	 * SolicitarPagosRequestDTO, java.lang.String)
	 */
	@Override
	@Transactional
	public List<DevolucionConDTO> marcarDevolucion(SolicitarPagosRequestDTO marcarDevoluciones, String createdBy) {

		Boolean flagEstatus = null;

		// Se validan parametros
		if (marcarDevoluciones == null || marcarDevoluciones.getIdMovimientos() == null) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		}

		// Valida que el folio de conciliacion exista
		conciliacionDataValidator.validateFolioExists(marcarDevoluciones.getFolio());

		// Valida los ids de movimientos
		conciliacionDataValidator.validateIdsMovimientosConciliacionExists(marcarDevoluciones.getFolio(),
				marcarDevoluciones.getIdMovimientos());

		// Valida que el estatus de los movimientos sea el primero y unico valido para
		// cambiar a devolucion (1, 2)
//		Optional<EstatusTransito> estatusTransito = estatusTransitoRepository
//				.findById(ConciliacionConstants.ESTATUS_TRANSITO_NO_IDENTIFICADO_MIDAS);
//		if (null == estatusTransito || !estatusTransito.isPresent())
//			throw new ConciliacionException(ConciliacionConstants.GETTING_DEV_ESTATUS_HAS_GONE_WRONG,
//					CodigoError.NMP_PMIMONTE_BUSINESS_089);
		// Se envia el estatus 2 que significa solicitada como pago
		flagEstatus = BigInteger.ONE.compareTo((BigInteger) movimientoTransitoRepository.verifyIfIdsHaveRightEstatus(
				marcarDevoluciones.getIdMovimientos(), ConciliacionConstants.ESTATUS_TRANSITO_MARCADO_DEVOLUCION,
				ConciliacionConstants.ESTATUS_TRANSITO_NO_IDENTIFICADO_OPEN_PAY,
				marcarDevoluciones.getIdMovimientos().size())) == 0;
		if (!flagEstatus) {
			throw new ConciliacionException(ConciliacionConstants.NOT_ALLOWED_STATUS_IDS,
					CodigoError.NMP_PMIMONTE_BUSINESS_090);
		}
		List<DevolucionConDTO> movimientosMarcados = new ArrayList<DevolucionConDTO>();

		try {

			// Se obtiene el catalogo devolucion pendiente
			EstatusDevolucion edPendiente = estatusDevolucionRepository
					.getOne(ConciliacionConstants.ESTATUS_DEVOLUCION_PENDIENTE);

			// Se obtiene el catalogo de movimiento en transito transferido
//			EstatusTransito etMarcadoDev = estatusTransitoRepository
//					.getOne(ConciliacionConstants.ESTATUS_TRANSITO_MARCADO_DEVOLUCION);

			// Por cada movimiento en transito:
			for (Integer idMovimientoTransito : marcarDevoluciones.getIdMovimientos()) {

				// Se obtiene el movimiento en transito asignado al folio y al id
				MovimientoTransito movimientoTransito = movimientoTransitoRepository
						.findByIdFolioAndIdMovimiento(marcarDevoluciones.getFolio(), idMovimientoTransito);
				if (movimientoTransito == null) { 
					throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND,
							CodigoError.NMP_PMIMONTE_0009);
				}

				// Se crea el movimiento devolucion en base al movimiento en transito
				MovimientoDevolucion movimientoDevolucion = MovimientoDevolucionBuilder
						.buildMovimientoFromMovimientoTransito(movimientoTransito, edPendiente, createdBy);
				movimientoDevolucionRepository.save(movimientoDevolucion);

				DevolucionConDTO devolucionConDTO = MovimientosTransitoBuilder
						.buildDevolucionConDTOFromMovimientoTransito(movimientoTransito, edPendiente);
				movimientosMarcados.add(devolucionConDTO);

				// Se actualiza el estatus del movimiento en transito como marcado para
				// devolucion (borrado logico)
				movimientoTransitoRepository.delete(movimientoTransito);

			}

			// Registro de actividad
			actividadGenericMethod.registroActividad(marcarDevoluciones.getFolio(),
					"Se realizo el marcado para devolucion de "
							.concat(String.valueOf(marcarDevoluciones.getIdMovimientos().size()))
							.concat(" movimiento(s) de la conciliacion con el folio: "
									.concat(String.valueOf(marcarDevoluciones.getFolio()))),
					TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.MOVIMIENTOS);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS,
					CodigoError.NMP_PMIMONTE_BUSINESS_035);
		}

		return movimientosMarcados;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#actualizar
	 * (java.util.List, java.lang.String)
	 */
	@Transactional
	public List<DevolucionEntidadDTO> actualizar(List<DevolucionUpdtDTO> devolucionUpdtDTOList, String modifiedBy)
			throws ConciliacionException {
		// Objetos requeridos
		List<DevolucionEntidadDTO> devolucionesDTO = new ArrayList<>();
		List<Integer> ids = null;
		Map<Integer, BaseEntidadDTO> baseEntidadDTOMap = null;
		Optional<EstatusDevolucion> estatusDevolucion = null;
		Boolean flagExist = null;
		Boolean flagHaveRightEstatus = null;

		// Se contruye lista de ids para las consultas de validaciones
		ids = DevolucionesBuilder.buildIntegerListFromDevolucionUpdtDTOList(devolucionUpdtDTOList);

		// Valida que los ids de movimientos de devoluciones existan
		flagExist = ((BigInteger) movimientoDevolucionRepository.checkIfIdsExists(ids, ids.size()))
				.compareTo(BigInteger.ONE) == 0;
		if (!flagExist)
			throw new ConciliacionException(ConciliacionConstants.DEV_MOVS_DONT_EXIST,
					CodigoError.NMP_PMIMONTE_BUSINESS_096);

		// Valida que los movimientos de devolucion tengan un estatus correcto (2)
		flagHaveRightEstatus = ((BigInteger) movimientoDevolucionRepository.verifyIfHaveRightEstatus(ids, ids.size()))
				.compareTo(BigInteger.ONE) == 0;
		if (!flagHaveRightEstatus)
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_098.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_098);

		// Se otienen el estatus de devolucion liquidado y las correspondientes
		// entidades para setear a los movimientos de respuesta
		estatusDevolucion = estatusDevolucionRepository.findById(3);
		baseEntidadDTOMap = getMapEntidadesByIdMovimientoDevolucion(ids);

		// Construye las entidades a persistir
		for (DevolucionUpdtDTO devolucionDTO : devolucionUpdtDTOList) {

			// Se obtiene el movimiento devolucion por id
			MovimientoDevolucion devolucion = this.movimientoDevolucionRepository
					.findByIdMovimiento(devolucionDTO.getIdMovimiento());
			// Se actualiza el estatus de la devolucion a liquidada
			devolucion.setFechaLiquidacion(devolucionDTO.getFecha());
			if (devolucionDTO.getLiquidar() != null && devolucionDTO.getLiquidar()) {
				devolucion.setEstatus(estatusDevolucion.isPresent() ? estatusDevolucion.get()
						: new EstatusDevolucion(ConciliacionConstants.ESTATUS_DEVOLUCION_LIQUIDADA));
			}
			devolucion.setLastModifiedBy(modifiedBy);
			devolucion.setLastModifiedDate(new Date());
			devolucion.setFechaLiquidacion(devolucionDTO.getFecha());
			this.movimientoDevolucionRepository.saveAndFlush(devolucion);

			// Se agrega al response
			devolucionesDTO.add(DevolucionesBuilder.buildDevolucionEntidadDTOFromMovimientoDevolucion(devolucion,
					baseEntidadDTOMap));
		}

		return devolucionesDTO;
	}

	// PRIVATES /////////////////////////////////////////////

	/**
	 * Se encarga de actualizar el estatus de los movimientos de devoluciones a
	 * solicitado y de realizar el envio del email de solicitud
	 * 
	 * @param movimientosDevolucion
	 * @param usuario
	 * @param folio
	 * @param issueAnActivity
	 * @return
	 */
	private List<DevolucionEntidadDTO> solicitarDevoluciones(List<MovimientoDevolucion> movimientosDevolucion,
			String usuario, final Long folio, final boolean issueAnActivity) {
		// Objetos necesarios
		Map<String, Object> map = null;
		List<DevolucionEntidadDTO> devolucionesSolicitadas = null;
		List<DevolucionEntidadDTO2> devolucionesSolicitadasInner = null;
		EstatusDevolucion edSolicitada = null;
		Entidad entidad = null;
		Long folioConciliacion = null;
		String cuenta = null;

		// Se procede al envio de las devoluciones
		if (CollectionUtils.isNotEmpty(movimientosDevolucion)) {
			devolucionesSolicitadas = new ArrayList<DevolucionEntidadDTO>();
			devolucionesSolicitadasInner = new ArrayList<>();
			// Se obtiene el catalogo de devolucion solicitada
			try {
				edSolicitada = estatusDevolucionRepository.getOne(ConciliacionConstants.ESTATUS_DEVOLUCION_SOLICITADA);
			} catch (Exception ex) {
				LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
				throw ex;
			}

			// Se filtran unicamente las devoluciones que tienen estatus pendiente
			// Se actualiza el estatus a solicitada
			for (MovimientoDevolucion md : movimientosDevolucion) {
				// Se compara el estatus para ver que sea el correcto
				if (md.getEstatus().getId().compareTo(ConciliacionConstants.ESTATUS_DEVOLUCION_PENDIENTE) != 0) {
					throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_098.getDescripcion(),
							CodigoError.NMP_PMIMONTE_BUSINESS_098);
				}
				// Se setean las propiedades base
				md.setEstatus(edSolicitada);
				md.setLastModifiedDate(new Date());
				md.setLastModifiedBy(usuario);
				// Se guardan los movimientos devolucion
				movimientoDevolucionRepository.saveAndFlush(md);
				// Se obtiene la entidad y la cuenta para agregar a la respuesta
				if(null == folioConciliacion) {
					folioConciliacion = md.getIdConciliacion();
				}
				try {
					entidad = entidadRepository.findByConciliacion(md.getIdConciliacion());
					Object obj = cuentaRepository.findCuentaNumeroByConciliacionId(md.getIdConciliacion());
					cuenta = null != obj ? obj.toString(): null;
				} catch (Exception ex) {
					LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
					throw ex;
				}
				// Se fabrica el objeto de respuesta
				try {
					map = DevolucionesBuilder.buildDevolucionEntidadDTOAndDTO2FromMovimientosDevolucion(md, entidad, cuenta);
					devolucionesSolicitadas.add((DevolucionEntidadDTO) map.get("DTO"));
					devolucionesSolicitadasInner.add((DevolucionEntidadDTO2) map.get("DTO2"));
				} catch (Exception ex) {
					LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
					throw ex;
				}
			}

		}
		// ENVIO DE E-MAIL PARA SOLICITUD DE DEVOLUCIONES
		try {
			// Se hace el siguiente proceso para cuando no se especifica un folio de
			// coniliacion
			if (null == folio) {
				// Se agrupan las devoluciones por entidad en una estructura Map
				Map<String, List<DevolucionEntidadDTO2>> mapByEntidad = new HashMap<>();
				for (DevolucionEntidadDTO2 elem : devolucionesSolicitadasInner) {
					if (!mapByEntidad.containsKey(elem.getEntidad().getId().toString().concat(elem.getCuenta()))) {
						List<DevolucionEntidadDTO2> list = new LinkedList<>(Arrays.asList(elem));
						mapByEntidad.put(elem.getEntidad().getId().toString().concat(elem.getCuenta()), list);
					} else {
						mapByEntidad.get(elem.getEntidad().getId().toString().concat(elem.getCuenta())).add(elem);
					}
				}

				// Se invoca el metodo de envio pr cada grupo de devoluciones que comparten una
				// entidad en comun
				for (Map.Entry<String, List<DevolucionEntidadDTO2>> entry : mapByEntidad.entrySet()) {
					solicitarDevolucionesService.enviarSolicitudDevoluciones(entry.getValue(), folioConciliacion);
				}
			} else
				solicitarDevolucionesService.enviarSolicitudDevoluciones(devolucionesSolicitadasInner, folio);
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		}
		return devolucionesSolicitadas;
	}

	/**
	 * Construye un mapa con la clave de id de movimiento y el objeto de tipo
	 * BaseEntidadDTO segun correspondan (primero obtiene todos los datos mediante
	 * una consulta de base de datos)
	 * 
	 * @param idsMovimientoDevolucion
	 * @return
	 */
	private Map<Integer, BaseEntidadDTO> getMapEntidadesByIdMovimientoDevolucion(
			List<Integer> idsMovimientoDevolucion) {
		Map<Integer, BaseEntidadDTO> map = null;
		List<BaseEntidadDTODev> baseEntidadDTODevList = null;
		if (null != idsMovimientoDevolucion && !idsMovimientoDevolucion.isEmpty()) {
			baseEntidadDTODevList = movimientoDevolucionRepository
					.findEntidadesByIdsMovimientos(idsMovimientoDevolucion);
		}
		if (null != baseEntidadDTODevList && !baseEntidadDTODevList.isEmpty()) {
			map = new HashMap<>();
			for (BaseEntidadDTODev baseEntidadDTODev : baseEntidadDTODevList) {
				if (null != baseEntidadDTODev) {
					map.put(baseEntidadDTODev.getIdMovimiento(), new BaseEntidadDTO(baseEntidadDTODev.getId(),
							baseEntidadDTODev.getNombre(), baseEntidadDTODev.getDescripcion()));
				}
			}
		}
		return map;
	}

	/**
	 * Realiza la suma de montos de una lista de movimientos de tipo
	 * DevolucionEntidadDTO y la regresa en un numero de tipo BigDecimal
	 * 
	 * @param devolucionEntidadDTOListList
	 * @return
	 */
	private static BigDecimal getTotalFromDevolucionEntidadDTOList(
			List<DevolucionEntidadDTO> devolucionEntidadDTOListList) {
		BigDecimal total = null;
		if (null != devolucionEntidadDTOListList && !devolucionEntidadDTOListList.isEmpty()) {
			total = new BigDecimal(0);
			for (DevolucionEntidadDTO devolucionEntidadDTO : devolucionEntidadDTOListList) {
				total = total.add(null != devolucionEntidadDTO && null != devolucionEntidadDTO.getMonto()
						? devolucionEntidadDTO.getMonto()
						: BigDecimal.ZERO);
			}
		}
		return total;
	}

}
