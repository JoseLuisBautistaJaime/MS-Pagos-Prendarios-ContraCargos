/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.CatalogoOperacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.CatalogoTipoContratoRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ReportePagosRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosLibresDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CatalogoOperacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CatalogoTipoContrato;

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
	 * Repository de catalogo de operaciones
	 */
	@Autowired
	@Qualifier("catalogoOperacionRepository")
	private CatalogoOperacionRepository catalogoOperacionRepository;

	/**
	 * Repository de catalogo de tipos de contrato
	 */
	@Autowired
	@Qualifier("catalogoTipoContratoRepository")
	private CatalogoTipoContratoRepository catalogoTipoContratoRepository;

	/**
	 * Repository de Reporte de pagos libres (movimientos nocturnos (midas))
	 */
	@Autowired
	@Qualifier("reportePagosRepository")
	private ReportePagosRepository reportePagosRepository;

	/**
	 * Regresa una lista de objetos de tipo ReportePagosLibresDTO en base a los
	 * parametros de busqueda de tipo ReporteRequestDTO
	 * 
	 * @param reporteRequestDTO
	 * @return
	 */
	public List<ReportePagosLibresDTO> getReportePagosLibres(ReporteRequestDTO reporteRequestDTO) {
		List<ReportePagosLibresDTO> reportePagosLibresDTOList = null;
		CatalogoOperacion catalogoOperacion = null;
		CatalogoTipoContrato catalogoTipoContrato = null;
		String operacionAbr = null;
		String tipoContratoAbr = null;
		catalogoOperacion = catalogoOperacionRepository.findById(reporteRequestDTO.getOperacion()).isPresent()
				? catalogoOperacionRepository.findById(reporteRequestDTO.getOperacion()).get()
				: null;
		catalogoTipoContrato = catalogoTipoContratoRepository.findById(reporteRequestDTO.getProducto()).isPresent()
				? catalogoTipoContratoRepository.findById(reporteRequestDTO.getProducto()).get()
				: null;
		if (null != catalogoOperacion && null != catalogoOperacion.getAbreviatura() && null != catalogoTipoContrato
				&& null != catalogoTipoContrato.getAbreviatura()) {
			operacionAbr = catalogoOperacion.getAbreviatura();
			tipoContratoAbr = catalogoTipoContrato.getAbreviatura();
		} else
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_CATALOG_INFORMATION);
		reportePagosLibresDTOList = reportePagosRepository.getReportePagosLibres(reporteRequestDTO.getFechaDesde(),
				reporteRequestDTO.getFechaHasta(), tipoContratoAbr, operacionAbr, reporteRequestDTO.getSucursales(),
				reporteRequestDTO.getPartida());
		return reportePagosLibresDTOList;
	}

}
