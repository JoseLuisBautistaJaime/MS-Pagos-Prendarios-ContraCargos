/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import mx.com.nmp.pagos.mimonte.ActividadGenericMethod;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ReporteRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.MovimientosException;
import mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @name MovimientosMidasService
 * @description Clase service que realiza operaciones con movimientos
 *              relacionados con movimientos midas
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:37 PM
 */
@Service("movimientosMidasService")
public class MovimientosMidasService {

	/**
	 * Utilizada para manipular los mensajes informativos y de error.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(MovimientosMidasService.class);

	/**
	 * Repository de movimientos midas
	 */
	@Autowired
	@Qualifier("movimientosMidasRepository")
	private MovimientosMidasRepository movimientosMidasRepository;

	/**
	 * Repository de Reporte
	 */
	@Autowired
	@Qualifier("reporteRepository")
	private ReporteRepository reporteRepository;

	/**
	 * Conciliacion Helper
	 */
	@Autowired
	private ConciliacionHelper conciliacionHelper;

	/**
	 * Registro de actividades
	 */
	@Autowired
	private ActividadGenericMethod actividadGenericMethod;

	public MovimientosMidasService() {
		super();
	}

	/**
	 * Regresa el total de registros de movimientos midas por id de conciliacion
	 * 
	 * @param idConciliacion
	 * @param estatus
	 * @return
	 */
	public Long countByConciliacionId(final Long idConciliacion, final Boolean estatus) {
		LOG.debug("countByConciliacionId {}, {}", idConciliacion, estatus);
		if (null != estatus)
			return movimientosMidasRepository.countByReporteConciliacionIdAndEstatus(idConciliacion, estatus);
		else
			return movimientosMidasRepository.countByReporteConciliacionId(idConciliacion);
	}


	/**
	 * Regresa una lista de movimientos midas
	 * 
	 * @param commonConciliacionRequestDTO
	 * @return
	 */
	public List<MovimientoMidasDTO> findByFolio(
			final CommonConciliacionEstatusRequestDTO commonConciliacionRequestDTO) {
//		@SuppressWarnings("deprecation")
//		Pageable pageable = new PageRequest(commonConciliacionRequestDTO.getPagina(),
//				commonConciliacionRequestDTO.getResultados());
		return MovimientosBuilder
				.buildMovimientoMidasDTOListFromMovimientoMidasList(null != commonConciliacionRequestDTO.getEstatus()
						? movimientosMidasRepository
								.findByReporteConciliacionIdAndEstatus(commonConciliacionRequestDTO.getFolio(),
										commonConciliacionRequestDTO.getEstatus()/* , pageable */)
						: movimientosMidasRepository
								.findByReporteConciliacionId(commonConciliacionRequestDTO.getFolio()));
	}

	/**
	 * Guarda una lista de movimientos de midas
	 * 
	 * @param movimientoProcesosNocturnosDTOList
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(MovimientoProcesosNocturnosListResponseDTO movimientoProcesosNocturnosDTOList,
			String userRequest) {

		// Se valida que exista la conciliacion
		Long folio = movimientoProcesosNocturnosDTOList.getFolio();
		LOG.debug("getConciliacionByFolio({},{})", folio, ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);
		Conciliacion conciliacion = this.conciliacionHelper.getConciliacionByFolio(folio,
				ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);

		if (conciliacion.getSubEstatus() == null || conciliacion.getSubEstatus().getId() == null ||
				!ConciliacionConstants.CON_SUB_ESTATUS_CARGA_MOV_PN.contains(conciliacion.getSubEstatus().getId())) {
			LOG.error("La conciliacion no tiene un sub-estatus valido. Sub-estatus: [" + conciliacion.getSubEstatus() + "]");
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_030.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_030);
		}

		LOG.debug("buildReporte({})", conciliacion.getId());
		Reporte reporte = buildReporte(conciliacion.getId(), movimientoProcesosNocturnosDTOList.getFechaDesde(),
				movimientoProcesosNocturnosDTOList.getFechaHasta(), userRequest);
		if (null == reporte)
			throw new MovimientosException(ConciliacionConstants.REPORT_GENERATION_ERROR_MESSAGE,
					CodigoError.NMP_PMIMONTE_BUSINESS_044);
		try {

			LOG.debug("saveReporte");
			reporte = reporteRepository.save(reporte);

			LOG.debug("buildMovimientoMidasList({})", reporte.getId());
			// Se persisten los movimientos midas
			List<MovimientoMidas> movimientoMidasList = MovimientosBuilder
					.buildMovimientoMidasListFromMovimientoProcesosNocturnosListResponseDTO(
							movimientoProcesosNocturnosDTOList, reporte.getId());

			LOG.debug("Se guardan los movimientos, total={}", movimientoMidasList.size());
			long ini = System.nanoTime();
			if (!CollectionUtils.isEmpty(movimientoMidasList)) {
				movimientosMidasRepository.saveAll(movimientoMidasList);
			}
			long fin = System.nanoTime();
			LOG.debug("Tiempo total: {}", (fin-ini)/1000000000);

			// Registro de actividad
			LOG.debug("registroActividad({})", conciliacion.getId());
			actividadGenericMethod.registroActividad(movimientoProcesosNocturnosDTOList.getFolio(),
					"Se registraron " + movimientoProcesosNocturnosDTOList.getMovimientos().size()
							+ " movimientos provenientes de procesos nocturnos,"
							+ " para la conciliacion con el folio: " + movimientoProcesosNocturnosDTOList.getFolio(),
					TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.MOVIMIENTOS);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MovimientosException(ConciliacionConstants.REPORT_GENERATION_ERROR_MESSAGE,
					CodigoError.NMP_PMIMONTE_BUSINESS_044);
		}

		// Notificar cambios o alta de reportes, si existen...
		LOG.debug("generarConciliacion({}, {})", folio, reporte.getId());
		this.conciliacionHelper.generarConciliacion(folio, Arrays.asList(reporte));
	}

	/**
	 * Construye un objeto de tipo reporte para ser persistido durante el registro
	 * de movimientos de proveedor transaccional
	 * 
	 * @param folio
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param userRequest
	 * @return
	 */
	public static Reporte buildReporte(final Long folio, final Date fechaDesde, final Date fechaHasta,
			final String userRequest) {
		Reporte reporte = new Reporte();
		if (null == folio || null == fechaDesde || null == fechaHasta || null == userRequest)
			return null;
		reporte.setConciliacion(new Conciliacion(folio));
		reporte.setCreatedBy(userRequest);
		reporte.setCreatedDate(new Date());
		reporte.setDisponible(true);
		reporte.setFechaDesde(fechaDesde);
		reporte.setFechaHasta(fechaHasta);
		reporte.setId(0);
		reporte.setLastModifiedBy(null);
		reporte.setLastModifiedDate(null);
		reporte.setTipo(TipoReporteEnum.MIDAS);
		return reporte;
	}

}