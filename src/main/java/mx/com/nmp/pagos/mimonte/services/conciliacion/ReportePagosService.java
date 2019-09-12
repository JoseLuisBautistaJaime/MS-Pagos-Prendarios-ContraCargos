/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosEnLineaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosEnLineaOuterDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteRequestDTO;

/**
 * @name ReportePagosService
 * @description Clase de capa de serivicio que contiene la logica de nogocios
 *              para servicios relacionados con reportes
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 17/05/2019 14:31 hrs.
 * @version 0.1
 */
@Service("reportePagosService")
public class ReportePagosService {

	/**
	 * Repository de Reporte de pagos libres (movimientos nocturnos (midas))
	 */
	@Autowired
	@Qualifier("movimientosMidasRepository")
	private MovimientosMidasRepository movimientosMidasRepository;

	/**
	 * Regresa una lista de objetos de tipo ReportePagosEnLineaDTO en base a los
	 * parametros de busqueda de tipo ReporteRequestDTO
	 * 
	 * @param reporteRequestDTO
	 * @return
	 */
	public ReportePagosEnLineaOuterDTO getReportePagosEnLinea(ReporteRequestDTO reporteRequestDTO) {
		ReportePagosEnLineaOuterDTO reportePagosEnLineaOuterDTO = null;
		List<ReportePagosEnLineaDTO> reportePagosEnLineaDTOList = null;
		BigDecimal sum = new BigDecimal("0.0");
		// Querys con sucursales
		if (null != reporteRequestDTO.getFechaDesde() && null != reporteRequestDTO.getFechaHasta()
				&& null != reporteRequestDTO.getSucursales() && !reporteRequestDTO.getSucursales().isEmpty())
			reportePagosEnLineaDTOList = movimientosMidasRepository.getReportePagosEnLineaWithFechas(
					reporteRequestDTO.getFechaDesde(), reporteRequestDTO.getFechaHasta(),
					reporteRequestDTO.getProducto(), reporteRequestDTO.getOperacion(),
					reporteRequestDTO.getSucursales(), reporteRequestDTO.getPartida());
		else if (null != reporteRequestDTO.getFechaDesde() && null == reporteRequestDTO.getFechaHasta()
				&& null != reporteRequestDTO.getSucursales() && !reporteRequestDTO.getSucursales().isEmpty())
			reportePagosEnLineaDTOList = movimientosMidasRepository.getReportePagosEnLineaWithFechaDesde(
					reporteRequestDTO.getFechaDesde(), reporteRequestDTO.getProducto(),
					reporteRequestDTO.getOperacion(), reporteRequestDTO.getSucursales(),
					reporteRequestDTO.getPartida());
		else if (null == reporteRequestDTO.getFechaDesde() && null != reporteRequestDTO.getFechaHasta()
				&& null != reporteRequestDTO.getSucursales() && !reporteRequestDTO.getSucursales().isEmpty())
			reportePagosEnLineaDTOList = movimientosMidasRepository.getReportePagosEnLineaWithFechaHasta(
					reporteRequestDTO.getFechaHasta(), reporteRequestDTO.getProducto(),
					reporteRequestDTO.getOperacion(), reporteRequestDTO.getSucursales(),
					reporteRequestDTO.getPartida());
		else if (null != reporteRequestDTO.getSucursales() && !reporteRequestDTO.getSucursales().isEmpty())
			reportePagosEnLineaDTOList = movimientosMidasRepository.getReportePagosEnLineaWithoutFechas(
					reporteRequestDTO.getProducto(), reporteRequestDTO.getOperacion(),
					reporteRequestDTO.getSucursales(), reporteRequestDTO.getPartida());
		// Querys sin sucursales
		else if (null != reporteRequestDTO.getFechaDesde() && null != reporteRequestDTO.getFechaHasta()
				&& null == reporteRequestDTO.getSucursales()
				|| (null != reporteRequestDTO.getSucursales() && reporteRequestDTO.getSucursales().isEmpty()))
			reportePagosEnLineaDTOList = movimientosMidasRepository.getReportePagosEnLineaWithFechasNS(
					reporteRequestDTO.getFechaDesde(), reporteRequestDTO.getFechaHasta(),
					reporteRequestDTO.getProducto(), reporteRequestDTO.getOperacion(), reporteRequestDTO.getPartida());
		else if (null != reporteRequestDTO.getFechaDesde() && null == reporteRequestDTO.getFechaHasta()
				&& null == reporteRequestDTO.getSucursales()
				|| (null != reporteRequestDTO.getSucursales() && reporteRequestDTO.getSucursales().isEmpty()))
			reportePagosEnLineaDTOList = movimientosMidasRepository.getReportePagosEnLineaWithFechaDesdeNS(
					reporteRequestDTO.getFechaDesde(), reporteRequestDTO.getProducto(),
					reporteRequestDTO.getOperacion(), reporteRequestDTO.getPartida());
		else if (null == reporteRequestDTO.getFechaDesde() && null != reporteRequestDTO.getFechaHasta()
				&& null == reporteRequestDTO.getSucursales()
				|| (null != reporteRequestDTO.getSucursales() && reporteRequestDTO.getSucursales().isEmpty()))
			reportePagosEnLineaDTOList = movimientosMidasRepository.getReportePagosEnLineaWithFechaHastaNS(
					reporteRequestDTO.getFechaHasta(), reporteRequestDTO.getProducto(),
					reporteRequestDTO.getOperacion(), reporteRequestDTO.getPartida());
		else
			reportePagosEnLineaDTOList = movimientosMidasRepository.getReportePagosEnLineaWithoutFechasNS(
					reporteRequestDTO.getProducto(), reporteRequestDTO.getOperacion(), reporteRequestDTO.getPartida());

		reportePagosEnLineaOuterDTO = new ReportePagosEnLineaOuterDTO();
		reportePagosEnLineaOuterDTO.setMovimientos(reportePagosEnLineaDTOList);
		reportePagosEnLineaOuterDTO.setTotalMovimientos(reportePagosEnLineaDTOList.size());
		for (ReportePagosEnLineaDTO reportePagosEnLineaDTO : reportePagosEnLineaDTOList) {
			sum = sum.add(reportePagosEnLineaDTO.getMonto());
		}
		reportePagosEnLineaOuterDTO.setMontoTotal(sum);
		return reportePagosEnLineaOuterDTO;
	}

}
