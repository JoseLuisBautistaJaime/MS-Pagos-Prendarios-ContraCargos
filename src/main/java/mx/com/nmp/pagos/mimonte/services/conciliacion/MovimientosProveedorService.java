/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.ReporteBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoProveedorRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ReporteRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMovimientosProveedorRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorBatchDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.MovimientosException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;

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

	/**
	 * Conciliacion
	 */
	@Autowired
	private ConciliacionRepository conciliacionRepository;



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
	public Long countByConciliacionId(final Integer idConciliacion) {
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
	 * @param listRequestDTO
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(MovimientoTransaccionalListRequestDTO listRequestDTO, final String userRequest) throws ConciliacionException {

		Conciliacion conciliacion = this.conciliacionRepository.findByFolio(listRequestDTO.getFolio());
		if (conciliacion == null) {
			throw new ConciliacionException("Conciliacion con el folio " + listRequestDTO.getFolio() + " no existe", CodigoError.NMP_PMIMONTE_BUSINESS_045);
		}

		// Obtiene el reporte del proveedor transaccional
		Reporte reporte = null;
		/*try {
			reporte = reporteRepository.findLastByIdConciliacionAndTipo(listRequestDTO.getFolio(), TipoReporteEnum.PROVEEDOR);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ex.getMessage());
		}*/

		// Si no existe el reporte se crea uno nuevo
		if (reporte == null) {
			reporte = ReporteBuilder.buildReporte(conciliacion,
					listRequestDTO.getFechaDesde(),
					listRequestDTO.getFechaHasta(), userRequest);
		}
		// En caso de existir se actualiza la fecha de la ultima modificacion
		/*else {
			reporte.setLastModifiedBy(userRequest);
			reporte.setLastModifiedDate(new Date());
		}*/

		// Se guarda el reporte y los movimientos
		try {

			reporte = reporteRepository.save(reporte);
			List<MovimientoProveedor> movimientoProveedorList = MovimientosBuilder
					.buildMovimientoProveedorListFromMovimientoTransaccionalListRequestDTO(
							listRequestDTO, reporte.getId());

			// Verificar si se guarda en batch
			movimientoProveedorRepository.saveAll(movimientoProveedorList);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MovimientosException(ConciliacionConstants.REPORT_GENERATION_ERROR_MESSAGE, CodigoError.NMP_PMIMONTE_BUSINESS_044);
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
		return MovimientosBuilder
				.buildMovimientoProveedorDTOListFromMovimientoProveedorList(movimientoProveedorRepository
						.findByReporteConciliacionId(commonConciliacionRequestDTO.getFolio()));
	}

}