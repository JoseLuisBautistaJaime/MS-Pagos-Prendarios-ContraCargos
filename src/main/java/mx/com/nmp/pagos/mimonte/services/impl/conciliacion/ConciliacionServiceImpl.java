/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.ConciliacionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientoComisionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientoDevolucionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosTransitoBuilder;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.CuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTOList;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionResponseSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoRequestDTO;
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

	
	/**
	 * Metodo que da de alta una conciliación regresando un folio a partir de un objeto de tipo ConciliacionResponseSaveDTO
	 */
	@Override
	@Transactional
	public ConciliacionDTO saveConciliacion(ConciliacionResponseSaveDTO conciliacionRequestDTO, String createdBy) {

		// Validación del objeto ConciliacionRequestDTO
		if (conciliacionRequestDTO.getCuenta() == null || conciliacionRequestDTO.getCuenta().getId() < 1)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		if (conciliacionRequestDTO.getEntidad() == null || conciliacionRequestDTO.getEntidad().getId() < 1)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);

		// Validación del atributo createdBy
		if (createdBy == null || createdBy.isEmpty() || createdBy.equals(""))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);

		// Búsqueda y validacion del idCuenta.
		Cuenta cuenta = cuentaRepository.findById(conciliacionRequestDTO.getCuenta().getId()).isPresent()
				? cuentaRepository.findById(conciliacionRequestDTO.getCuenta().getId()).get()
				: null;
		if (cuenta.getId() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		// Búsqueda y validación del idEntidad.
		Entidad entidad = entidadRepository.findById(conciliacionRequestDTO.getEntidad().getId()).isPresent()
				? entidadRepository.findById(conciliacionRequestDTO.getEntidad().getId()).get()
				: null;
		if (entidad.getId() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		// Búsqueda para setear por default para el estatus de conciliación En Proceso.
		EstatusConciliacion estatusConciliacion = estatusConciliacionRepository
				.findByNombre(ConciliacionConstants.EN_PROCESO);
		if (estatusConciliacion == null || estatusConciliacion.getId() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		conciliacionRequestDTO.setEstatus(new EstatusConciliacionDTO(estatusConciliacion.getId()));

		SubEstatusConciliacion subEstatusConciliacion = subEstatusConciliacionRepository
				.findByDescripcion(ConciliacionConstants.CREADA);
		if (subEstatusConciliacion == null || subEstatusConciliacion.getId() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		conciliacionRequestDTO.setSubEstatus(new SubEstatusConciliacionDTO(subEstatusConciliacion.getId()));

		Conciliacion conciliacion = conciliacionRepository
				.save(ConciliacionBuilder.buildConciliacionFromConciliacionResponseSaveDTO(conciliacionRequestDTO));

		conciliacion.setEstatus(estatusConciliacion);
		conciliacion.setCuenta(cuenta);
		conciliacion.setEntidad(entidad);
		conciliacion.setSubEstatus(subEstatusConciliacion);

		return ConciliacionBuilder.buildConciliacionDTOFromConciliacion(conciliacion);

	}

	/**
	 * Metodo que actualiza la informacion de los movimientos en transito y movimientos de la comisión.
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
		EstatusConciliacion estatusConciliacion = estatusConciliacionRepository.findById(consultaConciliacionRequestDTO.getIdEstatus()).isPresent()
				? estatusConciliacionRepository.findById(consultaConciliacionRequestDTO.getIdEstatus()).get()
						: null;
		if (estatusConciliacion.getId() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		// Búsqueda del folio en la tabla de to_conciliacion
		Conciliacion conciliacion = conciliacionRepository.findByFolio(consultaConciliacionRequestDTO.getFolio());
		if (conciliacion == null || conciliacion.getId() == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

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

		return ConciliacionBuilder.buildConciliacionDTOListFromConciliacion(conciliacion, reporte, global,
				MovimientoDevolucionBuilder.buildDevolucionConDTOListFromMovimientoDevolucionList(mD),
				MovimientosTransitoBuilder.buildMovTransitoDTOListFromMovimientoTransitoList(mT),
				MovimientoComisionBuilder.buildComisionesDTOListFromMovimientoComisionList(mC));
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService#getById(java.lang.Integer)
	 */
	public Conciliacion getById(Integer idConciliacion) {
		return conciliacionRepository.findByFolio(idConciliacion);
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService#generarConciliacion(java.lang.Integer, java.lang.String)
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
		if (reportes == null) {
			throw new ConciliacionException("No se tiene disponible ningun reporte para generar la conciliacion");
		}

		// Al menos 2 reportes
		if (reportes.size() == 1) {
			throw new ConciliacionException("Se requiere al menos 2 reportes para generar la conciliacion. Reporte recibido: " + reportes.get(0).getTipo());
		}


		// Notificar cambios o alta de reportes, si existen...
		ReporteObservable reporteObservable = new ReporteObservable(reportes, idConciliacion);
		reporteObservable.addObserver(reporteObserver);
		reporteObservable.notifyObservers();

	}

}
