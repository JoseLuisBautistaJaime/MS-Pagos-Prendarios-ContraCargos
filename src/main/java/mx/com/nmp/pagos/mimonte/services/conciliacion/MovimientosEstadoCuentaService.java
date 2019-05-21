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

import com.ibm.icu.util.Calendar;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.ReporteBuilder;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ReporteRepository;
import mx.com.nmp.pagos.mimonte.dto.EstadoCuentaWraper;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMovEstadoCuentaRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaFileLayout;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaImplementacionEnum;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaBatchDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDBDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.services.EstadoCuentaParserService;
import mx.com.nmp.pagos.mimonte.services.EstadoCuentaReaderService;

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

	/**
	 * Repository de Estado Cuenta
	 */
	@Autowired
	@Qualifier("estadoCuentaRepository")
	private EstadoCuentaRepository estadoCuentaRepository;

	/**
	 * Estado de cuenta Reader
	 */
	@Autowired
	@Qualifier("estadoCuentaReaderC43Service")
	private EstadoCuentaReaderService estadoCuentaReaderService;

	/**
	 * Estado de cuenta Parser
	 */
	@Autowired
	@Qualifier("estadoCuentaParserC43Service")
	private EstadoCuentaParserService estadoCuentaParserService;


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
		return movimientoEstadoCuentaRepository.countMovimientos(idConciliacion);
	}

	/**
	 * Encuentra la lista de conciliaciones por folio de conciliacion
	 * 
	 * @param commonConciliacionRequestDTO
	 * @return
	 */
	public List<MovimientoEstadoCuentaDTO> findByFolioAndPagination(
			final CommonConciliacionRequestDTO commonConciliacionRequestDTO) {
		List<MovimientoEstadoCuentaDBDTO> movimientoEstadoCuentaDBDTOLst = null;
		List<MovimientoEstadoCuentaDTO> movimientoEstadoCuentaDTOList = null;
		Pageable pageable = PageRequest.of(commonConciliacionRequestDTO.getPagina(),
				commonConciliacionRequestDTO.getResultados());
		movimientoEstadoCuentaDBDTOLst = movimientoEstadoCuentaRepository
				.listMovimientos((long) commonConciliacionRequestDTO.getFolio(), pageable);
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
	public void save(final SaveEstadoCuentaRequestDTO saveEstadoCuentaRequestDTO, final String userRequest) {
		Reporte reporte = reporteRepository.save(
				ReporteBuilder.buildReporteFromSaveEstadoCuentaRequestDTO(saveEstadoCuentaRequestDTO, userRequest));
		
		// Procesa la consulta del estado de cuenta, consulta los archivos y persiste los movimientos del estado de cuenta
		Long idConciliacion = saveEstadoCuentaRequestDTO.getFolio().longValue();
		procesarConsultaEstadoCuenta(reporte.getFechaDesde(), reporte.getFechaHasta(), idConciliacion, reporte.getId());
	}

	/**
	 * Consulta estado de cuenta en base a las fechas indicadas
	 * 
	 * @param saveEstadoCuentaRequestDTO
	 * @param userRequest
	 */
	public void procesarConsultaEstadoCuenta(Date fechaInicial, Date fechaFinal, Long idConciliacion, Long idReporte) throws ConciliacionException {

		// Consulta los diferentes estados de cuenta por cada fecha
		Date fechaEstadoCuenta = fechaInicial;
		while (!fechaEstadoCuenta.after(fechaFinal)) {

			// Lee el archivo usando la implementacion cuaderno 43
			EstadoCuentaFileLayout estadoCuentaFileLayout = estadoCuentaReaderService.read(fechaEstadoCuenta, idConciliacion, EstadoCuentaImplementacionEnum.CUADERNO_43);
			if (estadoCuentaFileLayout == null) {
				throw new ConciliacionException("Error al leer el archivo de estado de cuenta para la fecha " + fechaEstadoCuenta + "");
			}

			// Parsea el archivo
			EstadoCuentaWraper estadoCuentaWraper = estadoCuentaParserService.extract(estadoCuentaFileLayout);
			if (estadoCuentaWraper == null) {
				throw new ConciliacionException("Error al parsear el archivo de estado de cuenta para la fecha " + fechaEstadoCuenta + "");
			}

			// Guarda el archivo de estado de cuenta y los movimientos
			try {
				EstadoCuenta estadoCuenta = new EstadoCuenta();
				estadoCuenta.setIdReporte(idReporte);
				estadoCuenta.setFechaCarga(new Date());
				estadoCuenta.setTotalMovimientos(estadoCuentaWraper.movimientos != null ? estadoCuentaWraper.movimientos.size() : 0);
				estadoCuenta.setCabecera(estadoCuentaWraper.cabecera);
				estadoCuenta.setTotales(estadoCuentaWraper.totales);
				estadoCuenta.setTotalesAdicional(estadoCuentaWraper.totalesAdicional);
				estadoCuentaRepository.save(estadoCuenta);
	
				// Se persisten los movimientos
				if (estadoCuentaWraper.movimientos != null) {
					List<MovimientoEstadoCuenta> movimientos = estadoCuentaWraper.movimientos;
					// Se setea el id del estado de cuenta
					for (MovimientoEstadoCuenta movimiento : movimientos) {
						movimiento.setIdEstadoCuenta(estadoCuenta.getId());
					}
	
					movimientoEstadoCuentaRepository.saveAll(movimientos);
				}
			}
			catch (Exception ex) {
				throw new ConciliacionException("Error al persistir los movimientos del archivo del estado de cuenta");
			}


			// Se mueve al siguiente dia para consultar el estado de cuenta
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaEstadoCuenta);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			fechaEstadoCuenta = cal.getTime();
		}
		
	}

}