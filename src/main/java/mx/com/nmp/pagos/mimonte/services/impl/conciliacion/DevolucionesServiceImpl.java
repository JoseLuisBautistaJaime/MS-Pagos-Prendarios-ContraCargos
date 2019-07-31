/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.DevolucionesBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientoDevolucionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosTransitoBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstatusConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstatusDevolucionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstatusTransitoRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoDevolucionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoTransitoRepository;
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
import mx.com.nmp.pagos.mimonte.model.EstatusTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;
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
	private SolicitarDevolucionesService solicitarDevolucionesService;

	@Autowired
	private ConciliacionHelper conciliacionHelper;

	@Autowired
	@Qualifier("estatusTransitoRepository")
	private EstatusTransitoRepository estatusTransitoRepository;

	@Autowired
	private EstatusConciliacionRepository estatusConciliacionRepository;

	@Autowired
	private ConciliacionDataValidator conciliacionDataValidator;

	@Value("${mimonte.variables.estatus-varios.no-identificada}")
	private String nombreEstatusDevolucion;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#
	 * consultaDevolucion(java.lang.Integer)
	 */
	@Override
	public List<DevolucionConDTO> consultaDevolucion(Integer folio) {

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

		if (liquidarDevoluciones == null || liquidarDevoluciones.getFolio() == null
				|| liquidarDevoluciones.getMovimientos() == null) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		}

		List<DevolucionEntidadDTO> movimientosLiquidados = new ArrayList<DevolucionEntidadDTO>();

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

				// Se actualiza el estatus a liquidada unicamente a los movimientos que se
				// encuentran solicitados
				if (md.getEstatus().getId() == ConciliacionConstants.ESTATUS_DEVOLUCION_SOLICITADA) {

					md.setEstatus(edLiquidada);
					md.setLastModifiedDate(new Date());
					md.setLastModifiedBy(usuario);
					movimientoDevolucionRepository.saveAndFlush(md);

					Entidad entidad = entidadRepository.findByConciliacion(md.getIdConciliacion());
					DevolucionEntidadDTO devolucionEntidadDTO = DevolucionesBuilder
							.buildDevolucionEntidadDTOFromMovimientosDevolucion(md, entidad);
					movimientosLiquidados.add(devolucionEntidadDTO);
				}
			}

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
			List<Integer> idsEstatus = Arrays.asList(ConciliacionConstants.ESTATUS_DEVOLUCION_PENDIENTE,
					ConciliacionConstants.ESTATUS_DEVOLUCION_SOLICITADA);
			List<MovimientoDevolucion> movimientosDevolucion = movimientoDevolucionRepository
					.findByIdConciliacionAndEstatusIdIn(folio.getFolio(), idsEstatus);

			// Se validan que existan todos los movimientos
			if (movimientosDevolucion == null || movimientosDevolucion.size() <= 0) {
				throw new ConciliacionException(ConciliacionConstants.THERE_IS_NO_MOVIMIENTOS_DEVOLUCION_PENDIENTES,
						CodigoError.NMP_PMIMONTE_BUSINESS_092);
			}
			movimientosSolicitados = solicitarDevoluciones(movimientosDevolucion, modifiedBy, folio.getFolio());
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
		Optional<EstatusConciliacion> estatusConcliacion = null;
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
		// Valida que el estatus de conciliacion exista
		if (null != devoluciones.getEstatus()) {
			estatusConcliacion = estatusConciliacionRepository.findById(devoluciones.getEstatus());
			if (!estatusConcliacion.isPresent())
				throw new ConciliacionException(ConciliacionConstants.ESTATUS_CONCILIACION_DOESNT_EXISTS,
						CodigoError.NMP_PMIMONTE_BUSINESS_091);
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
					devoluciones.getSucursal(), devoluciones.getFechaDesde(), devoluciones.getFechaHasta());
		}

		// La fecha final nula
		else if (null != devoluciones.getFechaDesde() && null == devoluciones.getFechaHasta()) {
			result = conciliacionRepository.findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursalWOFechaHasta(
					devoluciones.getEstatus(), devoluciones.getIdEntidad(), devoluciones.getIdentificadorCuenta(),
					devoluciones.getSucursal(), devoluciones.getFechaDesde());
		}

		// La fecha inicial nula
		else if (null == devoluciones.getFechaDesde() && null != devoluciones.getFechaHasta()) {
			result = conciliacionRepository.findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursalWOFechaDesde(
					devoluciones.getEstatus(), devoluciones.getIdEntidad(), devoluciones.getIdentificadorCuenta(),
					devoluciones.getSucursal(), devoluciones.getFechaHasta());
		}

		// Ambas fechas nulas
		else {
			result = conciliacionRepository.findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursalWOFechas(
					devoluciones.getEstatus(), devoluciones.getIdEntidad(), devoluciones.getIdentificadorCuenta(),
					devoluciones.getSucursal());
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
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS,
					CodigoError.NMP_PMIMONTE_BUSINESS_035);
		}

		// Se validan que existan todos los movimientos
		if (movimientosDevolucion == null || movimientosDevolucion.size() <= 0
				|| movimientosDevolucion.size() != solicitar.getIdsMovimientos().size()) {
			throw new ConciliacionException("No existe movimientos devolucion con los ids especificados",
					CodigoError.NMP_PMIMONTE_0008);
		}

		List<DevolucionEntidadDTO> movimientosSolicitados = solicitarDevoluciones(movimientosDevolucion, modifiedBy,
				null);

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

		EstatusTransito estatusTransito = null;
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
		// cambiar a devolucion (1)
		estatusTransito = estatusTransitoRepository.findByNombre(nombreEstatusDevolucion);
		if (null == estatusTransito)
			throw new ConciliacionException(ConciliacionConstants.GETTING_DEV_ESTATUS_HAS_GONE_WRONG,
					CodigoError.NMP_PMIMONTE_BUSINESS_089);
		flagEstatus = BigInteger.ONE.compareTo((BigInteger) movimientoTransitoRepository
				.verifyIfIdsHaveRightEstatus(marcarDevoluciones.getIdMovimientos(), estatusTransito.getId())) == 0;
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
				if (movimientoTransito == null) { // TODO: Validar el estatus del movimiento en transito
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
				// TODO: Eliminar lineas comentadas una vez que se pruebe la funcionalidad
//				movimientoTransito.setLastModifiedBy(createdBy);
//				movimientoTransito.setLastModifiedDate(new Date());
//				movimientoTransito.setEstatus(etMarcadoDev);
				movimientoTransitoRepository.delete(movimientoTransito);

			}
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

		List<DevolucionEntidadDTO> devolucionesDTO = new ArrayList<DevolucionEntidadDTO>();

		// Validar que las conciliaciones asociadas a los movimientos aun estan en
		// proceso ...
		for (DevolucionUpdtDTO devolucionDTO : devolucionUpdtDTOList) {

			// Se obtiene el movimiento devolucion por id
			MovimientoDevolucion devolucion = this.movimientoDevolucionRepository
					.findByIdMovimiento(devolucionDTO.getIdMovimiento());
			if (devolucion == null) {
				throw new ConciliacionException("No existe el movimiento con id " + devolucionDTO.getIdMovimiento(),
						CodigoError.NMP_PMIMONTE_0008);
			}

			if (devolucion.getEstatus().getId() == ConciliacionConstants.ESTATUS_DEVOLUCION_LIQUIDADA) { // Ya fue
																											// liquidado
																											// no se
																											// pueden
																											// realizar
																											// mas
																											// acciones
				throw new ConciliacionException(
						"Estatus del movimiento " + devolucionDTO.getIdMovimiento() + " es incorrecto ",
						CodigoError.NMP_PMIMONTE_BUSINESS_035);
			}

			// Se actualiza el estatus de la devolucion a liquidada
			devolucion.setFechaLiquidacion(devolucionDTO.getFecha());
			if (devolucionDTO.getLiquidar() != null && devolucionDTO.getLiquidar()) {
				devolucion.setEstatus(new EstatusDevolucion(ConciliacionConstants.ESTATUS_DEVOLUCION_LIQUIDADA));
			}
			devolucion.setLastModifiedBy(modifiedBy);
			devolucion.setLastModifiedDate(new Date());
			this.movimientoDevolucionRepository.save(devolucion);

			// Se agrega al response
			devolucionesDTO.add(DevolucionesBuilder.buildDevolucionEntidadDTOFromMovimientoDevolucion(devolucion));
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
	 * @return
	 */
	private List<DevolucionEntidadDTO> solicitarDevoluciones(List<MovimientoDevolucion> movimientosDevolucion,
			String usuario, final Integer folio) {
		Map<String, Object> map = null;
		List<DevolucionEntidadDTO> devolucionesSolicitadas = null;
		List<DevolucionEntidadDTO2> devolucionesSolicitadasInner = null;

		// Se procede al envio de las devoluciones
		if (CollectionUtils.isNotEmpty(movimientosDevolucion)) {

			devolucionesSolicitadas = new ArrayList<DevolucionEntidadDTO>();
			devolucionesSolicitadasInner = new ArrayList<>();

			try {

				// Se obtiene el catalogo de devolucion solicitada
				EstatusDevolucion edSolicitada = estatusDevolucionRepository
						.getOne(ConciliacionConstants.ESTATUS_DEVOLUCION_SOLICITADA);

				// Se filtran unicamente las devoluciones que tienen estatus pendiente
				// Se actualiza el estatus a solicitada
				for (MovimientoDevolucion md : movimientosDevolucion) {

					if (md.getEstatus().getId() == ConciliacionConstants.ESTATUS_DEVOLUCION_LIQUIDADA) {
						throw new ConciliacionException(
								"La devolucion con id " + md.getId() + " tiene un estatus incorrecto",
								CodigoError.NMP_PMIMONTE_BUSINESS_035);
					}

					md.setEstatus(edSolicitada);
					md.setLastModifiedDate(new Date());
					md.setLastModifiedBy(usuario);
					movimientoDevolucionRepository.saveAndFlush(md);

					// Se agrega el movimiento a la respuesta
					Entidad entidad = entidadRepository.findByConciliacion(md.getIdConciliacion());
					map = DevolucionesBuilder.buildDevolucionEntidadDTOAndDTO2FromMovimientosDevolucion(md, entidad);
					devolucionesSolicitadas.add((DevolucionEntidadDTO) map.get("DTO"));
					devolucionesSolicitadasInner.add((DevolucionEntidadDTO2) map.get("DTO2"));
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS,
						CodigoError.NMP_PMIMONTE_BUSINESS_035);
			}

			// Se envia el email con la solicitud de devoluciones
			solicitarDevolucionesService.enviarSolicitudDevoluciones(devolucionesSolicitadasInner, folio);

		}

		return devolucionesSolicitadas;
	}

}
