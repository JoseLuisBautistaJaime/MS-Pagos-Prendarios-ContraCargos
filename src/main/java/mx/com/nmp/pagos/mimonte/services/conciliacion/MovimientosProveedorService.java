/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoProveedorRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ReporteRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMovimientosProveedorRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorBatchDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.MovimientosException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

/**
 * @name MovimientosProveedorService
 * @description Clase service que realiza operaciones con movimientos
 *              relacionados con movimientos de proveedor
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:38 PM
 */
@Service("movimientosProveedorService")
public class MovimientosProveedorService {

	/**
	 * repository de movimientos proveedor
	 */
	@Autowired
	@Qualifier("movimientoProveedorRepository")
	private MovimientoProveedorRepository movimientoProveedorRepository;

	/**
	 * Repository de Reporte
	 */
	@Autowired
	@Qualifier("reporteRepository")
	private ReporteRepository reporteRepository;

	public MovimientosProveedorService() {
		super();
	}

	/**
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 */
	public List<MovimientoProveedorDTO> listByDates(Date fechaDesde, Date fechaHasta) {
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
	public List<MovimientoProveedorDTO> listByConciliacion(Long idConciliacion, Integer page, Integer pageSize) {
		return null;
	}

	/**
	 * 
	 * @param criteria
	 * @param page
	 * @param pageSize
	 */
	public List<MovimientoProveedorDTO> search(ConsultaMovimientosProveedorRequestDTO criteria, Integer page,
			Integer pageSize) {
		return null;
	}

	/**
	 * Regresa el total de registros encontrados compatibles con el id de
	 * conciliacion especificado como parametro
	 * 
	 * @param idConciliacion
	 * @return
	 */
	public Long countByConciliacionId(final Long idConciliacion) {
		return movimientoProveedorRepository.countByReporteConciliacionId(idConciliacion);
	}

	/**
	 * 
	 * @param lista
	 */
	public void saveBatch(List<MovimientoProveedorBatchDTO> lista) {

	}

	/**
	 * Guarda una lista de entidades de tipo MovimientoProveedor
	 * 
	 * @param movimientoTransaccionalListRequestDTO
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(MovimientoTransaccionalListRequestDTO movimientoTransaccionalListRequestDTO,
			final String userRequest) {
		Reporte reporte = buildReporte(movimientoTransaccionalListRequestDTO.getFolio(),
				movimientoTransaccionalListRequestDTO.getFechaDesde(),
				movimientoTransaccionalListRequestDTO.getFechaHasta(), userRequest);
		if (null == reporte)
			throw new MovimientosException(ConciliacionConstants.REPORT_GENERATION_ERROR_MESSAGE);
		try {
			reporte = reporteRepository.save(reporte);
			if (0 == reporte.getId())
				throw new MovimientosException(ConciliacionConstants.REPORT_GENERATION_ERROR_MESSAGE);
			List<MovimientoProveedor> movimientoProveedorList = MovimientosBuilder
					.buildMovimientoProveedorListFromMovimientoTransaccionalListRequestDTO(
							movimientoTransaccionalListRequestDTO, reporte.getId());
			movimientoProveedorRepository.saveAll(movimientoProveedorList);
		} catch (Exception ex) {
			throw new MovimientosException(ConciliacionConstants.REPORT_GENERATION_ERROR_MESSAGE);
		}
	}

	/**
	 * Regresa una lista de los movimientos de provedor paginados y por un folio de
	 * conciliacion especifico
	 * 
	 * @param commonConciliacionRequestDTO
	 * @return
	 */
	public List<MovimientoProveedorDTO> findByFolio(final CommonConciliacionRequestDTO commonConciliacionRequestDTO) {
		@SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest(commonConciliacionRequestDTO.getPagina(),
				commonConciliacionRequestDTO.getResultados());
		return MovimientosBuilder
				.buildMovimientoProveedorDTOListFromMovimientoProveedorList(movimientoProveedorRepository
						.findByReporteConciliacionId((long) commonConciliacionRequestDTO.getFolio(), pageable));
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
		reporte.setId(0L);
		reporte.setLastModifiedBy(null);
		reporte.setLastModifiedDate(null);
		reporte.setTipo(TipoReporteEnum.PROVEEDOR);
		return reporte;
	}

}