/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.ActividadGenericMethod;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.ConciliacionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.EstatusConciliacionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientoComisionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientoDevolucionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosTransitoBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.SubEstatusConciliacionBuilder;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.CuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
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
import mx.com.nmp.pagos.mimonte.observable.ReporteObservable;
import mx.com.nmp.pagos.mimonte.observer.ReporteObserver;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService;

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
	private ReporteObserver reporteObserver;

	@Autowired
	private ConciliacionHelper conciliacionHelper;

	@Autowired
	@Qualifier("actividadRepository")
	ActividadRepository actividadRepository;

	/**
	 * Registro de actividades
	 */
	@Autowired
	private ActividadGenericMethod actividadGenericMethod;

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
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		}

		if (conciliacionRequestDTO.getEntidad() == null || conciliacionRequestDTO.getEntidad().getId() == null
				|| conciliacionRequestDTO.getEntidad().getId() < 1) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		}

		// Validación del atributo createdBy
		if (StringUtils.isBlank(createdBy)) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		}

		// Búsqueda y validacion del idCuenta.
		Optional<Cuenta> cuenta = cuentaRepository.findById(conciliacionRequestDTO.getCuenta().getId());
		if (!cuenta.isPresent()) {
			throw new ConciliacionException("La cuenta especificada no existe");
		}

		// Búsqueda y validación del idEntidad.
		Optional<Entidad> entidad = entidadRepository.findById(conciliacionRequestDTO.getEntidad().getId());
		if (!entidad.isPresent()) {
			throw new ConciliacionException("La entidad especificada no existe");
		}

		// Valida si la entidad y la cuenta estan relacionadas
		boolean flag = ((BigInteger) conciliacionRepository.checkCuentaEntidadRelationship(
				conciliacionRequestDTO.getCuenta().getId(), conciliacionRequestDTO.getEntidad().getId()))
						.compareTo(BigInteger.ONE) == 0;
		if (!flag)
			throw new ConciliacionException(ConciliacionConstants.THERE_IS_NO_SUCH_CUENTA_ENTIDAD_RELATIONSHIP);

		// Se obtiene el estatus y el sub estatus de la conciliacion
		Optional<EstatusConciliacion> estatusConciliacion = estatusConciliacionRepository
				.findById(ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);
		if (!estatusConciliacion.isPresent()) {
			throw new ConciliacionException("Estatus conciliacion en proceso no encontrado");
		}
		Optional<SubEstatusConciliacion> subEstatusConciliacion = subEstatusConciliacionRepository
				.findById(ConciliacionConstants.SUBESTATUS_CONCILIACION_CREADA);
		if (!subEstatusConciliacion.isPresent()) {
			throw new ConciliacionException("Sub estatus conciliacion creada no encontrado");
		}
		conciliacionRequestDTO.setEstatus(EstatusConciliacionBuilder
				.buildEstatusConciliacionDTOFromEstatusConciliacion(estatusConciliacion.get()));
		conciliacionRequestDTO.setSubEstatus(SubEstatusConciliacionBuilder
				.buildSubEstatusConciliacionDTOFromSubEstatusConciliacion(subEstatusConciliacion.get()));

		// Se valida que la conciliacion no exista
		Conciliacion conciliacionBD = conciliacionRepository
				.findByEntidadIdAndCuentaIdAndCreatedDate(entidad.get().getId(), cuenta.get().getId(), new Date());
		if (conciliacionBD != null) {
			throw new ConciliacionException("Ya existe una conciliacion para la entidad, cuenta para la fecha actual");
		}

		log.debug("Creando conciliacion...");

		// Se construye la conciliacion y se guarda
		Conciliacion conciliacion = ConciliacionBuilder
				.buildConciliacionFromConciliacionResponseSaveDTO(conciliacionRequestDTO);
		conciliacion = conciliacionRepository.save(conciliacion);

		// Se crea el objeto global vacio
		Global global = new Global();
		global.setConciliacion(conciliacion);
		global.setFecha(new Date());
		globalRepository.save(global);
		
		// Registro de actividad
		actividadGenericMethod.registroActividad(conciliacion.getId(), "Alta de conciliacion",
				TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.GENERACION_CONCILIACION, conciliacion.toString());

		return ConciliacionBuilder.buildConciliacionDTOFromConciliacionCuentaAndEntidad(conciliacion, cuenta.get(), entidad.get());

	}

	/**
	 * Metodo que actualiza la informacion de los movimientos en transito y
	 * movimientos de la comisión.
	 */
	@Override
	@Transactional
	public ActualizaionConciliacionRequestDTO actualizaConciliacion(
			ActualizaionConciliacionRequestDTO actualizaionConciliacionRequestDTO) {

		// Validación del request
		if (actualizaionConciliacionRequestDTO == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);

		if (actualizaionConciliacionRequestDTO.getFolio() < 1)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);

		// if (actualizaionConciliacionRequestDTO.getComisiones() == null)
		// throw new
		// ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);

		if (actualizaionConciliacionRequestDTO.getComisiones() != null) {
			for (ComisionesRequestDTO comisiones : actualizaionConciliacionRequestDTO.getComisiones()) {
				if (comisiones.getDescripcion() == null || comisiones.getDescripcion().isEmpty()
						|| comisiones.getDescripcion().equals("") || comisiones.getEstatus() == null
						|| comisiones.getFecha() == null || comisiones.getId() < 1 || comisiones.getMonto() == null)
					throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
			}
		}

		// if (actualizaionConciliacionRequestDTO.getMovimientosTransito() == null)
		// throw new
		// ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		if (actualizaionConciliacionRequestDTO.getMovimientosTransito() != null) {
			for (MovTransitoRequestDTO movimientosTransito : actualizaionConciliacionRequestDTO
					.getMovimientosTransito()) {
				if (movimientosTransito.getId() < 1 || movimientosTransito.getTipo() == null)
					throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
			}
		}

		// Búsqueda del folio en la tabla de to_conciliacion
		Conciliacion conciliacion = conciliacionHelper
				.getConciliacionByFolio(actualizaionConciliacionRequestDTO.getFolio());

//		// Búsqueda del folio en la tabla de to_movimiento_conciliacion en base al
//		// folio.
//		MovimientoConciliacion movimientoConciliacion = movimientoConciliacionRepository
//				.findByIdMovimientoConciliacion(conciliacion.getId());
//		if (movimientoConciliacion == null || movimientoConciliacion.getId() == null)
//			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		List<Integer> lst = new ArrayList<>();
		Map<Integer, MovTransitoRequestDTO> map = new HashMap<>();
		for (MovTransitoRequestDTO movimientosTransito : actualizaionConciliacionRequestDTO.getMovimientosTransito()) {
			lst.add(movimientosTransito.getId());
			map.put(movimientosTransito.getId(), movimientosTransito);
		}

		List<MovimientoTransito> movimientoTransito = movimientoTransitoRepository.findByIds(lst);
		if (movimientoTransito == null || movimientoTransito.isEmpty())
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		for (MovimientoTransito mT : movimientoTransito) {
			mT.setTipoContratoDesc(map.get(mT.getId()).getTipo());
		}
		movimientoTransitoRepository.saveAll(movimientoTransito);

		List<Integer> lstC = new ArrayList<>();
		Map<Integer, ComisionesRequestDTO> mapC = new HashMap<>();
		for (ComisionesRequestDTO movimientoComision : actualizaionConciliacionRequestDTO.getComisiones()) {
			lstC.add(movimientoComision.getId());
			mapC.put(movimientoComision.getId(), movimientoComision);
		}

		List<MovimientoComision> movimientosComision = movimientoComisionRepository.findByIds(lstC);
		if (movimientosComision == null || movimientosComision.isEmpty())
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		for (MovimientoComision mC : movimientosComision) {
			mC.setDescripcion(mapC.get(mC.getId()).getDescripcion());
			mC.setEstatus(mapC.get(mC.getId()).getEstatus());
			mC.setFechaOperacion(mapC.get(mC.getId()).getFecha());
			mC.setMonto(mapC.get(mC.getId()).getMonto());
		}
		movimientoComisionRepository.saveAll(movimientosComision);

		// Registro de actividad
		actividadGenericMethod.registroActividad(conciliacion.getId(), "Actualizacion de conciliacion",
				TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.ACTUALIZAR_CONCILIACION,
				actualizaionConciliacionRequestDTO.toString());

		return actualizaionConciliacionRequestDTO;
	}

	/**
	 * Metodo que realiza una busqueda a partir de un objeto de tipo
	 * ConsultaConciliacionRequestDTO devolviendo como resultado una lista de tipo
	 * ConsultaConciliacionDTO.
	 */
	@Override
	public List<ConsultaConciliacionDTO> consulta(ConsultaConciliacionRequestDTO consultaConciliacionRequestDTO) {

		// Validación de la fecha final no sea menor que la fecha inicial.
		if (consultaConciliacionRequestDTO.getFechaDesde() != null
				&& consultaConciliacionRequestDTO.getFechaHasta() != null) {
			if (consultaConciliacionRequestDTO.getFechaHasta().before(consultaConciliacionRequestDTO.getFechaDesde()))
				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		}

		// Búsqueda y validación del idEntidad.
		if (consultaConciliacionRequestDTO.getIdEntidad() != null
				&& consultaConciliacionRequestDTO.getIdEntidad() > 0) {
			Optional<Entidad> entidad = entidadRepository.findById(consultaConciliacionRequestDTO.getIdEntidad());
			if (!entidad.isPresent()) {
				throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);
			}
		}

		// Búsqueda del estatus de la conciliacion a partir de idEstatus.
		if (consultaConciliacionRequestDTO.getIdEstatus() != null
				&& consultaConciliacionRequestDTO.getIdEstatus() > 0) {
			Optional<EstatusConciliacion> estatusConciliacion = estatusConciliacionRepository
					.findById(consultaConciliacionRequestDTO.getIdEstatus());
			if (!estatusConciliacion.isPresent())
				throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);
		}

		// Registro de actividad
		actividadGenericMethod.registroActividad(consultaConciliacionRequestDTO.getFolio(), "Consulta de conciliacion",
				TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.CONSULTA_CONCILIACION,
				consultaConciliacionRequestDTO.toString());

		return ConciliacionBuilder.buildConsultaConciliacionDTOListFromConciliacionList(
				conciliacionRepository.findByFolioAndIdEntidadAndIdEstatusAndFecha(
						consultaConciliacionRequestDTO.getFolio(), consultaConciliacionRequestDTO.getIdEntidad(),
						consultaConciliacionRequestDTO.getIdEstatus(), consultaConciliacionRequestDTO.getFechaDesde(),
						consultaConciliacionRequestDTO.getFechaHasta()));
	}

	/**
	 * Metodo que consulta a partir del folio la conciliacion con los siguientes
	 * objetos: el estatus de la conciliacion, el sub estatus de la conciliacion, la
	 * entidad, la cuenta, reportes (Midas, Proveedor y estado de cuenta), global,
	 * devoluciones, movimientos en transito y comisiones
	 */
	@Override
	public ConciliacionDTOList consultaFolio(Integer folio) {

		// Validación del folio en el request.
		if (folio == null || folio < 1)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);

		// Búsqueda de la conciliación a partir del folio.
		Conciliacion conciliacion = conciliacionHelper.getConciliacionByFolio(folio);

		// Búsqueda de los movimientos en devolución a partir del folio
		List<MovimientoDevolucion> mD = movimientoDevolucionRepository.findByIdConciliacion(folio);

		// Búsqueda de los movimientos en transito a partir del folio.
		List<MovimientoTransito> mT = movimientoTransitoRepository.findByIdConciliacion(folio);

		// Búsqueda de los movimientos en comisión a partir del folio.
		List<MovimientoComision> mC = movimientoComisionRepository.findByIdConciliacion(folio);

		// Búsqueda de los reporte a partir del folio.
		List<Reporte> reporte = reporteRepository.findByIdConciliacion(folio);

		// Registro de actividad
		actividadGenericMethod.registroActividad(folio, "Consulta de folio conciliacion", TipoActividadEnum.ACTIVIDAD,
				SubTipoActividadEnum.CONSULTA_CONCILIACION, conciliacion.toString());

		return ConciliacionBuilder.buildConciliacionDTOListFromConciliacion(conciliacion, reporte,
				MovimientoDevolucionBuilder.buildDevolucionConDTOListFromMovimientoDevolucionList(mD),
				MovimientosTransitoBuilder.buildMovTransitoDTOListFromMovimientoTransitoList(mT),
				MovimientoComisionBuilder.buildComisionesDTOListFromMovimientoComisionList(mC));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService#getById(
	 * java.lang.Integer)
	 */
	public Conciliacion getById(Integer idConciliacion) {
		return conciliacionHelper.getConciliacionByFolio(idConciliacion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService#
	 * enviarConciliacion(java.lang.Integer, java.lang.String)
	 */
	@Override
	@Transactional
	public void enviarConciliacion(Integer idConciliacion, String usuario) {

		// Validar conciliacion y actualizar estatus
		try {
			Conciliacion conciliacion = conciliacionHelper.getConciliacionByFolio(idConciliacion);

			// Verificar si es al momento de cerrar la conciliacion
			if (conciliacion.getEstatus() == null
					|| conciliacion.getEstatus().getId() != ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO) {
				throw new ConciliacionException("La conciliacion tiene un estatus incorrecto");
			}

			// Verificar que se encuentra en el sub estatus correcto
			List<Long> idsSubEstatusIncorrectos = new ArrayList<Long>();
			idsSubEstatusIncorrectos.add(ConciliacionConstants.SUBESTATUS_CONCILIACION_CREADA.longValue());
			idsSubEstatusIncorrectos.add(ConciliacionConstants.SUBESTATUS_CONCILIACION_FINALIZADA.longValue());
			if (conciliacion.getSubEstatus() == null
					|| idsSubEstatusIncorrectos.contains(conciliacion.getSubEstatus().getId())) {
				throw new ConciliacionException("La conciliacion tiene un sub estatus incorrecto");
			}

			conciliacion
					.setSubEstatus(new SubEstatusConciliacion(ConciliacionConstants.SUBESTATUS_CONCILIACION_ENVIADA));
			conciliacion.setLastModifiedBy(usuario);
			conciliacion.setLastModifiedDate(new Date());

			conciliacionRepository.save(conciliacion);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al actualizar los ids de asiento contable");
		}

	}

	/**
	 * Actualiza el sub estatus de una conciliacion por folio
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void actualizaSubEstatusConciliacion(ActualizarSubEstatusRequestDTO actualizarSubEstatusRequestDTO,
			String usuario) {
		// Se obtienen: El id del estatus conciliacion el orden del mismo y el roden del
		// subestatus de acuerdo al id de subestatus especificado como parametro
		// mediante un query nativo
		Map<String, Object> map = conciliacionRepository
				.findIdEstatusConciliacion(actualizarSubEstatusRequestDTO.getIdSubEstatus());
		if (null == map || null == map.get("estatus"))
			throw new ConciliacionException(ConciliacionConstants.NO_STATUS_FOR_SUCH_SUB_STATUS);
		// Se obtienen: el id del estatus conciliacion, el orden del mismo y el orden
		// del subestatus por folio de conciliacion mediante un query nativo
		Map<String, Object> currenOrders = conciliacionRepository
				.findOrderSubstatusAndStatusByFolio(actualizarSubEstatusRequestDTO.getFolio());
		if (null == currenOrders || null == currenOrders.get("sub_estatus_order"))
			throw new ConciliacionException(ConciliacionConstants.ERROR_GETTING_CURRENT_SUB_STATUS);
		// Se valida que el orden del estatus actual no sea mayor al orden dele status
		// que se va a actualizar
		if (Integer.parseInt(currenOrders.get("estatus_order").toString()) > Integer
				.parseInt(map.get("estatus_order").toString()))
			throw new ConciliacionException(ConciliacionConstants.WRONG_ORDER_SUB_STATUS);
		// Se actualiza el sub estatus de la conciliacion al que se recibio como
		// parametro, adicionalmente se actualizan los campos createdBy y createdDate
		conciliacionRepository.actualizaSubEstatusConciliacion(actualizarSubEstatusRequestDTO.getFolio(),
				new SubEstatusConciliacion(actualizarSubEstatusRequestDTO.getIdSubEstatus()), usuario, new Date(),
				new EstatusConciliacion(Integer.parseInt(map.get("estatus").toString())));
		// Registro de actividad
		actividadGenericMethod.registroActividad(actualizarSubEstatusRequestDTO.getFolio(),
				"Actualizacion de subEstatus de conciliacion", TipoActividadEnum.ACTIVIDAD,
				SubTipoActividadEnum.ACTUALIZACION_ESTATUS_CONCILIACION, actualizarSubEstatusRequestDTO.toString());
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
		if (null != resumenConciliacionRequestDTO.getFechaInicial()
				&& null != resumenConciliacionRequestDTO.getFechaFinal()) {
			res = conciliacionRepository.resumenConciliaciones(resumenConciliacionRequestDTO.getFechaInicial(),
					resumenConciliacionRequestDTO.getFechaFinal(), ConciliacionConstants.CONCILIACION_EN_PROCESO_VALUE,
					ConciliacionConstants.DEVOLUCION_LIQUIDAD_VALUE);
		} else {
			conciliacionRepository.resumenConciliaciones(ConciliacionConstants.CONCILIACION_EN_PROCESO_VALUE,
					ConciliacionConstants.DEVOLUCION_LIQUIDAD_VALUE);
		}
		if (null != res && !res.isEmpty())
			resumenConciliacionDTO = new ResumenConciliacionDTO(res.get("en_proceso").longValue(),
					res.get("dev_liquidadas").longValue(), res.get("conc_totales").longValue());
		if (null == resumenConciliacionDTO)
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND);
		return resumenConciliacionDTO;
	}

	/**
	 * Consulta actividades por un rango de fechas y folio de conciliacion
	 * opcionales, de no tener folio de conciliacion ni fecha regresa los 10 ultimos
	 * registros
	 */
	@Override
	public List<ConsultaActividadDTO> consultaActividades(ConsultaActividadesRequest consultaActividadesRequest) {
		List<ConsultaActividadDTO> consultaActividadDTOList = null;
		// Ningun atributo es nulo
		if (null != consultaActividadesRequest.getFolio() && null != consultaActividadesRequest.getFechaDesde()
				&& null != consultaActividadesRequest.getFechaHasta())
			consultaActividadDTOList = actividadRepository.findByFolioFechaDesdeAndFechaHasta(
					consultaActividadesRequest.getFolio(), consultaActividadesRequest.getFechaDesde(),
					consultaActividadesRequest.getFechaHasta());
		// La fechaHasta es nula
		else if (null != consultaActividadesRequest.getFolio() && null != consultaActividadesRequest.getFechaDesde())
			consultaActividadDTOList = actividadRepository.findByFolioAndFechaDesde(
					consultaActividadesRequest.getFolio(), consultaActividadesRequest.getFechaDesde());
		// La fechaDesde es nula
		else if (null != consultaActividadesRequest.getFolio() && null != consultaActividadesRequest.getFechaHasta())
			consultaActividadDTOList = actividadRepository.findByFolioAndFechaHasta(
					consultaActividadesRequest.getFolio(), consultaActividadesRequest.getFechaHasta());
		// Ambas fechas son nulas
		else if (null != consultaActividadesRequest.getFolio())
			consultaActividadDTOList = actividadRepository.findByFolio(consultaActividadesRequest.getFolio());
		// Todos los atributos son nulos se consultan los ultimos 10 por default
		else
			consultaActividadDTOList = actividadRepository.nGetTopXActividades(10);
		return consultaActividadDTOList;
	}

	@Override
	public List<MovTransitoDTO> consultaMovimientosTransito(Integer folio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void solicitarPagos(SolicitarPagosRequestDTO solicitarPagosRequestDTO, String usuario) {
		// TODO Auto-generated method stub
	}

	@Transactional
	public void actualizarPS(ActualizarIdPSRequest actualizarIdPSRequest, String usuario) {

		// Actualizar estatus de la conciliacion
		// Se valida que se pueda realizar la transicion
		// ActualizarSubEstatusRequestDTO actualizarRequest = new
		// ActualizarSubEstatusRequestDTO();
		// actualizarRequest.setIdSubEstatus(ConciliacionConstants.SUBESTATUS_CONCILIACION_CREADA);
		// actualizarRequest.setFolio(actualizarIdPSRequest.getFolio());

		// actualizaSubEstatusConciliacion(actualizarRequest, usuario);

		// Actualizar los ids en la conciliacion
		try {
			Conciliacion conciliacion = conciliacionHelper.getConciliacionByFolio(actualizarIdPSRequest.getFolio());

			// Verificar si es al momento de cerrar la conciliacion
			if (conciliacion.getEstatus() == null
					|| conciliacion.getEstatus().getId() != ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO) {
				throw new ConciliacionException("La conciliacion tiene un estatus incorrecto");
			}

			// Verificar que se encuentra en el sub estatus correcto
			List<Long> idsSubEstatusIncorrectos = new ArrayList<Long>();
			idsSubEstatusIncorrectos.add(ConciliacionConstants.SUBESTATUS_CONCILIACION_CREADA.longValue());
			idsSubEstatusIncorrectos.add(ConciliacionConstants.SUBESTATUS_CONCILIACION_FINALIZADA.longValue());
			if (conciliacion.getSubEstatus() == null
					|| idsSubEstatusIncorrectos.contains(conciliacion.getSubEstatus().getId())) {
				throw new ConciliacionException("La conciliacion tiene un sub estatus incorrecto");
			}

			conciliacion.setLastModifiedBy(usuario);
			conciliacion.setLastModifiedDate(new Date());
			conciliacion.setIdAsientoContable(actualizarIdPSRequest.getIdAsientoContable());
			conciliacion.setIdPolizaTesoreria(actualizarIdPSRequest.getIdPolizaTesoreria());

			// TODO: Al recibir ambos ids , se actualiza la conciliacion a finalizada

			conciliacionRepository.save(conciliacion);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al actualizar los ids de asiento contable");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService#
	 * generarConciliacion(java.lang.Integer, java.lang.String)
	 */
	@Transactional
	public void generarConciliacion(Integer idConciliacion, String lastModifiedBy) throws ConciliacionException {

		// Validación del request
		if (idConciliacion == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);

		if (lastModifiedBy == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);

		// Obtiene todos los reportes de la bd generados hasta el momento
		List<Reporte> reportes = reporteRepository.findByIdConciliacion(idConciliacion);
		if (reportes == null || reportes.size() == 0) {
			throw new ConciliacionException("No se tiene disponible ningun reporte para generar la conciliacion");
		}

		// Al menos 2 reportes
		if (reportes.size() == 1) {
			throw new ConciliacionException(
					"Se requiere al menos 2 reportes para generar la conciliacion. Reporte recibido: "
							+ reportes.get(0).getTipo());
		}

		// Notificar cambios o alta de reportes, si existen...
		ReporteObservable reporteObservable = new ReporteObservable(reportes, idConciliacion);
		reporteObservable.addObserver(reporteObserver);
		reporteObservable.notifyObservers();

		// Registro de actividad
		// TODO: Remover comentario
//		actividadGenericMethod.registroActividad(folio, "Generar conciliacion", TipoActividadEnum.ACTIVIDAD,
//				SubTipoActividadEnum.ACTIVIDAD_CONCILIACION, folio.toString());

	}

}
