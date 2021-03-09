/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.helper.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.BonificacionesBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.ConciliacionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dao.CuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoBonificacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoProveedorRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ReporteRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.MovimientoJdbcRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionResponseSaveDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.helper.ConciliacionSemanalHelper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoBonificacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService;

/**
 * @name ConciliacionHelperImpl
 * @description Clase helper para la creacion de la conciliacion semanal
 *
 * @author Jorge Galvez
 * @creationDate 16/20/2019 17:34 hrs.
 * @version 0.1
 */
@Component
public class ConciliacionSemanalHelperImpl implements ConciliacionSemanalHelper {

	private static final Logger LOG = LoggerFactory.getLogger(ConciliacionSemanalHelperImpl.class);

	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



	@Autowired
	private ConciliacionService conciliacionService;

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private ReporteRepository reporteRepository;

	@Autowired
	private MovimientosMidasRepository movimientosMidasRepository;

	@Autowired
	private MovimientoProveedorRepository movimientoProveedorRepository;

	@Autowired
	private MovimientoBonificacionRepository movimientoBonificacionRepository;

	@Autowired
	private MovimientoJdbcRepository movimientoJdbcRepository;


	@Transactional
	public ConciliacionDTO crearConciliacionSemanal(Date fechaConciliacion, List<Long> conciliacionesIds, String createdBy) throws ConciliacionException {

		// Se obtiene la ultima conciliacion y se usa de base para generar la nueva
		Conciliacion original = conciliacionService.getById(conciliacionesIds.get(conciliacionesIds.size() - 1));

		Long idEntidad = original.getEntidad().getId();
		Long idCuenta = original.getCuenta().getId();
		String idCorresponsal = CorresponsalEnum.OXXO.getNombre();

		// Crear la nueva conciliacion
		ConciliacionDTO conciliacionNuevaDTO = crearConciliacion(idEntidad, idCuenta, idCorresponsal, fechaConciliacion, createdBy);

		// Se copian los movimientos de midas
		copiarMovimientosMidas(conciliacionNuevaDTO.getFolio(), conciliacionesIds, fechaConciliacion, createdBy);

		// Se copian los movimientos del proveedor
		copiarMovimientosProveedor(conciliacionNuevaDTO.getFolio(), conciliacionesIds, fechaConciliacion, createdBy);

		// Copiar Bonificaciones
		copiarMovimientosBonificacion(conciliacionNuevaDTO.getFolio(), conciliacionesIds, createdBy);

		// Se actualiza el subestatus de la conciliacion para dejarla en el estado correcto antes de la carga del estado de cuenta
		// TODO:

		return conciliacionNuevaDTO;
	}



	/////////////////////////////////////////////////
	// CONCIACION ///////////////////////////////////
	/////////////////////////////////////////////////

	/**
	 * 
	 * @param idEntidad
	 * @param idCuenta
	 * @param idCorresponsal
	 * @param fecha
	 * @param createdBy
	 * @return
	 * @throws ConciliacionException
	 */
	private ConciliacionDTO crearConciliacion(Long idEntidad, Long idCuenta, String idCorresponsal, Date fecha, String createdBy) throws ConciliacionException {
		ConciliacionDTO nuevaConciliacionDTO = null;
		try {

			// Se genera la nueva conciliacion
			LOG.debug("T>>> Inicia creacion de conciliacion semanal {}", sdf.format(new Date()));
			ConciliacionRequestDTO requestDTO =  new ConciliacionRequestDTO();
			requestDTO.setIdEntidad(idEntidad);
			requestDTO.setIdCuenta(idCuenta);
			requestDTO.setIdCorresponsal(idCorresponsal);

			ConciliacionResponseSaveDTO conciliacionResponseSaveDTO = ConciliacionBuilder
					.buildConciliacionResponseSaveDTOFromConciliacionRequestDTO(requestDTO, fecha, null, createdBy);

			nuevaConciliacionDTO = conciliacionService.saveConciliacion(conciliacionResponseSaveDTO, createdBy);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al crear la conciliacion semanal", CodigoError.NMP_PMIMONTE_0011);
		}
		return nuevaConciliacionDTO;
	}



	/////////////////////////////////////////////////
	// REPORTE //////////////////////////////////////
	/////////////////////////////////////////////////


	/**
	 * Crea un nuevo reporte para la carga de los movimientos
	 * @param idConciliacion
	 * @param tipoReporte
	 * @param fechaConciliacion
	 * @param createdBy
	 * @return
	 * @throws ConciliacionException
	 */
	private Reporte buildReporte(Long idConciliacion, TipoReporteEnum tipoReporte, Date fechaConciliacion, String createdBy) throws ConciliacionException {

		LOG.debug("T>>> Inicial persistencia reporte {}: {}", tipoReporte, sdf.format(new Date()));

		Reporte reporte = new Reporte();
		try {
			reporte.setConciliacion(this.conciliacionService.getById(idConciliacion));
			reporte.getConciliacion().setCuenta(this.cuentaRepository.getOne(reporte.getConciliacion().getCuenta().getId()));
			reporte.setCreatedBy(createdBy);
			reporte.setCreatedDate(new Date());
			reporte.setDisponible(true);
			reporte.setFechaDesde(fechaConciliacion);
			reporte.setFechaHasta(fechaConciliacion);
			reporte.setId(0);
			reporte.setLastModifiedBy(null);
			reporte.setLastModifiedDate(null);
			reporte.setTipo(tipoReporte);
			reporte = reporteRepository.save(reporte);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al crear el reporte " + tipoReporte + " para la conciliacion semanal", CodigoError.NMP_PMIMONTE_0011);
		}

		return reporte;
	}



	/////////////////////////////////////////////////
	// MIDAS ////////////////////////////////////////
	/////////////////////////////////////////////////


	/**
	 * Realiza la copia de los movimientos midas de la conciliacion origen a la conciliacion destino
	 * @param idConciliacionNueva
	 * @param idsConciliaciones
	 * @param fechaConciliacion
	 * @param createdBy
	 * @throws ConciliacionException
	 */
	private void copiarMovimientosMidas(Long idConciliacionNueva, List<Long> idsConciliaciones, Date fechaConciliacion, String createdBy) throws ConciliacionException {

		Reporte reporteMidas = buildReporte(idConciliacionNueva, TipoReporteEnum.MIDAS, fechaConciliacion, createdBy);

		try {
			for (Long idConciliacion : idsConciliaciones) {
	
				// Se persisten los movimientos midas
				LOG.debug("T>>> Inicia copia movimientos Midas: {} para la conciliacion {}", sdf.format(new Date()), idConciliacion);
				List<MovimientoMidas> movimientoMidasList = buildMovimientosMidas(idConciliacion, reporteMidas.getId());
				if (!CollectionUtils.isEmpty(movimientoMidasList)) {
					movimientoJdbcRepository.insertarLista(movimientoMidasList);
				}
				LOG.debug("T>>> Finaliza la copia de movimientos Midas: {} para la conciliacion {}", sdf.format(new Date()), idConciliacion);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al realizar la copia de los movimientos Midas para la conciliacion semanal", CodigoError.NMP_PMIMONTE_0011);
		}
	}


	/**
	 * Construye los movimientos midas en base a la conciliacion original
	 * @param idConciliacion
	 * @param idReporte
	 * @return
	 * @throws ConciliacionException
	 */
	private List<MovimientoMidas> buildMovimientosMidas(Long idConciliacion, Integer idReporte) throws ConciliacionException {
		List<MovimientoMidas> movsMidas = new ArrayList<MovimientoMidas>();
		try {
			LOG.debug("T>>> Se construyen movimientos midas: {} para la conciliacion {}", sdf.format(new Date()), idConciliacion);
			List<MovimientoMidas> movsMidasBD = movimientosMidasRepository.findByConciliacionId(idConciliacion);
			if (movsMidasBD != null && movsMidasBD.size() > 0) {
				for (MovimientoMidas movMidasBD : movsMidasBD) {
					movsMidas.add(MovimientosBuilder.buildMovimientoMidasFromMovimientoMidas(movMidasBD, idReporte.longValue()));
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al construir los movimientos midas", CodigoError.NMP_PMIMONTE_0011);
		}
		return movsMidas;
	}



	//////////////////////////////////////////////////
	// PROVEEDOR TRANSACCCIONAL /////////////////////
	/////////////////////////////////////////////////


	/**
	 * Realiza la copia de los movimientos proveedor de la conciliacion origen a la conciliacion destino
	 * @param idConciliacionNueva
	 * @param idsConciliaciones
	 * @param fechaConciliacion
	 * @param createdBy
	 * @throws ConciliacionException
	 */
	private void copiarMovimientosProveedor(Long idConciliacionNueva, List<Long> idsConciliaciones, Date fechaConciliacion, String createdBy) throws ConciliacionException {

		Reporte reporteProveedor = buildReporte(idConciliacionNueva, TipoReporteEnum.PROVEEDOR, fechaConciliacion, createdBy);

		try {
			for (Long idConciliacion : idsConciliaciones) {
	
				// Se persisten los movimientos proveedor
				LOG.debug("T>>> Inicia copia movimientos proveedor: {} para la conciliacion {}", sdf.format(new Date()), idConciliacion);
				List<MovimientoProveedor> movimientoProveedorList = buildMovimientosProveedor(idConciliacion, reporteProveedor.getId());
				if (!CollectionUtils.isEmpty(movimientoProveedorList)) {
					movimientoJdbcRepository.insertarLista(movimientoProveedorList);
				}
				LOG.debug("T>>> Finaliza la copia de movimientos proveedor: {} para la conciliacion {}", sdf.format(new Date()), idConciliacion);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al realizar la copia de los movimientos proveedor para la conciliacion semanal", CodigoError.NMP_PMIMONTE_0011);
		}
	}


	/**
	 * Construye los movimientos proveedor en base a la conciliacion original
	 * @param idConciliacion
	 * @param idReporte
	 * @return
	 * @throws ConciliacionException
	 */
	private List<MovimientoProveedor> buildMovimientosProveedor(Long idConciliacion, Integer idReporte) throws ConciliacionException {
		List<MovimientoProveedor> movsProveedor = new ArrayList<MovimientoProveedor>();
		try {
			LOG.debug("T>>> Se construyen movimientos proveedor: {} para la conciliacion {}", sdf.format(new Date()), idConciliacion);
			List<MovimientoProveedor> movsProveedorBD = movimientoProveedorRepository.findByReporteConciliacionId(idConciliacion, TipoReporteEnum.PROVEEDOR);
			if (movsProveedorBD != null && movsProveedorBD.size() > 0) {
				for (MovimientoProveedor movProveedorBD : movsProveedorBD) {
					movsProveedor.add(MovimientosBuilder.buildMovimientoProveedorFromMovimientoProveedor(movProveedorBD, idReporte));
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al construir los movimientos proveedor", CodigoError.NMP_PMIMONTE_0011);
		}
		return movsProveedor;
	}


	/**
	 * Se copian los movimientos de bonificaciones de las conciliaciones
	 * @param folio
	 * @param conciliacionesIds
	 * @param createdBy
	 */
	private void copiarMovimientosBonificacion(Long idConciliacion, List<Long> conciliacionesIds, String createdBy) throws ConciliacionException {
		List<MovimientoBonificacion> movsBonificaciones = buildBonificaciones(idConciliacion, conciliacionesIds);
		if (movsBonificaciones != null && movsBonificaciones.size() > 0) {
			try {
				this.movimientoBonificacionRepository.saveAll(movsBonificaciones);
			}
			catch (Exception ex) {
				ex.printStackTrace();
				throw new ConciliacionException("Error guardar copiar los movimientos de bonificacion", CodigoError.NMP_PMIMONTE_0011);
			}
		}
	}



	/**
	 * Se construyen las bonificaciones
	 * @param idConciliacion
	 * @param conciliacionesIds
	 */
	private List<MovimientoBonificacion> buildBonificaciones(Long idConciliacion, List<Long> conciliacionesIds) throws ConciliacionException {
		List<MovimientoBonificacion> movsBonificaciones = new ArrayList<MovimientoBonificacion>();
		try {
			LOG.debug("T>>> Se construyen movimientos bonificaciones: {} para la conciliacion {}", sdf.format(new Date()), idConciliacion);
			if (conciliacionesIds != null && conciliacionesIds.size() > 0) {
				for (Long idConciliacionOriginal : conciliacionesIds) {
					List<MovimientoBonificacion> movsBonificacionesBD = movimientoBonificacionRepository.findByIdConciliacion(idConciliacionOriginal);
					if (movsBonificacionesBD != null && movsBonificacionesBD.size() > 0) {
						for (MovimientoBonificacion movBonificacionBD : movsBonificacionesBD) {
							movsBonificaciones.add(BonificacionesBuilder.buildBonificaciones(movBonificacionBD, idConciliacion));
						}
					}
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al construir los movimientos bonificaciones", CodigoError.NMP_PMIMONTE_0011);
		}
		return movsBonificaciones;
	}

}
