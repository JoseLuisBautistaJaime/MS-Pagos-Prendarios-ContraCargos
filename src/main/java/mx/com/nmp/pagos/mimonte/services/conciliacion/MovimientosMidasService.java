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
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMovimientosMidasRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasBatchDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasDTO;

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
	 * @return
	 */
	public Integer countByConciliacionId(final Long idConciliacion) {
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
//	public List<MovimientoMidasDTO> findByFolio(final CommonConciliacionRequestDTO commonConciliacionRequestDTO) {
//		@SuppressWarnings("deprecation")
//		Pageable pageable = new PageRequest(commonConciliacionRequestDTO.getPagina(),
//				commonConciliacionRequestDTO.getResultados());
//		return MovimientosBuilder.buildMovimientoMidasDTOListFromMovimientoMidasList(movimientosMidasRepository
//				.findByReporteConciliacionId((long) commonConciliacionRequestDTO.getFolio(), pageable));
//	}

	/**
	 * 
	 * @param lista
	 */
	public void saveBatch(List<MovimientoMidasBatchDTO> lista) {

	}

}