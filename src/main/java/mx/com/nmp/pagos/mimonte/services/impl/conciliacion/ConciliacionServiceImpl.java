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
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.CuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ActividadRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstatusConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.GlobalRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoComisionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoConciliacionRepository;
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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SubEstatusConciliacionDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
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
	private MovimientoConciliacionRepository movimientoConciliacionRepository;

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
		if (conciliacionRequestDTO.getCuenta() == null || conciliacionRequestDTO.getCuenta().getId() == null || conciliacionRequestDTO.getCuenta().getId() < 1) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		}

		if (conciliacionRequestDTO.getEntidad() == null || conciliacionRequestDTO.getEntidad().getId() == null || conciliacionRequestDTO.getEntidad().getId() < 1) {
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

		// Se obtiene el estatus y el sub estatus de la conciliacion
		Optional<EstatusConciliacion> estatusConciliacion = estatusConciliacionRepository.findById(ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);
		if (!estatusConciliacion.isPresent()) {
			throw new ConciliacionException("Estatus conciliacion en proceso no encontrado");
		}
		Optional<SubEstatusConciliacion> subEstatusConciliacion = subEstatusConciliacionRepository.findById(ConciliacionConstants.SUBESTATUS_CONCILIACION_CREADA);
		if (!subEstatusConciliacion.isPresent()) {
			throw new ConciliacionException("Sub estatus conciliacion creada no encontrado");
		}
		conciliacionRequestDTO.setEstatus(EstatusConciliacionBuilder.buildEstatusConciliacionDTOFromEstatusConciliacion(estatusConciliacion.get()));
		conciliacionRequestDTO.setSubEstatus(SubEstatusConciliacionBuilder.buildSubEstatusConciliacionDTOFromSubEstatusConciliacion(subEstatusConciliacion.get()));

		// Se construye la conciliacion y se guarda
		Conciliacion conciliacion = ConciliacionBuilder.buildConciliacionFromConciliacionResponseSaveDTO(conciliacionRequestDTO);
		conciliacion = conciliacionRepository.save(conciliacion);

		// Registro de actividad
		actividadGenericMethod.registroActividad(conciliacion.getId(), "Alta de conciliacion",
				TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.GENERACION_CONCILIACION, conciliacion.toString());

		return ConciliacionBuilder.buildConciliacionDTOFromConciliacion(conciliacion);

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

		if (actualizaionConciliacionRequestDTO.getComisiones() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);

		for (ComisionesRequestDTO comisiones : actualizaionConciliacionRequestDTO.getComisiones()) {
			if (comisiones.getDescripcion() == null || comisiones.getDescripcion().isEmpty()
					|| comisiones.getDescripcion().equals("") || comisiones.getEstatus() == null
					|| comisiones.getFecha() == null || comisiones.getId() < 1 || comisiones.getMonto() == null)
				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		}

		if (actualizaionConciliacionRequestDTO.getMovimientosTransito() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);

		for (MovTransitoRequestDTO movimientosTransito : actualizaionConciliacionRequestDTO.getMovimientosTransito()) {
			if (movimientosTransito.getId() < 1 || movimientosTransito.getTipo() == null)
				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		}

		// Búsqueda del folio en la tabla de to_conciliacion
		Conciliacion conciliacion = conciliacionRepository.findByFolio(actualizaionConciliacionRequestDTO.getFolio());
		if (conciliacion == null || conciliacion.getId() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

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
		movimientoTransitoRepository.flush();

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
		movimientoComisionRepository.flush();

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

		// Validacion del objeto request de tipo ConsultaConciliacionRequestDTO
		if (consultaConciliacionRequestDTO.getIdEntidad() == null || consultaConciliacionRequestDTO.getIdEntidad() < 1
				|| consultaConciliacionRequestDTO.getIdEstatus() == null
				|| consultaConciliacionRequestDTO.getIdEstatus() < 1
				|| consultaConciliacionRequestDTO.getFolio() == null || consultaConciliacionRequestDTO.getFolio() < 1
				|| consultaConciliacionRequestDTO.getFechaDesde() == null
				|| consultaConciliacionRequestDTO.getFechaHasta() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);

		// Validación de la fecha final no sea menor que la fecha inicial.
		if (consultaConciliacionRequestDTO.getFechaHasta().before(consultaConciliacionRequestDTO.getFechaDesde()))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);

		// Búsqueda y validación del idEntidad.
		Entidad entidad = entidadRepository.findById(consultaConciliacionRequestDTO.getIdEntidad()).isPresent()
				? entidadRepository.findById(consultaConciliacionRequestDTO.getIdEntidad()).get()
				: null;
		if (entidad == null || entidad.getId() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		// Búsqueda del estatus de la conciliacion a partir de idEstatus.
		EstatusConciliacion estatusConciliacion = estatusConciliacionRepository
				.findById(consultaConciliacionRequestDTO.getIdEstatus()).isPresent()
						? estatusConciliacionRepository.findById(consultaConciliacionRequestDTO.getIdEstatus()).get()
						: null;
		if (estatusConciliacion.getId() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		// Búsqueda del folio en la tabla de to_conciliacion
		Conciliacion conciliacion = conciliacionRepository.findByFolio(consultaConciliacionRequestDTO.getFolio());
		if (conciliacion == null || conciliacion.getId() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

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
		Conciliacion conciliacion = conciliacionRepository.findByFolio(folio);
		if (conciliacion == null || conciliacion.getId() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		// Búsqueda de los movimientos en devolución a partir del folio
		List<MovimientoDevolucion> mD = movimientoDevolucionRepository.findByIdConciliacion(folio);
		if (mD == null || mD.isEmpty())
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		// Búsqueda de los movimientos en transito a partir del folio.
		List<MovimientoTransito> mT = movimientoTransitoRepository.findByIdConciliacion(folio);
		if (mT == null || mT.isEmpty())
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		// Búsqueda de los movimientos en comisión a partir del folio.
		List<MovimientoComision> mC = movimientoComisionRepository.findByIdConciliacion(folio);
		if (mC == null || mC.isEmpty())
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		// Búsqueda de los reporte a partir del folio.
		List<Reporte> reporte = reporteRepository.findByIdConciliacion(folio);
		if (reporte == null || reporte.isEmpty())
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		// Búsqueda de los globales a partir del folio.
		Global global = globalRepository.findByIdConciliacion(folio);
		if (global == null || global.getId() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		// Registro de actividad
		actividadGenericMethod.registroActividad(folio, "Consulta de folio conciliacion", TipoActividadEnum.ACTIVIDAD,
				SubTipoActividadEnum.CONSULTA_CONCILIACION, conciliacion.toString());

		return ConciliacionBuilder.buildConciliacionDTOListFromConciliacion(conciliacion, reporte, global,
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
		return conciliacionRepository.findByFolio(idConciliacion);
	}

	@Override
	public void generarConciliacion(Integer idConciliacion, String usuario, String urlCallBack) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enviarConciliacion(Integer idConciliacion, String usuario) {
		// TODO Auto-generated method stub

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void actualizaSubEstatusConciliacion(ActualizarSubEstatusRequestDTO actualizarSubEstatusRequestDTO,
			String usuario) {
		Map<String, Object> map = conciliacionRepository
				.findIdEstatusConciliacion(actualizarSubEstatusRequestDTO.getIdSubEstatus());
		if (null == map || null == map.get("estatus"))
			throw new ConciliacionException(ConciliacionConstants.NO_STATUS_FOR_SUCH_SUB_STATUS);
		Map<String, Object> currenOrders = conciliacionRepository.findOrderSubstatusAndStatusByFolio(actualizarSubEstatusRequestDTO.getFolio());
		if(null == currenOrders || null == currenOrders.get("sub_estatus_order"))
			throw new ConciliacionException(ConciliacionConstants.ERROR_GETTING_CURRENT_SUB_STATUS);
		if(Integer.parseInt(currenOrders.get("sub_estatus_order").toString()) > Integer.parseInt(map.get("sub_estatus_order").toString()))
			throw new ConciliacionException(ConciliacionConstants.WRONG_ORDER_SUB_STATUS);
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
		res = (null != resumenConciliacionRequestDTO.getFechaInicial()
				&& null != resumenConciliacionRequestDTO.getFechaFinal())
						? conciliacionRepository.resumenConciliaciones(resumenConciliacionRequestDTO.getFechaInicial(),
								resumenConciliacionRequestDTO.getFechaFinal(),
								ConciliacionConstants.CONCILIACION_EN_PROCESO_VALUE,
								ConciliacionConstants.DEVOLUCION_LIQUIDAD_VALUE)
						: conciliacionRepository.resumenConciliaciones(
								ConciliacionConstants.CONCILIACION_EN_PROCESO_VALUE,
								ConciliacionConstants.DEVOLUCION_LIQUIDAD_VALUE);
		if (null != res && !res.isEmpty())
			resumenConciliacionDTO = new ResumenConciliacionDTO(res.get("en_proceso").longValue(), res.get("dev_liquidadas").longValue(),
					res.get("conc_totales").longValue());

		// Registro de actividad
		actividadGenericMethod.registroActividad(0, "Obtencion de resumen de conciliacion", TipoActividadEnum.ACTIVIDAD,
				SubTipoActividadEnum.CONSULTA_CONCILIACION, resumenConciliacionRequestDTO.toString());

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
			consultaActividadDTOList = actividadRepository.findByFilter(consultaActividadesRequest.getFolio(),
					consultaActividadesRequest.getFechaDesde(), consultaActividadesRequest.getFechaHasta());
		// La fechaHasta es nula
		else if (null != consultaActividadesRequest.getFolio() && null != consultaActividadesRequest.getFechaDesde())
			consultaActividadDTOList = actividadRepository.findByFilterF(consultaActividadesRequest.getFolio(),
					consultaActividadesRequest.getFechaDesde());
		// La fechaDesde es nula
		else if (null != consultaActividadesRequest.getFolio() && null != consultaActividadesRequest.getFechaHasta())
			consultaActividadDTOList = actividadRepository.findByFilterF2(consultaActividadesRequest.getFolio(),
					consultaActividadesRequest.getFechaHasta());
		// Ambas fechas son nulas
		else if (null != consultaActividadesRequest.getFolio())
			consultaActividadDTOList = actividadRepository.findByFilter(consultaActividadesRequest.getFolio());
		// Todos los atributos son nulos
		else
			consultaActividadDTOList = actividadRepository.nGetTopTenActividades();
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

	public void actualizarPS(ActualizarIdPSRequest actualizarIdPSRequest, String usuario) {
		// TODO
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
