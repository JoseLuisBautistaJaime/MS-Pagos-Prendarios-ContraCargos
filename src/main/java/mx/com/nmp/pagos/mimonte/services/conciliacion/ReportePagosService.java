/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.math.BigDecimal;
import java.util.*;

import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoOperacionEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoProductoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosEnLineaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosEnLineaOuterDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteRequestDTO;
import mx.com.nmp.pagos.mimonte.util.FechasUtil;

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
		// Objetos necesarios
		ReportePagosEnLineaOuterDTO reportePagosEnLineaOuterDTO = null;
		Map<String, Date> mapD = null;
		List<ReportePagosEnLineaDTO> reportePagosEnLineaDTOList = null;
		BigDecimal sum = new BigDecimal("0.0");
		
		// Ajuste de fechas
		mapD = FechasUtil.adjustDates(reporteRequestDTO.getFechaDesde(), reporteRequestDTO.getFechaHasta());
		reporteRequestDTO.setFechaDesde(mapD.get("startDate"));
		reporteRequestDTO.setFechaHasta(mapD.get("endDate"));
		
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

		for (ReportePagosEnLineaDTO reportePagosEnLineaDTO : reportePagosEnLineaDTOList) {
			sum = sum.add(reportePagosEnLineaDTO.getMonto());
			if (reportePagosEnLineaDTO.getTipoProducto() == null || reportePagosEnLineaDTO.getTipoProducto().isEmpty()) {
				reportePagosEnLineaDTO.setTipoProducto(getTipoProductoByIdTipoProducto(reportePagosEnLineaDTO.getIdTipoContrato()));
			}
			if (reportePagosEnLineaDTO.getOperacion() == null || reportePagosEnLineaDTO.getOperacion().isEmpty()) {
				reportePagosEnLineaDTO.setOperacion(getOperacionByIdOperacion(reportePagosEnLineaDTO.getIdOperacion()));
			}
		}
		reportePagosEnLineaOuterDTO = new ReportePagosEnLineaOuterDTO();
		reportePagosEnLineaOuterDTO.setMovimientos(reportePagosEnLineaDTOList);
		reportePagosEnLineaOuterDTO.setTotalMovimientos(reportePagosEnLineaDTOList.size());

		reportePagosEnLineaOuterDTO.setMontoTotal(sum);
		return reportePagosEnLineaOuterDTO;
	}

	/**
	 * Metodo para recuperar la descripción de la Operacion apartir de un idOperacion
	 * @param idOperacion
	 * @return Operacion
	 */
	private String getOperacionByIdOperacion(Integer idOperacion) {
		String operacion = "";

		Optional<TipoOperacionEnum> tipoOperacionEnum = Arrays.stream(TipoOperacionEnum.values())
				.filter(t -> t.getIdTipoOperacion().equals(idOperacion)).findFirst();

		if (tipoOperacionEnum.isPresent())
			return tipoOperacionEnum.get().getTipoOperacion();

		return operacion;
	}

	/**
	 * Metodo para recuperar la descripción de la TipoProducto apartir de un idTipoProducto
	 * @param idTipoProducto
	 * @return TipoProducto
	 */
	private String getTipoProductoByIdTipoProducto(Integer idTipoProducto) {
		String tipoProducto = "";

		Optional<TipoProductoEnum> tipoProductoEnum = Arrays.stream(TipoProductoEnum.values())
				.filter(t -> t.getIdTipoProducto().equals(idTipoProducto)).findFirst();

		if (tipoProductoEnum.isPresent())
			return tipoProductoEnum.get().getTipoProducto();

		return tipoProducto;
	}


}
