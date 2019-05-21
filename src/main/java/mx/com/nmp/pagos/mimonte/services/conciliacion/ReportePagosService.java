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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosLibresDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosLibresOuterDTO;
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
	 * Regresa una lista de objetos de tipo ReportePagosLibresDTO en base a los
	 * parametros de busqueda de tipo ReporteRequestDTO
	 * 
	 * @param reporteRequestDTO
	 * @return
	 */
	public ReportePagosLibresOuterDTO getReportePagosLibres(ReporteRequestDTO reporteRequestDTO) {
		ReportePagosLibresOuterDTO reportePagosLibresOuterDTO = null;
		List<ReportePagosLibresDTO> reportePagosLibresDTOList = null;
		BigDecimal sum = new BigDecimal("0.0");
		reportePagosLibresDTOList = movimientosMidasRepository.getReportePagosLibres(reporteRequestDTO.getFechaDesde(),
				reporteRequestDTO.getFechaHasta(), reporteRequestDTO.getProducto(), reporteRequestDTO.getOperacion(),
				reporteRequestDTO.getSucursales(), reporteRequestDTO.getPartida());
		reportePagosLibresOuterDTO = new ReportePagosLibresOuterDTO();
		reportePagosLibresOuterDTO.setMovimientos(reportePagosLibresDTOList);
		reportePagosLibresOuterDTO.setTotalMovimientos(reportePagosLibresDTOList.size());
		for (ReportePagosLibresDTO reportePagosLibresDTO : reportePagosLibresDTOList) {
			sum = sum.add(reportePagosLibresDTO.getMonto());
		}
		reportePagosLibresOuterDTO.setMontoTotal(sum);
		return reportePagosLibresOuterDTO;
	}

}
