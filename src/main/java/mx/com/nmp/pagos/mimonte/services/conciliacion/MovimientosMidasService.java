/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ReporteRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMovimientosMidasRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasBatchDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.MovimientosException;
import mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

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



	public MovimientosMidasService() {
		super();
	}

	/**
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 */
	public List<MovimientoMidasDTO> listByDates(Date fechaDesde, Date fechaHasta) {
		return null;
	}

	/**
	 * Regresa el total de registros de movimientos midas por id de conciliacion
	 * 
	 * @param idConciliacion
	 * @param estatus
	 * @return
	 */
	public Long countByConciliacionId(final Integer idConciliacion, final Boolean estatus) {
		if (null != estatus)
			return movimientosMidasRepository.countByReporteConciliacionIdAndEstatus(idConciliacion, estatus);
		else
			return movimientosMidasRepository.countByReporteConciliacionId(idConciliacion);
	}

	/**
	 * 
	 * @param idConciliacion
	 * @param page
	 * @param pageSize
	 */
	public List<MovimientoMidasDTO> listByConciliacion(Long idConciliacion, Integer page, Integer pageSize) {
		return null;
	}

	/**
	 * 
	 * @param criteria
	 * @param page
	 * @param pageSize
	 */
	public List<MovimientoMidasDTO> search(ConsultaMovimientosMidasRequestDTO criteria, Integer page,
			Integer pageSize) {
		return null;
	}

	/**
	 * 
	 * @param criterios
	 */
	public Integer count(ConsultaMovimientosMidasRequestDTO criterios) {
		return 0;
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
	 * 
	 * @param lista
	 */
	public void saveBatch(List<MovimientoMidasBatchDTO> lista) {

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
		Integer folio = movimientoProcesosNocturnosDTOList.getFolio();
		Conciliacion conciliacion = this.conciliacionHelper.getConciliacionByFolio(folio, ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);

		Reporte reporte = buildReporte(conciliacion.getId(),
				movimientoProcesosNocturnosDTOList.getFechaDesde(), movimientoProcesosNocturnosDTOList.getFechaHasta(),
				userRequest);
		if (null == reporte)
			throw new MovimientosException(ConciliacionConstants.REPORT_GENERATION_ERROR_MESSAGE,
					CodigoError.NMP_PMIMONTE_BUSINESS_044);
		try {
			reporte = reporteRepository.save(reporte);
			if (0 == reporte.getId())
				throw new MovimientosException(ConciliacionConstants.REPORT_GENERATION_ERROR_MESSAGE,
						CodigoError.NMP_PMIMONTE_BUSINESS_044);
			List<MovimientoMidas> movimientoMidasList = MovimientosBuilder
					.buildMovimientoMidasListFromMovimientoProcesosNocturnosListResponseDTO(
							movimientoProcesosNocturnosDTOList, reporte.getId());
			movimientosMidasRepository.saveAll(movimientoMidasList);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MovimientosException(ConciliacionConstants.REPORT_GENERATION_ERROR_MESSAGE,
					CodigoError.NMP_PMIMONTE_BUSINESS_044);
		}

		// Notificar cambios o alta de reportes, si existen...
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
	public static Reporte buildReporte(final Integer folio, final Date fechaDesde, final Date fechaHasta,
			final String userRequest) {
		Reporte reporte = new Reporte();
		if (null == folio || null == fechaDesde || null == fechaHasta || null == userRequest)
			return null;
		reporte.setConciliacion(new Conciliacion((long) folio));
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