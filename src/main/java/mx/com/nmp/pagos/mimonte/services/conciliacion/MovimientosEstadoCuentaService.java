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
import mx.com.nmp.pagos.mimonte.builder.conciliacion.ReporteBuilder;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ReporteRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMovEstadoCuentaRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaBatchDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDBDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestDTO;

/**
 * @name MovimientosEstadoCuentaService
 * 
 * @description Realiza operaciones de logica de negocio sobre objetos
 *              relacionados con movimientos de estado de cuenta
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:40 PM
 */
@Service("movimientosEstadoCuentaService")
public class MovimientosEstadoCuentaService {

	/**
	 * Repository de MovimientoEstadoCuentaRepository
	 */
	@Autowired
	@Qualifier("movimientoEstadoCuentaRepository")
	private MovimientoEstadoCuentaRepository movimientoEstadoCuentaRepository;

	/**
	 * Repository de Reporte
	 */
	@Autowired
	@Qualifier("reporteRepository")
	private ReporteRepository reporteRepository;

	public MovimientosEstadoCuentaService() {
		super();
	}

	/**
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 */
	public List<MovimientoEstadoCuentaDTO> listByDates(Date fechaDesde, Date fechaHasta) {
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
	public List<MovimientoEstadoCuentaDTO> listByConciliacion(Long idConciliacion, Integer page, Integer pageSize) {
		return null;
	}

	/**
	 * 
	 * @param criteria
	 * @param page
	 * @param pageSize
	 */
	public List<MovimientoEstadoCuentaDTO> search(ConsultaMovEstadoCuentaRequestDTO criteria, Integer page,
			Integer pageSize) {
		return null;
	}

	/**
	 * 
	 * @param criterios
	 */
	public Integer count(ConsultaMovEstadoCuentaRequestDTO criterios) {
		return 0;
	}

	/**
	 * 
	 * @param movimientos
	 */
	public void saveBatch(List<MovimientoEstadoCuentaBatchDTO> movimientos) {

	}

	/**
	 * Regresa el total de registros de movimientos estado de cuenta relacionados
	 * con el id de conciliacion especificado como parametro
	 * 
	 * @param idConciliacion
	 * @return
	 */
	public Long countByConciliacionId(final Long idConciliacion) {
		return movimientoEstadoCuentaRepository.jpqlCBIC(idConciliacion);
	}

	/**
	 * Encuentra la lista de conciliaciones por folio de conciliacion
	 * 
	 * @param commonConciliacionRequestDTO
	 * @return
	 */
	public List<MovimientoEstadoCuentaDTO> findByFolio(
			final CommonConciliacionRequestDTO commonConciliacionRequestDTO) {
		List<MovimientoEstadoCuentaDBDTO> movimientoEstadoCuentaDBDTOLst = null;
		List<MovimientoEstadoCuentaDTO> movimientoEstadoCuentaDTOList = null;
		@SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest(commonConciliacionRequestDTO.getPagina(),
				commonConciliacionRequestDTO.getResultados());
		movimientoEstadoCuentaDBDTOLst = movimientoEstadoCuentaRepository
				.jpqlFBIC((long) commonConciliacionRequestDTO.getFolio(), pageable);
		movimientoEstadoCuentaDTOList = MovimientosBuilder
				.buildMovimientoEstadoCuentaDTOListFromMovimientoEstadoCuentaDBDTOList(movimientoEstadoCuentaDBDTOLst);
		return movimientoEstadoCuentaDTOList;
	}

	/**
	 * Guarda un reporte relacionado con un movimiento de estado de cuenta
	 * 
	 * @param saveEstadoCuentaRequestDTO
	 * @param userRequest
	 */
	public void saveByFolio(final SaveEstadoCuentaRequestDTO saveEstadoCuentaRequestDTO, final String userRequest) {
		reporteRepository.save(
				ReporteBuilder.buildReporteFromSaveEstadoCuentaRequestDTO(saveEstadoCuentaRequestDTO, userRequest));
	}

}