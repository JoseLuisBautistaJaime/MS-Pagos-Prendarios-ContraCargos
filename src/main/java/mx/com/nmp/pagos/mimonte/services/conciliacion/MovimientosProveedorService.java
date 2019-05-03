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

import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoProveedorRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMovimientosProveedorRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorBatchDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;

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

}